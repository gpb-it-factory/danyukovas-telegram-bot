package com.example.gpb.factories;

import com.example.gpb.handlers.*;
import com.example.gpb.handlers.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;

@Slf4j
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
        log.info("Поиск нужной команды для исполнения.");
        if (text == null) {
            return notTextCommand;
        }
        String commandText = text.split(" ")[0];
        return commands.stream()
                .filter(v -> v.getCommandName().equals(commandText))
                .findFirst()
                .orElse(wrongCommand);
    }
}
