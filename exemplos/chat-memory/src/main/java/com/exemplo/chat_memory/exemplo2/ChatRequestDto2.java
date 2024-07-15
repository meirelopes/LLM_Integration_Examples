package com.exemplo.chat_memory.exemplo2;

import dev.langchain4j.data.message.AiMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatRequestDto2 {
    private String message;
    private Integer id;

    public ChatRequestDto2(AiMessage aiMessage, Integer id) {
        this.id = id;
        this.message = aiMessage.text();
    }
}
