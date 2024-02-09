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

import java.text.SimpleDateFormat;
import java.sql.Timestamp;

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
    @PostMapping("/insertSecond")
    public ResponseEntity<String> insertCategorieCultureRecolte(@RequestParam String dateInsertStr, @RequestParam int idParcelle, @RequestParam int idTerrain, @RequestParam int rec) {
        Connexion c = new Connexion();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dateInsert = (Date) dateFormat.parse(dateInsertStr);

            Recolte recolte = new Recolte(dateInsert, idParcelle, idTerrain, rec);
            recolte.insertRecolte(c);
            return ResponseEntity.ok("saisonCulture insérée avec succès");
        } catch (Exception var8) {
            var8.printStackTrace();
            return ResponseEntity.status(500).body("Erreur lors de l'insertion de la Recolte : " + var8.getMessage());
        }
    }
}



