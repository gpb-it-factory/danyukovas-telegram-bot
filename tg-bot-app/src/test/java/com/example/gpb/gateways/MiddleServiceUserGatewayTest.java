package com.example.gpb.gateways;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MiddleServiceUserGatewayTest {

    @Mock
    RestTemplate restTemplate;
    @Mock
    Message message;
    static String uri;

    @InjectMocks
    MiddleServiceUserGateway gateway;

    @Test
    void whenPostRegisterUserBadScenarioTest() throws IOException {

        String data = Files.readString(Paths.get("src/test/test-data/resp-ok.json"));
        when(restTemplate.postForEntity(uri, message, String.class)).thenReturn(ResponseEntity.ok(data));

        String res = gateway.postRegisterUser(uri, message);
        String exp = data;

        assertEquals(exp, res);
    }

    @Test
    void whenPostRegisterUserGoodScenarioTest() {

        when(restTemplate.postForEntity(uri, message, String.class)).thenReturn(ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build());

        String res = gateway.postRegisterUser(uri, message);
        String exp = "Пользователь успешно зарегистрирован";

        assertEquals(exp, res);
    }
}