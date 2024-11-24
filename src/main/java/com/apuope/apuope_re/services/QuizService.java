package com.apuope.apuope_re.services;

import com.apuope.apuope_re.dto.ChatRequestData;
import com.apuope.apuope_re.dto.ConversationData;
import com.apuope.apuope_re.dto.MessageData;
import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.jooq.tables.records.ConversationRecord;
import com.apuope.apuope_re.jooq.tables.records.MessageRecord;
import com.apuope.apuope_re.jooq.tables.records.UsersRecord;
import com.apuope.apuope_re.repositories.ConversationRepository;
import com.apuope.apuope_re.repositories.UserRepository;
import com.apuope.apuope_re.utils.Constants;
import com.apuope.apuope_re.utils.Constants.MessageSource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.jooq.DSLContext;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.List;

import java.util.Collections;
import java.util.Optional;

@Service
public class QuizService {
    private final DSLContext dslContext;
    private final JWTService jwtService;
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final EmbeddingService embeddingService;
    private final RetrievalService retrievalService;

    @Value("${llm.api.url}")
    private String apiUrl;

    @Value("${llm.api.key}")
    private String apiKey;

    public QuizService(DSLContext dslContext, JWTService jwtService, ConversationRepository conversationRepository,
            UserRepository userRepository, EmbeddingService embeddingService, RetrievalService retrievalService) {
        this.dslContext = dslContext;
        this.jwtService = jwtService;
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
        this.embeddingService = embeddingService;
        this.retrievalService = retrievalService;
    }
    public void requestQuiz() throws SQLException, JsonProcessingException {
        var instructions = "\"create quiz based on chapter 1." +
                " Quiz " +
                "should have three " +
                "questions and each" +
                " should have three" +
                " choices. Answer should contain only json, json should include all questions as a list and one question object is like this:" +
                "{\"id\": 1, \"question\": \"question here\", \"choice_a\": \"choice a value here\", \"choice_b\": \"choice b value here\", " +
                "\"choice_c\": \"choice " +
                "c value" +
                " " +
                "here\", \"correct_answer\": \"id of the correct answer like choice_a\", \"points\": \"number, how many points this gives\"}";


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
        System.out.println(root);
        System.out.println(response.getBody());
        String content = root.path("choices").get(0).path("message").path("content").asText();
    }
}
