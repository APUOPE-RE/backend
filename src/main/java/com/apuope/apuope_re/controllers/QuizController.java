package com.apuope.apuope_re.controllers;

import com.apuope.apuope_re.dto.*;
import com.apuope.apuope_re.services.QuizService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping(value = "/generateQuiz/{lectureId}")
    public ResponseEntity<ResponseData<Object>> generateQuiz(HttpServletRequest request, @PathVariable("lectureId") Integer lectureId) throws SQLException,
            JsonProcessingException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer ", "");
        ResponseData<Object> responseData = quizService.generateQuiz(token, lectureId);

        if (responseData.getSuccess()) {
            return ResponseEntity.ok(responseData);
        } else {
            return ResponseEntity.internalServerError().body(responseData);
        }
    }

    @PostMapping(value = "/submitQuiz")
    public ResponseEntity<ResponseData<Object>> submitQuiz(HttpServletRequest request, @RequestBody QuizSubmitData input) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer ", "");
        ResponseData<Object> responseData = quizService.validateQuizAnswers(token, input);

        return responseData.getSuccess() ? ResponseEntity.ok(responseData) : ResponseEntity.internalServerError().body(responseData);
    }

    @GetMapping(value = "/fetchPreviousQuizzes")
    public ResponseEntity<Object> fetchPreviousQuizzes(HttpServletRequest request) throws SQLException, JsonProcessingException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer ", "");
        ResponseData<Object> responseData = quizService.fetchPreviousQuizzes(token);

        if (responseData.getSuccess()) {
            return ResponseEntity.ok(responseData.getData());
        } else {
            return ResponseEntity.internalServerError().body(responseData.getData());
        }
    }

    @GetMapping(value = "/fetchPreviousQuiz/{quizId}")
    public ResponseEntity<Object> fetchPreviousQuiz(HttpServletRequest request, @PathVariable("quizId") Integer quizId) throws SQLException,
            JsonProcessingException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer ", "");
        ResponseData<Object> responseData = quizService.fetchPreviousQuiz(token, quizId);

        if (responseData.getSuccess()) {
            return ResponseEntity.ok(responseData.getData());
        } else {
            return ResponseEntity.internalServerError().body(responseData.getData());
        }
    }
}