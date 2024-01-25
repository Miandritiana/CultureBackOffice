package back.backoffice_culture.Models;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class ParcelleCulture {
    
    int idParcelleCulture;
    Timestamp daty;
    int idParcelle;
    int idCateCult;
    int rendement;

    public ParcelleCulture(Timestamp daty, int idParcelle, int idCateCult, int rendement) {
        this.daty = daty;
        this.idParcelle = idParcelle;
        this.idCateCult = idCateCult;
        this.rendement = rendement;
    }

    public ParcelleCulture(int idParcelleCulture, Timestamp daty, int idParcelle, int idCateCult, int rendement) {
        this.idParcelleCulture = idParcelleCulture;
        this.daty = daty;
        this.idParcelle = idParcelle;
        this.idCateCult = idCateCult;
        this.rendement = rendement;
    }

    public ParcelleCulture() {
    }

    public int getIdParcelleCulture() {
        return idParcelleCulture;
    }

    public void setIdParcelleCulture(int idParcelleCulture) {
        this.idParcelleCulture = idParcelleCulture;
    }

    public Timestamp getDaty() {
        return daty;
    }

    public void setDaty(Timestamp daty) {
        this.daty = daty;
    }

    public int getIdParcelle() {
        return idParcelle;
    }

    public void setIdParcelle(int idParcelle) {
        this.idParcelle = idParcelle;
    }

    public int getIdCateCult() {
        return idCateCult;
    }

    public void setIdCateCult(int idCateCult) {
        this.idCateCult = idCateCult;
    }

    public int getRendement() {
        return rendement;
    }

    public void setRendement(int rendement) {
        this.rendement = rendement;
    }

    public ParcelleCulture[] selectParcelleCulture(Connexion c) throws Exception
    {
        try {
            c=new Connexion();
            Connection cc = c.getConnection();
            System.out.println(cc);
            Statement stmt = cc.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ParcelleCulture"); 
            Vector<ParcelleCulture> v=new Vector<ParcelleCulture>();
            while(rs.next())
            {
                v.add(new ParcelleCulture(rs.getInt(1),rs.getTimestamp(2),rs.getInt(3),rs.getInt(4),rs.getInt(5)));
            }
            ParcelleCulture[] lParcelleCulture=new ParcelleCulture[v.size()];
            v.copyInto(lParcelleCulture);
            stmt.close();
            cc.close();
            return lParcelleCulture;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }    
    }

    public void insertPC(ParcelleCulture inserer, Connexion c) throws Exception {
        try {
            Connection cc = c.getConnection();
            String query = "INSERT INTO ParcelleCulture(daty,idp,idcatecult,rendement) VALUES (?,?,?,?)";
            
            try (PreparedStatement pstmt = cc.prepareStatement(query)) {
                pstmt.setTimestamp(1, inserer.getDaty());
                pstmt.setInt(2, inserer.getIdParcelle());
                pstmt.setInt(3, inserer.getIdCateCult());
                pstmt.setInt(4, inserer.getRendement());
                int rep = pstmt.executeUpdate();
                System.out.println(rep);
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
