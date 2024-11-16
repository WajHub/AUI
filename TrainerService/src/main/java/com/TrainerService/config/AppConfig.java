package com.TrainerService.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    @Qualifier("pokemon")
    public RestTemplate restTemplate(@Value("${world.pokemon.url}") String baseUrl) {
        return new RestTemplateBuilder()
                .rootUri(baseUrl)
                .build();
    }
}
