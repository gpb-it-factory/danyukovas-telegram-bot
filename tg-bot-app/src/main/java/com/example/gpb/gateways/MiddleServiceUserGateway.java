package com.example.gpb.gateways;

import com.example.gpb.exceptions.ResourceAccessExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.IOException;

@Component
public class MiddleServiceUserGateway {

    private final RestTemplate restTemplate;

    @Autowired
    public MiddleServiceUserGateway(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String postRegisterUser(String uri, Message message) {
        ResponseEntity<?> response;
        try {
            response = restTemplate.postForEntity(uri, message.getChat().getFirstName(), String.class);
        } catch (ResourceAccessException e) {
            return new ResourceAccessExceptionHandler().handlerException();
        }
        if (response.getStatusCode() == HttpStatus.OK) {
            return (String) response.getBody();
        }
        return "Пользователь успешно зарегистрирован";
    }
}
