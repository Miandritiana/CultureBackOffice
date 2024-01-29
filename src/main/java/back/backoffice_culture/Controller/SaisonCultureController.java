package back.backoffice_culture.Controller;


import java.sql.PreparedStatement;
import java.sql.Timestamp;

import back.backoffice_culture.Models.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/saisonCulture")
public class SaisonCultureController {

    @GetMapping("/")
    public SaisonCulture[] getAllSaisonCulture() {
        Connexion c = new Connexion();
        SaisonCulture saisonCulture = new SaisonCulture();

        try {
            return saisonCulture.selectSaisonCulture(c);
        } catch (Exception e) {
            e.printStackTrace();
            return new SaisonCulture[0];
        }
    }

    @PutMapping("/update")
    public void update(@RequestParam int idSaisonCulture,@RequestParam int IdCateCult, @RequestParam int nouvelEffet) throws Exception {
        Connexion c = new Connexion();
        SaisonCulture cateCult = new SaisonCulture();
        cateCult.updateSaisonCulture(idSaisonCulture, IdCateCult,nouvelEffet, c);
    }


    @PostMapping("/insert")
    public ResponseEntity<String> insertCategorieCulture(@RequestParam int idSaison, @RequestParam int idCateCult, @RequestParam int effet) {
        Connexion c = new Connexion();

        try {
            SaisonCulture saisonCulture = new SaisonCulture(idSaison, idCateCult, effet);
            saisonCulture.insertSaisonCulture(c);

            return ResponseEntity.ok("saisonCulture insérée avec succès");
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.status(500).body("Erreur lors de l'insertion de la saisonCulture : " + e.getMessage());
        }
    }

}
