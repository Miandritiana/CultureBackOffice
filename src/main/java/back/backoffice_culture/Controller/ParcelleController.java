package back.backoffice_culture.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import back.backoffice_culture.Models.Connexion;
import back.backoffice_culture.Models.User;

@RestController
@RequestMapping("/api")
public class ParcelleController {

    @GetMapping("/parcelle")
    public boolean parcelle(@RequestParam("nom") String nom, @RequestParam("password") String password) throws Exception {
        Connexion c = new Connexion();
        User user = new User();
        
        int idUser = user.checkLogin(c, nom, password);
        System.out.println("idUser"+idUser);
        if (idUser <= 0) {
            throw new IllegalArgumentException("L'ID utilisateur n'est pas valide");
        }
    
        boolean isAdmin = user.isAdmin(c, idUser);
    
        return isAdmin;
    }
}
