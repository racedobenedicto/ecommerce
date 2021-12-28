/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

import cart.ShoppingCart;
import entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ComandaModel;
import model.UserModel;
import web.ViewManager;

/**
 *
 * @author raquel
 */
public class checkoutAction implements Action {
    
    UserModel userModel;
    ComandaModel comandaModel;

    public checkoutAction(UserModel userModel, ComandaModel comandaModel){
        this.userModel = userModel;
        this.comandaModel = comandaModel;
    }
    
    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        
        int catId = new Integer(req.getParameter("categoryid"));
        
        User user = null;
        int idUser = new Integer(req.getParameter("idUser"));
        if(idUser != -1) {
            user = userModel.retrieve(idUser);
        }
        
        req.setAttribute("categoryid", catId);
        req.setAttribute("user", user);
            
        ShoppingCart shoppingCart = (ShoppingCart)req.getSession().getAttribute("shoppingCart");
        
        String check = "";
        
        if(shoppingCart.getNumberOfItems() != 0) {
            if(idUser != -1) {
                comandaModel.insertOrder(shoppingCart, idUser);
            }
            req.setAttribute("check", check);

            ViewManager.nextView(req, resp, "/view/checkout.jsp");
        } else {
            check = "Minimun order required";
            req.setAttribute("check", check);
            
            ViewManager.nextView(req, resp, "/view/viewcart.jsp");
        }
    }
}
