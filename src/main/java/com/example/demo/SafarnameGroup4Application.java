package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("entity")
@EnableJpaRepositories(basePackages = "repository")
@ComponentScan(basePackages = {"controller", "service", "repository", "entity", "dto", "mapper"})

public class SafarnameGroup4Application {

	public static void main(String[] args) {
		SpringApplication.run(SafarnameGroup4Application.class, args);
	}

}
