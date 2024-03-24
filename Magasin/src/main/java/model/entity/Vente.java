package model.entity;

import java.sql.Date;

public class Vente {
    private int id;
    private int quantite;
    private double sousTotal;
    private Date date;
    private ProduitPointVente produitPointVente;
    private Client client;
    private Personnel personnel;

    public Vente() {
    }

    public Vente(int id, int quantite, double sousTotal, Date date, ProduitPointVente produitPointVente, Client client, Personnel personnel) {
        this.setId(id);
        this.setQuantite(quantite);
        this.setSousTotal(sousTotal);
        this.setDate(date);
        this.setProduitPointVente(produitPointVente);
        this.setClient(client);
        this.setPersonnel(personnel);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getSousTotal() {
        return sousTotal;
    }

    public void setSousTotal(double sousTotal) {
        this.sousTotal = sousTotal;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ProduitPointVente getProduitPointVente() {
        return produitPointVente;
    }

    public void setProduitPointVente(ProduitPointVente produitPointVente) {
        this.produitPointVente = produitPointVente;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    @Override
    public String toString() {
        return "Vente{" +
                "id=" + id +
                ", quantite=" + quantite +
                ", sousTotal=" + sousTotal +
                ", date=" + date +
                ", produitPointVente=" + produitPointVente +
                ", client=" + client +
                ", personnel=" + personnel +
                '}';
    }
}
