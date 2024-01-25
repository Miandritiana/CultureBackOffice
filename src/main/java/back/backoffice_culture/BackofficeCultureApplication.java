package back.backoffice_culture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import back.backoffice_culture.Models.Parcelle;
import back.backoffice_culture.Models.Connexion;

@SpringBootApplication
public class BackofficeCultureApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(BackofficeCultureApplication.class, args);
		
		Connexion c = new Connexion();
		System.out.println(c.getConnection());

		Parcelle ccult = new Parcelle();
		Parcelle[] l_cu = ccult.selectParcelle(c);
		for (int i = 0; i < l_cu.length; i++) {
			System.out.println(l_cu[i].getNomParcelle());
		}
	}

}
