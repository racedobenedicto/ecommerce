/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

/**
 *
 * @author raquel
 */
public class UserModel {
    
    UserTransaction utx;
    EntityManager em;

    public UserModel(EntityManager em, UserTransaction utx) {
        this.utx = utx;
        this.em = em;
    }
    
    public List<User> retrieveAll(){
        Query q = em.createQuery("select o from User as o");
        return q.getResultList();
    }
    
    public int exist(String user) {
        int id = -1;
        List<User> userList = this.retrieveAll();
        for (User u : userList) {
            if(u.getUsername().equals(user)) {
                id = u.getIduser();
            }
        }       
        return id;
    }
    
    public void insertUser(User user) {
        try {
            utx.begin();
            em.persist(user);
            utx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public User retrieve(int idUser) {
        return em.find(User.class, idUser);
    }
}
