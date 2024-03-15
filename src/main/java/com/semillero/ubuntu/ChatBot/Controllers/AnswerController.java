package com.semillero.ubuntu.ChatBot.Controllers;

import com.semillero.ubuntu.ChatBot.DTOs.AnswerRequest;
import com.semillero.ubuntu.ChatBot.DTOs.AnswerResponse;
import com.semillero.ubuntu.ChatBot.DTOs.AnswerWithQuestionResponse;
import com.semillero.ubuntu.ChatBot.Services.Impl.AnswerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerServiceImpl service;

    @PostMapping("/create")
    public ResponseEntity<AnswerWithQuestionResponse> createAnswer(@RequestBody AnswerRequest request){
        return ResponseEntity.ok(service.createAnswer(request));
    }

    @GetMapping("/answersNotFull")
    public ResponseEntity<List<AnswerResponse>> getAnswersNotFull(){
        return ResponseEntity.ok(service.getAllAnswersNotFull());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnswerResponse>findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

}
