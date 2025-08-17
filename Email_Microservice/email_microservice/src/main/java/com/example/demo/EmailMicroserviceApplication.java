package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
	    "controller","dto","service","service.impl"
	})
public class EmailMicroserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmailMicroserviceApplication.class, args);
    }
}
