package model.entity;

public class Produit {
    private int id;
    private String nom;
    private String description;
    private String pays;
    private float tva;
    private int seuil;
    private boolean estPesable;
    private float unitePoids;
    private TypeProduit typeProduit;

    public Produit() {
    }

    public Produit(int id, String nom, String description, String pays, float tva, int seuil, boolean estPesable, float unitePoids, TypeProduit typeProduit) {
        this.setId(id);
        this.setNom(nom);
        this.setDescription(description);
        this.setPays(pays);
        this.setTva(tva);
        this.setSeuil(seuil);
        this.setEstPesable(estPesable);
        this.setUnitePoids(unitePoids);
        this.setTypeProduit(typeProduit);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public float getTva() {
        return tva;
    }

    public void setTva(float tva) {
        this.tva = tva;
    }

    public int getSeuil() {
        return seuil;
    }

    public void setSeuil(int seuil) {
        this.seuil = seuil;
    }

    public boolean getEstPesable() {
        return estPesable;
    }

    public void setEstPesable(boolean estPesable) {
        this.estPesable = estPesable;
    }

    public float getUnitePoids() {
        return unitePoids;
    }

    public void setUnitePoids(float unitePoids) {
        this.unitePoids = unitePoids;
    }

    public TypeProduit getTypeProduit() {
        return typeProduit;
    }

    public void setTypeProduit(TypeProduit typeProduit) {
        this.typeProduit = typeProduit;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", pays='" + pays + '\'' +
                ", tva=" + tva +
                ", estPesable=" + estPesable +
                ", unitePoids=" + unitePoids +
                '}';
    }


}
