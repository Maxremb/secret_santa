package com.maxrmeb.secretsanta.secretsanta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.maxrmeb.secretsanta.secretsanta", "controller"})
public class SecretsantaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecretsantaApplication.class, args);

		/*
		 * RAF  :
		 * 
		 * Rajouter dépendance spring mail et implémenter solution envoie email
		 * Recherche si méthode similaire par téléphone (SMS)
		 */
	}

}
