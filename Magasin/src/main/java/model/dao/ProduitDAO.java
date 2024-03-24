package model.dao;

import model.entity.Produit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProduitDAO extends DAO<Produit> {
    /**
     * 
     * @return 
     */
    @Override
    public ArrayList<Produit> getAll() {
        ArrayList<Produit> listes = new ArrayList<>();
        Produit produit;
        TypeProduitDAO typeProduitDao;
        String sql = "SELECT * FROM Produit";
        try{
            Statement statement = this.connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                produit = new Produit();
                typeProduitDao = new TypeProduitDAO();
                listes.add(this.createProduct(resultSet, produit, typeProduitDao));
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
    public Produit find(int id) {
        Produit produit = new Produit();
        TypeProduitDAO type = new TypeProduitDAO();
        String sql = "SELECT * FROM Produit WHERE id = ?";
        try{
            PreparedStatement ps = this.connect.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                produit = this.createProduct(rs, produit, type);
            }
            rs.close();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return produit;
    }
/**
 * 
 * @param produit
 * @return 
 */
    @Override
    public int create(Produit produit) {
        String sql = "INSERT INTO Produit(nom, description,pays,tva,seuil,est_pesable,unite_poids,type) VALUES(?,?,?,?,?,?,?,?)";
        int id = -1;
        try{
            PreparedStatement ps = this.connect.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.createPreparedStatement(ps, produit).executeUpdate();
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
 * @param produit
 * @return 
 */
    @Override
    public Produit update(Produit produit) {
        String sql = "UPDATE Produit SET nom=?, description=?,pays=?,tva=?,seuil=?,est_pesable=?,unite_poids=?,type=? WHERE id = ?";
        int id = -1;
        try{
            PreparedStatement ps = this.connect.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(9,produit.getId());
            this.createPreparedStatement(ps, produit).executeUpdate();
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
    public Produit delete(Produit object) {
        return null;
    }
/**
 * Mise à jour de la requête préparée
 * @param resultSet
 * @param produit
 * @param typeProduitDao
 * @return
 * @throws SQLException 
 */
    public Produit createProduct(ResultSet resultSet, Produit produit, TypeProduitDAO typeProduitDao) throws SQLException {
        produit.setId(resultSet.getInt("id"));
        produit.setNom(resultSet.getString("nom"));
        produit.setDescription(resultSet.getString("description"));
        produit.setPays(resultSet.getString("pays"));
        produit.setTva(resultSet.getFloat("tva"));
        produit.setSeuil(resultSet.getInt("seuil"));
        produit.setEstPesable(resultSet.getBoolean("est_pesable"));
        produit.setUnitePoids(resultSet.getFloat("unite_poids"));
        produit.setTypeProduit(typeProduitDao.find(resultSet.getInt("type")));
        return produit;
    }

/**
 * Création d'un produit à partir du resultset
 * @param ps
 * @param produit
 * @return
 * @throws SQLException 
 */
    public PreparedStatement createPreparedStatement(PreparedStatement ps, Produit produit) throws SQLException {
        ps.setString(1, produit.getNom());
        ps.setString(2, produit.getDescription());
        ps.setString(3, produit.getPays());
        ps.setFloat(4, produit.getTva());
        ps.setInt(5, produit.getSeuil());
        ps.setBoolean(6, produit.getEstPesable());
        ps.setFloat(7, produit.getUnitePoids());
        ps.setInt(8, produit.getTypeProduit().getId());
        return ps;
    }
}
