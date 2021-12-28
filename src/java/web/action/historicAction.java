/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

import cart.ShoppingCart;
import entity.Category;
import entity.Comanda;
import entity.Product;
import entity.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CategoryModel;
import model.ComandaModel;
import model.ProductModel;
import model.UserModel;
import web.ViewManager;

/**
 *
 * @author raque
 */
public class historicAction implements Action {
    
    CategoryModel categoryModel;
    ProductModel productModel;
    UserModel userModel;
    ComandaModel comandaModel;

    public historicAction(CategoryModel categoryModel, ProductModel productModel, UserModel userModel, ComandaModel comandaModel){
        this.categoryModel = categoryModel;
        this.productModel = productModel;
        this.userModel = userModel;
        this.comandaModel = comandaModel;
    }

    public void perform(HttpServletRequest req, HttpServletResponse resp) {
    
    
        User user = null;
        int nItems = 0;
        int productId = new Integer(req.getParameter("productid"));
        
        Product product = productModel.retrieve(productId);
        
        ShoppingCart shoppingCart = (ShoppingCart)req.getSession().getAttribute("shoppingCart");
        if (shoppingCart == null){
            shoppingCart = new ShoppingCart();
            req.getSession().setAttribute("shoppingCart", shoppingCart);
        }
           
        shoppingCart.addItem(product);
        nItems = shoppingCart.getNumberOfItems();
        
        req.setAttribute("productModel", productModel);
        req.setAttribute("categories", categoryModel.retrieveAll());
        req.setAttribute("nItems", nItems);
        req.setAttribute("user", user);
        
        if(req.getParameter("idUser") != null) {
            int idUser = new Integer(req.getParameter("idUser"));        
            List<Comanda> lastOrders = comandaModel.retrieveAll(idUser);
            req.setAttribute("lastOrders", lastOrders);
            req.setAttribute("user", userModel.retrieve(idUser));
            req.setAttribute("productModel", productModel);
        } else {
            req.setAttribute("lastOrders", null);
            req.setAttribute("user", null);
        }
        
        ViewManager.nextView(req, resp, "/view/init.jsp");
    }
}
