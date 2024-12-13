package com.apuope.apuope_re.controllers;

import com.apuope.apuope_re.dto.ResetPasswordData;
import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.jooq.tables.records.TokenRecord;
import com.apuope.apuope_re.services.EmailService;
import com.apuope.apuope_re.services.ResetPasswordService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ResetPasswordController {
    @Autowired
    private ResetPasswordService resetPasswordService;
    @Autowired
    private EmailService emailService;

    @PostMapping(value = "/sendResetPasswordLink")
    public ResponseEntity<ResponseData<Object>> sendResetPasswordLink(@RequestBody String email) throws MessagingException {
        ResponseData<Object> response = resetPasswordService.generateEmailToken(email);
        if (response.getSuccess()) {
            TokenRecord token = (TokenRecord) response.getData();
            var responseLink = emailService.sendResetPasswordLink(token, email);
            return responseLink.getSuccess() ? ResponseEntity.ok(responseLink) : ResponseEntity.internalServerError().body(responseLink);
        }
        return ResponseEntity.internalServerError().body(response);
    }

    @PostMapping(value = "/resetPassword")
    public ResponseEntity<ResponseData<Object>> resetPassword(@RequestBody ResetPasswordData input) {
        ResponseData<Object> response = resetPasswordService.resetPassword(input);
        return response.getSuccess() ? ResponseEntity.ok(response) : ResponseEntity.internalServerError().body(response);
    }
}
