package back.backoffice_culture.Controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import back.backoffice_culture.Models.*;

@RestController
@RequestMapping("/categoriecultures")
public class CategorieCultureController {

    @GetMapping("/")
    public CategorieCulture[] getAllCategorieCultures() {
        Connexion c = new Connexion();
        CategorieCulture ccult = new CategorieCulture();

        try {
            return ccult.selectCategorieCulture(c);
        } catch (Exception e) {
            e.printStackTrace();
            return new CategorieCulture[0]; 
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertCategorieCulture(@RequestParam String nom, @RequestParam int rendement) {
        Connexion c = new Connexion();

        try {
            CategorieCulture CategorieCulture = new CategorieCulture(nom, rendement);
            CategorieCulture.insertCategorieCulture(CategorieCulture, c);

            return ResponseEntity.ok("CategorieCulture insérée avec succès");
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.status(500).body("Erreur lors de l'insertion de la parcelle : " + e.getMessage());
        }
    }

    @PutMapping("/update")
    public void update(@RequestParam int id,@RequestParam String nom, @RequestParam int rendement) throws Exception {
        Connexion c = new Connexion();
        CategorieCulture cateCult = new CategorieCulture();
        cateCult.updateCategorieCulture(id, nom, c);
    }
    
    @DeleteMapping("/delete")
    public void delete(@RequestParam int id) throws Exception {
        Connexion c = new Connexion();
        CategorieCulture cateCult = new CategorieCulture();
        cateCult.deleteCategorieCulture(id, c);
    }

    @GetMapping("/statistiques")
    public Map<String, Integer> getTotalRendementParCategorie() {
        Connexion c = new Connexion();
        CategorieCulture ccult = new CategorieCulture();

        try {
            return ccult.getTotalRendementParCategorie(c);
        } catch (Exception e) {
            e.printStackTrace();
            return null; 
        }
    }

    @GetMapping("/filtreMultiCritere")
    public ViewListeCulture[] getAllCategorieCultures(@RequestParam Integer idculture, @RequestParam String nom) {
        Connexion c = new Connexion();
        ViewListeCulture ccult = new ViewListeCulture();

        try {
            return ccult.selectViewListeCulture(c, idculture, nom);
        } catch (Exception e) {
            e.printStackTrace();
            return new ViewListeCulture[0];
        }
    }
}
