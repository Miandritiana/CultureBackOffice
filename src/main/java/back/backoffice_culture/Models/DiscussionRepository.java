package back.backoffice_culture.Models;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.MongoRepository;


@SpringBootApplication
public interface DiscussionRepository extends MongoRepository<Discussion, String> {
    // List<Discussion> findByIdUserSend(int idUserSend);

    List<Discussion> findByIdUserSendAndIdUserReceive(int idUserSend, int idUserReceive);
}
