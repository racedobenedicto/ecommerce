/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import cart.ShoppingCart;
import cart.ShoppingCartItem;
import entity.Comanda;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

/**
 *
 * @author raquel
 */
public class ComandaModel {
    
    UserTransaction utx;
    EntityManager em;

    public ComandaModel(EntityManager em, UserTransaction utx) {
        this.utx = utx;
        this.em = em;
    }
    
    public List<Comanda> retrieveAll(int userId){
        //retrieve the products that one user have bought
        List<Comanda> comandas = new ArrayList<Comanda>();
        Query q = em.createQuery("SELECT o FROM Comanda as o WHERE o.idUser=:userId");
        q.setParameter("userId", userId);
         
        if(!q.getResultList().isEmpty()) {
            comandas = q.getResultList();
        }
        return comandas;
    }
    
    public void insertOrder(ShoppingCart cart, int idUser) {
        int idProduct = 0;
        int quantity = 0;
        
        if(cart != null) {
            for(ShoppingCartItem i : cart.getItems()) {
                idProduct = i.getProduct().getId();
                quantity = i.getQuantity();
                Comanda order = new Comanda();
                order.setDatatime(new Date());
                order.setIdUser(idUser);
                order.setIdProduct(idProduct);
                order.setQuantity(quantity);

                try {
                    utx.begin();
                    em.persist(order);
                    utx.commit();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    public void deleteOrder(ShoppingCart cart, int idUser) {
        int idProduct = 0;
        int quantity = 0;
        
        for(ShoppingCartItem i : cart.getItems()) {
            idProduct = i.getProduct().getId();
            quantity = i.getQuantity();
            Comanda order = new Comanda();
            order.setDatatime(new Date());
            order.setIdUser(idUser);
            order.setIdProduct(idProduct);
            order.setQuantity(quantity);
        
            try {
                utx.begin();
                order = em.merge(order);
                em.remove(order);
                utx.commit();
            }  catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
