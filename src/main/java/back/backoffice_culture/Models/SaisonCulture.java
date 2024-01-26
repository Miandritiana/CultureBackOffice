package back.backoffice_culture.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SaisonCulture {
    int idSaisonCulture;
    int idSaison;
    int idCateCult;
    int effet;

    public SaisonCulture() {
    }

    public SaisonCulture(int idSaison, int idCateCult, int effet) {
        this.idSaison = idSaison;
        this.idCateCult = idCateCult;
        this.effet = effet;
    }

    public SaisonCulture(int idSaisonCulture, int idSaison, int idCateCult, int effet) {
        this.idSaisonCulture = idSaisonCulture;
        this.idSaison = idSaison;
        this.idCateCult = idCateCult;
        this.effet = effet;
    }

    public int getIdSaisonCulture() {
        return idSaisonCulture;
    }

    public void setIdSaisonCulture(int idSaisonCulture) {
        this.idSaisonCulture = idSaisonCulture;
    }

    public int getIdSaison() {
        return idSaison;
    }

    public void setIdSaison(int idSaison) {
        this.idSaison = idSaison;
    }

    public int getIdCateCult() {
        return idCateCult;
    }

    public void setIdCateCult(int idCateCult) {
        this.idCateCult = idCateCult;
    }

    public int getEffet() {
        return effet;
    }

    public void setEffet(int effet) {
        this.effet = effet;
    }

    public static SaisonCulture[] selectSaisonCulture(Connexion c) throws Exception {
        List<SaisonCulture> saisonsCulture = new ArrayList<>();

        try {
            Connection cc = c.getConnection();
            Statement stmt = cc.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM saisonCulture");

            while (rs.next()) {
                int idSaisonCulture = rs.getInt(1);
                int idSaison = rs.getInt(2);
                int idcatecult = rs.getInt(3);
                int effet = rs.getInt(4);

                SaisonCulture saisonCulture = new SaisonCulture(idSaisonCulture, idSaison, idcatecult, effet);
                saisonsCulture.add(saisonCulture);
            }

            stmt.close();
            cc.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return saisonsCulture.toArray(new SaisonCulture[0]);
    }

    public void insertSaisonCulture(Connexion connexion) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connexion.getConnection();
            String query = "INSERT INTO saisonCulture (idSaison, idcatecult, effet) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, this.idSaison);
            preparedStatement.setInt(2, this.idCateCult);
            preparedStatement.setInt(3, this.effet);
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

    public void updateSaisonCulture(int idSaisonCulture, int IdCateCult, int nouvelEffet, Connexion c) throws Exception {
        try {
            Connection cc = c.getConnection();
            String query = "UPDATE saisonCulture SET effet=? WHERE idSaisonCulture=? and  idcatecult=?";
            System.out.println("query " + query);
            try (PreparedStatement pstmt = cc.prepareStatement(query)) {
                pstmt.setInt(1, nouvelEffet);
                pstmt.setInt(2, IdCateCult);
                pstmt.setInt(3, idSaisonCulture);

                int rep = pstmt.executeUpdate();
                System.out.println(rep);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
