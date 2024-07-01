package com.example.gpb.exceptions;

public class TelegramMessageSendException extends RuntimeException {

    public TelegramMessageSendException(String message, Throwable cause) {
        super(message, cause);
    }
}
