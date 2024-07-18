package com.mentoria.desafio1.controller;

import com.mentoria.desafio1.controller.dto.ChatRequest;
import com.mentoria.desafio1.controller.dto.ChatResponse;
import com.mentoria.desafio1.service.AIService;
import com.mentoria.desafio1.service.LoadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AIController {
    private final AIService aiService;
    private final LoadService loadService;

    @PostMapping("/chat")
    public ChatResponse getChatResponseExtend(@RequestBody ChatRequest request) {
        loadService.loadSingle();
        return new ChatResponse(aiService.getResponse(request));
    }
}