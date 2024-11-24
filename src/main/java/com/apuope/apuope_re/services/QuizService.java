package com.apuope.apuope_re.services;

import com.apuope.apuope_re.dto.*;
import com.apuope.apuope_re.jooq.tables.records.UsersRecord;
import com.apuope.apuope_re.repositories.QuizRepository;
import com.apuope.apuope_re.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jooq.DSLContext;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.List;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class QuizService {
    private static final Integer QUESTION_AMOUNT = 10;
    private final DSLContext dslContext;
    private final JWTService jwtService;
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RetrievalService retrievalService;

    @Value("${llm.api.url}")
    private String apiUrl;

    @Value("${llm.api.key}")
    private String apiKey;

    public QuizService(DSLContext dslContext, JWTService jwtService, QuizRepository quizRepository,
            UserRepository userRepository, RetrievalService retrievalService) {
        this.dslContext = dslContext;
        this.jwtService = jwtService;
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
        this.retrievalService = retrievalService;
    }

    private List<QuestionData> mapCorrectOptionsFormat(List<QuestionData> questionData){
        questionData.forEach(q -> {
            q.setCorrectOption(switch (q.getCorrectOption().toLowerCase()) {
                case "a", "optiona", "option a" -> "option_a";
                case "b", "optionb", "option b" -> "option_b";
                case "c", "optionc", "option c" -> "option_c";
                default -> q.getCorrectOption();
            });
        });
        return questionData;
    }

    public ResponseData<Object> generateQuiz(String token, Integer lectureId) throws SQLException, JsonProcessingException {
        try {
            String userEmail = jwtService.extractEmail(token);
            Optional<UsersRecord> userOpt = userRepository.findVerifiedUserByEmail(userEmail, dslContext);
            if (userOpt.isPresent()) {
                Integer accountId = userOpt.get().getId();

                var instructions = "Create a quiz based on lecture " + lectureId + ". " + "Quiz should have " + QUESTION_AMOUNT + " questions. " +
                        "Each question has three options: option_a, option_b and option_c. " + "The correct_option must strictly point to the " +
                        "correct value, e.g. option_a. " + "The value of question_id is always the position of the question in the test. " + "Each question and option must be maximum 500 " + "characters. " + "Each correct answer " +
                        "is " + "equal to one point. " + "Generated quiz should be in JSON format. " + "Here is an example, how the quiz in JSON " +
                        "format should be. " + "This example quiz has two questions (example quiz don't need to be provided back): " + "[{\"id\": " +
                        "1, \"question_id\": 1,\"question\": \"question here\",\"option_a\": \"answer option a\",\"option_b\": \"answer option b\"," +
                        "\"option_c\": \"answer option c\",\"correct_option\": \"choice_a\",\"points\": 1},{\"id\": 2, \"question_id\": 2, " +
                        "\"question\": \"question here\",\"option_a\": \"answer option a\",\"option_b\": \"answer option b\",\"option_c\": \"answer option c\",\"correct_option\": \"choice_a\",\"points\": 1}]";

                List<String> promptContext = retrievalService.getQuizContext(1);
                String context = String.join(" ", promptContext);

                HttpHeaders headers = new HttpHeaders();
                headers.set("Authorization", "Bearer " + apiKey);
                headers.set("Content-Type", "application/json");

                ObjectNode requestBody = objectMapper.createObjectNode();
                requestBody.put("model", "llama2-13b");
                ArrayNode messages = requestBody.putArray("messages");
                ObjectNode systemMessage = messages.addObject();
                systemMessage.put("role", "system");
                systemMessage.put("content", "Context from chapters of the textbook related to selected lecture: " + context);

                ObjectNode userMessage = messages.addObject();
                userMessage.put("role", "user");
                userMessage.put("content", instructions);

                HttpEntity<String> requestEntity = new HttpEntity<>(objectMapper.writeValueAsString(requestBody), headers);

                ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);

                JsonNode root = objectMapper.readTree(response.getBody());
                String content = root.path("choices").get(0).path("message").path("content").asText();
                content = content.trim();
                content = content.replaceAll("\\n", "");  // Example: remove any extra escaping
                System.out.println("Modified content: " + content);
                Pattern pattern = Pattern.compile("\\[.*?\\]");
                Matcher matcher = pattern.matcher(content);

                if (matcher.find()) {
                    content = matcher.group();
                    System.out.println(content);
                    ObjectMapper objectMapper = new ObjectMapper();
                    List<QuestionData> questionData = objectMapper.readValue(content, new TypeReference<List<QuestionData>>() {
                    });

                    questionData = mapCorrectOptionsFormat(questionData);
                    return new ResponseData<>(true, quizRepository.saveQuiz(questionData, accountId, lectureId, dslContext));
                }
                throw new Exception("Couldn't extract questions generated.");
            }
            throw new Exception("No user found.");
        } catch (Exception e) {
            return new ResponseData<>(false, "Quiz generation failed. Please try again.");
        }
    }

    public ResponseData<Object> validateQuizAnswers(String token, QuizSubmitData quizSubmitData){
        try {
            String userEmail = jwtService.extractEmail(token);
            Optional<UsersRecord> userOpt = userRepository.findVerifiedUserByEmail(userEmail, dslContext);
            if (userOpt.isPresent()) {
                Integer accountId = userOpt.get().getId();

                QuizData quizData = quizRepository.fetchQuizByQuizId(quizSubmitData.getQuizId(), dslContext);
                var quizResult = quizRepository.saveQuizResult(
                        accountId, quizSubmitData.getQuizId(),quizData.getMaxPoints(), dslContext);
                Integer points = 0;

                for (QuizSubmitAnswerData answer : quizSubmitData.getQuestionAnswerDataList()) {
                    QuestionData questionData = quizRepository.fetchQuestionByQuestionId(
                            quizSubmitData.getQuizId(),answer.getQuestionNumber(), dslContext);

                    boolean correctAnswer = questionData.getCorrectOption().equals(answer.getAnswer());

                    quizRepository.saveQuizAnswer(quizResult.getId(), questionData.getId(), answer.getQuestionNumber(), answer.getAnswer(),
                            correctAnswer, correctAnswer
                                    ? 1 : 0,
                            dslContext);
                    points += correctAnswer ? 1 : 0;
                }
                quizResult.setQuizAnswerDataList(quizRepository.fetchQuizAnswersByQuizResultId(quizResult.getId(), dslContext));
                quizResult.setScore(points);
                System.out.println(quizResult.getDateTime());

                return new ResponseData<>(true, quizResult);
            }
            throw new Exception("No user found.");
        } catch (Exception e){
            System.out.println(e.toString());
            return new ResponseData<>(false, "Validating and saving quiz answers failed.");
        }
    }
}
