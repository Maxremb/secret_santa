package com.maxremb.api.secretsanta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
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
