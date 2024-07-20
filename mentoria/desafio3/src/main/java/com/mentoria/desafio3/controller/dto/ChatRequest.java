package com.mentoria.desafio3.controller.dto;

import java.util.Map;

public record ChatRequest(String question, Integer userId, Map<String, String> metadata) { }

