/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.entity.ProduitPointVente;


/**
 *
 * @author tobby
 */
public class ProduitPointVenteDAO extends DAO<ProduitPointVente>{
    /**
     * 
     * @return 
     */
    @Override
    public ArrayList<ProduitPointVente> getAll() {
        ArrayList<ProduitPointVente> listes = new ArrayList<>();
        ProduitPointVente produitPointVente;
        ProduitDAO produitDAO;
        PointVenteDAO pointVenteDAO;
        String sql = "SELECT * FROM Produit_Point_vente WHERE est_actif = 1";
        try{
            Statement statement = this.connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                produitPointVente = new ProduitPointVente();
                produitDAO = new ProduitDAO();
                pointVenteDAO = new PointVenteDAO();
                listes.add(this.createProduitPointVente(resultSet, produitPointVente, produitDAO,pointVenteDAO));
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
    public ProduitPointVente find(int id) {
        ProduitPointVente produitPointVente  = new ProduitPointVente();
        ProduitDAO produitDAO = new ProduitDAO();
        PointVenteDAO pointVenteDAO = new PointVenteDAO();
        String sql = "SELECT * FROM Produit_Point_vente WHERE id = ?";
        try{
            PreparedStatement ps = this.connect.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                produitPointVente = this.createProduitPointVente(rs, produitPointVente, produitDAO,pointVenteDAO);
            }
            rs.close();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return produitPointVente;
    }
/**
 * 
 * @param produitPointVente
 * @return 
 */
    @Override
    public int create(ProduitPointVente produitPointVente) {
        String sql = "INSERT INTO Produit_Point_vente(point_vente,produit,quantite,est_actif) VALUES(?,?,?,?)";
        int id = -1;
        try{
            PreparedStatement ps = this.connect.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.createPreparedStatement(ps, produitPointVente).executeUpdate();
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
 * @param produitPointVente
 * @return 
 */
    @Override
    public ProduitPointVente update(ProduitPointVente produitPointVente) {
        String sql = "UPDATE Produit_Point_vente SET point_vente = ?, produit=?, quantite=?,est_actif=? WHERE id = ?";
        int id = -1;
        try{
            PreparedStatement ps = this.connect.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(5, produitPointVente.getId());
            this.createPreparedStatement(ps, produitPointVente).executeUpdate();
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
    public ProduitPointVente delete(ProduitPointVente object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   /**
    * Mise à jour de la requête préparée
    * @param resultSet
    * @param produitPointVente
    * @param produitDAO
    * @param pointVenteDAO
    * @return
    * @throws SQLException 
    */
    public ProduitPointVente createProduitPointVente(ResultSet resultSet, ProduitPointVente produitPointVente, ProduitDAO produitDAO, PointVenteDAO pointVenteDAO) throws SQLException {
        produitPointVente.setId(resultSet.getInt("id"));
        produitPointVente.setEstActif(resultSet.getBoolean("est_actif"));
        produitPointVente.setQuantite(resultSet.getInt("quantite"));
        produitPointVente.setProduit(produitDAO.find(resultSet.getInt("produit")));
        produitPointVente.setPointVente(pointVenteDAO.find(resultSet.getInt("point_vente")));
        return produitPointVente;
    }
    /**
     * Création d'un produit point de vente à partir du resultset
     * @param ps
     * @param produitPointVente
     * @return
     * @throws SQLException 
     */
    
    public PreparedStatement createPreparedStatement(PreparedStatement ps, ProduitPointVente produitPointVente) throws SQLException {
        ps.setInt(1, produitPointVente.getPointVente().getNumero());
        ps.setInt(2, produitPointVente.getProduit().getId());
        ps.setInt(3, produitPointVente.getQuantite());
        ps.setBoolean(4, produitPointVente.getEstActif());
        return ps;
    }
}
