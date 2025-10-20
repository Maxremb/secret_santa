package com.maxremb.api.secretsanta.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxremb.api.secretsanta.modele.MailMessage;
import com.maxremb.api.secretsanta.modele.Personne;
import com.maxremb.api.secretsanta.service.SecretSantaService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RequestMapping(path = "/api")
@RestController
//@Slf4j
//@CrossOrigin(allowCredentials = "true", origins = "http://glorious-guide-95r4r7rpvq927j9v-9000")
public class SecretSantaController {

    @Autowired
    private SecretSantaService secretSantaService;

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Assign ok"),
        @ApiResponse(responseCode = "404", description = "Assignement not ok")
    })
    @PostMapping("/assign")
    public Map<Personne, Personne> getMySecretSantaResult(@RequestBody List<Personne> people) {
        Map<Personne, Personne> result = secretSantaService.assignSecretSantas(people);
        return result;
    }

    @PostMapping("/email")
    public boolean sendbyEmail(@RequestBody List<Personne> people, @RequestBody MailMessage mailMessage) {
        //envoie mail
        boolean result = secretSantaService.sendEmail( people, mailMessage);
        return result;
    }

    @PostMapping("/sms")
    public String sendBySMS(@RequestBody String entity) {
        //envoie SMS ?
        return entity;
    }
    
    

}
