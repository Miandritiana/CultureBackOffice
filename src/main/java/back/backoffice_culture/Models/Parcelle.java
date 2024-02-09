package back.backoffice_culture.Models;

import java.sql.Timestamp;
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

    public int insertParcelle(Parcelle inserer, Connexion c) throws Exception {
        int idParcelle = -1;  
        try {
            Connection cc = c.getConnection();
            String query = "INSERT INTO Parcelle(nomp, taille) VALUES (?,?)";

            try (PreparedStatement pstmt = cc.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, inserer.getNomParcelle());
                pstmt.setInt(2, inserer.getTaille());
                int rep = pstmt.executeUpdate();

                if (rep > 0) {
                    ResultSet generatedKeys = pstmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        idParcelle = generatedKeys.getInt(1);
                    } else {
                        throw new Exception("Échec de la récupération de l'ID de la parcelle après insertion.");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return idParcelle;
    }

    public void insertParcelleCulture(String nomP,int taille,int idTerrain,Connexion c)
    {
        try {
            Parcelle Parcelle = new Parcelle(nomP,taille);
            int idParcelle=Parcelle.insertParcelle(Parcelle, c);

            // ParcelleCulture ParcelleCulture = new ParcelleCulture(daty,idParcelle,idCateCult);
            // ParcelleCulture.insertPC(ParcelleCulture,c);

            TerrainParcelle TerrainParcelle = new TerrainParcelle(idTerrain,idParcelle);
            TerrainParcelle.insertTerrainParcelle(TerrainParcelle,c);

            // TerrainUser TerrainUser = new TerrainUser(idTerrain,idUser);
            // TerrainUser.insertTerrainUser(TerrainUser,c);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
