/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlleur;

import java.util.ArrayList;
import model.dao.ClientDAO;
import model.entity.Client;

/**
 *
 * @author tobby
 */
public class ClientControlleur {
    private ArrayList<Client> listeClient;
    private ClientDAO clientDAO = new ClientDAO();

    public ClientControlleur() {
        listeClient = new ArrayList<>();
    }

    public ArrayList<Client> getListeClient() {
        listeClient = clientDAO.getAll();
        return listeClient;
    }

    public void setListeClient(ArrayList<Client> listeClient) {
        this.listeClient = listeClient;
    }
    
    public boolean createClient(Client client){
        try{
            clientDAO.create(client);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    
}
