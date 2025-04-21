package com.varun.openApiTry.controller;

import com.varun.openApiTry.service.ChatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ApiController {

    private final ChatService chatService;

    public ApiController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat")
    public ResponseEntity<?> chat(@RequestParam String prompt){
        String response = chatService.getChatCompletion(prompt);
        if (response!=null)
            return ResponseEntity.status(HttpStatus.OK).body(response);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Server Error");
    }

}
