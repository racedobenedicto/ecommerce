<%-- 
    Document   : viewcart
    Created on : 20-oct-2021, 10:42:03
    Author     : raquel
--%>

<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ page import="entity.Category" %>
<%@ page import="entity.Product" %>
<%@page import="cart.ShoppingCartItem"%>
<%@page import="cart.ShoppingCart"%>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Expires" CONTENT="0">
        <meta http-equiv="Cache-Control" CONTENT="no-cache">
        <meta http-equiv="Pragma" CONTENT="no-cache">
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>eCommerce Sample</title>
    </head>
    
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
        <p></p> <!-- Enter (salto de linea) -->
            
        <h3>Your shopping cart contains <%=nItems%> items.</h3>
        
        <a href="javascript:categoryid()">
            <small>Clear cart</small>
            <script>
                function categoryid() {
                    document.sendCategoryId.submit()
                }
            </script>
        </a>
        <form action="clearcart.do" method="post" name="sendCategoryId">
            <input type="hidden" name="categoryid" value="<%=catId%>">
            <input type="hidden" name="idUser" value="<%=idUser%>">
        </form>
        <p></p> <!-- Enter (salto de linea) -->
        <a href="category.do?categoryid=<%=catId%>&idUser=<%=idUser%>">
            <small>Continue shopping</small>
        </a>
        <p></p> <!-- Enter (salto de linea) -->        
        <a href="javascript:categoryid2()">
            <img src="img/paypal.jpg" width="100" height="50">
            <script>
                function categoryid2() {
                    document.sendCategoryId2.submit()
                }
            </script>
        </a>
        <%
            String check = (String)request.getAttribute("check");
            if(check != "") {
        %>    
        <p style="color:red;"><%=check%></p>
        <% } %>
        <form action="checkout.do" method="post" name="sendCategoryId2">
            <input type="hidden" name="idUser" value="<%=idUser%>">
            <input type="hidden" name="categoryid" value="<%=catId%>">
        </form>
        
        <p></p> <!-- Enter (salto de linea) -->
        
        <table width="50%" border="1" bordercolordark="#000000" bordercolorlight="#FFFFFF" cellpadding="3" cellspacing="0">
            
            <tr> <font size="4" face="Verdana">
                <!-- Cabeceras: product (foto), name (name + description), 
                price (€/unit), quantity (entrada de texto + boton update) -->
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
                    <form action="updatecart.do" method="post">
                        <input type="hidden" name="productid" value="<%=productShopping.getId()%>">
                        <input type="text" name="quantity" value="<%=shoppingItem.getQuantity()%>">
                        <input type="hidden" name="categoryid" value="<%=catId%>">
                        <input type="hidden" name="idUser" value="<%=idUser%>">
                        <input type="submit" name="submit" value="update">
                    </form>
                </td>
            </font> </tr>
            <% } %>
        </table>
        <br>
        <% String total = String.format("%.2f",shoppingCart.getTotal()); %>
        <font size="4" face="Verdana">     
            <b>Total amount: <%=total%>€</b>
        </font>    
</html>
