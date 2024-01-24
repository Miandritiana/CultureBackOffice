package back.backoffice_culture.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Parcelle {
    int idParcelle;
    String nomParcelle;
    int taille;

    public Parcelle(String nomParcelle, int taille) {
        this.nomParcelle = nomParcelle;
        this.taille = taille;
    }
    public Parcelle(int idParcelle, String nomParcelle, int taille) {
        this.idParcelle = idParcelle;
        this.nomParcelle = nomParcelle;
        this.taille = taille;
    }
    public Parcelle() {
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
    public int getTaille() {
        return taille;
    }
    public void setTaille(int taille) {
        this.taille = taille;
    }

    public Parcelle[] selectParcelle(Connexion c) throws Exception
    {
        try {
            c=new Connexion();
            Connection cc = c.getConnection();
            System.out.println(cc);
            Statement stmt = cc.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Parcelle"); 
            Vector<Parcelle> v=new Vector<Parcelle>();
            while(rs.next())
            {
                v.add(new Parcelle(rs.getInt(1),rs.getString(2),rs.getInt(3)));
            }
            Parcelle[] lParcelle=new Parcelle[v.size()];
            v.copyInto(lParcelle);
            stmt.close();
            cc.close();
            return lParcelle;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }    
    }

    public void insertParcelle(Parcelle inserer, Connexion c) throws Exception {
        try {
            Connection cc = c.getConnection();
            String query = "INSERT INTO Parcelle(nomp,taille) VALUES (?,?)";
            
            try (PreparedStatement pstmt = cc.prepareStatement(query)) {
                pstmt.setString(1, inserer.getNomParcelle());
                pstmt.setInt(2, inserer.getTaille());
                int rep = pstmt.executeUpdate();
                System.out.println(rep);
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
