package com.apuope.apuope_re.controllers;

import com.apuope.apuope_re.entities.User;
import com.apuope.apuope_re.repositories.UserRepository;
import com.apuope.apuope_re.services.LogInService;
import com.apuope.apuope_re.types.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class LogInController {
    @Autowired
    private LogInService logInService;

    @PostMapping(value = "/Login")
    public ResponseEntity<Boolean> validateUser(@RequestBody UserCredentials input) {
        boolean valid = logInService.validateUser(input);
        if (valid){
            return ResponseEntity.ok(true);
        }
        else {
            return ResponseEntity.ok(false);
        }
    }
}
