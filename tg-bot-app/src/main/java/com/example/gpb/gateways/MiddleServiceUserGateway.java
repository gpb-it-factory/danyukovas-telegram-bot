package com.example.gpb.gateways;

import com.example.gpb.models.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class MiddleServiceUserGateway {

    private final RestClient restClient;

    @Autowired
    public MiddleServiceUserGateway(RestClient restClient) {
        this.restClient = restClient;
    }

    public String postRegisterUser(String uri, CreateUserRequest userRequest) {
        ResponseEntity<String> response = restClient.post()
                .uri(uri)
                .body(userRequest)
                .retrieve().toEntity(String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return "Пользователь успешно зарегистрирован";
    }
}
