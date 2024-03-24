/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlleur;

import java.sql.Date;
import java.util.ArrayList;
import model.dao.PrixDAO;
import model.dao.ProduitDAO;
import model.dao.TypeProduitDAO;
import model.entity.Prix;
import model.entity.Produit;

/**
 *
 * @author tobby
 */
public class ProduitControlleur {
    
    private ArrayList<Produit> listeProduits;
    private ProduitDAO produitDAO = new ProduitDAO();
    private PrixDAO prixDAO = new PrixDAO();
    private ArrayList<Prix> produits = new ArrayList<>();
    private TypeProduitDAO typeProduitDAO = new TypeProduitDAO();
    
    public ProduitControlleur() {
        listeProduits = new ArrayList<>();
        produits = prixDAO.getAll();
    }

    public ArrayList<Produit> getListeProduits() {
        listeProduits = produitDAO.getAll();
        return listeProduits;
    }

    public void setListeProduits(ArrayList<Produit> listeProduits) {
        this.listeProduits = listeProduits;
    }

    public ArrayList<Prix> getProduits() {
        return produits;
    }

    public void setProduits(ArrayList<Prix> produits) {
        this.produits = produits;
    }
    
    
    public void createProduit(Produit produit, Prix prix){
        int id = 0;
        id = produitDAO.create(produit);
        if(id != 0){
            produit.setId(id);
            prix.setProduit(produit);
            prix.setDateApplication(new Date(new java.util.Date().getTime()));
            prixDAO.create(prix);
            produits.add(prix);
        }
    }
    /**
     * Méthode qui renvoie le prix à partir d'un produit
     * @param produit
     * @return Prix
     */
    public Prix getProduitPrix(Produit produit){
        for (Prix prix : produits) {
            if(prix.getProduit().getId() == produit.getId()){
                return prix;
            }
        }
        return null;
    }
    
    
}
