package com.apuope.apuope_re.controllers;

import com.apuope.apuope_re.dto.ChatRequestData;
import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.services.ChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ChatbotController {
    @Autowired
    private ChatbotService chatbotService;

    @PostMapping(value = "/chatBot")
    public ResponseEntity<ResponseData<String>> validateUser(@RequestBody ChatRequestData input) {
        return ResponseEntity.ok(chatbotService.sendRequest(input));
    }
}
