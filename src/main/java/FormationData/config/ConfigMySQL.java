package FormationData.config;

import java.sql.*;

public class ConfigMySQL {

    private static ConfigMySQL instance = null;

    // Attributs de connexion
    private String url;
    private String username;
    private String password;


    private Connection connection;

    // en privé pour empêcher une instanciation depuis l'extérieur
    private ConfigMySQL(){
    }

    public static ConfigMySQL getInstance(){
        // La première fois qu'on appelle la méthode
        if(instance == null){
            instance = new ConfigMySQL();
        }
        return instance;
    }

    /**
     * Fonction qui enregistre les paramètres de connexion
     */
    public void connectionDAO(){
        this.url = "jdbc:mysql://localhost:3306/testJDBC";
        this.username = "amine";
        this.password = "G[=?dp\\$:;";

    }

    // Demande de connection
    public Connection getConnection() throws SQLException {
        // Chargement du Driver JDBC
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
        connectionDAO();
        this.connection = DriverManager.getConnection(this.url, this.username, this.password);

        return this.connection;
    }

    // Fermeture de la connexion
    /**
     * Fonction qui permet de se déconnecter de la connexion
     */
    public void seDeconnecter(){
        try {
            if(this.connection != null){
                this.connection.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public void closeResultEtPS(ResultSet result, PreparedStatement ps){
        if(result != null){
            try {
                result.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(ps != null){
            try{
                ps.close();
            } catch(SQLException e2){
                e2.printStackTrace();
            }
        }
    }
}
