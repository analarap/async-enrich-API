package com.desafiotres.compass.config;

import com.desafiotres.compass.decoder.PostNotFoundExceptionDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public ErrorDecoder errorDecoder() {
        return new PostNotFoundExceptionDecoder();
    }
}