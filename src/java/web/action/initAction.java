package web.action;

import cart.ShoppingCart;
import entity.Comanda;
import static java.lang.Integer.parseInt;
import java.util.List;
import javax.servlet.http.*;
import model.CategoryModel;
import model.ComandaModel;
import model.ProductModel;
import model.UserModel;
import web.ViewManager;

public class initAction implements Action {

    CategoryModel categoryModel;
    ComandaModel comandaModel;
    ProductModel productModel;
    UserModel userModel;
    
    public initAction(CategoryModel categoryModel, ComandaModel comandaModel, ProductModel productModel, UserModel userModel){
        this.categoryModel = categoryModel;
        this.comandaModel = comandaModel;
        this.productModel = productModel;
        this.userModel = userModel;
    }

    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        
        req.setAttribute("categories", categoryModel.retrieveAll());
        
        ShoppingCart shoppingCart = (ShoppingCart)req.getSession().getAttribute("shoppingCart");
        if (shoppingCart == null){
            shoppingCart = new ShoppingCart();
            req.getSession().setAttribute("shoppingCart", shoppingCart);
        }
        req.setAttribute("nItems", shoppingCart.getNumberOfItems());
        
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
