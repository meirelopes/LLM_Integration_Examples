package com.exemplo.chat_memory.exemplo2;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.service.MemoryId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService2 {
    private final Assistant2 assistant2;

    public AiMessage getResponse(ChatRequestDto2 message){
        UserMessage userMessage = UserMessage.from(message.getMessage());
        return assistant2.chat(message.getId(), userMessage);
    }
}
