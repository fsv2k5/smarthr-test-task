package com.smarthr.employeedb.config;

import com.smarthr.employeedb.exception.HttpResponseErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new HttpResponseErrorHandler());
        return restTemplate;
    }
}
