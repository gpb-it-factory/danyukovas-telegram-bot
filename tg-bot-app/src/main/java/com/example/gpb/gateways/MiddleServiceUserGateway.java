package com.example.gpb.gateways;

import com.example.gpb.models.CreateUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
public class MiddleServiceUserGateway {

    private final RestClient restClient;

    @Autowired
    public MiddleServiceUserGateway(RestClient restClient) {
        this.restClient = restClient;
    }

    public String postRegisterUser(String uri, CreateUserRequest userRequest) throws ResourceAccessException {
        log.info("Запрос в middle сервис для регистрации аккаунта.");
        return restClient.post()
                .uri(uri)
                .body(userRequest)
                .retrieve()
                .body(String.class);
    }
}
