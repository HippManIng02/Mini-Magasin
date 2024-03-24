package model.entity;

public class PointVente {
    private int numero;
    private String nom;
    private Adresse adresse;

    public PointVente() {
    }

    public PointVente(int numero, String nom, Adresse adresse) {
        this.setNumero(numero);
        this.setNom(nom);
        this.setAdresse(adresse);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "PointVente{" +
                ", numero=" + numero +
                ", nom='" + nom + '\'' +
                ", adresse=" + adresse +
                '}';
    }
}
