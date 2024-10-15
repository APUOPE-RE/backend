package com.apuope.apuope_re.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class testController {
    @GetMapping("/test")
    public String test() {
        return "Test...";
    }

    @GetMapping("/test2")
    public String test2() {
        return "Test2...";
    }
}
