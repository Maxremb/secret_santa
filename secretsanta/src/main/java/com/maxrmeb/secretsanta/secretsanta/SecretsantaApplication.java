package com.maxrmeb.secretsanta.secretsanta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SecretsantaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecretsantaApplication.class, args);

		/*
		 * RAF  :
		 * Crée modeèle personne (nom/prénom/email,telephone ?)
		 * 
		 * Rajouter dépendance spring mail et implémenter solution envoie email
		 * Recherche si méthode similaire par téléphone (SMS)
		 */
	}

}
