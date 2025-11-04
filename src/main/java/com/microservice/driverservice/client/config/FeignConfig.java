package com.microservice.driverservice.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.httpclient.ApacheHttpClient;

@Configuration
public class FeignConfig {

    @Bean
    public ApacheHttpClient apacheHttpClient() {
        return new ApacheHttpClient();
    }
}
