package com.exemplo.chat_memory.exemplo1;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.service.spring.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final Assistant assistant;

    public AiMessage getResponse(String message){
        UserMessage userMessage = UserMessage.from(message);
        return assistant.chat(userMessage);
    }
}
