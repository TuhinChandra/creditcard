package com.sapient.creditcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class CreditcardManagementApplication {

	public static void main(final String[] args) {
		SpringApplication.run(CreditcardManagementApplication.class, args);
	}
}
