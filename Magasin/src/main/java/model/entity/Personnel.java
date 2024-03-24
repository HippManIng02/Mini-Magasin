package model.entity;

public class Personnel extends Personne{
    private int numeroBadge;
    private Personnel personnel;
    private Adresse adresse;
    private Fonction poste;
    private PointVente pointVente;

    public Personnel() {
    }

    public Personnel(int id, String nom, String prenom, String email, int numeroBadge, Personnel personnel, Adresse adresse, Fonction poste, PointVente pointVente) {
        super(id, nom, prenom, email);
        this.setNumeroBadge(numeroBadge);
        this.setPersonnel(personnel);
        this.setAdresse(adresse);
        this.setPoste(poste);
        this.setPointVente(pointVente);
    }

    public int getNumeroBadge() {
        return numeroBadge;
    }

    public void setNumeroBadge(int numeroBadge) {
        this.numeroBadge = numeroBadge;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Fonction getPoste() {
        return poste;
    }

    public void setPoste(Fonction poste) {
        this.poste = poste;
    }

    public PointVente getPointVente() {
        return pointVente;
    }

    public void setPointVente(PointVente pointVente) {
        this.pointVente = pointVente;
    }

    @Override
    public String toString() {
        return "Personnel{" +
                "numeroBadge=" + numeroBadge +
                ", personnel=" + personnel +
                ", id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
