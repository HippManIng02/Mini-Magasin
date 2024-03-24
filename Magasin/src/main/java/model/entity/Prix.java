package model.entity;


import java.sql.Date;

public class Prix {
    private int id;
    private double montant;
    private Date dateApplication;
    private Date dateFinApplication;
    private Produit produit;

    public Prix() {
    }

    public Prix(int id, double montant, Date dateApplication, Date dateFinApplication, Produit produit) {
        this.setId(id);
        this.setMontant(montant);
        this.setDateApplication(dateApplication);
        this.setDateFinApplication(dateFinApplication);
        this.setProduit(produit);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getDateApplication() {
        return dateApplication;
    }

    public void setDateApplication(Date dateApplication) {
        this.dateApplication = dateApplication;
    }

    public Date getDateFinApplication() {
        return dateFinApplication;
    }

    public void setDateFinApplication(Date dateFinApplication) {
        this.dateFinApplication = dateFinApplication;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    @Override
    public String toString() {
        return "Prix{" +
                "id=" + id +
                ", montant=" + montant +
                ", dateApplication=" + dateApplication +
                ", dateFinApplication=" + dateFinApplication +
                ", produit=" + produit +
                '}';
    }
}
