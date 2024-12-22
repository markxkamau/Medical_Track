package com.example.MedicalWebInput;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableScheduling
public class MedicalWebInputApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalWebInputApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:5173") // Allow specific origins
						.allowedMethods("GET", "POST", "PUT", "DELETE") // Allowed HTTP methods
						.allowedHeaders("*") // Allow all headers
						.allowCredentials(true);
			}
		};
	}

}
