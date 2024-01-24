package back.backoffice_culture.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Terrain {
    int idTerrain;
    String description;
    String geolocalisation;
    int status;

    public Terrain(String description, String geolocalisation, int status) {
        this.description = description;
        this.geolocalisation = geolocalisation;
        this.status = status;
    }

    public Terrain(int idTerrain, String description, String geolocalisation, int status) {
        this.idTerrain = idTerrain;
        this.description = description;
        this.geolocalisation = geolocalisation;
        this.status = status;
    }

    public Terrain() {
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

    public String getGeolocalisation() {
        return geolocalisation;
    }

    public void setGeolocalisation(String geolocalisation) {
        this.geolocalisation = geolocalisation;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Terrain[] selectTerrain(Connexion c) throws Exception
    {
        try {
            c=new Connexion();
            Connection cc = c.getConnection();
            System.out.println(cc);
            Statement stmt = cc.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Terrain"); 
            Vector<Terrain> v=new Vector<Terrain>();
            while(rs.next())
            {
                v.add(new Terrain(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
            }
            Terrain[] lTerrain=new Terrain[v.size()];
            v.copyInto(lTerrain);
            stmt.close();
            cc.close();
            return lTerrain;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }    
    }

    public void insertTerrain(Terrain inserer, Connexion c) throws Exception {
        try {
            Connection cc = c.getConnection();
            String query = "INSERT INTO Terrain(description,geolocalisation,status) VALUES (?,?,0)";
            
            try (PreparedStatement pstmt = cc.prepareStatement(query)) {
                pstmt.setString(1, inserer.getDescription());
                pstmt.setString(2, inserer.getGeolocalisation());
                int rep = pstmt.executeUpdate();
                System.out.println(rep);
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTerrainStatus(int idTerrain, Connexion c) {
        try {
            Connection cc = c.getConnection();
            String updateQuery = "UPDATE Terrain SET status = 1 WHERE idTerrain = ?";
            System.out.println("updateQuery"+updateQuery);
            try (PreparedStatement updateStmt = cc.prepareStatement(updateQuery)) {
                updateStmt.setInt(1, idTerrain);
                updateStmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void validerTerrain(int idTerrain, int idParcelle, int idUser, Connexion c) {
        try {
            updateTerrainStatus(idTerrain, c);
            TerrainParcelle TerrainParcelle = new TerrainParcelle(idTerrain,idParcelle);
            TerrainParcelle.insertTerrainParcelle(TerrainParcelle, c);

            
            TerrainUser TerrainUser = new TerrainUser(idTerrain,idUser);
            TerrainUser.insertTerrainUser(TerrainUser, c);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        
}
