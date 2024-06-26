package com.example.gpb.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
@Component
public class WrongCommand implements Command {

    @Override
    public String getCommandName() {
        return "default";
    }

    @Override
    public String respMessage(Message message) {
        log.info("Формирование ответа сообщения при ненайденной команде.");
        return "Invalid command, " + message.getChat().getUserName() + ", please, enter the correct one";
    }
}
