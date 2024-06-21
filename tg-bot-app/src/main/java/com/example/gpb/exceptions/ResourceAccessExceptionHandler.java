package com.example.gpb.exceptions;

public class ResourceAccessExceptionHandler implements CustomExceptionHandler {
    @Override
    public String handlerException() {
        return "Данный адрес на данный момент недоступен( Попробуй позже.";
    }
}
