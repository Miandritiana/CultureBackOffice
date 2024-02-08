package back.backoffice_culture.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TerrainMobile {
    public TerrainMobile(String description, int idUser, String nomUser, String photo) {
        this.description = description;
        this.idUser = idUser;
        this.nomUser = nomUser;
        this.photo = photo;
    }


    public TerrainMobile(int idTerrain, String description, int idUser, String nomUser, String photo) {
        this.idTerrain = idTerrain;
        this.description = description;
        this.idUser = idUser;
        this.nomUser = nomUser;
        this.photo = photo;
    }


    public TerrainMobile() {
    }
    private int idTerrain;
    private String description;
    private int idUser;
    private String nomUser;
    private String photo;


    public static TerrainMobile[] selectTerrainsById(Connexion c, int idUser) {
        try {
            Connection cc = c.getConnection();
            String query = "SELECT idterrain,description,iduser,nomuser,photo FROM viewDetailsTerrain WHERE iduser = ? and status =1";

            try (PreparedStatement pstmt = cc.prepareStatement(query)) {
                pstmt.setInt(1, idUser);
                ResultSet rs = pstmt.executeQuery();

                Vector<TerrainMobile> v = new Vector<>();
                while (rs.next()) {
                    v.add(new TerrainMobile(
                            rs.getInt("idterrain"),
                            rs.getString("description"),
                            rs.getInt("iduser"),
                            rs.getString("nomuser"),
                            rs.getString("photo")
                    ));
                }

                TerrainMobile[] resultArray = new TerrainMobile[v.size()];
                v.copyInto(resultArray);
                return resultArray;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public int getIdTerrain() {
        return idTerrain;
    }


    public void setIdTerrain(int idTerrain) {
        this.idTerrain = idTerrain;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public int getIdUser() {
        return idUser;
    }


    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }


    public String getNomUser() {
        return nomUser;
    }


    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }


    public String getPhoto() {
        return photo;
    }


    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
