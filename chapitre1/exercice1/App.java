import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) throws Exception {
        // Information de connexion
        final String url = "jdbc:postgresql://192.168.1.12/";
        final String user = "postgres";
        final String password = "password";

        // Connexion à la base de données.
        Connection connexion = null;
        Statement statement = null;
        try {
            connexion = DriverManager.getConnection(url, user, password);
            System.out.println("Connecté au serveur de gestion de la base de données : " + url);
            statement = connexion.createStatement();
            try {
                String requeteSQLCreate = "CREATE DATABASE test1";
                statement.executeUpdate(requeteSQLCreate);
            } catch (SQLException e) {
                System.out.println("Base de données test1 déjà existante.\n" + e.getMessage());
            }
            // On ferme la connexion existante pour se connecter sur la base de données test1.
            statement.close();
            connexion.close();
            connexion = DriverManager.getConnection(url + "test1", user, password);
            statement = connexion.createStatement();
            
            // Création de la table Section
            String requeteSQLTableSection = "CREATE TABLE IF NOT EXISTS Section (id SERIAL PRIMARY KEY, nom VARCHAR(30))";
            statement.executeUpdate(requeteSQLTableSection);
            
            // Insertion des valeurs dans la table Section
            String requeteSQLValeurSection = "INSERT INTO Section (nom) VALUES ('Informatiques de gestion'),('Droit')";
            statement.executeUpdate(requeteSQLValeurSection);
            
            // Création de la table Cours
            String requeteSQLTableCours = "CREATE TABLE IF NOT EXISTS Cours (id SERIAL PRIMARY KEY, id_section INTEGER, nom VARCHAR(30), CONSTRAINT fk_section FOREIGN KEY (id_section) REFERENCES Section(id))";
            statement.executeUpdate(requeteSQLTableCours);
            
            // Récupération des id pour la section Droit et Informatiques de gestion
            String requeteSQLIdSection = "SELECT id, nom FROM section where nom in ('Informatiques de gestion','Droit')";
            ResultSet resultSet = statement.executeQuery(requeteSQLIdSection);
            int idDroit = 0;
            int idInf = 0;
            while(resultSet.next()){
                if (resultSet.getString(2).equals("Droit")){
                    idDroit = resultSet.getInt(1);
                } else{
                    idInf = resultSet.getInt(1);
                }
            }
            if (idDroit == 0 || idInf == 0){
                throw new Exception("Recupération des identifiants de section incorrecte.");
            }
            // Insertion des valeurs dans la table Cours
            String requeteSQLValeurCours = "INSERT INTO Cours (id_section, nom) VALUES (" + idInf + ", 'Base des réseaux'), (" + idInf + ", 'Systèmes d exploitation'), (" + idInf + ", 'Programmation orienté objet'),(" + idDroit + ", 'Droit civil'),(" + idDroit + ", 'Droit commercial')";
            statement.executeUpdate(requeteSQLValeurCours);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // Toujours fermer les requêtes.
            if (statement != null) {
                statement.close();
            }

            // Toujours fermer la connexion à la base de données.
            if (connexion != null) {
                connexion.close();
            }
        }
    }
}