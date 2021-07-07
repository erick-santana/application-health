package com.imalive.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ImAliveApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImAliveApplication.class, args);
	}
}
