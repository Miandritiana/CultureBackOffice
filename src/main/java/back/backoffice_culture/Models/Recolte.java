package back.backoffice_culture.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;
public class Recolte {
     int idRecolte;
     Date dateRecolte;
     int idParcelle;
     int idTerrain;
     int recolte;

    public Recolte() {
    }

    public Recolte(int idRecolte, Date dateRecolte, int idParcelle, int idTerrain, int recolte) {
        this.idRecolte = idRecolte;
        this.dateRecolte = dateRecolte;
        this.idParcelle = idParcelle;
        this.idTerrain = idTerrain;
        this.recolte = recolte;
    }

    public int getIdRecolte() {
        return idRecolte;
    }

    public void setIdRecolte(int idRecolte) {
        this.idRecolte = idRecolte;
    }

    public Date getDateRecolte() {
        return dateRecolte;
    }

    public void setDateRecolte(Date dateRecolte) {
        this.dateRecolte = dateRecolte;
    }

    public int getIdParcelle() {
        return idParcelle;
    }

    public void setIdParcelle(int idParcelle) {
        this.idParcelle = idParcelle;
    }

    public int getIdTerrain() {
        return idTerrain;
    }

    public void setIdTerrain(int idTerrain) {
        this.idTerrain = idTerrain;
    }

    public int getRecolte() {
        return recolte;
    }

    public void setRecolte(int recolte) {
        this.recolte = recolte;
    }

    public static Recolte[] selectAllRecoltes(Connexion connexion) throws Exception {
        try {
            Connection connection = connexion.getConnection();
            String query = "SELECT * FROM recolte";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            Vector<Recolte> recolteList = new Vector<>();
            while (resultSet.next()) {
                Recolte recolte = new Recolte();
                recolte.setIdRecolte(resultSet.getInt("idRecolte"));
                recolte.setDateRecolte(resultSet.getDate("dateRecolte"));
                recolte.setIdParcelle(resultSet.getInt("idParcelle"));
                recolte.setIdTerrain(resultSet.getInt("idTerrain"));
                recolte.setRecolte(resultSet.getInt("recolte"));
                recolteList.add(recolte);
            }

            Recolte[] recolteArray = new Recolte[recolteList.size()];
            recolteList.copyInto(recolteArray);

            resultSet.close();
            preparedStatement.close();
            connection.close();

            return recolteArray;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void insertRecolte(Connexion connexion) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connexion.getConnection();
            String query = "INSERT INTO recolte (dateRecolte, idParcelle, idTerrain, recolte) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, new java.sql.Date(this.dateRecolte.getTime()));
            preparedStatement.setInt(2, this.idParcelle);
            preparedStatement.setInt(3, this.idTerrain);
            preparedStatement.setInt(4, this.recolte);
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
