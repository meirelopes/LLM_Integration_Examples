package com.mentoria.desafio3.controller;

import com.mentoria.desafio3.controller.dto.ChatRequest;
import com.mentoria.desafio3.controller.dto.ChatResponse;
import com.mentoria.desafio3.service.AIService;
import com.mentoria.desafio3.service.EmbeddingComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AIController {
    private final AIService aiService;
    private final EmbeddingComponent embeddingComponent;


    @PostMapping("/chat")
    public ChatResponse getChatResponse(@RequestBody ChatRequest request) {
        Map<String, String> metadata = request.metadata();
        embeddingComponent.processContentChromaDB(metadata);
        String response = aiService.getResponse(request, metadata);
        return new ChatResponse(response);
    }
}

