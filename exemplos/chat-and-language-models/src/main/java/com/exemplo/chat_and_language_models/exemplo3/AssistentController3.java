package com.exemplo.chat_and_language_models.exemplo3;

import dev.langchain4j.data.message.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import dev.langchain4j.data.message.AiMessage;

@RestController
public class AssistentController3 {

    @Autowired
    private Assistant3 assistant3;

    @PostMapping("/chat3")
    public ResponseEntity<ChatResponseDto> chat3(@RequestBody String message) {
            AiMessage response = assistant3.chat(UserMessage.from(message));
            return ResponseEntity.ok(new ChatResponseDto(response));
    }
}
