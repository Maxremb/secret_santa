package controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxrmeb.secretsanta.secretsanta.service.SecretSantaService;


@RequestMapping(path = "/api")
@RestController
//@Slf4j
//@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
public class SecretSantaController {

    @Autowired
    private SecretSantaService secretSantaService;

    @PostMapping("/assign")
    public Map<String, String> getMySecretSantaResult(List<String> people) {
        return secretSantaService.assignSecretSantas(people);
    }

}
