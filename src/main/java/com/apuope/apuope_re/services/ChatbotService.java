package com.apuope.apuope_re.services;

import com.apuope.apuope_re.dto.ChatRequestData;
import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.jooq.tables.Conversation;
import com.apuope.apuope_re.jooq.tables.records.ConversationRecord;
import com.apuope.apuope_re.repositories.ConversationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class ChatbotService {
    private final DSLContext dslContext;
    private final ConversationRepository conversationRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${llm.api.url}")
    private String apiUrl;

    @Value("${llm.api.key}")
    private String apiKey;

    public ChatbotService(DSLContext dslContext, ConversationRepository conversationRepository) {
        this.dslContext = dslContext;
        this.conversationRepository = conversationRepository;
    }

    public ConversationRecord fetchConversation(ChatRequestData request) {
        ConversationRecord conversation = conversationRepository.findByConversationId(request.getConversationId(), dslContext);

        if (conversation == null){
            return conversationRepository.createConversation(request.getUserId(), request.getChapterId(), "", dslContext);
        }
        return conversation;
    }

   /* public ResponseData<String> saveMessage(Integer conversationId, String message) {
        return conversationRepository.createMessage(conversationId, message, dslContext);
    }*/


    public ResponseData<String> sendRequest(ChatRequestData request) throws JsonProcessingException {
        ConversationRecord conversation = fetchConversation(request);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        String requestBody = "{"
                + "\"model\": \"llama3.1-8b\","
                + "\"messages\": [{\"role\": \"user\", \"content\": \"" + request.getData() + "\"}]"
                + "}";

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);

        JsonNode root = objectMapper.readTree(response.getBody());
        String content = root.path("choices").get(0).path("message").path("content").asText();

        return new ResponseData<>(true, content);
    }
}
