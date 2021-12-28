/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

import cart.ShoppingCart;
import entity.Category;
import entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CategoryModel;
import model.ProductModel;
import model.UserModel;
import web.ViewManager;

/**
 *
 * @author raquel
 */
public class categoryAction implements Action {
    
    CategoryModel categoryModel;
    ProductModel productModel;
    UserModel userModel;

    public categoryAction(CategoryModel categoryModel, ProductModel productModel, UserModel userModel){
        this.categoryModel = categoryModel;
        this.productModel = productModel;
        this.userModel = userModel;
    }

    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        
        int nItems = 0;
        User user = null;
        //Recuperar dades de la request
        int categoryId = new Integer(req.getParameter("categoryid"));
        Category category = categoryModel.retrieve(categoryId);
        
        ShoppingCart shoppingCart = (ShoppingCart)req.getSession().getAttribute("shoppingCart");
        if (shoppingCart != null) {
            nItems = shoppingCart.getNumberOfItems();
        }
        
        int idUser = new Integer(req.getParameter("idUser"));
        if(idUser != -1) {
            user = userModel.retrieve(idUser);
        }        
        
        //Enviar dades al jsp
        req.setAttribute("products", productModel.productsOfCategory(category));
        req.setAttribute("categories", categoryModel.retrieveAll());
        req.setAttribute("category", category);
        req.setAttribute("nItems", nItems);
        req.setAttribute("user", user);
        
        //Forward
        ViewManager.nextView(req, resp, "/view/category.jsp");
    }    
}
