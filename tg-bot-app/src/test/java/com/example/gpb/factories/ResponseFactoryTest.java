package com.example.gpb.factories;

import com.example.gpb.gateways.MiddleServiceUserGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.objects.Message;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ResponseFactoryTest {

    @Mock
    Message message;
    @Mock
    MiddleServiceUserGateway middleServiceUserGateway;

    @InjectMocks
    ResponseFactory responseFactory = new ResponseFactory(middleServiceUserGateway);


    @Test
    public void whenCaseTextTest() {

        when(message.hasText()).thenReturn(true);
        when(message.getText()).thenReturn("/ping");

        String res = responseFactory.respMessage(message);
        String exp = "pong";

        assertEquals(exp, res);
    }

    @Test
    public void whenDefaultResultTest() {

        when(message.hasText()).thenReturn(true);
        when(message.getText()).thenReturn("/mock");

        String res = responseFactory.respMessage(message);
        String exp = "WRONG MESSAGE BUDDY";

        assertEquals(exp, res);
    }

    @Test
    public void whenHasNoTextTest() {

        when(message.hasText()).thenReturn(false);

        String res = responseFactory.respMessage(message);
        String exp = "Application can take only text!";

        assertEquals(exp, res);
    }
}