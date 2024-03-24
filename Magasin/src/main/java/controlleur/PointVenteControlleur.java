/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlleur;

import java.util.ArrayList;
import model.dao.AdresseDAO;
import model.dao.PointVenteDAO;
import model.dao.ProduitDAO;
import model.dao.ProduitPointVenteDAO;
import model.entity.Adresse;
import model.entity.PointVente;
import model.entity.Produit;
import model.entity.ProduitPointVente;

/**
 *
 * @author tobby
 */
public class PointVenteControlleur {
    
    private ArrayList<PointVente> listePointVentes;
    private AdresseDAO adresseDAO = new AdresseDAO();
    private PointVenteDAO pointVenteDAO = new PointVenteDAO();
    private ProduitDAO produitDAO = new ProduitDAO();
    private ProduitPointVenteDAO ppVenteDAO = new ProduitPointVenteDAO();

    public PointVenteControlleur() {
        listePointVentes = new ArrayList<>();
    }

    public ArrayList<PointVente> getListePointVentes() {
        listePointVentes = pointVenteDAO.getAll();
        return listePointVentes;
    }

    public void setListePointVentes(ArrayList<PointVente> listePointVentes) {
        this.listePointVentes = listePointVentes;
    }
    
    public void createPointVente(PointVente pointVente, Adresse adresse){
        int id = 0, temp = 0;
        id = adresseDAO.create(adresse);
        if(id != 0){
            adresse.setId(id);
            pointVente.setAdresse(adresse);
            temp = pointVenteDAO.create(pointVente);
            pointVente.setNumero(temp);
            listePointVentes.add(pointVente);
        }
    }
    public boolean ajouterProduitToPointVente(ProduitPointVente ppVente, int idProduit, int idPointVente){
        int id = -1;
        Produit produit ;
        PointVente pointVente;
        produit = produitDAO.find(idProduit);
        pointVente = pointVenteDAO.find(idPointVente);
        if(produit != null && pointVente != null){
            ppVente.setProduit(produit);
            ppVente.setPointVente(pointVente);
            id = ppVenteDAO.create(ppVente);
        }
        if(id == -1){
            return false;
        }else{
            return true;
        }
    }
    /**
     *  Renvoie un produitpointvente à partir d'un id
     * @param ppventes
     * @param id
     * @return ProduitPointVente
     */
    public ProduitPointVente getProduitPointVenteById(ArrayList<ProduitPointVente> ppventes, int id){
        for (ProduitPointVente ppVente : ppventes) {
            if(ppVente.getId() == id){
                return ppVente;
            }
        }
        return null;
    }
    
}
