package com.exemplo.chat_and_language_models.exemplo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class AssistantController {

    @Autowired
    Assistant assistant;

    @PostMapping("/chat")
    public String chat(@RequestBody String message) {
        return assistant.chat(message);
    }
}
