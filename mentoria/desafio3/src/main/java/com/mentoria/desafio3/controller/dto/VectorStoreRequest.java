package com.mentoria.desafio3.controller.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class VectorStoreRequest {
    private List<Map<String, String>> metadata;
    private List<String> documents;
    private List<String> ids;
}