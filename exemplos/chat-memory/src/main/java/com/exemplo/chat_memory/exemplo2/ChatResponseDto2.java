package com.exemplo.chat_memory.exemplo2;

import dev.langchain4j.agent.tool.ToolExecutionRequest;
import dev.langchain4j.data.message.AiMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatResponseDto2 {
    private String text;
    private Integer id;
    private List<ToolExecutionRequest> toolExecutionRequests;

    public ChatResponseDto2(AiMessage aiMessage,Integer id) {
        this.id = id;
        this.text = aiMessage.text();
        this.toolExecutionRequests = aiMessage.toolExecutionRequests() != null ? aiMessage.toolExecutionRequests() : Collections.emptyList();
    }
}
