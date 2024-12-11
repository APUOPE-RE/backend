package com.apuope.apuope_re.controllers;

import com.apuope.apuope_re.dto.*;
import com.apuope.apuope_re.services.ChatbotService;
import com.apuope.apuope_re.services.EmbeddingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ChatbotController {
    private final ChatbotService chatbotService;

    private EmbeddingService embeddingService;

    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @GetMapping(value = "/conversations")
    public ResponseEntity<List<ConversationData>> fetchAllConversations(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer ", "");
        return ResponseEntity.ok(chatbotService.fetchAllConversations(token));
    }

    @GetMapping(value = "/conversation/{id}")
    public ResponseEntity<List<MessageData>> fetchConversation(@PathVariable("id") Integer conversationId) {
        return ResponseEntity.ok(chatbotService.fetchConversation(conversationId));
    }

    @PostMapping(value = "/chatBot")
    public ResponseEntity<ResponseData<Object>> sendRequest(HttpServletRequest request, @RequestBody ChatRequestData input)
            throws JsonProcessingException, SQLException, JSONException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer ", "");
        var response = chatbotService.sendRequest(token, input);
        return response.getSuccess() ? ResponseEntity.ok(response) : ResponseEntity.internalServerError().body(response);
    }

    @PutMapping(value = "/updateConversationTitle/{id}")
    public ResponseEntity<ResponseData<String>> updateConversationTitle(@PathVariable("id") Integer conversationId, @RequestBody String newTitle) {
        ResponseData<String> response = chatbotService.updateConversationTitle(conversationId, newTitle);
        return response.getSuccess() ? ResponseEntity.ok(response) : ResponseEntity.internalServerError().body(response);
    }

    @GetMapping(value = "/deleteConversation/{id}")
    public ResponseEntity<ResponseData<String>> deleteConversation(@PathVariable("id") Integer conversationId) {
        ResponseData<String> response = chatbotService.deleteConversation(conversationId);
        return response.getSuccess() ? ResponseEntity.ok(response) : ResponseEntity.internalServerError().body(response);
    }
}
