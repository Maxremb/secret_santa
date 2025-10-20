
package com.maxrmeb.secretsanta.secretsanta.controllerTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxrmeb.secretsanta.secretsanta.modele.Personne;
import com.maxrmeb.secretsanta.secretsanta.service.SecretSantaService;

//@SpringBootTest
@AutoConfigureMockMvc
public class SecretSantaControllerTets {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SecretSantaService secretSantaService;

    @Test
    public void getMySecretSantaResultTest() throws Exception {
        List<Personne> people = Arrays.asList(new Personne("Aloce"), new Personne("Bob"), new Personne("Charlie"));
        Map<Personne, Personne> assignments = new HashMap<>();
        assignments.put(new Personne("Aloce"), new Personne("Bob"));
        assignments.put(new Personne("Bob"), new Personne("Charlie"));
        assignments.put(new Personne("Charlie"), new Personne("Aloce"));
        Mockito.when(secretSantaService.assignSecretSantas(people)).thenReturn(assignments);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/assign")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(people)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Alice").value("Bob"))
                .andExpect(jsonPath("$.Bob").value("Charlie"))
                .andExpect(jsonPath("$.Charlie").value("Alice"));
    }
}

