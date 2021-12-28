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
import model.UserModel;
import web.ViewManager;

/**
 *
 * @author raquel
 */
public class clearcartAction implements Action {
    
    UserModel userModel;

    public clearcartAction(UserModel userModel){
        this.userModel = userModel;
    }
    
    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        
        ShoppingCart shoppingCart = (ShoppingCart)req.getSession().getAttribute("shoppingCart");
        shoppingCart.clear();
        req.getSession().setAttribute("shoppingCart", shoppingCart);
        int catId = Integer.parseInt(req.getParameter("categoryid"));
        req.setAttribute("categoryid", catId);
        
        User user = null;
        int idUser = new Integer(req.getParameter("idUser"));
        if(idUser != -1) {
            user = userModel.retrieve(idUser);
        }
        req.setAttribute("user", user);
        req.setAttribute("check", "");
        
        ViewManager.nextView(req, resp, "/view/viewcart.jsp");
    }
}
