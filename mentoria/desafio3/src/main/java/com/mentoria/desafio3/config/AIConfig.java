package com.mentoria.desafio3.config;

import com.mentoria.desafio3.service.Assistant;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.rag.content.injector.DefaultContentInjector;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.amikos.chromadb.handler.DefaultApi;

import static java.util.Arrays.asList;

@Configuration
public class AIConfig {

    @Bean
    public ChatLanguageModel chatLanguageModel() {
        String apiKey = System.getenv("OPENAI_API_KEY");
        return OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gpt-3.5-turbo")
                .responseFormat("json_object")
                .temperature(0.7)
                .build();
    }

    @Bean
    public EmbeddingModel embeddingModel() {
        return OpenAiEmbeddingModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .modelName("text-embedding-ada-002")
                .build();
    }

    @Bean
    public EmbeddingStore<TextSegment> embeddingStore() {
        return new InMemoryEmbeddingStore<>();
    }

    @Bean
    public EmbeddingStoreContentRetriever embeddingStoreContentRetriever() {
        return EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore())
                .embeddingModel(embeddingModel())
                .build();
    }

    @Bean
    public DefaultContentInjector contentInjector() {
        return DefaultContentInjector.builder()
                .metadataKeysToInclude(asList("file_name", "index"))
                .build();
    }

    @Bean
    public RetrievalAugmentor retrievalAugmentor() {
        return DefaultRetrievalAugmentor.builder()
                .contentRetriever(embeddingStoreContentRetriever())
                .contentInjector(contentInjector())
                .build();
    }

    @Bean
    public Assistant ragAssistent() {
        return AiServices.builder(Assistant.class)
                .chatLanguageModel(chatLanguageModel())
                .retrievalAugmentor(retrievalAugmentor())
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(20))
                .build();
    }

    @Bean
    public DefaultApi defaultApi() {
        return new DefaultApi();
    }
}
