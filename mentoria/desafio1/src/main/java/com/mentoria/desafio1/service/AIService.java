package com.mentoria.desafio1.service;

import com.mentoria.desafio1.controller.dto.ChatRequest;

public interface AIService {
    String getResponse(ChatRequest chatRequest);
}
