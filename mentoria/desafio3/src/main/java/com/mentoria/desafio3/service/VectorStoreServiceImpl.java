package com.mentoria.desafio3.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.mentoria.desafio3.controller.dto.VectorStoreRequest;
import org.springframework.stereotype.Service;
import tech.amikos.chromadb.Client;
import tech.amikos.chromadb.Collection;
import tech.amikos.chromadb.EmbeddingFunction;
import tech.amikos.chromadb.OpenAIEmbeddingFunction;
import tech.amikos.chromadb.handler.ApiException;
import tech.amikos.chromadb.handler.DefaultApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class VectorStoreServiceImpl implements VectorStoreService {
    private final Client client;
    private Collection collection;
    private final EmbeddingFunction embeddingFunction;

    public VectorStoreServiceImpl(DefaultApi api) {
        String chromaUrl = System.getenv("CHROMA_URL");
        String apiKey = System.getenv("OPENAI_API_KEY");
        this.client = new Client(chromaUrl);
        this.embeddingFunction = new OpenAIEmbeddingFunction(apiKey, "text-embedding-3-small");
        try {
            this.collection = this.client.createCollection("analise-de-performance", null, true, embeddingFunction);
        } catch (ApiException e) {
            e.printStackTrace();
            System.out.println("Erro ao criar ou recuperar a coleção: " + e.getMessage());
        }
    }

    @Override
    public void saveVectorStore(VectorStoreRequest request) {
        try {
            Collection collection = client.createCollection("analise-de-performance", null, true, embeddingFunction);
            collection.add(null, request.getMetadata(), request.getDocuments(), request.getIds());
            System.out.println("Documentos adicionados com sucesso: " + request.getDocuments().size());
            for (String document : request.getDocuments()) {
                System.out.println(document);
            }
            for (Map<String, String> metadata : request.getMetadata()) {
                System.out.println(metadata);
            }
            for (String id : request.getIds()) {
                System.out.println(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    @Override
    public List<Map<String, Object>> getVectors() {
        try {
            Collection.GetResult result = collection.get();  // Busca todos os documentos sem filtros
            if (result != null && result.getDocuments() != null) {
                List<String> documents = result.getDocuments();
                List<Map<String, Object>> parsedDocuments = new ArrayList<>();
                for (String doc : documents) {
                    Map<String, Object> parsedDocument = parseDocument(doc);
                    parsedDocuments.add(parsedDocument);
                }
                return parsedDocuments;
            } else {
                System.out.println("Não foram encontrados documentos na resposta da consulta.");
                return Collections.emptyList();
            }
        } catch (ApiException e) {
            e.printStackTrace();
            return Collections.emptyList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private Map<String, Object> parseDocument(String document) {
        try {
            Gson gson = new Gson();
            // Tenta parsear como objeto JSON
            return gson.fromJson(document, new TypeToken<Map<String, Object>>() {}.getType());
        } catch (JsonSyntaxException e) {
            // Se falhar, retorna o documento como string simples
            System.err.println("Erro ao fazer parsing do JSON do documento: " + e.getMessage());
            return Collections.singletonMap("document", document);
        }
    }
    @Override
    public List<String> getDocumentsByIds(List<String> ids) {
        try {
            Collection.GetResult result = collection.get(ids, null, null);
            return result.getDocuments();
        } catch (ApiException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getDocumentsByMetadata(Map<String, String> metadata) {
        try {
            Collection.GetResult result = collection.get(null, metadata, null);
            return result.getDocuments();
        } catch (ApiException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}