package com.teddybear.reswiki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ReswikiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReswikiApplication.class, args);
	}

}
