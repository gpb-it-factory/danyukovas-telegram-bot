package com.example.gpb.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
@Component
public class PingCommand implements Command {

    @Override
    public String getCommandName() {
        return "/ping";
    }

    @Override
    public String respMessage(Message message) {
        log.info("Исполнение команды /ping.");
        return "pong to " + message.getChat().getUserName();
    }
}
