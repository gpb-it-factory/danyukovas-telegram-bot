package com.example.gpb.handlers;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface Command {

    String respMessage(Message message);
}
