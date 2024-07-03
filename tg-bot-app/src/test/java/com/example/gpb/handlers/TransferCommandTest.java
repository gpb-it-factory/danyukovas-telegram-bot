package com.example.gpb.handlers;

import com.example.gpb.gateways.MiddleServiceTransferGateway;
import com.example.gpb.models.CreateTransferRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.ResourceAccessException;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransferCommandTest {

    @Mock
    private MiddleServiceTransferGateway transferGateway;

    @InjectMocks
    private TransferCommand command;

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
    public void whenFewerArgumentsTest() {

        message.setText("/transfer");

        String res = command.respMessage(message);
        String exp = "Введено неверное количество аргументов для выполнения перевода.";

        assertEquals(exp, res);
    }

    @Test
    public void whenMoreArgumentsTest() {

        message.setText("/transfer 123 123 123 123 1321 123 123 1231 31132");

        String res = command.respMessage(message);
        String exp = "Введено неверное количество аргументов для выполнения перевода.";

        assertEquals(exp, res);
    }

    @Test
    public void whenWrongTypeOfAmountArgumentTest() {

        message.setText("/transfer test test");

        String res = command.respMessage(message);
        String exp =  "Пожалуйста, введите корректный username пользователя и сумму перевода.";

        assertEquals(exp, res);
    }

    @Test
    public void whenBothArgumentsWrongTest() {

        message.setText("/transfer 1243 test");

        String res = command.respMessage(message);
        String exp =  "Пожалуйста, введите корректный username пользователя и сумму перевода.";

        assertEquals(exp, res);
    }

    @Test
    public void whenSuccessPostTransferRequestTest() {

        message.setFrom(new User(1L, "test", false));
        message.setText("/transfer user 1234.5");
        var transfer = new CreateTransferRequest("1", "user", new BigDecimal("1234.5"));
        when(transferGateway.postTransferRequest(null, transfer)).thenReturn("Трансфер прошел успешно");

        String res = command.respMessage(message);
        String exp = "Трансфер прошел успешно";

        assertEquals(exp, res);
    }

    @Test
    public void whenExceptionPostTransferRequestTest() {

        message.setFrom(new User(1L, "test", false));
        message.setText("/transfer user 1234.5");
        var transfer = new CreateTransferRequest("1", "user", new BigDecimal("1234.5"));
        when(transferGateway.postTransferRequest(null, transfer)).thenThrow(ResourceAccessException.class);

        String res = command.respMessage(message);
        String exp = """
                К сожалению, Middle-service сейчас недоступен, нам очень жаль, попробуйте позже. Вот вам зайчик:\s
                (\\(\\
                ( -.-)
                o_(")(")""";

        assertEquals(exp, res);
    }

}