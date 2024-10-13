package com.apuope.apuope_re.controllers;

import com.apuope.apuope_re.dto.RegistrationData;
import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.services.EmailService;
import com.apuope.apuope_re.services.RegistrationService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ResetPasswordController {
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private EmailService emailService;

    @PostMapping(value = "/register")
    public ResponseEntity<ResponseData<String>> validateUser(@RequestBody RegistrationData input) throws MessagingException {
        ResponseData<String> response = registrationService.registerUser(input);
        if (response.getSuccess()) {
            emailService.sendVerification(input.getEmail());
        }
        return ResponseEntity.ok(response);
    }
}
