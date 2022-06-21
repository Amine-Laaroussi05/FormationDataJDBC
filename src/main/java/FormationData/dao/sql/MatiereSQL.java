package FormationData.dao.sql;

import FormationData.config.ConfigMySQL;
import FormationData.dao.MatiereDAO;
import FormationData.model.Adresse;
import FormationData.model.Difficulte;
import FormationData.model.Formateur;
import FormationData.model.Matiere;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatiereSQL implements MatiereDAO {

    @Override
    public List<Matiere> findAll() {
        // Créer ma Liste à récupérer
        List<Matiere> matieres = new ArrayList<Matiere>();
        // Pour communiquer j'ai besoin de 3 infos:
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet result = null;

        // Ouvrir ma connexion
        try {
            connection = ConfigMySQL.getInstance().getConnection();
            // Préparer ma requête
            ps = connection.prepareStatement("SELECT id, nom, duree FROM Matiere");
            // Exécuter la requête avec mon interprète
            result = ps.executeQuery();
            // parcours ma liste de résultat avec une boucle
            while(result.next()){
                // Pour chaque ligne obj sql: je crée une matière obj java
                Long id = result.getLong("id");
                String nom = result.getString("nom");
                Integer duree = result.getInt("duree");
                String difficulteLabel = result.getString("difficulte");
                Difficulte difficulte = Difficulte.valueOf(difficulteLabel);
                Matiere findMatiere = new Matiere(id, nom, duree, difficulte);
                matieres.add(findMatiere);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            // Fermer les instances de communications
            ConfigMySQL.getInstance().closeResultEtPS(result, ps);
            ConfigMySQL.getInstance().seDeconnecter();;
        }

        return matieres;
    }

    @Override
    public Matiere find(Long id) {
        // Créer un utilisateur à récupérer
        Matiere matiere = new Matiere();
        // Pour communiquer j'ai besoin de 3 infos:
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet result = null;

        // Ouvrir ma connexion
        try {
            connection = ConfigMySQL.getInstance().getConnection();
            // Préparer ma requête
            ps = connection.prepareStatement("SELECT id, nom, duree, difficulte FROM Matiere WHERE id = ?");
            ps.setLong(1,id);
            // Exécuter la requête avec mon interprète
            result = ps.executeQuery();
            // parcours ma liste de résultat avec une boucle
            if(result.next()){
                // Pour chaque ligne obj sql: je crée une matière obj java
                String nom = result.getString("nom");
                Integer duree = result.getInt("duree");
                String difficulteLabel = result.getString("difficulte");
                Difficulte difficulte = Difficulte.valueOf(difficulteLabel);
                matiere = new Matiere(id, nom, duree, difficulte);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            // Fermer les instances de communications
            ConfigMySQL.getInstance().closeResultEtPS(result, ps);
            ConfigMySQL.getInstance().seDeconnecter();;
        }

        return matiere;
    }

    @Override
    public void create(Matiere obj) {
// Pour communiquer j'ai besoin de 2 infos:
        Connection connection;
        PreparedStatement ps = null;

        // Ouvrir ma connexion
        try {
            connection = ConfigMySQL.getInstance().getConnection();
            // Préparer ma requête
            ps = connection.prepareStatement("INSERT INTO Matiere (id, nom, duree, difficulte) VALUES (?,?,?,?,?)");
            ps.setLong(1, obj.getId());
            ps.setString(2,obj.getNom());
            ps.setLong(3, obj.getDuree());
            Difficulte difficulte = obj.getDifficulte();
            String difficulteLabel = difficulte.toString();
            ps.setString(4,difficulteLabel);


            // Exécuter la requête avec mon interprète
            int rows = ps.executeUpdate();
            if(rows != 1){
                throw new Exception("problème à la création de la matière:" + obj);

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
    public void update(Matiere obj) {
// Pour communiquer j'ai besoin de 2 infos:
        Connection connection;
        PreparedStatement ps = null;

        // Ouvrir ma connexion
        try {
            connection = ConfigMySQL.getInstance().getConnection();
            // Préparer ma requête
            ps = connection.prepareStatement("UPDATE Matiere SET nom = ?, duree = ?, difficulte = ? WHERE id = ?");
            ps.setString(1,obj.getNom());
            ps.setLong(2, obj.getDuree());
            String difficulteLabel = obj.getDifficulte().toString();
            ps.setString(3,difficulteLabel);
            ps.setLong(4, obj.getId());

            // Exécuter la requête avec mon interprète
            int rows = ps.executeUpdate();
            if(rows != 1){
                throw new Exception("problème à la modification de la matière:" + obj);

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
            ps = connection.prepareStatement("DELETE FROM Matiere WHERE id = ?");
            ps.setLong(1, id);


            // Exécuter la requête avec mon interprète
            int rows = ps.executeUpdate();
            if(rows != 1){
                throw new Exception("problème à la suppression de la matière à l'id:" + id);

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
