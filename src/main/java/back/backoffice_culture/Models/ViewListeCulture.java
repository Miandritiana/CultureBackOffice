package back.backoffice_culture.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class ViewListeCulture {
    private int idTerrain;
    private int idParcelle;
    private int idUser;
    private int idCategorieCulture;
    private String nomCulture;
    private String nomTerrain;
    private String nomParcelle;
    private String nomUser;

    public ViewListeCulture() {
    }

    public ViewListeCulture(int idTerrain, int idParcelle, int idUser, int idCategorieCulture, String nomCulture, String nomTerrain, String nomParcelle, String nomUser) {
        this.idTerrain = idTerrain;
        this.idParcelle = idParcelle;
        this.idUser = idUser;
        this.idCategorieCulture = idCategorieCulture;
        this.nomCulture = nomCulture;
        this.nomTerrain = nomTerrain;
        this.nomParcelle = nomParcelle;
        this.nomUser = nomUser;
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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdCategorieCulture() {
        return idCategorieCulture;
    }

    public void setIdCategorieCulture(int idCategorieCulture) {
        this.idCategorieCulture = idCategorieCulture;
    }

    public String getNomCulture() {
        return nomCulture;
    }

    public void setNomCulture(String nomCulture) {
        this.nomCulture = nomCulture;
    }

    public String getNomTerrain() {
        return nomTerrain;
    }

    public void setNomTerrain(String nomTerrain) {
        this.nomTerrain = nomTerrain;
    }

    public String getNomParcelle() {
        return nomParcelle;
    }

    public void setNomParcelle(String nomParcelle) {
        this.nomParcelle = nomParcelle;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public static ViewListeCulture[] selectViewListeCulture(Connexion c) {
        try {
            Connection cc = c.getConnection();
            String query = "SELECT * FROM viewListeCulture";
            try (PreparedStatement pstmt = cc.prepareStatement(query)) {
                ResultSet rs = pstmt.executeQuery();

                Vector<ViewListeCulture> v = new Vector<>();
                while (rs.next()) {
                    v.add(new ViewListeCulture(
                            rs.getInt("idterrain"),
                            rs.getInt("idparcelle"),
                            rs.getInt("iduser"),
                            rs.getInt("idcatecult"),
                            rs.getString("nomculture"),
                            rs.getString("nomterrain"),
                            rs.getString("nomp"),
                            rs.getString("nomuser")
                    ));
                }

                ViewListeCulture[] resultArray = new ViewListeCulture[v.size()];
                v.copyInto(resultArray);
                return resultArray;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static ViewListeCulture[] selectViewListeCulture(Connexion c, Integer idCulture, String nom) {
        try {
            Connection cc = c.getConnection();
            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM viewListeCulture WHERE 1=1");

            if (idCulture != null && idCulture != 0) {
                queryBuilder.append(" AND idcatecult = ?");
            }

            if (nom != null && !nom.isEmpty()) {
                queryBuilder.append(" AND nomUser = ?");
            }

            try (PreparedStatement pstmt = cc.prepareStatement(queryBuilder.toString())) {
                int parameterIndex = 1;

                if (idCulture != null && idCulture != 0) {
                    pstmt.setInt(parameterIndex++, idCulture);
                }

                if (nom != null && !nom.isEmpty()) {
                    pstmt.setString(parameterIndex, nom);
                }

                ResultSet rs = pstmt.executeQuery();

                Vector<ViewListeCulture> v = new Vector<>();
                while (rs.next()) {
                    v.add(new ViewListeCulture(
                            rs.getInt("idterrain"),
                            rs.getInt("idparcelle"),
                            rs.getInt("iduser"),
                            rs.getInt("idcatecult"),
                            rs.getString("nomculture"),
                            rs.getString("nomterrain"),
                            rs.getString("nomp"),
                            rs.getString("nomuser")
                    ));
                }

                ViewListeCulture[] resultArray = new ViewListeCulture[v.size()];
                v.copyInto(resultArray);
                return resultArray;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
