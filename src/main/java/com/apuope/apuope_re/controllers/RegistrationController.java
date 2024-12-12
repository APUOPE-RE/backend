package com.apuope.apuope_re.controllers;

import com.apuope.apuope_re.dto.RegistrationData;
import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.services.EmailService;
import com.apuope.apuope_re.services.RegistrationService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private EmailService emailService;

    @PostMapping(value = "/register")
    public ResponseEntity<ResponseData<Object>> validateUser(@RequestBody RegistrationData input) throws MessagingException {
        ResponseData<Object> response = registrationService.registerUser(input);
        if (response.getSuccess()) {
            var emailServiceResponse = emailService.sendVerification(input.getEmail());
            return emailServiceResponse.getSuccess() ? ResponseEntity.ok(emailServiceResponse) : ResponseEntity.internalServerError().body(emailServiceResponse);
        }
        return ResponseEntity.internalServerError().body(response);
    }

    @GetMapping(value = "/verify/{uuid}")
    public ResponseEntity<ResponseData<Object>> verifyAccount(@PathVariable("uuid") UUID uuid) {
        ResponseData<Object> response = registrationService.verifyUser(uuid);
        return response.getSuccess() ? ResponseEntity.ok(response) : ResponseEntity.internalServerError().body(response);
    }
}
