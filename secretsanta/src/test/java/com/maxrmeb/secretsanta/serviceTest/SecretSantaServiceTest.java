package com.maxrmeb.secretsanta.serviceTest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@AutoConfigureMockMvc
public class SecretSantaServiceTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void assignSecretSantasTest() throws Exception {
        List<Personne> people = Arrays.asList(new Personne("Aloce"), new Personne("Bob"), new Personne("Charlie"));
        Map<Personne, Personne> assignments = new HashMap<>();
        assignments.put(new Personne("Aloce"), new Personne("Bob"));
        assignments.put(new Personne("Bob"), new Personne("Charlie"));
        assignments.put(new Personne("Charlie"), new Personne("Aloce"));

        Mockito.when(secretSantaService.assignSecretSantas(people)).thenReturn(assignments);
    }

    public void sendSMSTest() throws Exception {
        // TODO MRE
    }  

    public void sendEmailTest() throws Exception {
        // TODO MRE
        // utiliser MailTrap pour tester sans vrai SMTP
    }

}
