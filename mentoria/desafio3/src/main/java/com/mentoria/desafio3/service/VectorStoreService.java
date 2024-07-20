package com.mentoria.desafio3.service;

import com.mentoria.desafio3.controller.dto.VectorStoreRequest;

import java.util.List;
import java.util.Map;

public interface VectorStoreService {
    void saveVectorStore(VectorStoreRequest request);
    List<Map<String, Object>> getVectors();
    List<String> getDocumentsByIds(List<String> ids);
    List<String> getDocumentsByMetadata(Map<String, String> metadata);
}

