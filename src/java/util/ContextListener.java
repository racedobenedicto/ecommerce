package util;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.transaction.UserTransaction;
import model.CategoryModel;
import model.ComandaModel;
import model.ProductModel;
import model.UserModel;


/**
 *
 * @author  juanluis
 *
 * Web application lifecycle listener.
 */
public class ContextListener implements ServletContextListener, HttpSessionListener {

    @Resource
    private UserTransaction utx;
    @PersistenceContext
    private EntityManager em;

    public void contextInitialized(ServletContextEvent event) {
        try {
            ServletContext context = event.getServletContext();

            CategoryModel categoryModel = new CategoryModel(em, utx);
            ProductModel productModel   = new ProductModel(em, utx);
            UserModel userModel   = new UserModel(em, utx);
            ComandaModel comandaModel   = new ComandaModel(em, utx);
            
            context.setAttribute("categoryModel", categoryModel);
            context.setAttribute("productModel", productModel);
            context.setAttribute("userModel", userModel);
            context.setAttribute("comandaModel", comandaModel);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent evt) {

    }

    public void sessionCreated(HttpSessionEvent arg0) {

        arg0.getSession().setAttribute("session_data", "test");

    }

    public void sessionDestroyed(HttpSessionEvent arg0) {

        arg0.getSession().getAttribute("session_data");
        
    }
}
