package com.example.gpb.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResourceAccessExceptionHandler implements CustomExceptionHandler {

    @Override
    public String handlerException() {
        log.error("Не получается подключится к middle сервису(он выключен).");
        return"""
                К сожалению, Middle-service сейчас недоступен, нам очень жаль, попробуйте позже. Вот вам зайчик:\s
                (\\(\\
                ( -.-)
                o_(")(")""";
    }
}
