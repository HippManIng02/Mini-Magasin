package model.dao;

import model.entity.Fonction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FonctionDAO extends DAO<Fonction>{
    /**
     * 
     * @return 
     */
    @Override
    public ArrayList<Fonction> getAll() {
        ArrayList<Fonction> listes = new ArrayList<>();
        Fonction fonction;
        String sql = "SELECT * FROM Fonction";
        try{
            Statement statement = this.connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                fonction = new Fonction();
                fonction.setId(resultSet.getInt("id"));
                fonction.setLibelle(resultSet.getString("libelle"));
                listes.add(fonction);
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
    public Fonction find(int id) {
        Fonction fonction = new Fonction();
        String sql = "SELECT * FROM Fonction WHERE id = ?";
        try{
            PreparedStatement ps = this.connect.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                fonction.setId(id);
                fonction.setLibelle(rs.getString("libelle"));
            }
            rs.close();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return fonction;
    }

    /**
     * 
     * @param fonction
     * @return 
     */
    @Override
    public int create(Fonction fonction) {
        String sql = "INSERT INTO Fonction(libelle) VALUES(?)";
        int id = -1;
        try{
            PreparedStatement ps = this.connect.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, fonction.getLibelle());
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
    public Fonction update(Fonction type) {
        String sql = "UPDATE Fonction(libelle) VALUES(?) WHERE id = ?";
        try{
            PreparedStatement ps = this.connect.prepareStatement(sql);
            ps.setString(1, type.getLibelle());
            ps.setInt(3, type.getId());
            ps.executeUpdate();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return this.find(type.getId());
    }

    @Override
    public Fonction delete(Fonction fonction) {
        return null;
    }

}
