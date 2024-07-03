package com.example.gpb.gateways;

import com.example.gpb.models.CreateTransferRequest;
import com.example.gpb.models.ResponseToFront;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
public class MiddleServiceTransferGateway {

    private final RestClient restClient;

    @Autowired
    public MiddleServiceTransferGateway(RestClient restClient) {
        this.restClient = restClient;
    }

    public String postTransferRequest(String uri, CreateTransferRequest transfer) {
        log.info("Запрос в middle сервис для выполнения перевода денег c аккаунта пользователя {} на аккаунт пользователю {}.", transfer.from(), transfer.to());
        return restClient.post()
                .uri(uri)
                .body(transfer)
                .retrieve()
                .body(ResponseToFront.class)
                .answer();
    }
}
