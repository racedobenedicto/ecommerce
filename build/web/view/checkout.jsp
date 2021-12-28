<%-- 
    Document   : checkout
    Created on : 01-nov-2021, 19:13:24
    Author     : raquel
--%>

<%@page import="entity.Product"%>
<%@page import="cart.ShoppingCartItem"%>
<%@page import="java.util.List"%>
<%@page import="cart.ShoppingCart"%>
<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Expires" CONTENT="0">
        <meta http-equiv="Cache-Control" CONTENT="no-cache">
        <meta http-equiv="Pragma" CONTENT="no-cache">
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>eCommerce Sample</title>
    </head>
    <body>
        <%
            ShoppingCart shoppingCart = (ShoppingCart)request.getSession().getAttribute("shoppingCart");
            int nItems = shoppingCart.getNumberOfItems();
            int catId = (Integer)request.getAttribute("categoryid");
            
            User user = (User)request.getAttribute("user");
            int idUser = -1;
            if(user != null) {
                String username = user.getUsername();
                idUser = user.getIduser();
        %>
        
        <p>Active user: <%=username%></p>
        <form action="logout.do" method="post">
            <input type="hidden" name="idUser" value="<%=idUser%>">
            <input type="submit" name="submit" value="LOGOUT">
        </form>
        
        <% } %>
        
        <p></p>
        <img src="img/cart.gif">
        <small><%=nItems%> items</small>
        <p></p>
        
        <h3>Your shopping cart contains <%=nItems%> items.</h3>
        
        <form action="https://www.paypal.com/cgi-bin/webscr" method="post">
            <input type="hidden" name="cmd" value="_xclick">
            <input type="hidden" name="business" value="wmad@upc.edu">
            <input type="hidden" name="item_name" value="Total Amount">
            <input type="hidden" name="currency_code" value="EUR">
            <input type="hidden" name="amount" value= "<%=shoppingCart.getTotal()%>" >
            <input type="image" src="img/paypal.jpg" width="100" height="50" border="0" name="submit" alt="PayPal, la forma más segura y rápida de pagar en línea.">
            <img alt="" border="0" src="img/paypal.jpg" width="1" height="1">
        </form>
        <p></p>
        
        <table width="50%" border="1" bordercolordark="#000000" bordercolorlight="#FFFFFF" cellpadding="3" cellspacing="0">
            
            <tr> <font size="4" face="Verdana">
                <td valign="center" align="middle">
                    <b>Product</b>
                </td>
                <td valign="center" align="middle">
                    <b>Name</b>
                </td>
                <td valign="center" align="middle">
                    <b>Price</b>
                </td>
                <td valign="center" align="middle">
                    <b>Quantity</b>
                </td>
            </tr>
            <%
                List<ShoppingCartItem> list = shoppingCart.getItems();
                for (ShoppingCartItem shoppingItem : list) {
                    Product productShopping = shoppingItem.getProduct();
            %>
            
            <tr> <font size="2" face="Verdana">
                <td valign="center" align="middle">
                    <img src="img/products/<%=productShopping.getName()%>.png"
                        alt="<%=productShopping.getName()%>" >
                </td>
                <td valign="center" align="middle">
                    <b><%=productShopping.getName()%></b>
                    <p><%=productShopping.getDescription()%></p>
                </td>
                <td valign="center" align="middle">
                    <p><%=productShopping.getPrice()%>€/unit</p>
                </td>
                <td valign="center" align="middle">
                    <p><%=shoppingItem.getQuantity()%></p>
                </td>
            </font> </tr>
            <% } %>
        </table>
        <br>
        <% String total = String.format("%.2f",shoppingCart.getTotal()); %>
        <font size="4" face="Verdana">     
            <b>Total amount: <%=total%>€</b>
        </font>        
        
        <!-- botón cancelar -->
        <p></p>
        <form action="viewcart.do" method="post">
            <input type="hidden" name="categoryid" value="<%=catId%>">
            <input type="hidden" name="idUser" value="<%=idUser%>">
            <input type="submit" name="submit" value="Cancel">
        </form>
        
    </body>
</html>
