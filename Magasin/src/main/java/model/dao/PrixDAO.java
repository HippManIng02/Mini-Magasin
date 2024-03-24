package model.dao;

import model.entity.Prix;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PrixDAO extends DAO<Prix>{
    /**
     * 
     * @return 
     */
    @Override
    public ArrayList<Prix> getAll() {
        ArrayList<Prix> listes = new ArrayList<>();
        Prix prix;
        ProduitDAO produitDAO;
        String sql = "SELECT * FROM Prix WHERE date_fin_application IS NULL";
        try{
            Statement statement = this.connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                prix = new Prix();
                produitDAO = new ProduitDAO();
                listes.add(this.createPrice(resultSet, prix, produitDAO));
            }
            statement.close();
            resultSet.close();
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
    public Prix find(int id) {
        Prix prix = new Prix();
        ProduitDAO produit = new ProduitDAO();
        String sql = "SELECT * FROM Prix WHERE id = ?";
        try{
            PreparedStatement ps = this.connect.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.first()){
                prix = this.createPrice(rs, prix, produit);
            }
            rs.close();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return prix;
    }
/**
 * 
 * @param prix
 * @return 
 */
    @Override
    public int create(Prix prix) {
        String sql = "INSERT INTO Prix(montant, date_application, date_fin_application, produit) VALUES(?,?,?,?)";
        int id = -1;
        try{
            PreparedStatement ps = this.connect.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.createPreparedStatement(ps, prix).executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
            rs.close();
            ps.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return id;
    }
/**
 * 
 * @param prix
 * @return 
 */
    @Override
    public Prix update(Prix prix) {
        String sql = "UPDATE Prix SET montant =?, date_application=?, date_fin_application=?, produit=? WHERE id = ?";
        int id = -1;
        try{
            PreparedStatement ps = this.connect.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(5, prix.getId());
            this.createPreparedStatement(ps, prix).executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
            rs.close();
            ps.close();
        } catch (SQLException e){
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
    public Prix delete(Prix object) {
        return null;
    }
/**
 * Mise de la requête préparée
 * @param ps
 * @param prix
 * @return
 * @throws SQLException 
 */
    public PreparedStatement createPreparedStatement(PreparedStatement ps, Prix prix) throws SQLException {
        ps.setDouble(1, prix.getMontant());
        ps.setDate(2, prix.getDateApplication());
        ps.setDate(3, prix.getDateFinApplication());
        ps.setInt(4, prix.getProduit().getId());
        return ps;
    }
    /**
     * Création de prix à partir des resultset
     * @param resultSet
     * @param prix
     * @param produitDAO
     * @return
     * @throws SQLException 
     */
    public Prix createPrice(ResultSet resultSet, Prix prix, ProduitDAO produitDAO) throws SQLException {
        prix.setId(resultSet.getInt("id"));
        prix.setMontant(resultSet.getDouble("montant"));
        prix.setDateApplication(resultSet.getDate("date_application"));
        prix.setDateFinApplication(resultSet.getDate("date_fin_application"));
        prix.setProduit(produitDAO.find(resultSet.getInt("produit")));
        return prix;
    }
}
