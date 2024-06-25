package com.example.gpb.exceptions;

public class ResourceAccessExceptionHandler implements CustomExceptionHandler {

    @Override
    public String handlerException() {
        return"""
                К сожалению, Middle-service сейчас недоступен, нам очень жаль, попробуйте позже. Вот вам зайчик:\s
                (\\(\\
                ( -.-)
                o_(")(")""";
    }
}
