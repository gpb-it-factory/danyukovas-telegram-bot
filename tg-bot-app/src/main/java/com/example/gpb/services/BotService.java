package com.example.gpb.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class BotService extends TelegramLongPollingBot {

    private final String botName;

    public BotService(@Value("${bot.token}") String token, @Value("${bot.name}") String botName) {
        super(token);
        this.botName = botName;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            var id = update.getMessage().getChatId();
            var message = update.getMessage().getText();
            var newMessage = new SendMessage();
            newMessage.setChatId(id);
            if (message.equals("/ping")) {
                newMessage.setText("pong");
                try {
                    sendApiMethod(newMessage);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else {
                newMessage.setText("WRONG MESSAGE BUDDY");
                try {
                    sendApiMethod(newMessage);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            var newMessage = new SendMessage();
            newMessage.setText("SOME WORDS PLEASE");
            try {
                sendApiMethod(newMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botName;

    }
}

