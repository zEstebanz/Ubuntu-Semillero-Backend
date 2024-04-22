package com.semillero.ubuntu.ChatBot.Controllers.chatbot;

import com.semillero.ubuntu.ChatBot.DTOs.chatbot.InitialQuestionRequest;
import com.semillero.ubuntu.ChatBot.DTOs.chatbot.QuestionResponse;
import com.semillero.ubuntu.ChatBot.DTOs.chatbot.SecondaryQuestionRequest;
import com.semillero.ubuntu.ChatBot.DTOs.chatbot.SecondaryQuestionResponse;
import com.semillero.ubuntu.ChatBot.Services.Impl.QuestionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/question")
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

    @GetMapping("/questionsNotActive")
    public ResponseEntity<List<QuestionResponse>> getQuestionsNotActive(){
        return ResponseEntity.ok(service.getQuestionsNotActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/hide/{id}")
    public ResponseEntity<QuestionResponse> hideQuestion(@PathVariable Long id){
        return ResponseEntity.ok(service.hideQuestion(id));
    }

    @PutMapping("/show/{id}")
    public ResponseEntity<QuestionResponse> showQuestion(@PathVariable Long id){
        return ResponseEntity.ok(service.showQuestion(id));
    }

    @PutMapping("/text/{id}")
    public ResponseEntity<QuestionResponse> updateQuestionText(@PathVariable("id") Long id, @RequestParam String text){
        return ResponseEntity.ok(service.updateQuestionText(id, text));
    }

}
