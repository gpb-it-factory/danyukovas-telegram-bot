package com.example.gpb.handlers;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public class CommandInvoker {

    public String invokeCommand(Command command, Message message) {
        return command.respMessage(message);
    }
}
