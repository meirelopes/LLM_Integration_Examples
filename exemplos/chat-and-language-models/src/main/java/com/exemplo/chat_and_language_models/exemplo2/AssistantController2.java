package com.exemplo.chat_and_language_models.exemplo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class AssistantController2 {

    @Autowired
    Assistant2 assistant2;

    @PostMapping("/chat2")
    public String chat(@RequestBody String message) {
        return assistant2.chat(message);
    }
}
