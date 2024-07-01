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
class RegisterAccountCommandTest {

    @Mock
    MiddleServiceAccountGateway middleServiceAccountGateway;

    @InjectMocks
    private RegisterAccountCommand command;

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
    public void whenSuccessPostRegisterAccountTest() {

        var accRequest = new CreateAccountRequestV2("Акционный");
        when(middleServiceAccountGateway.postRegisterAccount(null, accRequest, 1L)).thenReturn("Аккаунт создан.");

        String res = command.respMessage(message);
        String exp = "Аккаунт создан.";

        assertEquals(exp, res);
    }

    @Test
    public void whenExceptionPostRegisterAccountTest() {

        var accRequest = new CreateAccountRequestV2("Акционный");
        when(middleServiceAccountGateway.postRegisterAccount(null, accRequest, 1L)).thenThrow(ResourceAccessException.class);

        String res = command.respMessage(message);
        String exp = """
                К сожалению, Middle-service сейчас недоступен, нам очень жаль, попробуйте позже. Вот вам зайчик:\s
                (\\(\\
                ( -.-)
                o_(")(")""";

        assertEquals(exp, res);
    }
}
