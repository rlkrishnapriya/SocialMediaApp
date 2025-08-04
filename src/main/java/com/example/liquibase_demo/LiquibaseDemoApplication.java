package com.example.liquibase_demo;

import com.cloudinary.Cloudinary;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class LiquibaseDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiquibaseDemoApplication.class, args);
	}

	@Bean
	public Cloudinary cloudinary() {
		Map<String, String> config = new HashMap<>();
		config.put("cloud_name", "du5ve5hgh");  
		config.put("api_key", "846162191436962");     
		config.put("api_secret", "Lqrh4U-5QHYbp5AcPuOL-KVfIUI");
		return new Cloudinary(config);
	}
}