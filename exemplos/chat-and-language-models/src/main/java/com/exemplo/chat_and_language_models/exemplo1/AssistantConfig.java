package com.exemplo.chat_and_language_models.exemplo1;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AssistantConfig {
    @Bean
    public Assistant assistent() {
        return AiServices.builder(Assistant.class)
                .chatLanguageModel(chatLanguageModel())
                .build();
    }
    @Bean
    public ChatLanguageModel chatLanguageModel() {
        String apiKey = System.getenv("OPENAI_API_KEY");
        return new OpenAiChatModel.OpenAiChatModelBuilder()
                .apiKey(apiKey)
                .modelName("gpt-3.5-turbo")
                .temperature(0.7)
                .build();
    }
}
