package back.backoffice_culture.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class TerrainUser {
    public TerrainUser(int idTerrain, int idUser) {
        this.idTerrain = idTerrain;
        this.idUser = idUser;
    }

    public TerrainUser(int idTerrainUser, int idTerrain, int idUser) {
        this.idTerrainUser = idTerrainUser;
        this.idTerrain = idTerrain;
        this.idUser = idUser;
    }

    public TerrainUser() {
    }

    int idTerrainUser;
    int idTerrain;
    int idUser;

    public int getIdTerrainUser() {
        return idTerrainUser;
    }



    public void setIdTerrainUser(int idTerrainUser) {
        this.idTerrainUser = idTerrainUser;
    }



    public int getIdTerrain() {
        return idTerrain;
    }



    public void setIdTerrain(int idTerrain) {
        this.idTerrain = idTerrain;
    }



    public int getIdUser() {
        return idUser;
    }



    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }   

    public void insertTerrainUser(TerrainUser inserer, Connexion c) throws Exception {
        try {
            Connection cc = c.getConnection();
            String query = "INSERT INTO terrainuser(idterrain,iduser) VALUES (?,?)";
            System.out.println(query);
            try (PreparedStatement pstmt = cc.prepareStatement(query)) {
                pstmt.setInt(1, inserer.getIdTerrain());
                pstmt.setInt(2, inserer.getIdUser());
                int rep = pstmt.executeUpdate();
                System.out.println(rep);
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
