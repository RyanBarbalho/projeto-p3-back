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



	@Bean
	CommandLineRunner run(UserRepository userRepository){
		//usuarios de teste
		UserModel u1 = new UserModel("User@gmail.com", "boglus", "$2a$10$0uBMt4CaJJayMk.tW7TKfuqLh/dMO1lHbjRBXpAvPxC/jcldVXbR.", UserRole.valueOf("USER"));
		UserModel u2 = new UserModel("admin", "admin", "$2a$10$0uBMt4CaJJayMk.tW7TKfuqLh/dMO1lHbjRBXpAvPxC/jcldVXbR.", UserRole.valueOf("ADMIN"));
		//inserir no banco de dados

		return args -> {
			userRepository.save(u1);
			userRepository.save(u2);
		};
	}
}
