package com.example.gpb.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
public class ResourceAccessExceptionHandler implements CustomExceptionHandler {

    @Override
    public String handlerException(Message message) {
        log.error("Не получается подключится к middle сервису(он выключен). Пользователь {}.", message.getChatId());
        return"""
                К сожалению, Middle-service сейчас недоступен, нам очень жаль, попробуйте позже. Вот вам зайчик:\s
                (\\(\\
                ( -.-)
                o_(")(")""";
    }
}
