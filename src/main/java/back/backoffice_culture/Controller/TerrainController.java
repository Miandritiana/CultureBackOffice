package back.backoffice_culture.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import back.backoffice_culture.Models.CategorieCulture;
import back.backoffice_culture.Models.Connexion;
import back.backoffice_culture.Models.Terrain;
import back.backoffice_culture.Models.User;
import back.backoffice_culture.Models.ViewDetailsTerrain;

@RestController
@RequestMapping("/terrains")
public class TerrainController {

    @GetMapping("/status")
    public ViewDetailsTerrain[] getTerrainDetailsByStatus(@RequestParam int status) {
        Connexion c = new Connexion();

        try {
            ViewDetailsTerrain[] terrainDetails = ViewDetailsTerrain.selectTerrainDetailsWithStatus(c, status);

            if (terrainDetails != null) {
                return terrainDetails;
            } else {
                throw new RuntimeException("Erreur lors de la récupération des détails du terrain.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération des détails du terrain : " + e.getMessage());
        }
    }

    @PostMapping("/valider")
    public ResponseEntity<String> validerTerrain(
            @RequestParam("idTerrain") int idTerrain,
            @RequestParam("idParcelle") int idParcelle,
            @RequestParam("idUser") int idUser) {
        try {
            Connexion c = new Connexion();  
            Terrain Terrain = new Terrain();

            Terrain.validerTerrain(idTerrain, idParcelle, idUser, c);
            return ResponseEntity.ok("Terrain validé avec succès.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Une erreur s'est produite lors de la validation du terrain.");
        }
    }
}
