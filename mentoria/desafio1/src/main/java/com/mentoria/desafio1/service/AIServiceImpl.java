package com.mentoria.desafio1.service;

import com.mentoria.desafio1.controller.dto.ChatRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AIServiceImpl implements AIService{
    private final Assistent assistent;

    @Override
    public String getResponse(ChatRequest chatRequest) {
        return assistent.chat(chatRequest.userId(), chatRequest.question());
    }

}
