package FormationData.dao.sql;

import FormationData.config.ConfigMySQL;
import FormationData.dao.AdresseDAO;
import FormationData.model.Adresse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdresseSQL implements AdresseDAO {

    @Override
    public List<Adresse> findAll() {
        // Créer ma Liste à récupérer
        List<Adresse> adresses = new ArrayList<Adresse>();
        // Pour communiquer j'ai besoin de 3 infos:
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet result = null;

        // Ouvrir ma connexion
        try {
            connection = ConfigMySQL.getInstance().getConnection();
            // Préparer ma requête
            ps = connection.prepareStatement("SELECT id, rue, complement, codePostal, ville, pays  FROM Adresse");
            // Exécuter la requête avec mon interprète
            result = ps.executeQuery();
            // parcours ma liste de résultat avec une boucle
            while(result.next()){
                // Pour chaque ligne obj sql: je crée une adresse obj java
                Long id = result.getLong("id");
                String rue = result.getString("rue");
                String complement = result.getString("complement");
                String codePostal = result.getString("codePostal");
                String ville = result.getString("ville");
                String pays = result.getString("pays");
                Adresse findAdresse = new Adresse(id, rue, complement, codePostal, ville, pays);
                adresses.add(findAdresse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            // Fermer les instances de communications
            ConfigMySQL.getInstance().closeResultEtPS(result, ps);
            ConfigMySQL.getInstance().seDeconnecter();;
        }

        return adresses;
    }

    @Override
    public Adresse find(Long id) {
        // Créer un utilisateur à récupérer
        Adresse adresse = new Adresse();
        // Pour communiquer j'ai besoin de 3 infos:
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet result = null;

        // Ouvrir ma connexion
        try {
            connection = ConfigMySQL.getInstance().getConnection();
            // Préparer ma requête
            ps = connection.prepareStatement("SELECT id, rue, complement, codePostal, ville, pays FROM Adresse WHERE id = ?");
            ps.setLong(1,id);
            // Exécuter la requête avec mon interprète
            result = ps.executeQuery();
            // parcours ma liste de résultat avec une boucle
            if(result.next()){
                // Pour chaque ligne obj sql: je crée une adresse obj java
                String rue = result.getString("rue");
                String complement = result.getString("complement");
                String codePostal = result.getString("codePostal");
                String ville = result.getString("ville");
                String pays = result.getString("pays");
                adresse = new Adresse(id, rue, complement, codePostal, ville, pays);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            // Fermer les instances de communications
            ConfigMySQL.getInstance().closeResultEtPS(result, ps);
            ConfigMySQL.getInstance().seDeconnecter();;
        }

        return adresse;
    }

    @Override
    public void create(Adresse obj) {
// Pour communiquer j'ai besoin de 2 infos:
        Connection connection;
        PreparedStatement ps = null;

        // Ouvrir ma connexion
        try {
            connection = ConfigMySQL.getInstance().getConnection();
            // Préparer ma requête
            ps = connection.prepareStatement("INSERT INTO Adresse (id, rue, complement, codePostal, ville, pays) VALUES (?,?,?,?,?,?)");
            ps.setLong(1, obj.getId());
            ps.setString(2,obj.getRue());
            ps.setString(3, obj.getComplement());
            ps.setString(4,obj.getCodePostal());
            ps.setString(5, obj.getVille());
            ps.setString(6, obj.getPays());


            // Exécuter la requête avec mon interprète
            int rows = ps.executeUpdate();
            if(rows != 1){
                throw new Exception("problème à la création de l'adresse:" + obj);

            }
            // parcours ma liste de résultat avec une boucle
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            // Fermer les instances de communications
            ConfigMySQL.getInstance().closeResultEtPS(null, ps);
            ConfigMySQL.getInstance().seDeconnecter();
        }
    }

    @Override
    public void update(Adresse obj) {
// Pour communiquer j'ai besoin de 2 infos:
        Connection connection;
        PreparedStatement ps = null;

        // Ouvrir ma connexion
        try {
            connection = ConfigMySQL.getInstance().getConnection();
            // Préparer ma requête
            ps = connection.prepareStatement("UPDATE Adresse SET rue = ?, complement = ?, codePostal = ?, ville = ?, pays = ? WHERE id = ?");
            ps.setString(1,obj.getRue());
            ps.setString(2, obj.getComplement());
            ps.setString(3,obj.getCodePostal());
            ps.setString(4, obj.getVille());
            ps.setString(5, obj.getPays());
            ps.setLong(6, obj.getId());

            // Exécuter la requête avec mon interprète
            int rows = ps.executeUpdate();
            if(rows != 1){
                throw new Exception("problème à la modification de l'adresse:" + obj);

            }
            // parcours ma liste de résultat avec une boucle
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            // Fermer les instances de communications
            ConfigMySQL.getInstance().closeResultEtPS(null, ps);
            ConfigMySQL.getInstance().seDeconnecter();
        }
    }

    @Override
    public void delete(Long id) {
// Pour communiquer j'ai besoin de 2 infos:
        Connection connection;
        PreparedStatement ps = null;

        // Ouvrir ma connexion
        try {
            connection = ConfigMySQL.getInstance().getConnection();
            // Préparer ma requête
            ps = connection.prepareStatement("DELETE FROM Adresse WHERE id = ?");
            ps.setLong(1, id);


            // Exécuter la requête avec mon interprète
            int rows = ps.executeUpdate();
            if(rows != 1){
                throw new Exception("problème à la suppression de l'adresse à l'id:" + id);

            }
            // parcours ma liste de résultat avec une boucle
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            // Fermer les instances de communications
            ConfigMySQL.getInstance().closeResultEtPS(null, ps);
            ConfigMySQL.getInstance().seDeconnecter();
        }
    }
    }

