package com.example.gpb.handlers;

import com.example.gpb.exceptions.ResourceAccessExceptionHandler;
import com.example.gpb.gateways.MiddleServiceUserGateway;
import com.example.gpb.models.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class RegisterCommand implements Command {

    private final String USER_REGISTER_URI;
    private final MiddleServiceUserGateway middleGateway;

    @Autowired
    public RegisterCommand(@Value("${user.register-uri}") String userRegisterUri, MiddleServiceUserGateway middleUserGateway) {
        this.middleGateway = middleUserGateway;
        this.USER_REGISTER_URI = userRegisterUri;
    }

    @Override
    public String getCommandName() {
        return "/register";
    }

    @Override
    public String respMessage(Message message) {
        var userRequest = new CreateUserRequest(message.getChatId(), message.getChat().getUserName());
        try {
            return middleGateway.postRegisterUser(USER_REGISTER_URI, userRequest);
        } catch (ResourceAccessException e) {
            return new ResourceAccessExceptionHandler().handlerException();
        }
    }
}
