package com.apuope.apuope_re.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LogInController {

    LogInController() {}

    @GetMapping(value = "/")
    public String getHello() {
        return "moikkamoi";
    }
}
