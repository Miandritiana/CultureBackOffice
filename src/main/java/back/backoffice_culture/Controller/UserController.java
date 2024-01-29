package back.backoffice_culture.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import back.backoffice_culture.Models.User;
import back.backoffice_culture.Models.Connexion;
import back.backoffice_culture.Models.User;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserController {

    @GetMapping("/checkLogin")
    public boolean checkLogin(@RequestParam("username") String nom, @RequestParam("password") String password) throws Exception {
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

    @PostMapping("/user")
    public ResponseEntity<String> insertUser(@RequestParam String username, @RequestParam String password) {
        Connexion c = new Connexion();

        try {
            User User = new User(username, password);
            User.insertUser(User, c);

            return ResponseEntity.ok("User insérée avec succès");
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.status(500).body("Erreur lors de l'insertion de la parcelle : " + e.getMessage());
        }
    }
}
