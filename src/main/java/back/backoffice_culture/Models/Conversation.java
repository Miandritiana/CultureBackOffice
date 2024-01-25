package back.backoffice_culture.Models;

import java.sql.Date;
public class Conversation {
    int idConversation;
    int idUserEnvoie;
    int idUserRecois;
    String message;
    java.sql.Date date;

    public int getIdConversation() {
        return idConversation;
    }

    public void setIdConversation(int idConversation) {
        this.idConversation = idConversation;
    }

    public int getIdUserEnvoie() {
        return idUserEnvoie;
    }

    public void setIdUserEnvoie(int idUserEnvoie) {
        this.idUserEnvoie = idUserEnvoie;
    }

    public int getIdUserRecois() {
        return idUserRecois;
    }

    public void setIdUserRecois(int idUserRecois) {
        this.idUserRecois = idUserRecois;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Conversation(int idConversation, int idUserEnvoie, int idUserRecois, String message, Date date) {
        this.idConversation = idConversation;
        this.idUserEnvoie = idUserEnvoie;
        this.idUserRecois = idUserRecois;
        this.message = message;
        this.date = date;
    }

    public Conversation() {
    }


}
