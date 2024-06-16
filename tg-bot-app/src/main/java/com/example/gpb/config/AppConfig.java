package com.example.gpb.config;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
@PropertySource("classpath:http-client-config.yml")
@PropertySource("classpath:uri-vault-config.yml")
public class AppConfig {}