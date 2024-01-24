package back.backoffice_culture.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class TerrainParcelle {
    int idTerrainParcelle;
    int idTerrain;
    int idParcelle;

    public TerrainParcelle(int idTerrain, int idParcelle) {
        this.idTerrain = idTerrain;
        this.idParcelle = idParcelle;
    }

    public TerrainParcelle(int idTerrainParcelle, int idTerrain, int idParcelle) {
        this.idTerrainParcelle = idTerrainParcelle;
        this.idTerrain = idTerrain;
        this.idParcelle = idParcelle;
    }

    public TerrainParcelle() {
    }

    public int getIdTerrainParcelle() {
        return idTerrainParcelle;
    }

    public void setIdTerrainParcelle(int idTerrainParcelle) {
        this.idTerrainParcelle = idTerrainParcelle;
    }

    public int getIdTerrain() {
        return idTerrain;
    }

    public void setIdTerrain(int idTerrain) {
        this.idTerrain = idTerrain;
    }

    public int getIdParcelle() {
        return idParcelle;
    }

    public void setIdParcelle(int idParcelle) {
        this.idParcelle = idParcelle;
    }

    public void insertTerrainParcelle(TerrainParcelle inserer, Connexion c) throws Exception {
        try {
            Connection cc = c.getConnection();
            String query = "INSERT INTO terrainparcelle(idterrain,idp) VALUES (?,?)";
            System.out.println(query);
            try (PreparedStatement pstmt = cc.prepareStatement(query)) {
                pstmt.setInt(1, inserer.getIdTerrain());
                pstmt.setInt(2, inserer.getIdParcelle());
                int rep = pstmt.executeUpdate();
                System.out.println(rep);
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
