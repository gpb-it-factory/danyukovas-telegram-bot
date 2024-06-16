package com.example.gpb.handlers;

import com.example.gpb.gateways.MiddleServiceUserGateway;
import com.example.gpb.models.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class RegisterCommand implements Command {

    private final String URI;
    private final MiddleServiceUserGateway middleGateway;

    @Autowired
    public RegisterCommand(@Value("${register-uri}") String userRegisterUri, MiddleServiceUserGateway middleUserGateway) {
        this.middleGateway = middleUserGateway;
        this.URI = userRegisterUri;
    }

    @Override
    public String respMessage(Message message) {
        var userRequest = new CreateUserRequest(message.getChatId(), message.getChat().getUserName());
        return middleGateway.postRegisterUser(URI, userRequest);
    }
}
