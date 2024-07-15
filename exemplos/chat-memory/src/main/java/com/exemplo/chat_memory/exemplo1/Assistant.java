package com.exemplo.chat_memory.exemplo1;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface Assistant {
    @SystemMessage(
            """
                    Você é um assistente especialista em AWS e irá ajudar as pessoas respondendo perguntas
                    sobre nuvem, mais especificament AWS. A resposta deve ser fornecida no formato json.
                    """
    )
    AiMessage chat (@UserMessage dev.langchain4j.data.message.UserMessage message);
}
