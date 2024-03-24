package model.dao;

import model.entity.Personnel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PersonnelDAO extends DAO<Personnel>{
   /**
    * 
    * @return 
    */
    @Override
    public ArrayList<Personnel> getAll() {
        ArrayList<Personnel> listes = new ArrayList<>();
        Personnel personnel;
        AdresseDAO adresseDAO;
        PersonnelDAO personnelDAO;
        FonctionDAO fonctionDAO;
        PointVenteDAO pointVenteDAO;
        String sql = "SELECT * FROM Personne WHERE role = 1";
        try{
            Statement statement = this.connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                personnel = new Personnel();
                adresseDAO = new AdresseDAO();
                personnelDAO = new PersonnelDAO();
                fonctionDAO = new FonctionDAO();
                pointVenteDAO = new PointVenteDAO();
                listes.add(this.createPersonnel(resultSet, personnel, adresseDAO, personnelDAO,fonctionDAO, pointVenteDAO));
            }
            resultSet.close();
            statement.close();
        }catch(SQLException e) {
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
    public Personnel find(int id) {
        Personnel personnel = new Personnel();
        AdresseDAO adresseDAO = new AdresseDAO();
        PersonnelDAO personnelDAO = new PersonnelDAO();
        FonctionDAO fonctionDAO = new FonctionDAO();
        PointVenteDAO pointVenteDAO = new PointVenteDAO();
        String sql = "SELECT * FROM Personne WHERE role = 1 AND id = ?";
        try{
            PreparedStatement ps = this.connect.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                personnel = this.createPersonnel(rs,personnel,adresseDAO,personnelDAO,fonctionDAO,pointVenteDAO);
            }
            rs.close();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return personnel;
    }

   /**
    * 
    * @param personnel
    * @return 
    */
    @Override
    public int create(Personnel personnel) {
        String sql = "INSERT INTO Personne(role,nom,prenom,email,numero_badge,adresse,superieur,fonction,point_vente) VALUES(1,?,?,?,?,?,?,?,?)";
        int id = -1;
        try{
            PreparedStatement ps = this.connect.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.createPreparedStatement(ps, personnel).executeUpdate();
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
 * @param personnel
 * @return 
 */
    @Override
    public Personnel update(Personnel personnel) {
        String sql = "UPDATE Personne SET role = 1,nom=?,prenom=?,email=?,numero_badge=?,adresse=?,superieur=?,fonction=?,point_vente=? WHERE id = ?";
        int id = -1;
        try{
            PreparedStatement ps = this.connect.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(9, personnel.getId());
            this.createPreparedStatement(ps, personnel).executeUpdate();
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
    public Personnel delete(Personnel object) {
        return null;
    }
/**
 * Mise à jour de la requête préparée
 * @param ps
 * @param personnel
 * @return
 * @throws SQLException 
 */
    public PreparedStatement createPreparedStatement(PreparedStatement ps, Personnel personnel) throws SQLException {
        ps.setString(1, personnel.getNom());
        ps.setString(2, personnel.getPrenom());
        ps.setString(3, personnel.getEmail());
        ps.setInt(4, personnel.getNumeroBadge());
        ps.setInt(5, personnel.getAdresse().getId());
        if(personnel.getPersonnel() != null){
            ps.setInt(6, personnel.getPersonnel().getId());
        }
        ps.setInt(6, 0);
        ps.setInt(7, personnel.getPoste().getId());
        ps.setInt(8, personnel.getPointVente().getNumero());
        return ps;
    }
/**
 * Création de personnel à partir du resultset
 * @param resultSet
 * @param personnel
 * @param adresseDAO
 * @param personnelDAO
 * @param fonctionDAO
 * @param pointVenteDAO
 * @return
 * @throws SQLException 
 */
    public Personnel createPersonnel(ResultSet resultSet, Personnel personnel, AdresseDAO adresseDAO, PersonnelDAO personnelDAO, FonctionDAO fonctionDAO, PointVenteDAO pointVenteDAO) throws SQLException {
        personnel.setId(resultSet.getInt("id"));
        personnel.setNom(resultSet.getString("nom"));
        personnel.setPrenom(resultSet.getString("prenom"));
        personnel.setEmail(resultSet.getString("email"));
        personnel.setNumeroBadge(resultSet.getInt("numero_badge"));
        personnel.setPersonnel(personnelDAO.find(resultSet.getInt("superieur")));
        personnel.setAdresse(adresseDAO.find(resultSet.getInt("adresse")));
        personnel.setPoste(fonctionDAO.find(resultSet.getInt("fonction")));
        personnel.setPointVente(pointVenteDAO.find(resultSet.getInt("point_vente")));
        return personnel;
    }
}
