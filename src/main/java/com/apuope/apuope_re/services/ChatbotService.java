package com.apuope.apuope_re.services;

import com.apuope.apuope_re.dto.ChatRequestData;
import com.apuope.apuope_re.dto.MessageData;
import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.jooq.tables.Conversation;
import com.apuope.apuope_re.jooq.tables.records.ConversationRecord;
import com.apuope.apuope_re.jooq.tables.records.MessageRecord;
import com.apuope.apuope_re.repositories.ConversationRepository;
import com.apuope.apuope_re.repositories.UserRepository;
import com.apuope.apuope_re.utils.Constants.MessageSource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ChatbotService {
    private final DSLContext dslContext;
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${llm.api.url}")
    private String apiUrl;

    @Value("${llm.api.key}")
    private String apiKey;

    public ChatbotService(DSLContext dslContext, ConversationRepository conversationRepository, UserRepository userRepository) {
        this.dslContext = dslContext;
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
    }

    public List<ConversationRecord> fetchAllConversations(HttpServletRequest request) {
        //String userEmail = jwtService.extractEmail(token);
        var userOpt = userRepository.findVerifiedUserByEmail("roosa.vihlman@tuni.fi", dslContext);
        if (userOpt.isPresent()) {
            return conversationRepository.fetchConversationByAccountId(userOpt.get().getId(), dslContext);
        }
    }

    public List<MessageData> fetchConversation(Integer conversationId) {
        return conversationRepository.fetchConversationById(conversationId, dslContext);
    }

    public ConversationRecord startConversation(ChatRequestData request) {
        return conversationRepository.createConversation(request.getUserId(), request.getChapterId(), "", dslContext);
    }

    public ResponseData<MessageData> sendRequest(ChatRequestData request) throws JsonProcessingException {
        if (request.getConversationId() == 0 || request.getConversationId() == null) {
            ConversationRecord conversation = startConversation(request);
            conversationRepository.createMessage(conversation.getId(), request.getData(), MessageSource.USER.getValue(),
                    dslContext);
        } else {
            conversationRepository.createMessage(request.getConversationId(), request.getData(), MessageSource.USER.getValue(),
                    dslContext);
        }

        /*HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        String requestBody = "{"
                + "\"model\": \"llama3.1-8b\","
                + "\"messages\": [{\"role\": \"user\", \"content\": \"" + request.getData() + "\"}]"
                + "}";

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);

        JsonNode root = objectMapper.readTree(response.getBody());
        String content = root.path("choices").get(0).path("message").path("content").asText();*/
        String content = "This is an LLM answer!";

        MessageRecord llmMessage = conversationRepository.createMessage(request.getConversationId(), request.getData(), MessageSource.LLM.getValue(),
                dslContext);

        MessageData response = new MessageData(llmMessage.getConversationId(), llmMessage.getId(),
                llmMessage.getContent(), llmMessage.getSource(), llmMessage.getDatetime());

        return new ResponseData<>(true, response);
    }
}
