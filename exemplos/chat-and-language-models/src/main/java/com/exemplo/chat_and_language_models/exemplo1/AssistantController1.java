package com.exemplo.chat_and_language_models.exemplo1;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AssistantController1 {
    private final Assistant1 assistant1;

    @PostMapping("/chat1")
    public String chat(@RequestBody String message) {

        return assistant1.chatModel.generate(message);
    }
}
