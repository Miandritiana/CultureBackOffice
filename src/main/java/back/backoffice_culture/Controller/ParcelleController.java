package back.backoffice_culture.Controller;

import java.sql.PreparedStatement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import back.backoffice_culture.Models.Connexion;
import back.backoffice_culture.Models.Parcelle;
import back.backoffice_culture.Models.User;

@RestController
@RequestMapping("/parcelles")
public class ParcelleController {

    @GetMapping("/")
    public Parcelle[] getAllParcelles() {
        Connexion c = new Connexion();
        Parcelle parcelle = new Parcelle();

        try {
            return parcelle.selectParcelle(c);
        } catch (Exception e) {
            e.printStackTrace();
            return new Parcelle[0]; 
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertParcelle(@RequestParam String nom, @RequestParam int taille) {
        Connexion c = new Connexion();

        try {
            Parcelle parcelle = new Parcelle(nom, taille);
            parcelle.insertParcelle(parcelle, c);

            return ResponseEntity.ok("Parcelle insérée avec succès");
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.status(500).body("Erreur lors de l'insertion de la parcelle : " + e.getMessage());
        }
    }

}

