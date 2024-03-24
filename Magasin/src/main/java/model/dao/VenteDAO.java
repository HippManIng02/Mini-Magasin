package model.dao;

import model.entity.Vente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VenteDAO extends DAO<Vente>{
    /**
     * 
     * @return 
     */
    @Override
    public ArrayList<Vente> getAll() {
        ArrayList<Vente> listes = new ArrayList<>();
        Vente vente;
        ProduitPointVenteDAO produitPointVenteDAO;
        ClientDAO clientDAO;
        PersonnelDAO personnelDAO;
        String sql = "SELECT * FROM Vente";
        try{
            Statement statement = this.connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                vente = new Vente();
                produitPointVenteDAO = new ProduitPointVenteDAO();
                clientDAO = new ClientDAO();
                personnelDAO = new PersonnelDAO();
                listes.add(this.createVente(resultSet,vente,produitPointVenteDAO,clientDAO,personnelDAO));
            }
            resultSet.close();
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listes;
    }
/**
 * 
 * @param id
 * @return 
 */
    @Override
    public Vente find(int id) {
        Vente vente = new Vente();
        ProduitPointVenteDAO produitPointVenteDAO = new ProduitPointVenteDAO();
        ClientDAO clientDAO = new ClientDAO();
        PersonnelDAO personnelDAO = new PersonnelDAO();
        String sql = "SELECT * FROM Vente WHERE id = ?";
        try{
            PreparedStatement ps = this.connect.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                vente = this.createVente(rs, vente, produitPointVenteDAO,clientDAO, personnelDAO);
            }
            rs.close();
            ps.close();
        }catch(SQLException e){
            e.printStackTrace();
        }

        return vente;
    }
/**
 * 
 * @param vente
 * @return 
 */
    @Override
    public int create(Vente vente) {
        String sql = "INSERT INTO Vente(quantite,sous_total,date,produit_point_vente,client,personnel) VALUES(?,?,?,?,?,?)";
        int id = -1;
        try{
            PreparedStatement ps = this.connect.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.createPreparedStatement(ps, vente).executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
            rs.close();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return id;
    }
    
    /**
     * 
     * @param vente
     * @return 
     */

    @Override
    public Vente update(Vente vente) {
        String sql = "UPDATE Vente SET quantite=?,sous_total=?,date=?,produit_point_vente=?,client=?,personnel=? WHERE id = ?";
        int id = -1;
        try{
            PreparedStatement ps = this.connect.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(7, vente.getId());
            this.createPreparedStatement(ps, vente).executeUpdate();
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
/**
 * 
 * @param object
 * @return 
 */
    @Override
    public Vente delete(Vente object) {
        return null;
    }
/**
 * Mise à jour de la requête préparée
 * @param ps
 * @param vente
 * @return
 * @throws SQLException 
 */
    public PreparedStatement createPreparedStatement(PreparedStatement ps, Vente vente) throws SQLException {
        ps.setInt(1,vente.getQuantite());
        ps.setDouble(2, vente.getSousTotal());
        ps.setDate(3, vente.getDate());
        ps.setInt(4, vente.getProduitPointVente().getId());
        ps.setInt(5, vente.getClient().getId());
        ps.setInt(6, vente.getPersonnel().getId());
        return ps;
    }
    /**
     * Création d'une vente à partir du resultset
     * @param resultSet
     * @param vente
     * @param produitPointVenteDAO
     * @param clientDAO
     * @param personnelDAO
     * @return
     * @throws SQLException 
     */
    public Vente createVente(ResultSet resultSet, Vente vente, ProduitPointVenteDAO produitPointVenteDAO, ClientDAO clientDAO, PersonnelDAO personnelDAO) throws SQLException {
        vente.setId(resultSet.getInt("id"));
        vente.setQuantite(resultSet.getInt("quantite"));
        vente.setSousTotal(resultSet.getDouble("sous_total"));
        vente.setDate(resultSet.getDate("date"));
        vente.setProduitPointVente(produitPointVenteDAO.find(resultSet.getInt("produit_point_vente")));
        vente.setClient(clientDAO.find(resultSet.getInt("client")));
        vente.setPersonnel(personnelDAO.find(resultSet.getInt("personnel")));
        return vente;
    }
}
