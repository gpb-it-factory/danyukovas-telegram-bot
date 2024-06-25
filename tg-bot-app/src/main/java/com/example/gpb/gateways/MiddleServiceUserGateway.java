package com.example.gpb.gateways;

import com.example.gpb.models.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

@Component
public class MiddleServiceUserGateway {

    private final RestClient restClient;

    @Autowired
    public MiddleServiceUserGateway(RestClient restClient) {
        this.restClient = restClient;
    }

    public String postRegisterUser(String uri, CreateUserRequest userRequest) throws ResourceAccessException {
        return restClient.post()
                .uri(uri)
                .body(userRequest)
                .retrieve()
                .body(String.class);
    }
}
