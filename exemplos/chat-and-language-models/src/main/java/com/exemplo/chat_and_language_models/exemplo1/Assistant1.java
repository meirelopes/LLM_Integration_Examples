package com.exemplo.chat_and_language_models.exemplo1;

import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.spring.AiService;

@AiService
public class Assistant1 {
    String apiKey = System.getenv("OPENAI_API_KEY");
    OpenAiChatModel chatModel = OpenAiChatModel.withApiKey(apiKey);
}
