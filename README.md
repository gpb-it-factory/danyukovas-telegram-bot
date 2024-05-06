# Приложение разработанное в рамках [GPB IT Factory Backend 2024](https://gpb.fut.ru/itfactory/backend) будет содержать:

---
1. Frontend часть, которая будет реализована с помощью telegram-bot'а на java.
2. Middle часть, которая будет принимать, обрабатывать и передавать в Backend HTTP запросы.
3. Backend часть, которая будет хранить данные, 
обрабатывать транзакции(выступать в роли автоматизированной банковской системы).

## Принцип взаимодействия между компонентами:
Реализованно с помощью PlanUML:
```
@startUML
actor Client

Client -> TelegramBot : HTTP-запрос
    activate TelegramBot 
        TelegramBot -> BankService : HTTP-запрос
        activate BankService
            BankService -> ABS : запрос
            activate ABS
                ABS -->  BankService : Данные с БД
            deactivate ABS
            BankService --> TelegramBot : HTTP-ответ с данными
        deactivate BankService
        TelegramBot --> Client : HTTP-ответ с данными
    deactivate TelegramBot
@endUML
```
    

