package back.backoffice_culture.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import back.backoffice_culture.Models.Connexion;
import back.backoffice_culture.Models.Discussion;
import back.backoffice_culture.Models.DiscussionRepository;
import back.backoffice_culture.Models.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/discussions")
public class DiscussionController {

    @Autowired
    private DiscussionRepository discussionRepository;

    @PostMapping("/create")
    public Discussion createDiscussion(@RequestParam Timestamp daty,int idUserSend,int idUserReceive,String message) throws Exception {
        Connexion c=new Connexion();
        User u1=new User();
        String nomUserSend=u1.getNomUserById(idUserSend,c);
        String nomUserReceive=u1.getNomUserById(idUserReceive,c);
        Discussion discussion=new Discussion(daty,idUserSend,idUserReceive,nomUserSend,nomUserReceive,message);
        return discussionRepository.save(discussion);
    }

    @GetMapping("/")
    public List<Discussion> getAllDiscussions() {
        return discussionRepository.findAll();
    }

    @GetMapping("/idutilisateur")
    public ResponseEntity<List<Discussion>> getAllUserDiscussions(@RequestParam int userSend,@RequestParam int userReceive) {
        List<Discussion> sentDiscussions = discussionRepository.findByIdUserSendAndIdUserReceive(userSend,userReceive);
        List<Discussion> receivedDiscussions = discussionRepository.findByIdUserSendAndIdUserReceive(userReceive,userSend);

        List<Discussion> allUserDiscussions = new ArrayList<>();
        allUserDiscussions.addAll(sentDiscussions);
        allUserDiscussions.addAll(receivedDiscussions);

        return ResponseEntity.ok().body(allUserDiscussions);
    }
}
