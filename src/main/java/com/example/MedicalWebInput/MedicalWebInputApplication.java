package com.example.MedicalWebInput;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MedicalWebInputApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalWebInputApplication.class, args);
	}

}
