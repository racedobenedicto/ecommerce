/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import cart.ShoppingCart;
import entity.Category;
import entity.Product;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

/**
 *
 * @author juanluis
 */
public class ProductModel {

    UserTransaction utx;
    EntityManager em;
    ShoppingCart shoppingCart = new ShoppingCart();

    public ProductModel(EntityManager em, UserTransaction utx) {
        this.utx = utx;
        this.em = em;
    }

    public List<Product> productsOfCategory(Category category) {
        Query q = em.createQuery("select o from Product as o where o.category=:category");
        q.setParameter("category", category);
        return q.getResultList();
    }
    
    public Product retrieve(int productId) {
        return em.find(Product.class, productId);
    }
    
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
}
