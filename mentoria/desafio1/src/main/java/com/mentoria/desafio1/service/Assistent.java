package com.mentoria.desafio1.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface Assistent {
    @SystemMessage(
            """
                      Você é um assistente de conversa. Responda de maneira amigável, com o formato json, com o campo message e
                      o conteúdo da mensagem que deverá ser em forma de texto único. 
                      Utilize seu conhecimento geral para responder da melhor maneira possível. Se for acessar algum banco de dados,
                      responda a pergunta em forma de uma redação atraente para o leitor.
                    """
    )
    String chat(@MemoryId int  memoryId, @UserMessage String userMessage);
}
