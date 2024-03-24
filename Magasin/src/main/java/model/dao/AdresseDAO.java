package model.dao;

import model.entity.Adresse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AdresseDAO extends DAO<Adresse>{
    /**
     *  Renvoi la liste des adresses
     * @return 
     */
    @Override
    public ArrayList<Adresse> getAll() {
        ArrayList<Adresse> listes = new ArrayList<>();
        Adresse adresse;
        String sql = "SELECT * FROM Adresse";
        try{
            Statement statement = this.connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                adresse = new Adresse();
                listes.add(this.createAddress(resultSet, adresse));
            }
            statement.close();
            resultSet.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listes;
    }
    
    /**
     * Renvoi un adresse à partir de son id
     * @param id
     * @return 
     */
    @Override
    public Adresse find(int id) {
        Adresse adresse = new Adresse();
        String sql = "SELECT * FROM Adresse WHERE id = ?";
        try{
            PreparedStatement ps = this.connect.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                adresse = this.createAddress(rs, adresse);
            }
            rs.close();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return adresse;
    }

    /**
     * Créée une adresse et renvoi son id
     * @param adresse
     * @return 
     */
    @Override
    public int create(Adresse adresse) {
        String sql = "INSERT INTO Adresse(code, numero, rue, departement) VALUES(?,?,?,?)";
        int id = -1;
        try{
            PreparedStatement ps = this.connect.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.createPreparedStatement(ps, adresse).executeUpdate();
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
     * @param adresse
     * @return 
     */
    @Override
    public Adresse update(Adresse adresse) {
        String sql = "UPDATE Adresse Set code =?, numero=?,rue=?,departement=? WHERE id = ?";
        int id = -1;
        try{
            PreparedStatement ps = this.connect.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(5, adresse.getId());
            this.createPreparedStatement(ps, adresse).executeUpdate();
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
    public Adresse delete(Adresse object) {
        return null;
    }

    /**
     * Remplacement des paramètres dans la requête prépaprée
     * @param ps
     * @param adresse
     * @return
     * @throws SQLException 
     */
    public PreparedStatement createPreparedStatement(PreparedStatement ps, Adresse adresse) throws SQLException {
        ps.setInt(1, adresse.getCode());
        ps.setInt(2, adresse.getNumero());
        ps.setString(3, adresse.getRue());
        ps.setString(4, adresse.getDepartement());
        return ps;
    }
    /**
     * Création de l'adresse à partir resulset
     * @param resultSet
     * @param adresse
     * @return
     * @throws SQLException 
     */
    public Adresse createAddress(ResultSet resultSet, Adresse adresse) throws SQLException {
        adresse.setId(resultSet.getInt("id"));
        adresse.setCode(resultSet.getInt("code"));
        adresse.setNumero(resultSet.getInt("numero"));
        adresse.setRue(resultSet.getString("rue"));
        adresse.setDepartement(resultSet.getString("departement"));
        return adresse;
    }
}
