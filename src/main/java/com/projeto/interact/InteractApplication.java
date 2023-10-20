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
		UserModel u1 = new UserModel("User@gmail.com", "boglus", "123456", UserRole.valueOf("USER"));
		UserModel u2 = new UserModel("brendanfraser@gmail.com", "brendan", "123456", UserRole.valueOf("ADMIN"));
		//inserir no banco de dados

		return args -> {
			userRepository.save(u1);
			userRepository.save(u2);
		};
	}
}
