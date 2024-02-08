package back.backoffice_culture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.mongodb.client.MongoClient;

import back.backoffice_culture.Models.Parcelle;
import back.backoffice_culture.Models.TerrainMobile;
import back.backoffice_culture.Models.ViewListeCulture;
import back.backoffice_culture.Models.ViewTerrainAValider;
import back.backoffice_culture.Models.Connexion;
import back.backoffice_culture.Models.DiscussionRepository;

@SpringBootApplication
public class BackofficeCultureApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(BackofficeCultureApplication.class, args);
		
		Connexion c = new Connexion();
		System.out.println(c.getConnection());

		// Parcelle ccult = new Parcelle();
		// Parcelle[] l_cu = ccult.selectParcelle(c);
		// for (int i = 0; i < l_cu.length; i++) {
		// 	System.out.println(l_cu[i].getNomParcelle());
		// }

		// TerrainMobile[] terrains = TerrainMobile.selectTerrainsById(c,3);


        // if (terrains != null) {
        //     for (TerrainMobile terrain : terrains) {
        //         System.out.println("ID Terrain : " + terrain.getIdTerrain());
        //         System.out.println("Description : " + terrain.getDescription());
        //         System.out.println("ID User : " + terrain.getIdUser());
        //         System.out.println("Nom User : " + terrain.getNomUser());
        //         System.out.println("Photo : " + terrain.getPhoto());
        //         System.out.println("------------------------------------------");
        //     }
        // } else {
        //     System.out.println("Aucun terrain à valider trouvé.");
        // }

        ViewListeCulture test = new ViewListeCulture();
        ViewListeCulture[] l = test.selectViewListeCulture(c, 2, "Rasoa");
        for (ViewListeCulture vv : l)
        {
            System.out.println(vv.getNomCulture());
            System.out.println(vv.getNomUser());
        }

        
	}

}
