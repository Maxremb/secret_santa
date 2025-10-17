package com.maxrmeb.secretsanta.secretsanta.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

@Service
public class SecretSantaService {

    public Map<String, String> assignSecretSantas(List<String> people) {
        HashMap<String, String> assignments = new HashMap<>();

        /*
         * On assigne une personne différente à chacun des participants
         */

        people.stream().forEach(person -> {
            String assigned;
            do {
                int randomIndex = (int) (ThreadLocalRandom.current().nextDouble() * people.size());
                assigned = people.get(randomIndex);
            } while (assigned.equals(person) || assignments.containsValue(assigned));
            assignments.put(person, assigned);
        });
        return assignments;
    }

}
