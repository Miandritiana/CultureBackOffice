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
    public ArrayList<ViewListeCulture> getListeCultureDynamique (Connexion c,int idCulture) throws Exception {
        ArrayList<ViewListeCulture> instruments = new ArrayList<>();

        try (Connection co = c.getConnection()) {
            StringBuilder sql = new StringBuilder("SELECT * from ViewListeCulture");

            if (idCulture != 0) {
                sql.append(" WHERE idCulture >= ?");
            }

            System.out.println(sql);

            try (PreparedStatement statement = co.prepareStatement(sql.toString())) {
                int parameterIndex = 1;

                if (idCulture != 0) {
                    statement.setInt(parameterIndex++, idCulture);
                }

                try (ResultSet resultSet = statement.executeQuery()) {
                    // Parcourir les résultats de la requête
                    while (resultSet.next()) {
                        ViewListeCulture culture = new ViewListeCulture();
                        culture.setIdTerrain(resultSet.getInt("idterrain"));
                        culture.setIdParcelle(resultSet.getInt("idparcelle"));
                        culture.setIdUser(resultSet.getInt("iduser"));
                        culture.setIdCategorieCulture(resultSet.getInt("idcatecult"));
                        culture.setNomCulture(resultSet.getString("nomculture"));
                        culture.setNomTerrain(resultSet.getString("nomterrain"));
                        culture.setNomParcelle(resultSet.getString("nomp"));
                        culture.setNomUser(resultSet.getString("nomuser"));

                        instruments.add(culture);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return instruments;
    }
}
