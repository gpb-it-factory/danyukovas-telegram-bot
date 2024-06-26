package com.example.gpb.handlers;

import com.example.gpb.gateways.MiddleServiceAccountGateway;
import com.example.gpb.models.CreateAccountRequestV2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.ResourceAccessException;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAccountsCommandTest {

    @Mock
    MiddleServiceAccountGateway middleServiceAccountGateway;

    @InjectMocks
    private GetAccountsCommand command;

    private Message message;

    @BeforeEach
    public void setUp() {
        message = new Message();
        Chat chat = new Chat();
        chat.setId(1L);
        chat.setUserName("Test");
        message.setChat(chat);
    }

    @Test
    public void whenSuccessGetAccountsTest() {

        when(middleServiceAccountGateway.getAllAccounts(null, 1L)).thenReturn("Список аккаунтов:");

        String res = command.respMessage(message);
        String exp = "Список аккаунтов:";

        assertEquals(exp, res);
    }

    @Test
    public void whenExceptionGetAccountsTest() {

        when(middleServiceAccountGateway.getAllAccounts(null, 1L)).thenThrow(ResourceAccessException.class);

        String res = command.respMessage(message);
        String exp = """
                К сожалению, Middle-service сейчас недоступен, нам очень жаль, попробуйте позже. Вот вам зайчик:\s
                (\\(\\
                ( -.-)
                o_(")(")""";

        assertEquals(exp, res);
    }

}