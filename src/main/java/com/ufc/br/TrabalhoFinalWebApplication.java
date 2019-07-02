package com.ufc.br;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TrabalhoFinalWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrabalhoFinalWebApplication.class, args);
		
		//System.out.println(new BCryptPasswordEncoder().encode("admin"));
	}

}
