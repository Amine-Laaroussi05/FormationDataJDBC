package FormationData.model;

import java.util.ArrayList;
import java.util.List;

public class Matiere {
    private Long id;
    private String nom;
    private int duree;
    private Difficulte difficulte;

    private List<Formateur> formateurs = new ArrayList<Formateur>();

    public Matiere() {
        super();
    }

    public Matiere(String nom, int duree, Difficulte difficulte) {
        super();
        this.nom = nom;
        this.duree = duree;
        this.difficulte = difficulte;
    }

    public Matiere(Long id, String nom, int duree, Difficulte difficulte) {
        super();
        this.id = id;
        this.nom = nom;
        this.duree = duree;
        this.difficulte = difficulte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Difficulte getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(Difficulte difficulte) {
        this.difficulte = difficulte;
    }

    public List<Formateur> getFormateurs() {
        return formateurs;
    }

    public void setFormateurs(List<Formateur> formateurs) {
        this.formateurs = formateurs;
    }
}
