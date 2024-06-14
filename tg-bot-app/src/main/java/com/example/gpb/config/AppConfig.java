package com.example.gpb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:http-client-config.yml")
@PropertySource("classpath: uri-vault-config.yml")
public class AppConfig {}
