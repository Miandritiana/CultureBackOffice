package back.backoffice_culture.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import back.backoffice_culture.Models.*;

@RestController
@RequestMapping("/api")
public class CategorieCultureController {

    @PutMapping("/update")
    public void update(@RequestParam int id,@RequestParam String nom, @RequestParam double prix) throws Exception {
        Connexion c = new Connexion();
        CategorieCulture cateCult = new CategorieCulture();
        cateCult.updateCategorieCulture(id, nom, prix, c); 
    }
    
    @DeleteMapping("/delete")
    public void delete(@RequestParam int id) throws Exception {
        Connexion c = new Connexion();
        CategorieCulture cateCult = new CategorieCulture();
        cateCult.deleteCategorieCulture(id, c);
    }

}
