package com.example.gpb.gateways;

import com.example.gpb.models.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class MiddleServiceUserGateway {

    private final RestClient restClient;

    @Autowired
    public MiddleServiceUserGateway(RestClient restClient) {
        this.restClient = restClient;
    }

    public String postRegisterUser(String uri, Message message) {
        var user = new CreateUserRequest(message.getChatId(), message.getChat().getFirstName());
        ResponseEntity<String> response = restClient.post()
                .uri(uri)
                .body(user)
                .retrieve().toEntity(String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return "Пользователь успешно зарегистрирован";
    }
}
