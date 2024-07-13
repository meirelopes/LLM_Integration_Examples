package com.exemplo.chat_and_language_models.exemplo1;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface Assistant {
    @SystemMessage("You are a polite assistant")
    String chat(String userMessage);
}