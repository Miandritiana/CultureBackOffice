package back.backoffice_culture.Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Saison {
    int idSaison;
    String nomSaison;

    public Saison() {
    }

    public Saison(int idSaison, String nomSaison) {
        this.idSaison = idSaison;
        this.nomSaison = nomSaison;
    }

    public int getIdSaison() {
        return idSaison;
    }

    public void setIdSaison(int idSaison) {
        this.idSaison = idSaison;
    }

    public String getNomSaison() {
        return nomSaison;
    }

    public void setNomSaison(String nomSaison) {
        this.nomSaison = nomSaison;
    }

    public static Saison[] selectAllSaisons(Connexion connexion) throws Exception {
        try {
            Connection connection = connexion.getConnection();
            String query = "SELECT * FROM saison";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            Vector<Saison> saisonList = new Vector<>();
            while (resultSet.next()) {
                Saison saison = new Saison();
                saison.setIdSaison(resultSet.getInt("idSaison"));
                saison.setNomSaison(resultSet.getString("nomSaison"));
                saisonList.add(saison);
            }

            Saison[] saisonArray = new Saison[saisonList.size()];
            saisonList.copyInto(saisonArray);

            resultSet.close();
            preparedStatement.close();
            connection.close();

            return saisonArray;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
