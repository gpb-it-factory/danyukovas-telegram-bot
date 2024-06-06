package com.example.gpb.config;


import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Configuration
public class RestTemplateConfig {

    private static final int CON_TIMEOUT = (int) TimeUnit.SECONDS.toMillis(3);
    private static final int REQ_TIMEOUT = (int) TimeUnit.SECONDS.toMillis(30);

    public HttpClientBuilder builder() {
        return HttpClients.custom()
                .setMaxConnPerRoute(20)
                .setMaxConnTotal(100);
    }

    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(CON_TIMEOUT);
        factory.setConnectionRequestTimeout(REQ_TIMEOUT);
        factory.setHttpClient(builder().build());
        return new RestTemplate(factory);
    }
}
