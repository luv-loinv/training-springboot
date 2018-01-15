package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class TrainingSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingSpringbootApplication.class, args);
	}
}
