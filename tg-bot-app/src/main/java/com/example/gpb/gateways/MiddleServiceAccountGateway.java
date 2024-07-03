package com.example.gpb.gateways;

import com.example.gpb.models.CreateAccountRequestV2;
import com.example.gpb.models.ResponseToFront;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

import java.util.List;

@Slf4j
@Component
public class MiddleServiceAccountGateway {

    private final RestClient restClient;

    @Autowired
    public MiddleServiceAccountGateway(RestClient restClient) {
        this.restClient = restClient;
    }

    public String postRegisterAccount(String uri, CreateAccountRequestV2 accountRequest, long id) throws ResourceAccessException {
        log.info("Запрос в middle сервис для регистрации аккаунта.");
        return restClient.post()
                .uri(uri, id)
                .body(accountRequest)
                .retrieve()
                .body(ResponseToFront.class)
                .answer();
    }

    public String getAllAccounts(String uri, long id) {
        log.info("Запрос в middle сервис для получения всех аккаунтов пользователя.");
        return restClient.get()
                .uri(uri, id)
                .retrieve()
                .body(ResponseToFront.class)
                .answer();
    }
}
