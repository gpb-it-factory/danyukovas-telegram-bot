package com.example.gpb.exceptions;

import lombok.extern.slf4j.Slf4j;
<<<<<<< feature/19
=======
import org.telegram.telegrambots.meta.api.objects.Message;
>>>>>>> trunk

@Slf4j
public class ResourceAccessExceptionHandler implements CustomExceptionHandler {

    @Override
<<<<<<< feature/19
    public String handlerException() {
        log.error("Не получается подключится к middle сервису(он выключен).");
=======
    public String handlerException(Message message) {
        log.error("Не получается подключится к middle сервису(он выключен). Пользователь {}.", message.getChatId());
>>>>>>> trunk
        return"""
                К сожалению, Middle-service сейчас недоступен, нам очень жаль, попробуйте позже. Вот вам зайчик:\s
                (\\(\\
                ( -.-)
                o_(")(")""";
    }
}
