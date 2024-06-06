package com.example.gpb.factories;

import com.example.gpb.gateways.MiddleServiceUserGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.objects.Message;

import static org.junit.jupiter.api.Assertions.*;

public class ResponseFactoryTest {

    private ResponseFactory responseFactory;

    @BeforeEach
    public void setUp() {
        RestTemplate restTemplate = new RestTemplate();
        MiddleServiceUserGateway middleServiceUserGateway = new MiddleServiceUserGateway(restTemplate);
        responseFactory = new ResponseFactory(middleServiceUserGateway);
    }

    @Test
    public void whenCaseResultTest() {

        Message message = new Message();
        message.setText("/ping");

        String res = responseFactory.respMessage(message);
        String exp = "pong";

        assertEquals(exp, res);
    }

    @Test
    public void whenDefaultResultTest() {

        Message message = new Message();
        message.setText("/mock");

        String res = responseFactory.respMessage(message);
        String exp = "WRONG MESSAGE BUDDY";

        assertEquals(exp, res);
    }

    @Test
    public void whenHasNoTextResultTest() {

        Message message = new Message();

        String res = responseFactory.respMessage(message);
        String exp = "Application can take only text!";

        assertEquals(exp, res);
    }
}