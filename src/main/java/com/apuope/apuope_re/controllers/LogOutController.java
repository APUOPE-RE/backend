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
    public ResponseEntity<ResponseData<Object>> logoutUser(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer ", "");
        var response = logOutService.handleLogout(token);
        return response.getSuccess() ? ResponseEntity.ok(response) : ResponseEntity.internalServerError().body(response);
    }
}
