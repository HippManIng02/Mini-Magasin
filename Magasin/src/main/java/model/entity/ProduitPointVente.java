package model.entity;

public class ProduitPointVente {
    private int id;
    private PointVente pointVente;
    private Produit produit;
    private int quantite;
    private boolean estActif = true;

    public ProduitPointVente() {
    }

    public ProduitPointVente(PointVente pointVente, Produit produit, int quantite, boolean estActif) {
        this.setPointVente(pointVente);
        this.setProduit(produit);
        this.setQuantite(quantite);
        this.setEstActif(estActif);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public PointVente getPointVente() {
        return pointVente;
    }

    public void setPointVente(PointVente pointVente) {
        this.pointVente = pointVente;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public boolean getEstActif() {
        return estActif;
    }

    public void setEstActif(boolean estActif) {
        this.estActif = estActif;
    }

    @Override
    public String toString() {
        return "ProduitPointVente{" +
                "pointVente=" + pointVente +
                ", produit=" + produit +
                ", quantite=" + quantite +
                ", estActif=" + estActif +
                '}';
    }
}
