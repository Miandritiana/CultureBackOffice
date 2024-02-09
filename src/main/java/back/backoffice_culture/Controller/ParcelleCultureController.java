package back.backoffice_culture.Controller;

import java.sql.PreparedStatement;
import java.sql.Timestamp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import back.backoffice_culture.Models.ParcelleCulture;
import back.backoffice_culture.Models.Connexion;
import back.backoffice_culture.Models.Parcelle;
import back.backoffice_culture.Models.User;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/parcelleCulture")
public class ParcelleCultureController {

    @PostMapping("/insert")
    public ResponseEntity<String> insertParcelleCulture(@RequestParam String dateInsertStr, @RequestParam int idParcel, @RequestParam int idCulture) {
        Connexion c = new Connexion();

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            Timestamp dateInsert = new Timestamp(dateFormat.parse(dateInsertStr).getTime());

            ParcelleCulture parcelle = new ParcelleCulture(dateInsert ,idParcel, idCulture);
            parcelle.insertPC(parcelle, c);

            return ResponseEntity.ok("ParcelleCulture insérée avec succès");
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.status(500).body("Erreur lors de l'insertion de la parcelle : " + e.getMessage());
        }
    }
}
