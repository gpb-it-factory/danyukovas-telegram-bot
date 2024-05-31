package com.example.gpb.factories;

import com.example.gpb.gateways.MiddleServiceUserGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class ResponseFactory {

    private static final String URI = "http://localhost:8080/api/users";
  
    private final MiddleServiceUserGateway middleGateway;

    @Autowired
    public ResponseFactory(MiddleServiceUserGateway middleGateway) {
        this.middleGateway = middleGateway;
    }
    public String respMessage(Message message) {
        return switch (message.getText()) {
            case "/start" -> "Hello " + message.getChat().getFirstName();
            case "/ping" -> "pong";
            case "/register" ->
                    middleGateway.postRegisterUser(URI, message);
            default -> "WRONG MESSAGE BUDDY";
        };
    }
}
