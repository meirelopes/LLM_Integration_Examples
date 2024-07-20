package com.mentoria.desafio3.controller;

import com.mentoria.desafio3.controller.dto.VectorStoreRequest;
import com.mentoria.desafio3.service.VectorStoreService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vectors")
@AllArgsConstructor
public class VectorStoreController {
    private final VectorStoreService vectorStoreService;

    @PostMapping
    public ResponseEntity<String> saveVectors(@RequestBody VectorStoreRequest vectorRequest) {
        vectorStoreService.saveVectorStore(vectorRequest);
        return ResponseEntity.ok("Vectors saved successfully");
    }
    @GetMapping
    public List<Map<String, Object>> getVectors() {
        return vectorStoreService.getVectors();
    }
    @GetMapping("/byIds")
    public ResponseEntity<List<String>> getVectorsByIds(@RequestParam List<String> ids) {
        List<String> documents = vectorStoreService.getDocumentsByIds(ids);
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/byMetadata")
    public ResponseEntity<List<String>> getVectorsByMetadata(@RequestParam Map<String, String> metadata) {
        List<String> documents = vectorStoreService.getDocumentsByMetadata(metadata);
        return ResponseEntity.ok(documents);
    }
}
