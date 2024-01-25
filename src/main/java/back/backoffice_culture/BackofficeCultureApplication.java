package back.backoffice_culture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import back.backoffice_culture.Models.CategorieCulture;
import back.backoffice_culture.Models.Connexion;

@SpringBootApplication
public class BackofficeCultureApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(BackofficeCultureApplication.class, args);
		
		Connexion c = new Connexion();
		System.out.println(c.getConnection());

		CategorieCulture ccult = new CategorieCulture();
		CategorieCulture[] l_cu = ccult.selectCategorieCulture(c);
		for (int i = 0; i < l_cu.length; i++) {
			System.out.println(l_cu[i].getNomCateCult());
		}
	}

}
