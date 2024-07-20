package com.mentoria.desafio3.service;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class EmbeddingComponent {
    private final EmbeddingModel embeddingModel;
    private final EmbeddingStore embeddingStore;
    private final VectorStoreService vectorStoreService;

    public void processContentChromaDB(Map<String, String> metadata) {

        try {
            System.out.println("Metadata: " + metadata);

            List<String> conteudoNivel = vectorStoreService.getDocumentsByMetadata(metadata);
            System.out.println("Metadata: " + metadata);
            System.out.println("Conteúdo: " + conteudoNivel.toString());

            String content = String.join(",", conteudoNivel);


            // Verifique se o conteúdo foi extraído
            if (content == null) {
                System.err.println("Não foi possível extrair o conteúdo do nível: " + metadata);
                return;
            }

            // Crie o documento a partir do conteúdo textual
            Document document = new Document(content);

            // Configure o EmbeddingStoreIngestor
            EmbeddingStoreIngestor embeddingStoreIngestor = EmbeddingStoreIngestor.builder()
                    .documentSplitter(DocumentSplitters.recursive(300, 10))
                    .embeddingModel(embeddingModel)
                    .embeddingStore(embeddingStore)
                    .build();

            // Processe o documento e armazene os embeddings
            embeddingStoreIngestor.ingest(document);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
