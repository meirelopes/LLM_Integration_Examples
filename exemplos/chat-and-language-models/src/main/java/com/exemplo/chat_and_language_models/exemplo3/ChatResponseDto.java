package com.exemplo.chat_and_language_models.exemplo3;

import dev.langchain4j.agent.tool.ToolExecutionRequest;
import dev.langchain4j.data.message.AiMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
public class ChatResponseDto {
    private String text;
    private List<ToolExecutionRequest> toolExecutionRequests;

    public ChatResponseDto(AiMessage aiMessage) {
        this.text = aiMessage.text();
        this.toolExecutionRequests = aiMessage.toolExecutionRequests() != null ? aiMessage.toolExecutionRequests() : Collections.emptyList();
    }
}
