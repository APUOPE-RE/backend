package com.apuope.apuope_re.controllers;

import com.apuope.apuope_re.dto.RegistrationData;
import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @PostMapping(value = "/Register")
    public ResponseEntity<ResponseData<String>> validateUser(@RequestBody RegistrationData input) {
        return ResponseEntity.ok(registrationService.registerUser(input));
    }
}
