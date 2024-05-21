package com.example.gpb.factories;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class ResponseFactory {

    public String respMessage(Message message) {
        return switch (message.getText()) {
            case "/start" -> "Hello " + message.getChat().getFirstName();
            case "/ping" -> "pong";
            default -> "WRONG MESSAGE BUDDY";
        };
    }
}
