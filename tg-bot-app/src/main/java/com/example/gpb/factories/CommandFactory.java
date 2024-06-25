package com.example.gpb.factories;

import com.example.gpb.handlers.*;
import com.example.gpb.handlers.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CommandFactory {

    private final Set<Command> commands;
    private final NotTextCommand notTextCommand;
    private final WrongCommand wrongCommand;

    @Autowired
    public CommandFactory(Set<Command> commands,
                          NotTextCommand notTextCommand,
                          WrongCommand wrongCommand) {
        this.commands = commands;
        this.wrongCommand = wrongCommand;
        this.notTextCommand = notTextCommand;
    }

    public Command getCommand(String text) {
        if (text != null) {
            return commands.stream()
                    .filter(v -> v.getCommandName().equals(text))
                    .findFirst()
                    .orElse(wrongCommand);
        }
        return notTextCommand;
    }
}
