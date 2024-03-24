package model.dao;

import model.entity.TypeProduit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TypeProduitDAO extends DAO<TypeProduit>{
    /**
     * 
     * @return 
     */
    @Override
    public ArrayList<TypeProduit> getAll() {
        ArrayList<TypeProduit> listes = new ArrayList<>();
        TypeProduit typeProduit;
        String sql = "SELECT * FROM Type_produit";
        try{
            Statement statement = this.connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                typeProduit = new TypeProduit();
                typeProduit.setId(resultSet.getInt("id"));
                typeProduit.setLibelle(resultSet.getString("libelle"));
                typeProduit.setDescription(resultSet.getString("description"));
                listes.add(typeProduit);
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
    public TypeProduit find(int id) {
        TypeProduit tp = new TypeProduit();
        String sql = "SELECT * FROM Type_produit WHERE id = ?";
        try{
            PreparedStatement ps = this.connect.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                tp.setId(id);
                tp.setLibelle(rs.getString("libelle"));
                tp.setDescription(rs.getString("description"));
            }
            rs.close();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return tp;
    }
/**
 * 
 * @param type
 * @return 
 */
    @Override
    public int create(TypeProduit type) {
        String sql = "INSERT INTO Type_produit(libelle, description) VALUES(?,?)";
        int id = -1;
        try{
            PreparedStatement ps = this.connect.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, type.getLibelle());
            ps.setString(2, type.getDescription());
            ps.executeUpdate();
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
 * @param type
 * @return 
 */
    @Override
    public TypeProduit update(TypeProduit type) {
        String sql = "UPDATE Type_Produit SET libelle=?, description=? WHERE id = ?";
        try{
            PreparedStatement ps = this.connect.prepareStatement(sql);
            ps.setString(1, type.getLibelle());
            ps.setString(2, type.getDescription());
            ps.setInt(3, type.getId());
            ps.executeUpdate();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
/**
 * 
 * @param object
 * @return 
 */
    @Override
    public TypeProduit delete(TypeProduit object) {
        return null;
    }
}
