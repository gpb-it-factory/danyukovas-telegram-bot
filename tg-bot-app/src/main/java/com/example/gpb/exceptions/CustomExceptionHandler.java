package com.example.gpb.exceptions;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface CustomExceptionHandler {

    public String handlerException(Message message);
}
