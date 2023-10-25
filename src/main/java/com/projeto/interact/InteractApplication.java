package com.projeto.interact;

import com.projeto.interact.domain.user.UserModel;
import com.projeto.interact.domain.user.UserRole;
import com.projeto.interact.respository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InteractApplication {

	public static void main(String[] args) {
		SpringApplication.run(InteractApplication.class, args);
	}


}
