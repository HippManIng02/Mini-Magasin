package model.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMysql {
    /**
     * Url de connexion
     */
    private static String url = "jdbc:mysql://localhost:3306/Magasin";
    /**
     * Nom de user
     */
    private static String user = "root";
    /**
     * Mot de passe de connexion
     */
    private static String password = "";
    /**
     * Objet de connexion
     */
    private static Connection connect;
    /**
     * Méthode qui retourne une instance existant et le crée s'il n'existe pas
     * @return
     */
    public static Connection getInstance(){
        if (connect == null){
            try{
                connect = DriverManager.getConnection(url, user, password);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return connect;
    }
}
