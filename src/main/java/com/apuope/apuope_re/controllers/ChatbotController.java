package com.apuope.apuope_re.controllers;

import com.apuope.apuope_re.dto.ChatRequestData;
import com.apuope.apuope_re.dto.ConversationData;
import com.apuope.apuope_re.dto.MessageData;
import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.services.ChatbotService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ChatbotController {
    private final ChatbotService chatbotService;

    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @GetMapping(value = "/conversations")
    public ResponseEntity<List<ConversationData>> fetchAllConversations(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer ", "");
        return ResponseEntity.ok(chatbotService.fetchAllConversations(token, request));
    }

    @GetMapping(value = "/conversation/{id}")
    public ResponseEntity<List<MessageData>> fetchConversation(@PathVariable("id") Integer conversationId) {
        return ResponseEntity.ok(chatbotService.fetchConversation(conversationId));
    }

    @PostMapping(value = "/chatBot")
    public ResponseEntity<ResponseData<MessageData>> sendRequest(@RequestBody ChatRequestData input) throws JsonProcessingException {
        return ResponseEntity.ok(chatbotService.sendRequest(input));
    }
}
