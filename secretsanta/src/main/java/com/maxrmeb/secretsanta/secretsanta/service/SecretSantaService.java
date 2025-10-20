package com.maxrmeb.secretsanta.secretsanta.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.maxrmeb.secretsanta.secretsanta.modele.MailMessage;
import com.maxrmeb.secretsanta.secretsanta.modele.Personne;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SecretSantaService {


	@Autowired
	private JavaMailSender javaMailSender;

    public boolean sendEmail(List<Personne> peoples, MailMessage mailMessage) {
        // On assigne les secret santas
        Map<Personne,Personne> mapSecretSanta = assignSecretSantas(peoples);

		log.info("Secret Sata service : Envoie par mail demandé");
        boolean isOK = mapSecretSanta.keySet().stream().allMatch(t -> t.getEmail() != null);
        if (isOK){

            for(int i =0; i < mapSecretSanta.size(); i++) {
                SimpleMailMessage msg = new SimpleMailMessage();
                
                // On Récupère l'expéditeur et le destinataire
                Personne sender = (Personne) mapSecretSanta.keySet().toArray()[i];
                Personne receiver = mapSecretSanta.get(sender);

                // Construction du message
                msg.setTo( sender.getEmail()) ; 
                msg.setSubject( "Secret santa is coming !!" );
                msg.setText( "Votre secret santa sera " + receiver.getNom() + ". N'oubliez pas de lui faire un cadeau !" );

                // Envoyer l'email à sender.getEmail() avec le sujet et le corps personnalisé
                log.info("Envoi de l'email à " + sender.getEmail() + " pour le destinataire " + receiver.getNom());
            }

            return true; // Retourne true si l'email a été envoyé avec succès
        } else {
            log.error("Secret Sata service : Envoie par mail impossible, email manquant");
            return false;
        }

    }
    

    public boolean sendSMS(List<Personne> peoples, String phoneNumber, String message) {
        // On assigne les secret santas
        Map<Personne,Personne> mapSecretSanta = assignSecretSantas(peoples);

        log.info("Secret Sata service : Envoie par sms demandé");
        boolean isOK = mapSecretSanta.keySet().stream().allMatch(t -> t.getTelephone() != null);
        if (isOK){
            for(int i =0; i < mapSecretSanta.size(); i++) {
                // objet SMS ?

                // On Récupère l'expéditeur et le destinataire
                Personne sender = (Personne) mapSecretSanta.keySet().toArray()[i];
                Personne receiver = mapSecretSanta.get(sender);

                // Construction du message


                // Envoyer l'email à sender.getEmail() avec le sujet et le corps personnalisé
                log.info("Envoi du sms à " + sender.getTelephone() + " pour le destinataire " + receiver.getNom());
            }
            // Implémentation de l'envoi d'email
            return true; // Retourne true si le SMS a été envoyé avec succès
        } else {
            log.error("Secret Sata service : Envoie par mail impossible, email manquant");
            return false;
        }
    }

    // MRE : Passer méthode en privée une fois testé
    public Map<Personne, Personne> assignSecretSantas( List<Personne> peoples ){
        HashMap<Personne, Personne> assignments = new HashMap<>();

        /*
         * On assigne une personne différente à chacun des participants
         */

        peoples.stream().forEach(person -> {
            Personne assigned;
            do {
                int randomIndex = (int) (ThreadLocalRandom.current().nextDouble() * peoples.size());
                assigned = peoples.get(randomIndex);
            } while (assigned.equals(person) || assignments.containsValue(assigned));
            assignments.put(person, assigned);
        });
        return assignments; 
    }
}
