package back.backoffice_culture.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import back.backoffice_culture.Models.Connexion;
import back.backoffice_culture.Models.ViewDetailsTerrain;
import back.backoffice_culture.Models.ViewListeCulture;

@RestController
@RequestMapping("/cultures")
public class CultureController {

    @GetMapping("/")
    public ViewListeCulture[] getViewCulture() {
        Connexion c = new Connexion();

        try {
            ViewListeCulture[] cultureDetails = ViewListeCulture.selectViewListeCulture(c);

            if (cultureDetails != null) {
                return cultureDetails;
            } else {
                throw new RuntimeException("Erreur lors de la récupération des détails du terrain.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération des détails du terrain : " + e.getMessage());
        }
    }
}
