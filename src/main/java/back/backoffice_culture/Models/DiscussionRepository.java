package back.backoffice_culture.Models;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DiscussionRepository extends MongoRepository<Discussion, String> {
    // List<Discussion> findByIdUserSend(int idUserSend);

    List<Discussion> findByIdUserSendAndIdUserReceive(int idUserSend, int idUserReceive);
}
