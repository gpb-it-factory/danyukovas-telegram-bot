package com.example.gpb.handlers;

import com.example.gpb.exceptions.ResourceAccessExceptionHandler;
import com.example.gpb.gateways.MiddleServiceAccountGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
@Component
public class GetAccountsCommand implements Command {
    private final String GET_ACCOUNTS_URI;
    private final MiddleServiceAccountGateway middleAccountGateway;

    @Autowired
    public GetAccountsCommand(@Value("${user.get-accounts-uri}") String userRegisterUri,
                                  MiddleServiceAccountGateway middleAccountGateway) {
        this.middleAccountGateway = middleAccountGateway;
        this.GET_ACCOUNTS_URI = userRegisterUri;
    }

    @Override
    public String getCommandName() {
        return "/getAccounts";
    }

    @Override
    public String respMessage(Message message) {
        log.info("Исполнение команды /getAccounts.");
        try {
            return middleAccountGateway.getAllAccounts(GET_ACCOUNTS_URI, message.getChatId());
        } catch (ResourceAccessException e) {
            return new ResourceAccessExceptionHandler().handlerException(message);
        }
    }
}
