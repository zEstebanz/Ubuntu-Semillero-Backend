package com.semillero.ubuntu.ChatBot.Controllers;

import com.semillero.ubuntu.ChatBot.DTOs.AnswerRequest;
import com.semillero.ubuntu.ChatBot.DTOs.AnswerResponse;
import com.semillero.ubuntu.ChatBot.Services.Impl.AnswerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerServiceImpl service;

    @PostMapping("/create")
    public ResponseEntity<AnswerResponse> createAnswer(@RequestBody AnswerRequest request){
        return ResponseEntity.ok(service.createAnswer(request));
    }
}
