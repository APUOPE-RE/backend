package com.apuope.apuope_re.controllers;

import com.apuope.apuope_re.dto.RegistrationData;
import com.apuope.apuope_re.dto.ResetPasswordData;
import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.dto.TokenData;
import com.apuope.apuope_re.jooq.tables.records.TokenRecord;
import com.apuope.apuope_re.services.EmailService;
import com.apuope.apuope_re.services.RegistrationService;
import com.apuope.apuope_re.services.ResetPasswordService;
import jakarta.mail.MessagingException;
import org.jooq.Result;
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
    public ResponseEntity<ResponseData<String>> sendResetPasswordLink(@RequestBody String email) throws MessagingException {
        TokenRecord response = resetPasswordService.generateEmailToken(email);
        if (response != null) {
            return ResponseEntity.ok(emailService.sendResetPasswordLink(response, email));
        }
        return ResponseEntity.ok(new ResponseData<>(false, "Error when creating email token."));
    }

    @PostMapping(value = "/resetPassword")
    public ResponseEntity<ResponseData<String>> resetPassword(@RequestBody ResetPasswordData input) throws MessagingException {
        ResponseData<String> response = resetPasswordService.resetPassword(input);
        return ResponseEntity.ok(new ResponseData<>(false, "Error when creating email token."));
    }
}
