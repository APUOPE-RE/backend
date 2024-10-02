package com.apuope.apuope_re.controllers;

import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.services.LogInService;
import com.apuope.apuope_re.dto.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class LogInController {
    @Autowired
    private LogInService logInService;

    @PostMapping(value = "/Login")
    public ResponseEntity<ResponseData<String>> validateUser(@RequestBody UserCredentials input) {
        return ResponseEntity.ok(logInService.validateUser(input));
    }
}
