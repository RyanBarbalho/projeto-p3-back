package com.projeto.interact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InteractApplication {

	public static void main(String[] args) {
		SpringApplication.run(InteractApplication.class, args);
	}
	//para poder testar o codigo com h2 o spring security estava pedindo
	//pra se registrar, com isso da pra acessar o h2-console

}
