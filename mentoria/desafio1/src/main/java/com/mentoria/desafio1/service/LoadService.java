package com.mentoria.desafio1.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class LoadService {
    private  final EmbeddingComponent embeddingComponent;
    @GetMapping("/loader/single")
    public void loadSingle() {
        log.info("Carregando Documento");
        embeddingComponent.loadSingleDocument();
        log.info("Documento Carregado");
    }
}
