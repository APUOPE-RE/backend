package com.apuope.apuope_re.controllers;

import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.services.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class VerificationController {
    @Autowired
    private VerificationService verificationService;

    @GetMapping(value = "/verify/{uuid}")
    public ResponseEntity<ResponseData<String>> verifyAccount(@PathVariable("uuid") UUID uuid) {
        ResponseData<String> s = verificationService.verifyUser(uuid);
        return ResponseEntity.ok(verificationService.verifyUser(uuid));
    }
}