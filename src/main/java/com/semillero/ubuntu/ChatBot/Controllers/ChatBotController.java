package com.semillero.ubuntu.ChatBot.Controllers;

import com.semillero.ubuntu.ChatBot.DTOs.AnswerPlusSecondaryQuestions;
import com.semillero.ubuntu.ChatBot.DTOs.QuestionResponse;
import com.semillero.ubuntu.ChatBot.Services.Impl.ChatBotServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/faq")
@RequiredArgsConstructor
public class ChatBotController {

    private final ChatBotServiceImpl chatBotService;

    @GetMapping("/initials")
    public ResponseEntity<List<QuestionResponse>> getActiveInitialQuestions(){

        return ResponseEntity.ok(chatBotService.getActiveInitialQuestion());
    }

    @GetMapping("/answer/{id}")
    public ResponseEntity<AnswerPlusSecondaryQuestions> getAnswerForQuestionId(@PathVariable Long id){

        return ResponseEntity.ok(chatBotService.getAnswerForQuestionId(id));
    }

}
