package com.semillero.ubuntu.ChatBot.Controllers;

import com.semillero.ubuntu.ChatBot.DTOs.InitialQuestionRequest;
import com.semillero.ubuntu.ChatBot.DTOs.QuestionResponse;
import com.semillero.ubuntu.ChatBot.DTOs.SecondaryQuestionRequest;
import com.semillero.ubuntu.ChatBot.DTOs.SecondaryQuestionResponse;
import com.semillero.ubuntu.ChatBot.Services.Impl.QuestionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/faq")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionServiceImpl service;

    @PostMapping("/initial")
    public ResponseEntity<QuestionResponse> createInitialQuestion(@RequestBody InitialQuestionRequest question){

        return ResponseEntity.ok(service.createInitialQuestion(question));
    }

    @PostMapping("/secondary")
    public ResponseEntity<SecondaryQuestionResponse> createSecondaryQuestion(@RequestBody SecondaryQuestionRequest question){

        return ResponseEntity.ok(service.createSecondaryQuestion(question));
    }

}
