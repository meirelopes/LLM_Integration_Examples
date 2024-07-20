package com.mentoria.desafio3.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

import java.util.Map;

public interface Assistant {
    @SystemMessage(
            """
                      Você é um assistente de conversa. Responda de maneira amigável, com o formato json, com o campo message e
                      o conteúdo da mensagem que deverá ser em forma de texto único. 
                      Utilize seu conhecimento geral para responder perguntas que não são específicas sobre avaliação de pessoas de níveis 
                      z3, z4, etc. Se for responder perguntas sobre avaliação de pessoas responda em forma de uma redação atraente para o leitor.
                    """
    )
    String chat(@MemoryId int memoryId, @UserMessage String userMessage, @UserMessage Map<String, String> metadata);
}
