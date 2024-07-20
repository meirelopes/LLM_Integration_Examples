package com.mentoria.desafio3.service;

import com.mentoria.desafio3.controller.dto.ChatRequest;

import java.util.Map;

public interface AIService {
    String getResponse(ChatRequest chatRequest, Map<String, String> metadata);
}
