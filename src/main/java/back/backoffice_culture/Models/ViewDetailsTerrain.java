package back.backoffice_culture.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ViewDetailsTerrain {
    public ViewDetailsTerrain() {
    }
    private int idTerrain;
    private String description;
    private String geolocalisation;
    private int status;
    private int idParcelle;
    private String nomParcelle;
    private int tailleParcelle;
    private int idUser;
    private String nomUser;
    private String photo;

    public ViewDetailsTerrain(int idTerrain, String description, String geolocalisation, int status, int idParcelle, String nomParcelle, int tailleParcelle, int idUser, String nomUser,String photo) {
        this.idTerrain = idTerrain;
        this.description = description;
        this.geolocalisation = geolocalisation;
        this.status = status;
        this.idParcelle = idParcelle;
        this.nomParcelle = nomParcelle;
        this.tailleParcelle = tailleParcelle;
        this.idUser = idUser;
        this.nomUser = nomUser;
        this.photo = photo;
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


    public int getIdParcelle() {
        return idParcelle;
    }


    public void setIdParcelle(int idParcelle) {
        this.idParcelle = idParcelle;
    }


    public String getNomParcelle() {
        return nomParcelle;
    }


    public void setNomParcelle(String nomParcelle) {
        this.nomParcelle = nomParcelle;
    }


    public int getTailleParcelle() {
        return tailleParcelle;
    }


    public void setTailleParcelle(int tailleParcelle) {
        this.tailleParcelle = tailleParcelle;
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
    public static ViewDetailsTerrain[] selectTerrainDetailsWithStatus(Connexion c, int status,int idUser) {
        try {
            Connection cc = c.getConnection();
            String query = "SELECT * FROM viewDetailsTerrain WHERE status = ? AND iduser != ? ";

            try (PreparedStatement pstmt = cc.prepareStatement(query)) {
                pstmt.setInt(1, status);
                pstmt.setInt(2, idUser);
                ResultSet rs = pstmt.executeQuery();

                Vector<ViewDetailsTerrain> v = new Vector<>();
                while (rs.next()) {
                    v.add(new ViewDetailsTerrain(
                            rs.getInt("idterrain"),
                            rs.getString("description"),
                            rs.getString("geolocalisation"),
                            rs.getInt("status"),
                            rs.getInt("idp"),
                            rs.getString("nomp"),
                            rs.getInt("taille"),
                            rs.getInt("iduser"),
                            rs.getString("nomuser"),
                            rs.getString("photo")
                    ));
                }

                ViewDetailsTerrain[] resultArray = new ViewDetailsTerrain[v.size()];
                v.copyInto(resultArray);
                return resultArray;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ViewDetailsTerrain[] selectTerrainDetailsWithStatus(Connexion c, int status) {
        try {
            Connection cc = c.getConnection();
            String query = "SELECT * FROM viewDetailsTerrain WHERE status = ?";

            try (PreparedStatement pstmt = cc.prepareStatement(query)) {
                pstmt.setInt(1, status);
                ResultSet rs = pstmt.executeQuery();

                Vector<ViewDetailsTerrain> v = new Vector<>();
                while (rs.next()) {
                    v.add(new ViewDetailsTerrain(
                            rs.getInt("idterrain"),
                            rs.getString("description"),
                            rs.getString("geolocalisation"),
                            rs.getInt("status"),
                            rs.getInt("idp"),
                            rs.getString("nomp"),
                            rs.getInt("taille"),
                            rs.getInt("iduser"),
                            rs.getString("nomuser"),
                            rs.getString("photo")
                    ));
                }

                ViewDetailsTerrain[] resultArray = new ViewDetailsTerrain[v.size()];
                v.copyInto(resultArray);
                return resultArray;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ViewDetailsTerrain[] selectTerrainDetailsById(Connexion c, int idUser) {
        try {
            Connection cc = c.getConnection();
            String query = "SELECT * FROM viewDetailsTerrain WHERE iduser = ? and status =1";

            try (PreparedStatement pstmt = cc.prepareStatement(query)) {
                pstmt.setInt(1, idUser);
                ResultSet rs = pstmt.executeQuery();

                Vector<ViewDetailsTerrain> v = new Vector<>();
                while (rs.next()) {
                    v.add(new ViewDetailsTerrain(
                            rs.getInt("idterrain"),
                            rs.getString("description"),
                            rs.getString("geolocalisation"),
                            rs.getInt("status"),
                            rs.getInt("idp"),
                            rs.getString("nomp"),
                            rs.getInt("taille"),
                            rs.getInt("iduser"),
                            rs.getString("nomuser"),
                            rs.getString("photo")
                    ));
                }

                ViewDetailsTerrain[] resultArray = new ViewDetailsTerrain[v.size()];
                v.copyInto(resultArray);
                return resultArray;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static ViewDetailsTerrain[] selectTerrainDetailsExceptById(Connexion c, int idUserToExclude) {
        try {
            Connection cc = c.getConnection();
            String query = "SELECT * FROM viewDetailsTerrain WHERE iduser <> ? and status =1";
    
            try (PreparedStatement pstmt = cc.prepareStatement(query)) {
                pstmt.setInt(1, idUserToExclude);
                ResultSet rs = pstmt.executeQuery();
    
                Vector<ViewDetailsTerrain> v = new Vector<>();
                while (rs.next()) {
                    v.add(new ViewDetailsTerrain(
                            rs.getInt("idterrain"),
                            rs.getString("description"),
                            rs.getString("geolocalisation"),
                            rs.getInt("status"),
                            rs.getInt("idp"),
                            rs.getString("nomp"),
                            rs.getInt("taille"),
                            rs.getInt("iduser"),
                            rs.getString("nomuser"),
                            rs.getString("photo")
                    ));
                }
    
                ViewDetailsTerrain[] resultArray = new ViewDetailsTerrain[v.size()];
                v.copyInto(resultArray);
                return resultArray;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
