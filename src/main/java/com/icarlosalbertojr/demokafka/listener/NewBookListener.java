package com.icarlosalbertojr.demokafka.listener;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class NewBookListener {
    
    @Bean
    public Consumer<String> newBookInput() {
        return book -> {
            log.info("New book added: {}", book);              
        };
    }

}
