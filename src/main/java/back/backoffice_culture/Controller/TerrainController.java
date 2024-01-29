package back.backoffice_culture.Controller;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import back.backoffice_culture.Models.CategorieCulture;
import back.backoffice_culture.Models.Connexion;
import back.backoffice_culture.Models.PhotoTerrain;
import back.backoffice_culture.Models.Terrain;
import back.backoffice_culture.Models.TerrainUser;
import back.backoffice_culture.Models.User;
import back.backoffice_culture.Models.ViewDetailsTerrain;
import back.backoffice_culture.Models.ViewTerrainAValider;


@RestController
@RequestMapping("/terrains")
public class TerrainController {

    @GetMapping("/status")
    public ViewDetailsTerrain[] getTerrainDetailsByStatus(@RequestParam int status,@RequestParam int idUser) {
        Connexion c = new Connexion();

        try {
            ViewDetailsTerrain[] terrainDetails = ViewDetailsTerrain.selectTerrainDetailsWithStatus(c, status,idUser);

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
    @GetMapping("/terrains")
    public ViewDetailsTerrain[] getTerrainDetails(@RequestParam int status) {
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

    @GetMapping("/terrain")
    public ViewDetailsTerrain[] getTerrainDetailsById(@RequestParam int idUser) {
        Connexion c = new Connexion();

        try {
            ViewDetailsTerrain[] terrainDetails = ViewDetailsTerrain.selectTerrainDetailsById(c,idUser);

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

    @PostMapping("/demandeterrain")
    public ResponseEntity<String> demandeTerrain(
            @RequestParam String desc,
            @RequestParam String geolocalisation,
            @RequestParam int idUser,
            @RequestPart("photo") MultipartFile photoFile,
            Connexion c) {
        try {
            Terrain t = new Terrain(desc, geolocalisation, 0);
            int idTerrain = t.insertTerrain(t, c);

            TerrainUser terrainUser = new TerrainUser(idTerrain, idUser);
            terrainUser.insertTerrainUser(terrainUser, c);

            String photoFileName = idTerrain + "_" + photoFile.getOriginalFilename();
            String uploadPath = "uploads/";

            Files.write(Paths.get(uploadPath + photoFileName), photoFile.getBytes());

            PhotoTerrain photoTerrain = new PhotoTerrain(idTerrain, photoFileName);
            photoTerrain.insertPhotoTerrain(photoTerrain, c);

            return ResponseEntity.ok("Terrain demandé avec succès");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erreur lors de la demande du terrain : " + e.getMessage());
        }
    }


    // @PostMapping("/valider")
    // public ResponseEntity<String> validerTerrain(
    //         @RequestParam("idTerrain") int idTerrain,
    //         @RequestParam("idParcelle") int idParcelle,
    //         @RequestParam("idUser") int idUser) {
    //     try {
    //         Connexion c = new Connexion();  
    //         Terrain Terrain = new Terrain();

    //         Terrain.validerTerrain(idTerrain, c);
    //         return ResponseEntity.ok("Terrain validé avec succès.");
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return ResponseEntity.status(500).body("Une erreur s'est produite lors de la validation du terrain.");
    //     }
    // }

    @PutMapping("/valider")
    public ResponseEntity<String> validerTerrain(
            @RequestParam("idTerrain") int idTerrain) {
        try {
            Connexion c = new Connexion();  
            Terrain terrain = new Terrain();

            terrain.updateTerrainStatus(idTerrain, c);
            return ResponseEntity.ok("Terrain validé avec succès.");
        } catch (TransactionException e) {
            // Log or handle specific validation exception
            e.printStackTrace();
            return ResponseEntity.status(400).body("Validation du terrain échouée: " + e.getMessage());
        } catch (Exception e) {
            // Log or handle other exceptions
            e.printStackTrace();
            return ResponseEntity.status(500).body("Une erreur s'est produite lors de la validation du terrain.");
        }
    }


    @PutMapping("/update")
    public ResponseEntity<String> updateTerrain(
            @RequestParam int idTerrain,
            @RequestParam String desc,
            @RequestPart("photoFile") MultipartFile photoFile) {
        try {
            Connexion c = new Connexion();
            Terrain terrain = new Terrain();
            terrain.updateTerrain(idTerrain, desc, c);
    
            String photoFileName = idTerrain + "_" + photoFile.getOriginalFilename();
            String uploadPath = "uploads/";
    
            Files.write(Paths.get(uploadPath + photoFileName), photoFile.getBytes());
    
            PhotoTerrain photoTerrain = new PhotoTerrain();
            photoTerrain.updatePhotoTerrain(photoFileName, idTerrain, c);
    
            return ResponseEntity.ok("Terrain mis à jour avec succès");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erreur lors de la mise à jour du terrain : " + e.getMessage());
        }
    }
    
    @GetMapping("/avalider")
    public ViewTerrainAValider[] getTerrainAValider() {
        Connexion c = new Connexion();

        try {
            ViewTerrainAValider tv = new ViewTerrainAValider();

		    ViewTerrainAValider[] terrainDetails = tv.selectTerrainAValider(c);

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
}
