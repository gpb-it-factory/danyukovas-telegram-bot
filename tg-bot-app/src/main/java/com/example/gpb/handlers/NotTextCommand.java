package com.example.gpb.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class NotTextCommand implements Command {

    @Override
    public String respMessage(Message message) {
        return message.getChat().getUserName() + ", please, send only text!";
    }
}
