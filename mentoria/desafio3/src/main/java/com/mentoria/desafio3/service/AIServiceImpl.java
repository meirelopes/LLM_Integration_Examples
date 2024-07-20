package com.mentoria.desafio3.service;

import com.mentoria.desafio3.controller.dto.ChatRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class AIServiceImpl implements AIService {
    private final Assistant assistant;

    @Override
    public String getResponse(ChatRequest chatRequest, Map<String, String> metadata) {
        return assistant.chat(chatRequest.userId(), chatRequest.question(), metadata);
    }
}