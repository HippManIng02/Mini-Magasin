package model.dao;

import model.entity.ConnectionMysql;
import java.sql.Connection;
import java.util.ArrayList;

public abstract class DAO<T> {
    /**
     * Récupération de l'instance de connection
     */
    public Connection connect = ConnectionMysql.getInstance();
    /**
     * Recuperer une liste d'objet
     * @return ArrayList<T>
     */
    public abstract ArrayList<T> getAll();
    /**
     * Permet de récupérer un objet via son ID
     * @param id
     * @return
     */
    public abstract T find(int id);
    /**
     * Permet de persister un objet dans la base de donnée
     * @param object
     */
    public abstract int create(T object);
    /**
     * Permet de modifier un élément dans la base de donnée
     * @param object
     */
    public abstract T update(T object);
    /**
     * Permet de supprimer un élément dans la base de donnée
     * @param object
     */
    public abstract T delete(T object);
}
