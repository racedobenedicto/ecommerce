package cart;

import entity.Product;
import java.util.*;

/**
 *
 * @author juanluis
 */
public class ShoppingCart {
    List<ShoppingCartItem> cartList = new ArrayList<ShoppingCartItem>();

    public synchronized void addItem(Product product) {
        boolean find = false;
        for (ShoppingCartItem cart : cartList) {
            if (cart.getProduct().equals(product)) {
                find = true;
                cart.setQuantity(cart.getQuantity()+1);
            }
        }
        if (!find) {
            ShoppingCartItem cartItem = new ShoppingCartItem(product);
            cartList.add(cartItem);
        }            
    }
    
    public synchronized void update(Product product, int quantity) {
        for (ShoppingCartItem cart : cartList) {
            if (cart.getProduct().equals(product)) {
                cart.setQuantity(quantity);
            }
        }
    }
    
    public synchronized List<ShoppingCartItem> getItems() {
        return cartList;
    }
    
    public synchronized int getNumberOfItems() {
        int nItems = 0;
        for (ShoppingCartItem cart : cartList) {
            nItems += cart.getQuantity();
        }
        return nItems;
    }
    
    public synchronized double getTotal() {
        double total = 0;
        for (ShoppingCartItem cart : cartList) {
            total += cart.getTotal();
        }
        return total;
    }
    
    public synchronized void clear() {
        cartList.clear();
    }
}