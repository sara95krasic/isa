package com.bioskopi.isa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class BioskopiApplication {

	
	
	public static void main(String[] args) {
		SpringApplication.run(BioskopiApplication.class, args);
	}
}
