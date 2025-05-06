package com.example.dummyjson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.example.dummyjson.config.WebClientConfig;

@SpringBootApplication
public class DummyJsonClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(DummyJsonClientApplication.class, args);
    }
}
