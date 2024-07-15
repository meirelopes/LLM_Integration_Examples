package com.exemplo.chat_and_language_models.exemplo3;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AssistantConfig3 {
    @Bean
    public Assistant3 assistent3() {
        return AiServices.builder(Assistant3.class)
                .chatLanguageModel(chatLanguageModel3())
                .build();
    }
    @Bean
    public ChatLanguageModel chatLanguageModel3() {
        String apiKey = System.getenv("OPENAI_API_KEY");
        return new OpenAiChatModel.OpenAiChatModelBuilder()
                .apiKey(apiKey)
                .modelName("gpt-3.5-turbo")
                .temperature(0.7)
                .build();
    }
}
