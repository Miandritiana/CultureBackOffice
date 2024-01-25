package back.backoffice_culture.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class PhotoTerrain {
    int idPhoto;
    int idTerrain;
    String photo;

    public PhotoTerrain(int idTerrain, String photo) {
        this.idTerrain = idTerrain;
        this.photo = photo;
    }


    public PhotoTerrain(int idPhoto, int idTerrain, String photo) {
        this.idPhoto = idPhoto;
        this.idTerrain = idTerrain;
        this.photo = photo;
    }


    public PhotoTerrain() {
    }

    public int getIdPhoto() {
        return idPhoto;
    }


    public void setIdPhoto(int idPhoto) {
        this.idPhoto = idPhoto;
    }


    public int getIdTerrain() {
        return idTerrain;
    }


    public void setIdTerrain(int idTerrain) {
        this.idTerrain = idTerrain;
    }


    public String getPhoto() {
        return photo;
    }


    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void insertPhotoTerrain(PhotoTerrain inserer, Connexion c) throws Exception {
        try {
            Connection cc = c.getConnection();
            String query = "INSERT INTO phototerrain(idterrain,photo) VALUES (?,?)";
            System.out.println(query);
            try (PreparedStatement pstmt = cc.prepareStatement(query)) {
                pstmt.setInt(1, inserer.getIdTerrain());
                pstmt.setString(2, inserer.getPhoto());
                int rep = pstmt.executeUpdate();
                System.out.println(rep);
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
