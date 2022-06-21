package FormationData.model;

import java.util.Date;

public class Stagiaire extends Personne{
    private Date dtNaissance;

    private Formateur formateur;

    public Stagiaire() {
        super();
    }

    public Stagiaire(Civilite civilite, String nom, String prenom, String email, Date dtNaissance) {
        super(civilite, nom, prenom, email);
        this.dtNaissance = dtNaissance;
    }

    public Stagiaire(Long id, Civilite civilite, String nom, String prenom, String email, Date dtNaissance) {
        super(id, civilite, nom, prenom, email);
        this.dtNaissance = dtNaissance;
    }

    public Date getDtNaissance() {
        return dtNaissance;
    }

    public void setDtNaissance(Date dtNaissance) {
        this.dtNaissance = dtNaissance;
    }

    public Formateur getFormateur() {
        return formateur;
    }

    public void setFormateur(Formateur formateur) {
        this.formateur = formateur;
    }
}
