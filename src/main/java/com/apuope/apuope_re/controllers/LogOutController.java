package com.apuope.apuope_re.controllers;

import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.services.LogOutService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class LogOutController {
    @Autowired
    private LogOutService logOutService;

    @GetMapping(value = "/logout")
    public ResponseEntity<ResponseData<String>> logoutUser(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer ", "");
        return ResponseEntity.ok(logOutService.handleLogout(token, request));
    }
}
