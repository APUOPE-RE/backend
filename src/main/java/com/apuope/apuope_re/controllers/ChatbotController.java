package com.apuope.apuope_re.controllers;

import com.apuope.apuope_re.dto.ChatRequestData;
import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.services.ChatbotService;
import com.apuope.apuope_re.services.EmbeddingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ChatbotController {
    private final ChatbotService chatbotService;

    private EmbeddingService embeddingService;

    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping(value = "/chatBot")
    public ResponseEntity<ResponseData<String>> validateUser(@RequestBody ChatRequestData input) throws JsonProcessingException{


        return ResponseEntity.ok(this.chatbotService.sendRequest(input));
    }
}