package FormationData.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Formateur extends Personne{
    private Date dtEmbauche;
    private int experience;
    private boolean interne;

    private List<Stagiaire> stagiaires = new ArrayList<Stagiaire>();

    private List<Matiere> competences = new ArrayList<Matiere>();

    public Formateur() {
        super();
    }

    public Formateur(Civilite civilite, String nom, String prenom, String email, Date dtEmbauche, int experience,
                     boolean interne) {
        super(civilite, nom, prenom, email);
        this.dtEmbauche = dtEmbauche;
        this.experience = experience;
        this.interne = interne;
    }

    public Formateur(Long id, Civilite civilite, String nom, String prenom, String email, Date dtEmbauche, int experience,
                     boolean interne) {
        super(id, civilite, nom, prenom, email);
        this.dtEmbauche = dtEmbauche;
        this.experience = experience;
        this.interne = interne;
    }

    public Date getDtEmbauche() {
        return dtEmbauche;
    }

    public void setDtEmbauche(Date dtEmbauche) {
        this.dtEmbauche = dtEmbauche;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public boolean isInterne() {
        return interne;
    }

    public void setInterne(boolean interne) {
        this.interne = interne;
    }

    public List<Stagiaire> getStagiaires() {
        return stagiaires;
    }

    public void setStagiaires(List<Stagiaire> stagiaires) {
        this.stagiaires = stagiaires;
    }

    public List<Matiere> getCompetences() {
        return competences;
    }

    public void setCompetences(List<Matiere> competences) {
        this.competences = competences;
    }
}
