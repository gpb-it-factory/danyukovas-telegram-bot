package com.example.gpb.services;

import com.example.gpb.factories.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class BotService extends TelegramLongPollingBot {

    private final String botName;
    private final ResponseFactory responseFactory;

    @Autowired
    public BotService(@Value("${bot.token}") String token, @Value("${bot.name}") String botName,
                      ResponseFactory responseFactory) {
        super(token);
        this.botName = botName;
        this.responseFactory = responseFactory;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            var newMessage = new SendMessage();
            newMessage.setChatId(update.getMessage().getChatId());
            newMessage.setText(responseFactory.respMessage(update.getMessage()));
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

