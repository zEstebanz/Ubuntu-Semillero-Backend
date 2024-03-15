package com.semillero.ubuntu.ChatBot.Controllers;

import com.semillero.ubuntu.ChatBot.DTOs.InitialQuestionRequest;
import com.semillero.ubuntu.ChatBot.DTOs.QuestionResponse;
import com.semillero.ubuntu.ChatBot.DTOs.SecondaryQuestionRequest;
import com.semillero.ubuntu.ChatBot.DTOs.SecondaryQuestionResponse;
import com.semillero.ubuntu.ChatBot.Services.Impl.QuestionServiceImpl;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/questionsNotActive")
    public ResponseEntity<List<QuestionResponse>> getQuestionsNotActive(){
        return ResponseEntity.ok(service.getQuestionsNotActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponse>findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<QuestionResponse>logicalDelete(@PathVariable Long id){
        return ResponseEntity.ok(service.logicalDelete(id));
    }

}
