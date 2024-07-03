package com.example.gpb.handlers;

import com.example.gpb.exceptions.ResourceAccessExceptionHandler;
import com.example.gpb.gateways.MiddleServiceAccountGateway;
import com.example.gpb.models.CreateAccountRequestV2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
@Component
public class RegisterAccountCommand implements Command {

    private final String ACCOUNT_REGISTER_URI;
    private final MiddleServiceAccountGateway middleAccountGateway;

    @Autowired
    public RegisterAccountCommand(@Value("${user.register-account-uri}") String userRegisterUri,
                                  MiddleServiceAccountGateway middleAccountGateway) {
        this.middleAccountGateway = middleAccountGateway;
        this.ACCOUNT_REGISTER_URI = userRegisterUri;
    }

    @Override
    public String getCommandName() {
        return "/regAccount";
    }

    @Override
    public String respMessage(Message message) {
        log.info("Исполнение команды /regAccount.");
        var accountRequest = new CreateAccountRequestV2("Акционный");
        try {
            return middleAccountGateway.postRegisterAccount(ACCOUNT_REGISTER_URI, accountRequest, message.getChatId());
        } catch (ResourceAccessException e) {
            return new ResourceAccessExceptionHandler().handlerException(message);
        }
    }
}
