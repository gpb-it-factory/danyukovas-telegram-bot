package com.example.gpb.factories;

import com.example.gpb.handlers.*;
import com.example.gpb.handlers.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandFactory {

    private final StartCommand startCommand;
    private final PingCommand pingCommand;
    private final RegisterCommand registerCommand;
    private final WrongCommand wrongCommand;
    private final NotTextCommand notTextCommand;

    @Autowired
    public CommandFactory(StartCommand startCommand, PingCommand pingCommand,
                          RegisterCommand registerCommand, WrongCommand wrongCommand,
                          NotTextCommand notTextCommand) {
        this.startCommand = startCommand;
        this.pingCommand = pingCommand;
        this.registerCommand = registerCommand;
        this.wrongCommand = wrongCommand;
        this.notTextCommand = notTextCommand;
    }

    public Command getCommand(String text) {
        if (text != null) {
            return switch (text) {
                case "/start" -> startCommand;
                case "/ping" -> pingCommand;
                case "/register" -> registerCommand;
                default -> wrongCommand;
            };
        }
        return notTextCommand;
    }
}
