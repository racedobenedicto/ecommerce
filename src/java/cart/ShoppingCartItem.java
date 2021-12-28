package cart;

import entity.Product;

/**
 *
 * @author juanluis
 */
public class ShoppingCartItem {
    Product product;
    int quantity;
    
    public ShoppingCartItem(Product product) {
        this.product = product;
        quantity = 1;
    }
    
    public Product getProduct() {
        return product;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public double getTotal() {
        //calcular importe total por producto
        return getQuantity()*product.getPrice();
    }
}