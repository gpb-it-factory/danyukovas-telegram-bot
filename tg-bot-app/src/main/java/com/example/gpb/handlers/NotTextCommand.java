package com.example.gpb.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
@Component
public class NotTextCommand implements Command {

    @Override
    public String getCommandName() {
        return "not_text";
    }

    @Override
    public String respMessage(Message message) {
<<<<<<< feature/19
        log.info("Формирование ответа сообщения при запросе не в виде текста.");
=======
        log.info("Формирование ответа сообщения при запросе не в виде текста пользователем {}.", message.getChatId());
>>>>>>> trunk
        return message.getChat().getUserName() + ", please, send only text!";
    }
}
