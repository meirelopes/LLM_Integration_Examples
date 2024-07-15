package com.exemplo.chat_memory.exemplo1;

import dev.langchain4j.data.message.AiMessage;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AssistantController {
    private ChatService chatService;

    @PostMapping("/chat1")
    public ChatResponseDto getResponse(@RequestBody String userMessage) {
        AiMessage response = chatService.getResponse(userMessage);
        return new ChatResponseDto(response);
    }
}
