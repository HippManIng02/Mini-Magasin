/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlleur;

import java.sql.Date;
import java.util.ArrayList;
import model.dao.ProduitPointVenteDAO;
import model.dao.VenteDAO;
import model.entity.Personnel;
import model.entity.ProduitPointVente;
import model.entity.Vente;

/**
 *
 * @author tobby
 */
public class VenteControlleur {
    private ArrayList<Vente> listeVente;
    private VenteDAO venteDAO = new VenteDAO();
    private ProduitPointVenteDAO ppVDAO = new ProduitPointVenteDAO();

    public VenteControlleur() {
        listeVente = new ArrayList<>();
    }

    public ArrayList<Vente> getListeVente() {
        listeVente = venteDAO.getAll();
        return listeVente;
    }

    public void setListeVente(ArrayList<Vente> listeVente) {
        this.listeVente = listeVente;
    }
    /**
     * 
     * @param listeVente
     * @param idClient
     * @return 
     */
    public ArrayList<Vente> getVenteByClient(ArrayList<Vente> listeVente, int idClient){
        ArrayList<Vente> liste = new ArrayList<>();
        for (Vente vente : listeVente) {
            if(vente.getClient().getId() == idClient){
                liste.add(vente);
            }
        }
        return liste;
    }
    /**
     * 
     * @param listePersonnel
     * @param idPointVente
     * @return 
     */
    public ArrayList<Personnel> getPersonnelByPointVente(ArrayList<Personnel> listePersonnel, int idPointVente){
        ArrayList<Personnel> liste = new ArrayList<>();
        for (Personnel personnel : listePersonnel){
            if(personnel.getPointVente().getNumero() == idPointVente){
                liste.add(personnel);
            }
        }
        return liste;
    }
    /**
     * 
     * @param listeppVente
     * @param term
     * @param idPointVente
     * @return 
     */
    public ArrayList<ProduitPointVente> rechercheProduitParParam(ArrayList<ProduitPointVente> listeppVente, String term, int idPointVente){
        ArrayList<ProduitPointVente> liste = new ArrayList<>();
        for (ProduitPointVente produitPointVente : listeppVente) {
            if(produitPointVente.getPointVente().getNumero() == idPointVente){
                if(produitPointVente.getProduit().getNom().toLowerCase().contains(term.toLowerCase())){
                liste.add(produitPointVente);
             }
            }
            
        }
        return liste;
    }
    /**
     * 
     * @param listeppVente
     * @param term
     * @param idPointVente
     * @return 
     */
    public ArrayList<ProduitPointVente> rechercheProduitParParam(ArrayList<ProduitPointVente> listeppVente, int term, int idPointVente){
        ArrayList<ProduitPointVente> liste = new ArrayList<>();
        for (ProduitPointVente produitPointVente : listeppVente) {
            if(produitPointVente.getPointVente().getNumero() == idPointVente){
                if((""+produitPointVente.getProduit().getId()).contains(""+term)){
                liste.add(produitPointVente);
             }
            }
            
        }
        return liste;
    }
    /**
     * 
     * @param prix
     * @param quantite
     * @param tva
     * @return 
     */
    public double calculSousTotal(double prix, int quantite, float tva){
        double resultat = 0;
        resultat = (prix * quantite) + (prix * quantite* (tva/100));
        return resultat;
    }
    
    /**
     * Enrégistrement des informations lié à la vente
     * @param vente
     * @param ppVente
     * @param liste
     * @return 
     */
    public boolean createVente(Vente vente, ArrayList<Object[]> liste){
        ProduitPointVente ppV ;
        Date date = new Date(new java.util.Date().getTime());
       try{
            for (Object[] objects : liste) {
            ppV = new ProduitPointVente();
            ppV = ppVDAO.find(Integer.parseInt(objects[0].toString().trim()));
            vente.setProduitPointVente(ppV);
            vente.setDate(date);
            vente.setQuantite(Integer.parseInt(objects[2].toString().trim()));
            vente.setSousTotal(calculSousTotal(Double.parseDouble(objects[3].toString().trim()), vente.getQuantite(), ppV.getProduit().getTva()));
            venteDAO.create(vente);
            listeVente.add(vente);
            ppV.setQuantite(ppV.getQuantite() - vente.getQuantite());
            ppVDAO.update(ppV);
         }
            return true;
       }catch(Exception e){
           return false;
       }
        
    }
    
    public ArrayList<ProduitPointVente> getProduitPointVente(int id){
        ArrayList<ProduitPointVente> lppv = new ArrayList<>();
        for (ProduitPointVente produitPointVente : ppVDAO.getAll()) {
            if(produitPointVente.getId() == id ){
                lppv.add(produitPointVente);
            }
        }
        return lppv;
    }
    
}
