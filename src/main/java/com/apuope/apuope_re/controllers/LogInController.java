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

    @PostMapping(value = "/login")
    public ResponseEntity<ResponseData<Object>> validateUser(@RequestBody UserCredentials input) {
        var response = logInService.validateUser(input);
        return response.getSuccess() ? ResponseEntity.ok(response) : ResponseEntity.internalServerError().body(response);
    }
}
