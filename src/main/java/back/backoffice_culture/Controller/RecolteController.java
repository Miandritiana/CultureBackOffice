package back.backoffice_culture.Controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import back.backoffice_culture.Models.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Recolte")
public class RecolteController {
    @GetMapping("/")
    public Recolte[] getAllSaisonCulture() {
        Connexion c = new Connexion();
        Recolte recolte = new Recolte();

        try {
            return recolte.selectAllRecoltes(c);
        } catch (Exception e) {
            e.printStackTrace();
            return new Recolte[0];
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertCategorieCulture(@RequestParam int idRecolte, @RequestParam Date dateRecolte, @RequestParam int idParcelle, @RequestParam int idTerrain, @RequestParam int rec) {
        Connexion c = new Connexion();

        try {
            Recolte recolte = new Recolte(idRecolte, dateRecolte, idParcelle,idTerrain, rec);
            recolte.insertRecolte(c);

            return ResponseEntity.ok("saisonCulture insérée avec succès");
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.status(500).body("Erreur lors de l'insertion de la Recolte : " + e.getMessage());
        }
    }
}
