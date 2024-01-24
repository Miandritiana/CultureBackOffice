package back.backoffice_culture.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class CategorieCulture {
    int idCategorieCulture;
    String nomCateCult;
    double prix;

   

    public CategorieCulture(String nomCateCult, double prix) {
        this.nomCateCult = nomCateCult;
        this.prix = prix;
    }

    public CategorieCulture(int idCategorieCulture, String nomCateCult, double prix) {
        this.idCategorieCulture = idCategorieCulture;
        this.nomCateCult = nomCateCult;
        this.prix = prix;
    }

    public CategorieCulture() {
    }

    public int getIdCategorieCulture() {
        return idCategorieCulture;
    }

    public void setIdCategorieCulture(int idCategorieCulture) {
        this.idCategorieCulture = idCategorieCulture;
    }

    public String getNomCateCult() {
        return nomCateCult;
    }

    public void setNomCateCult(String nomCateCult) {
        this.nomCateCult = nomCateCult;
    }

    public double getprix() {
        return prix;
    }

    public void setprix(double prix) {
        this.prix = prix;
    }


    public CategorieCulture[] selectCategorieCulture(Connexion c) throws Exception
    {
        try {
            c=new Connexion();
            Connection cc = c.getConnection();
            System.out.println(cc);
            Statement stmt = cc.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM categorieculture"); 
            Vector<CategorieCulture> v=new Vector<CategorieCulture>();
            while(rs.next())
            {
                v.add(new CategorieCulture(rs.getInt(1),rs.getString(2),rs.getDouble(3)));
            }
            CategorieCulture[] lCategorieCulture=new CategorieCulture[v.size()];
            v.copyInto(lCategorieCulture);
            stmt.close();
            cc.close();
            return lCategorieCulture;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }    
    }

    public void insertCategorieCulture(CategorieCulture inserer, Connexion c) throws Exception {
        try {
            Connection cc = c.getConnection();
            String query = "INSERT INTO CategorieCulture(nomcatecult,prix) VALUES (?,?)";
            
            try (PreparedStatement pstmt = cc.prepareStatement(query)) {
                pstmt.setString(1, inserer.getNomCateCult());
                pstmt.setDouble(2, inserer.getprix());
                int rep = pstmt.executeUpdate();
                System.out.println(rep);
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCategorieCulture(int idCateCult, String nouveauNom, double nouveauPrix, Connexion c) throws Exception {
        try {
            Connection cc = c.getConnection();
            String query = "UPDATE CategorieCulture SET nomcatecult=?, prix=? WHERE idcatecult=?";
            System.out.println("query"+query);
            try (PreparedStatement pstmt = cc.prepareStatement(query)) {
                pstmt.setString(1, nouveauNom);
                pstmt.setDouble(2, nouveauPrix);
                pstmt.setInt(3, idCateCult);
                
                int rep = pstmt.executeUpdate();
                System.out.println(rep);
            } 
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    

    public void deleteCategorieCulture(int id, Connexion c) throws Exception {
        try {
            Connection cc = c.getConnection();
            String query = "DELETE FROM categorieculture WHERE idcatecult=?";
            
            try (PreparedStatement pstmt = cc.prepareStatement(query)) {
                pstmt.setInt(1, id);
                
                int rep = pstmt.executeUpdate();
                System.out.println(rep);
            } 
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
