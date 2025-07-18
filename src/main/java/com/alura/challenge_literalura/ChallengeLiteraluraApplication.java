package com.alura.challenge_literalura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alura.challenge_literalura.view.Menu;

@SpringBootApplication
public class ChallengeLiteraluraApplication implements CommandLineRunner {

	@Autowired
	private Menu menu;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			menu.mostrarMenu();

		} catch (Exception e) {
			System.out.println("Ha ocurrido un error inesperado: " + e.getMessage());
		}
	}

}
