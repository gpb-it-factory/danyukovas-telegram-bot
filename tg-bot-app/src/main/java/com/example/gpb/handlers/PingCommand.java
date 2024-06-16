package com.example.gpb.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class PingCommand implements Command {

    @Override
    public String respMessage(Message message) {
        return "pong to " + message.getChat().getUserName();
    }
}
