package com.apuope.apuope_re.services;

import com.apuope.apuope_re.dto.*;
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
public class ChatbotService {
    private final DSLContext dslContext;
    private final JWTService jwtService;
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final EmbeddingService embeddingService;
    private final RetrievalService retrievalService;
    private final UserCredentialsService userCredentialsService;

    @Value("${llm.api.url}")
    private String apiUrl;

    @Value("${llm.api.key}")
    private String apiKey;

    public ChatbotService(DSLContext dslContext, JWTService jwtService, ConversationRepository conversationRepository,
            UserRepository userRepository, EmbeddingService embeddingService, RetrievalService retrievalService, UserCredentialsService userCredentialsService) {
        this.dslContext = dslContext;
        this.jwtService = jwtService;
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
        this.embeddingService = embeddingService;
        this.retrievalService = retrievalService;
        this.userCredentialsService = userCredentialsService;
    }

    public List<ConversationData> fetchAllConversations(String token) {
        String userEmail = jwtService.extractEmail(token);
        var response = userCredentialsService.checkAccountExists(userEmail);
        if (response.getSuccess()) {
            UsersRecord user = (UsersRecord) response.getData();
            return conversationRepository.fetchConversationByAccountId(user.getId(), dslContext);
        }
        return Collections.emptyList();
    }

    public List<MessageData> fetchConversation(Integer conversationId) {
        return conversationRepository.fetchConversationById(conversationId, dslContext);
    }

    public ResponseData<String> updateConversationTitle(Integer conversationId, String newTitle) {
        boolean titleUpdated = conversationRepository.updateConversationTitleById(conversationId, dslContext, newTitle);

        if (titleUpdated) {
            return new ResponseData<>(true, "Conversation title updated.");
        }
        return new ResponseData<>(false, "Conversation title update failed.");
    }

    public ResponseData<String> deleteConversation(Integer conversationId) {
        boolean deleted = conversationRepository.deleteConversationById(conversationId, dslContext);

        if (deleted) {
            return new ResponseData<>(true, "Conversation deleted.");
        }
        return new ResponseData<>(false, "Conversation deletion failed.");
    }

    public ConversationRecord startConversation(Integer userId, ChatRequestData request) {
        return conversationRepository.createConversation(userId, request.getLectureId(), "", dslContext);
    }

    public ResponseData<Object> sendRequest(String token, ChatRequestData chatRequest) throws JsonProcessingException, JSONException, SQLException {
        try {
            Integer conversationId;

            String userEmail = jwtService.extractEmail(token);
            var userExists = userCredentialsService.checkAccountExists(userEmail);

            if (userExists.getSuccess()) {
                if ((chatRequest.getConversationId() == 0 || chatRequest.getConversationId() == null)) {
                    UsersRecord user = (UsersRecord) userExists.getData();
                    ConversationRecord conversation = startConversation(user.getId(), chatRequest);
                    conversationId = conversation.getId();

                    conversationRepository.createMessage(conversationId, chatRequest.getData(), MessageSource.USER.getValue(), dslContext);
                } else {
                    conversationId = chatRequest.getConversationId();
                    conversationRepository.createMessage(chatRequest.getConversationId(), chatRequest.getData(), MessageSource.USER.getValue(), dslContext);
                }
            } else {
                return userExists;
            }
            double[] embedding = embeddingService.getEmbedding(chatRequest.getData());

            List<Integer> chapterIds = Constants.LectureChapters.getChaptersByLectureId(chatRequest.getLectureId());

            List<String> promptContext = retrievalService.findRelevantChunks(embedding, chapterIds);
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
            userMessage.put("content", chatRequest.getData());

            HttpEntity<String> requestEntity = new HttpEntity<>(objectMapper.writeValueAsString(requestBody), headers);

            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);

            JsonNode root = objectMapper.readTree(response.getBody());
            String content = root.path("choices").get(0).path("message").path("content").asText();

            MessageRecord llmMessage = conversationRepository.createMessage(conversationId, content, MessageSource.LLM.getValue(), dslContext);

            MessageData messageData = new MessageData(llmMessage.getConversationId(), llmMessage.getId(), llmMessage.getContent(), llmMessage.getSource(), llmMessage.getDatetime());

            return new ResponseData<>(true, messageData);
        } catch (Exception e) {
            return new ResponseData<>(false, "Error occurred when trying to contact the LLM. Please, try again.");
        }
    }
}
