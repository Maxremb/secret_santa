package com.maxremb.api.secretsanta.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.maxremb.api.secretsanta.modele.MailMessage;
import com.maxremb.api.secretsanta.modele.Personne;

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
                if (mailMessage.getSujet() == null || mailMessage.getSujet().isEmpty()){
                    msg.setSubject( "Secret santa is coming !!" );
                } else {
                    msg.setSubject( mailMessage.getSujet() );
                }
                if (mailMessage.getCorps() == null || mailMessage.getCorps().isEmpty()){
                    msg.setText( "Votre secret santa sera " + receiver.getNom() + ". N'oubliez pas de lui faire un cadeau !" );
                } else {
                    msg.setText(mailMessage.getCorps());
                    // TODO MRE : faire du vrai templating avec remplacement des variables
                }
                javaMailSender.send(msg);

                // Envoyer l'email à sender.getEmail() avec le sujet et le corps personnalisé
                log.debug("Envoi de l'email à " + sender.getEmail() + " pour le destinataire " + receiver.getNom());
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
    
    /*
    * On assigne une personne différente à chacun des participants
    */
    public Map<Personne, Personne> assignSecretSantas( List<Personne> peoples ){
        HashMap<Personne, Personne> assignments = new HashMap<>();
        
        // On rend la liste aléatoire
        Collections.shuffle(peoples);
        
        // On assigne le donneur et receveur
        for(int i = 0; i < peoples.size(); i++) {
            Personne giver = peoples.get(i);
            Personne receiver = peoples.get((i + 1) % peoples.size());
            assignments.put(giver, receiver);
        }

        return assignments; 
    }
}
