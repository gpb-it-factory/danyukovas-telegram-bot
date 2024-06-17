package com.example.gpb.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class WrongCommand implements Command {

    @Override
    public String respMessage(Message message) {
        return "Invalid command, " + message.getChat().getUserName() + ", please, enter the correct one";
    }
}
