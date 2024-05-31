package com.example.gpb.gateways;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class MiddleServiceUserGateway {

    private final RestTemplate restTemplate = new RestTemplate();

    public String postRegisterUser(String uri, Message message) {
        ResponseEntity<?> response =
                restTemplate.postForEntity(uri, message, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return (String) response.getBody();
        }
        return "Пользователь успешно зарегистрирован";
    }
}
