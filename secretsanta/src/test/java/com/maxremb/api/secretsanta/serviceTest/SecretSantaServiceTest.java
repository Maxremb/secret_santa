package com.maxremb.api.secretsanta.serviceTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.maxremb.api.secretsanta.modele.Personne;
import com.maxremb.api.secretsanta.service.SecretSantaService;


@SpringBootTest
public class SecretSantaServiceTest {

    @Autowired
    private SecretSantaService service;

    @Test
    public void assignSecretSantasTest() throws Exception {
        Personne alice = new Personne("ALice");
        Personne bob = new Personne("Bob");
        Personne charlie = new Personne("Charlie");
        List<Personne> people = Arrays.asList(alice, bob, charlie);

        Map<Personne, Personne> result = service.assignSecretSantas(people);

        Assert.isTrue(result.size() == 3, "Le nombre d'assignations doit être égal au nombre de personnes");
        Assert.isTrue(result.keySet().stream().allMatch(k -> !k.equals(result.get(k))), "Personne ne doit s'assigner lui-même");
        Assert.isTrue(result.get(alice) != null, "Alice doit avoir un Secret Santa assigné");
        Assert.isTrue(result.get(bob) != null, "Bob doit avoir un Secret Santa assigné");
        Assert.isTrue(result.get(charlie) != null, "Charlie doit avoir un Secret Santa assigné");
    }

    public void sendSMSTest() throws Exception {
        // TODO MRE
    }  

    public void sendEmailTest() throws Exception {
        // TODO MRE
        // utiliser MailTrap pour tester sans vrai SMTP
    }

}
