package com.example.gpb.factories;

import com.example.gpb.handlers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResponseFactory {

    private final StartCommand startCommand;
    private final PingCommand pingCommand;
    private final RegisterCommand registerCommand;
    private final DeafaultCommand deafaultCommand;
    private final WrongCommand wrongCommand;

    @Autowired
    public ResponseFactory(StartCommand startCommand, PingCommand pingCommand,
                           RegisterCommand registerCommand, DeafaultCommand deafaultCommand,
                           WrongCommand wrongCommand) {
        this.startCommand = startCommand;
        this.pingCommand = pingCommand;
        this.registerCommand = registerCommand;
        this.deafaultCommand = deafaultCommand;
        this.wrongCommand = wrongCommand;
    }

    public Command getCommand(String text) {
        if (text != null) {
            return switch (text) {
                case "/start" -> startCommand;
                case "/ping" -> pingCommand;
                case "/register" -> registerCommand;
                default -> deafaultCommand;
            };
        }
        return wrongCommand;
    }
}
