package com.example.gpb.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
@Component
public class StartCommand implements Command {

    @Override
    public String getCommandName() {
        return "/start";
    }

    @Override
    public String respMessage(Message message) {
        log.info("Исполнение команды /start пользователем {}.", message.getChatId());
        return "Hello " + message.getChat().getFirstName() + "!";
    }
}
