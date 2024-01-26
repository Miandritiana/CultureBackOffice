package back.backoffice_culture.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class User {
    int iduser;
    String nomuser;
    String password;

    public User(String nomuser, String password) {
        this.nomuser = nomuser;
        this.password = password;
    }
    public User(int iduser, String nomuser, String password) {
        this.iduser = iduser;
        this.nomuser = nomuser;
        this.password = password;
    }
    public User() {
    }
    public int getIduser() {
        return iduser;
    }
    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
    public String getNomuser() {
        return nomuser;
    }
    public void setNomuser(String nomuser) {
        this.nomuser = nomuser;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    } 

    public User[] selectUser(Connexion c) throws Exception
    {
        try {
            c=new Connexion();
            Connection cc = c.getConnection();
            System.out.println(cc);
            Statement stmt = cc.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM useruser"); 
            Vector<User> v=new Vector<User>();
            while(rs.next())
            {
                v.add(new User(rs.getInt(1),rs.getString(2),rs.getString(3)));
            }
            User[] lUser=new User[v.size()];
            v.copyInto(lUser);
            stmt.close();
            cc.close();
            return lUser;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }    
    }

    public void insertUser(User inserer, Connexion c) throws Exception {
        try {
            Connection cc = c.getConnection();
            String query = "INSERT INTO useruser(nomuser,password) VALUES (?,?)";
            
            try (PreparedStatement pstmt = cc.prepareStatement(query)) {
                pstmt.setString(1, inserer.getNomuser());
                pstmt.setString(2, inserer.getPassword());
                int rep = pstmt.executeUpdate();
                System.out.println(rep);
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int checkLogin(Connexion c, String nom, String passW) throws Exception {
        try (
            Connection cc = c.getConnection();
            Statement statement = cc.createStatement()) {
    
            String requete = "SELECT * FROM useruser WHERE nomuser = '" + nom + "' AND password = '" + passW + "'";
            System.out.println("requete"+requete);
            ResultSet resultat = statement.executeQuery(requete);
    
            while (resultat.next()) {
                return resultat.getInt("idUser");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Tsy azo le user....");
        }    
        return -1;
    }

    public boolean isAdmin(Connexion c, int idUser) throws Exception {
        try (Connection cc = c.getConnection();
            Statement statement = cc.createStatement()) {
    
            String requete = "SELECT idAdmin FROM useruser WHERE iduser = " + idUser;
            ResultSet resultat = statement.executeQuery(requete);
    
            if (resultat.next()) {
                return resultat.getBoolean("idAdmin");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error checking admin status");
        }
    
        return false;
    }

    public String getNomUserById(int idUser, Connexion c) throws Exception {
        String nomUtilisateur = null;
        try (Connection cc = c.getConnection();
             Statement statement = cc.createStatement()) {

            String requete = "SELECT nomuser FROM useruser WHERE iduser = " + idUser;
            ResultSet resultat = statement.executeQuery(requete);

            if (resultat.next()) {
                nomUtilisateur = resultat.getString("nomuser");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erreur lors de la récupération du nom de l'utilisateur");
        }

        return nomUtilisateur;
    }
}
