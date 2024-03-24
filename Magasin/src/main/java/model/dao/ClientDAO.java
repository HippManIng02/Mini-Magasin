package model.dao;

import model.entity.Client;
import model.entity.Personnel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClientDAO extends DAO<Client>{
    /**
     * renvoi tout les clients
     * @return 
     */
    @Override
    public ArrayList<Client> getAll() {
        ArrayList<Client> listes = new ArrayList<>();
        Client client;
        String sql = "SELECT * FROM Personne WHERE role = 2";
        try{
            Statement statement = this.connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                client = new Client();
                listes.add(this.createClient(resultSet, client));
            }
            resultSet.close();
            statement.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return listes;
    }
    /**
     * Renvoi un client à partir de son id
     * @param id
     * @return 
     */
    @Override
    public Client find(int id) {
        Client client = new Client();
        String sql = "SELECT * FROM Personne WHERE role = 2 AND id = ?";
        try{
            PreparedStatement ps = this.connect.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                client = this.createClient(resultSet, client);
            }
            resultSet.close();
            ps.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return client;
    }
    
    /**
     * Ajout un client
     * @param client
     * @return 
     */
    @Override
    public int create(Client client) {
        String sql = "INSERT INTO Personne(nom,prenom,email,numero_fidelite,code_postal, role) VALUES(?,?,?,?,?,2)";
        int id = -1;
        try{
            PreparedStatement ps = this.connect.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.createPreparedStatement(ps, client).executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if(resultSet.next()){
                id = resultSet.getInt(1);
            }
            resultSet.close();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return id;
    }

    /**
     * Modifie un client
     * @param client
     * @return 
     */
    @Override
    public Client update(Client client) {
        String sql = "UPDATE SET nom=?,prenom=?,email=?,numero_fidelit=?,code_postal=? WHERE id = ?";
        int id = -1;
        try{
            PreparedStatement ps = this.connect.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(6, client.getId());
            this.createPreparedStatement(ps, client).executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
            rs.close();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return this.find(id);
    }

    @Override
    public Client delete(Client object) {
        return null;
    }

    /**
     * Remplacement des paramètres dans la requête prépaprée
     * @param ps
     * @param client
     * @return
     * @throws SQLException 
     */
    public PreparedStatement createPreparedStatement(PreparedStatement ps, Client client) throws SQLException {
        ps.setString(1, client.getNom());
        ps.setString(2, client.getPrenom());
        ps.setString(3, client.getEmail());
        ps.setInt(4, client.getNumeroFidelite());
        ps.setInt(5, client.getCodePostal());
        return ps;
    }
   /**
    *  Création du client à partir du resultset
    * @param resultSet
    * @param client
    * @return
    * @throws SQLException 
    */
    public Client createClient(ResultSet resultSet, Client client) throws SQLException {
        client.setId(resultSet.getInt("id"));
        client.setNom(resultSet.getString("nom"));
        client.setPrenom(resultSet.getString("prenom"));
        client.setEmail(resultSet.getString("email"));
        client.setNumeroFidelite(resultSet.getInt("numero_fidelite"));
        client.setCodePostal(resultSet.getInt("code_postal"));
        return client;
    }
}
