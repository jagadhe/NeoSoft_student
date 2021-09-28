package com.jagadeesh.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
//@EnableWebSecurity
public class StudentFormApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentFormApplication.class, args);
	}

}
