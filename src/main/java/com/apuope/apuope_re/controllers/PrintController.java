package com.apuope.apuope_re.controllers;

import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.services.PrintService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PrintController {
    private final PrintService printService;

    public PrintController(PrintService printService) {
        this.printService = printService;
    }

    @GetMapping(value = "/fetchQuizPdf/{quizId}")
    public ResponseEntity<Object> generateQuiz(HttpServletRequest request, @PathVariable("quizId") Integer quizId) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer ", "");
        ResponseData<Object> responseData = printService.generatePdf(token, quizId);

        if (responseData.getSuccess()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=example.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(responseData.getData());
        } else {
            return ResponseEntity.internalServerError().body(responseData.getData());
        }
    }
}