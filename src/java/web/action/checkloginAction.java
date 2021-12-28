/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

import cart.ShoppingCart;
import entity.Comanda;
import entity.User;
import static java.lang.Integer.parseInt;
import java.sql.PreparedStatement;
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
 * @author raquel
 */
public class checkloginAction implements Action {
    
    CategoryModel categoryModel;
    UserModel userModel;
    ComandaModel comandaModel;
    ProductModel productModel;

    public checkloginAction(CategoryModel categoryModel, UserModel userModel, ComandaModel comandaModel, ProductModel productModel){
        this.categoryModel = categoryModel;
        this.userModel = userModel;
        this.comandaModel = comandaModel;
        this.productModel = productModel;
    }
    
    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        
        req.setAttribute("categories", categoryModel.retrieveAll());
        
        String username = req.getParameter("user");
        String pass = req.getParameter("pass");
        
        ShoppingCart shoppingCart = (ShoppingCart)req.getSession().getAttribute("shoppingCart");
        if (shoppingCart == null){
            shoppingCart = new ShoppingCart();
            req.getSession().setAttribute("shoppingCart", shoppingCart);
        }
        req.setAttribute("nItems", shoppingCart.getNumberOfItems());
        
        List<User> userList = userModel.retrieveAll();
        for (User u : userList) {
            if(u.getUsername().equals(username)) {
                if(u.getPassword().equals(pass)) {
                    req.setAttribute("user", u);
                    req.setAttribute("lastOrders", comandaModel.retrieveAll(u.getIduser()));
                    req.setAttribute("productModel", productModel);
                    
                    ViewManager.nextView(req, resp, "/view/init.jsp");
                } else {
                    String error = "Incorrect user or password. Try again!";
                    req.setAttribute("error", error);
                    ViewManager.nextView(req, resp, "/view/login.jsp");
                }
            }
        }       
    }
}
