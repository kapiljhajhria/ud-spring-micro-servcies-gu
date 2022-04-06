package com.jhajhria.jacksonexample;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JacksonExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(JacksonExampleApplication.class, args);
	}

	@Bean
	public ObjectMapper objectMapper() {
		return JsonMapper.builder()
				.addModule(new JavaTimeModule())
//				.propertyNamingStrategy(
//						PropertyNamingStrategy.SNAKE_CASE
//				)
				.build();
	}

}