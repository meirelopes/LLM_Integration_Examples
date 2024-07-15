package com.exemplo.chat_and_language_models.exemplo3;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface Assistant3 {
    @SystemMessage(
            """
                  Você é um assistente de conversa. Responda de maneira amigável e
                  se não souber algo, apenas desculpe-se e diga que não pode ajudar pois não sabe do que se trata.
                  """
    )
    AiMessage chat(@UserMessage dev.langchain4j.data.message.UserMessage userMessage);

}
