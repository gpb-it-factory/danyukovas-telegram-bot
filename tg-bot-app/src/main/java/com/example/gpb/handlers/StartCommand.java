package com.example.gpb.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class StartCommand implements Command {

    @Override
    public String getCommandName() {
        return "/start";
    }

    @Override
    public String respMessage(Message message) {
        return "Hello " + message.getChat().getFirstName() + "!";
    }
}
