package com.Farma.Farmacia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class FarmaciaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmaciaApplication.class, args);
	}

}