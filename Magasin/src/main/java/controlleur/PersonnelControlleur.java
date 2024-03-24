/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlleur;

import java.util.ArrayList;
import model.dao.AdresseDAO;
import model.dao.PersonnelDAO;
import model.dao.PointVenteDAO;
import model.entity.Adresse;
import model.entity.Personnel;
import model.entity.PointVente;

/**
 *
 * @author tobby
 */
public class PersonnelControlleur {
    private ArrayList<Personnel> listePersonnel;
    private PersonnelDAO personnelDAO = new PersonnelDAO();
    private AdresseDAO adresseDAO = new AdresseDAO();
    private PointVenteDAO pointVenteDAO = new PointVenteDAO();

    public PersonnelControlleur() {
        listePersonnel = new ArrayList<>();
    }

    public ArrayList<Personnel> getListePersonnel() {
        listePersonnel = personnelDAO.getAll();
        return listePersonnel;
    }

    public void setListePersonnel(ArrayList<Personnel> listePersonnel) {
        this.listePersonnel = listePersonnel;
    }
    
    /**
     * Ajout d'une personnel à la base de donnée
     */
    public boolean createPersonnel(Personnel personnel, Adresse adresse){
        int idAdresse = -1, temp = -1;
        try{
            idAdresse = adresseDAO.create(adresse);
            adresse.setId(idAdresse);
            personnel.setAdresse(adresse);
            temp = personnelDAO.create(personnel);
            personnel.setId(temp);
            listePersonnel.add(personnel);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    
    
    
}
