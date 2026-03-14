package com.r360;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@OpenAPIDefinition
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}