package com.icarlosalbertojr.demokafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icarlosalbertojr.demokafka.model.Book;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/books")
public class BookController {
    
    @Autowired
    private StreamBridge streamBridge;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private void newBook(@RequestBody Book book) throws JsonProcessingException {
        log.info("[BookController] book was received to save: {}", book);
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(book);
        streamBridge.send("newBookinput-in-0", content);
    }

}
