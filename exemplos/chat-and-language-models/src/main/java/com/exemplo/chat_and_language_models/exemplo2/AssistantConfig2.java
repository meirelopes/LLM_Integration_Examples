package com.exemplo.chat_and_language_models.exemplo2;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AssistantConfig2 {
    @Bean
    public Assistant2 assistent2() {
        return AiServices.builder(Assistant2.class)
                .chatLanguageModel(chatLanguageModel2())
                .build();
    }
    @Bean
    public ChatLanguageModel chatLanguageModel2() {
        String apiKey = System.getenv("OPENAI_API_KEY");
        return new OpenAiChatModel.OpenAiChatModelBuilder()
                .apiKey(apiKey)
                .modelName("gpt-3.5-turbo")
                .temperature(0.7)
                .build();
    }
}
