package back.backoffice_culture.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ViewTerrainAValider {

    public ViewTerrainAValider(int idTerrain, String description, String geolocalisation, int status,
            int nombreParcelle, int idUser, String nomUser, String photo) {
        this.idTerrain = idTerrain;
        this.description = description;
        this.geolocalisation = geolocalisation;
        this.status = status;
        this.nombreParcelle = nombreParcelle;
        this.idUser = idUser;
        this.nomUser = nomUser;
        this.photo = photo;
    }

    public ViewTerrainAValider() {
    }
    private int idTerrain;
    private String description;
    private String geolocalisation;
    private int status;
    private int nombreParcelle;
    private int idUser;
    private String nomUser;
    private String photo;

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

    public int getNombreParcelle() {
        return nombreParcelle;
    }

    public void setNombreParcelle(int nombreParcelle) {
        this.nombreParcelle = nombreParcelle;
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

    public static ViewTerrainAValider[] selectTerrainAValider(Connexion c) {
        try {
            Connection cc = c.getConnection();
            String query = "SELECT * FROM viewTerrainAValider WHERE status = 0";

            try (PreparedStatement pstmt = cc.prepareStatement(query)) {
                ResultSet rs = pstmt.executeQuery();

                Vector<ViewTerrainAValider> v = new Vector<>();
                while (rs.next()) {
                    v.add(new ViewTerrainAValider(
                            rs.getInt("idterrain"),
                            rs.getString("description"),
                            rs.getString("geolocalisation"),
                            rs.getInt("status"),
                            rs.getInt("nombre_parcelles"),
                            rs.getInt("iduser"),
                            rs.getString("nomuser"),
                            rs.getString("photo")
                    ));
                }

                ViewTerrainAValider[] resultArray = new ViewTerrainAValider[v.size()];
                v.copyInto(resultArray);
                return resultArray;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }    
    
}
