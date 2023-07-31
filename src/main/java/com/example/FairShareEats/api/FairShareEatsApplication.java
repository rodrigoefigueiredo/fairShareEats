package com.example.FairShareEats.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.FairShareEats")	
public class FairShareEatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FairShareEatsApplication.class, args);
	}
}
