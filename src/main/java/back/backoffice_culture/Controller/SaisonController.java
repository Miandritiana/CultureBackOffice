package back.backoffice_culture.Controller;

import java.sql.PreparedStatement;
import java.sql.Timestamp;

import back.backoffice_culture.Models.Saison;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import back.backoffice_culture.Models.Connexion;
import back.backoffice_culture.Models.Parcelle;
import back.backoffice_culture.Models.User;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/saison")
public class SaisonController {

    @GetMapping("/")
    public Saison[] getAllSaison() {
        Connexion c = new Connexion();
        Saison saison = new Saison();

        try {
            return saison.selectAllSaisons(c);
        } catch (Exception e) {
            e.printStackTrace();
            return new Saison[0];
        }
    }
}
