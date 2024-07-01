package com.example.gpb.handlers;

import com.example.gpb.exceptions.ResourceAccessExceptionHandler;
import com.example.gpb.gateways.MiddleServiceTransferGateway;
import com.example.gpb.models.CreateTransferRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class TransferCommand implements Command {

    private final String TRANSFER_URI;
    private final MiddleServiceTransferGateway transferGateway;

    private static final String NUM_REGEX = "(?:[1-9]\\d*|0)(?:\\.\\d*[1-9])?";

    @Autowired
    public TransferCommand(@Value("${user.transfers-uri}") String TRANSFER_URI, MiddleServiceTransferGateway transferGateway) {
        this.TRANSFER_URI = TRANSFER_URI;
        this.transferGateway = transferGateway;
    }

    @Override
    public String getCommandName() {
        return "/transfer";
    }

    @Override
    public String respMessage(Message message) {
        List<String> splittedText = Arrays.asList(message.getText().split(" "));
        if (splittedText.size() != 3) {
            log.info("Пользователь: '{}' ввел неправильное количество аргументов: '{}' для запроса на перевод.", message.getChatId(), message.getText());
            return "Введено неверное количество аргументов для выполнения перевода.";
        }
        String toAccount = splittedText.get(1);
        String amount = splittedText.get(2);
        if (amount.matches(NUM_REGEX)) {
            var transfer = new CreateTransferRequest(String.valueOf(message.getFrom().getId()), toAccount, new BigDecimal(amount));
            try {
                return transferGateway.postTransferRequest(TRANSFER_URI, transfer);
            } catch (ResourceAccessException e) {
                return new ResourceAccessExceptionHandler().handlerException();
            }
        } else {
            log.info("Пользователь: '{}' ввел неудовлетворительные данные : '{}'.", message.getChatId(), message.getText());
            return "Пожалуйста, введите корректный username пользователя и сумму перевода.";
        }
    }
}



