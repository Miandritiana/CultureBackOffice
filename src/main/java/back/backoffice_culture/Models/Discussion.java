package back.backoffice_culture.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "discussion")
public class Discussion {

    @Id
    private String id;
    private Date daty;
    private int idUserSend;
    private int idUserReceive;
    private String nomUserSend;
    private String nomUserReceive;
    private String message;
    public Discussion(Date daty, int idUserSend, int idUserReceive, String nomUserSend, String nomUserReceive,
            String message) {
        this.daty = daty;
        this.idUserSend = idUserSend;
        this.idUserReceive = idUserReceive;
        this.nomUserSend = nomUserSend;
        this.nomUserReceive = nomUserReceive;
        this.message = message;
    }
    public Discussion() {
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Date getDaty() {
        return daty;
    }
    public void setDaty(Date daty) {
        this.daty = daty;
    }
    public int getIdUserSend() {
        return idUserSend;
    }
    public void setIdUserSend(int idUserSend) {
        this.idUserSend = idUserSend;
    }
    public int getIdUserReceive() {
        return idUserReceive;
    }
    public void setIdUserReceive(int idUserReceive) {
        this.idUserReceive = idUserReceive;
    }
    public String getNomUserSend() {
        return nomUserSend;
    }
    public void setNomUserSend(String nomUserSend) {
        this.nomUserSend = nomUserSend;
    }
    public String getNomUserReceive() {
        return nomUserReceive;
    }
    public void setNomUserReceive(String nomUserReceive) {
        this.nomUserReceive = nomUserReceive;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    
}

