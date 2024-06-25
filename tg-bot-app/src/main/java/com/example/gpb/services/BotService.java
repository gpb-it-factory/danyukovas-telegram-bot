package com.example.gpb.services;

import com.example.gpb.handlers.Command;
import com.example.gpb.factories.CommandFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class BotService extends TelegramLongPollingBot {

    private final String botName;
    private final CommandFactory commandFactory;

    @Autowired
    public BotService(@Value("${bot.token}") String token, @Value("${bot.name}") String botName,
                      CommandFactory commandFactory) {
        super(token);
        this.botName = botName;
        this.commandFactory = commandFactory;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            var message = update.getMessage();
            var newMessage = responseMessageBuilder(message);
            try {
                sendApiMethod(newMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private SendMessage responseMessageBuilder(Message message) {
        Command command = commandFactory.getCommand(message.getText());
        return SendMessage.builder()
                .chatId(message.getChatId())
                .text(command.respMessage(message))
                .build();
    }

    @Override
    public String getBotUsername() {
        return botName;
    }
}

