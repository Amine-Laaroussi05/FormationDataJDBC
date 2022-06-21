package FormationData.dao.sql;

import FormationData.config.ConfigMySQL;
import FormationData.dao.PersonneDAO;
import FormationData.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonneSQL implements PersonneDAO {
    @Override
    public List<Personne> findAll() {
        // Créer ma Liste à récupérer
        List<Personne> personnes = new ArrayList<Personne>();
        // Pour communiquer j'ai besoin de 3 infos:
        Connection connection;
        PreparedStatement ps = null;
        ResultSet result = null;

        // Ouvrir ma connexion
        try {
            connection = ConfigMySQL.getInstance().getConnection();
            // Préparer ma requête
            ps = connection.prepareStatement("SELECT id, civilite, nom, prenom, email, adresse FROM Personne");
            // Exécuter la requête avec mon interprète
            result = ps.executeQuery();
            // parcours ma liste de résultat avec une boucle
            while(result.next()){
                // Pour chaque ligne obj sql: je crée une personne obj java
                Long id = result.getLong("id");
                String civiliteLabel = result.getString("civilite");
                Civilite civilite = Civilite.valueOf(civiliteLabel);
                String nom = result.getString("nom");
                String prenom = result.getString("prenom");
                String email = result.getString("email");
                Long adresseID = result.getLong("adresse");
                AdresseSQL adresseSQL = new AdresseSQL();
                Adresse adresse = adresseSQL.find(adresseID);
                Personne findPersonne = new Stagiaire(id, civilite, nom, prenom, email, null);
                findPersonne.setAdresse(adresse);
                personnes.add(findPersonne);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            // Fermer les instances de communications
            ConfigMySQL.getInstance().closeResultEtPS(result, ps);
            ConfigMySQL.getInstance().seDeconnecter();;
        }

        return personnes;
    }

    @Override
    public Personne find(Long id) {
        // Créer un utilisateur à récupérer
        Personne personne = new Stagiaire();
        // Pour communiquer j'ai besoin de 3 infos:
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet result = null;

        // Ouvrir ma connexion
        try {
            connection = ConfigMySQL.getInstance().getConnection();
            // Préparer ma requête
            ps = connection.prepareStatement("SELECT id, civilite, nom, prenom, email, adresse FROM Personne WHERE id = ?");
            ps.setLong(1,id);
            // Exécuter la requête avec mon interprète
            result = ps.executeQuery();
            // parcours ma liste de résultat avec une boucle
            if(result.next()){
                // Pour chaque ligne obj sql: je crée une adresse obj java
                String civiliteLabel = result.getString("civilite");
                Civilite civilite = Civilite.valueOf(civiliteLabel);
                String nom = result.getString("nom");
                String prenom = result.getString("prenom");
                String email = result.getString("email");
                Long adresseID = result.getLong("adresse");
                AdresseSQL adresseSQL = new AdresseSQL();
                Adresse adresse = adresseSQL.find(adresseID);
                personne = new Stagiaire(id, civilite, nom, prenom, email, null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            // Fermer les instances de communications
            ConfigMySQL.getInstance().closeResultEtPS(result, ps);
            ConfigMySQL.getInstance().seDeconnecter();;
        }

        return personne;
    }

    @Override
    public void create(Personne obj) {
// Pour communiquer j'ai besoin de 2 infos:
        Connection connection;
        PreparedStatement ps = null;

        // Ouvrir ma connexion
        try {
            connection = ConfigMySQL.getInstance().getConnection();
            // Préparer ma requête
            ps = connection.prepareStatement("INSERT INTO Personne (id, civilite, nom, prenom, email, adresse) VALUES (?,?,?,?,?,?)");
            ps.setLong(1, obj.getId());
            ps.setString(2,obj.getCivilite().getLabel());
            ps.setString(3, obj.getNom());
            ps.setString(4,obj.getPrenom());
            ps.setString(5, obj.getEmail());
            Long adresseID = obj.getAdresse().getId();
            ps.setLong(6, adresseID);


            // Exécuter la requête avec mon interprète
            int rows = ps.executeUpdate();
            if(rows != 1){
                throw new Exception("problème à la création de la personne:" + obj);

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
    public void update(Personne obj) {
// Pour communiquer j'ai besoin de 2 infos:
        Connection connection;
        PreparedStatement ps = null;

        // Ouvrir ma connexion
        try {
            connection = ConfigMySQL.getInstance().getConnection();
            // Préparer ma requête
            ps = connection.prepareStatement("UPDATE Personne SET civilite = ?, nom = ?, prenom = ?, email = ?, adresse = ? WHERE id = ?");
            ps.setString(1,obj.getCivilite().getLabel());
            ps.setString(2, obj.getNom());
            ps.setString(3,obj.getPrenom());
            ps.setString(4, obj.getEmail());
            Long adresseID = obj.getAdresse().getId();
            ps.setLong(5, adresseID);
            ps.setLong(6, obj.getId());

            // Exécuter la requête avec mon interprète
            int rows = ps.executeUpdate();
            if(rows != 1){
                throw new Exception("problème à la modification de la personne:" + obj);

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
            ps = connection.prepareStatement("DELETE FROM Personne WHERE id = ?");
            ps.setLong(1, id);


            // Exécuter la requête avec mon interprète
            int rows = ps.executeUpdate();
            if(rows != 1){
                throw new Exception("problème à la suppression de la personne avec l'id:" + id);

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

