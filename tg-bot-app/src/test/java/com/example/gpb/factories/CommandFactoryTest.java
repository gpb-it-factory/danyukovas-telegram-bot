package com.example.gpb.factories;

import com.example.gpb.handlers.Command;
import com.example.gpb.handlers.NotTextCommand;
import com.example.gpb.handlers.PingCommand;
import com.example.gpb.handlers.WrongCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CommandFactoryTest {

    private CommandFactory commandFactory;


    @BeforeEach
    public void setUp() {
        NotTextCommand notTextCommand = new NotTextCommand();
        WrongCommand wrongCommand = new WrongCommand();
        Set<Command> commands = Set.of(new PingCommand());
        commandFactory = new CommandFactory(commands, notTextCommand, wrongCommand);
    }

    @Test
    public void whenFindPingCommandTest() {

        String text = "/ping";

        var res = commandFactory.getCommand(text).getCommandName();;
        var exp = new PingCommand().getCommandName();;

        assertEquals(exp, res);;
    }

    @Test
    public void whenSendTextWhichNotCommandTest() {

        String text = "testWrongCommand";

        var res = commandFactory.getCommand(text).getCommandName();;
        var exp = new WrongCommand().getCommandName();;

        assertEquals(exp, res);
    }

    @Test
    public void whenSendNotTextTest() {

        String text = null;

        var res = commandFactory.getCommand(text).getCommandName();
        var exp = new NotTextCommand().getCommandName();

        assertEquals(exp, res);
    }
}
