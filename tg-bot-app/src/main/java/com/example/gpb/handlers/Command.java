package com.example.gpb.handlers;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface Command {

    String getCommandName();
    String respMessage(Message message);
}
