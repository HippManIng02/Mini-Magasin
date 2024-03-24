package model.dao;

import model.entity.PointVente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PointVenteDAO extends DAO<PointVente>{
    /**
     * 
     * @return 
     */
    @Override
    public ArrayList<PointVente> getAll() {
        ArrayList<PointVente> listes = new ArrayList<>();
        PointVente pointVente;
        AdresseDAO adresseDAO;
        String sql = "SELECT * FROM Point_vente";
        try{
            Statement statement = this.connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                pointVente = new PointVente();
                adresseDAO = new AdresseDAO();
                listes.add(this.createPointVente(resultSet, pointVente, adresseDAO));
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
    public PointVente find(int id) {
        PointVente pointVente = new PointVente();
        AdresseDAO adresseDAO = new AdresseDAO();
        String sql = "SELECT * FROM Point_vente WHERE numero = ?";
        try{
            PreparedStatement ps = this.connect.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                pointVente = this.createPointVente(rs, pointVente, adresseDAO);
            }
            rs.close();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return pointVente;
    }
/**
 * 
 * @param pointVente
 * @return 
 */
    @Override
    public int create(PointVente pointVente) {
        String sql = "INSERT INTO Point_vente(numero, nom, adresse) VALUES(?,?,?)";
        int id = -1;
        try{
            PreparedStatement ps = this.connect.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.createPreparedStatement(ps, pointVente).executeUpdate();
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
 * @param pointVente
 * @return 
 */
    @Override
    public PointVente update(PointVente pointVente) {
        String sql = "UPDATE Point_vente SET nom =?, adresse=? WHERE numero = ?";
        int id = -1;
        try{
            PreparedStatement ps = this.connect.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, pointVente.getNom());
            ps.setInt(2, pointVente.getAdresse().getId());
            ps.setInt(3, pointVente.getNumero());
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
        return this.find(id);
    }
/**
 * 
 * @param object
 * @return 
 */
    @Override
    public PointVente delete(PointVente object) {
        return null;
    }
/**
 * Mise à jour de la requête préparée
 * @param ps
 * @param pointVente
 * @return
 * @throws SQLException 
 */
    public PreparedStatement createPreparedStatement(PreparedStatement ps, PointVente pointVente) throws SQLException {
        ps.setInt(1, pointVente.getNumero());
        ps.setString(2, pointVente.getNom());
        ps.setInt(3, pointVente.getAdresse().getId());
        return ps;
    }
    /**
     * Création d'un point de vente à partir du resultset
     * @param resultSet
     * @param pointVente
     * @param adresseDAO
     * @return
     * @throws SQLException 
     */
    public PointVente createPointVente(ResultSet resultSet, PointVente pointVente, AdresseDAO adresseDAO) throws SQLException {
        pointVente.setNumero(resultSet.getInt("numero"));
        pointVente.setNom(resultSet.getString("nom"));
        pointVente.setAdresse(adresseDAO.find(resultSet.getInt("adresse")));
        return pointVente;
    }
}
