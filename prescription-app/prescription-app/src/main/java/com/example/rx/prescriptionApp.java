package com.example.rx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class prescriptionApp {

	public static void main(String[] args) {

		SpringApplication.run(prescriptionApp.class, args);


			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String rawPassword = "shakil";
			String encodedPassword = encoder.encode(rawPassword);
			System.out.println(encodedPassword);
	}

}
