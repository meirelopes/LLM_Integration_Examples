package com.exemplo.chat_memory.exemplo2;

import com.exemplo.chat_memory.exemplo1.ChatResponseDto;
import dev.langchain4j.data.message.AiMessage;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AssistantController2 {
    private ChatService2 chatService2;

    @PostMapping("/chat2")
    public ChatResponseDto2 getResponse(@RequestBody ChatRequestDto2 userMessage) {
        AiMessage response = chatService2.getResponse(userMessage);
        return new ChatResponseDto2(response, userMessage.getId());
    }
}
