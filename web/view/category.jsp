<%-- 
    Document   : category
    Created on : 19-oct-2021, 12:02:11
    Author     : raquel
--%>

<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ page import="entity.Category" %>
<%@ page import="entity.Product" %>
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
    <body>
        
        <%
            Category cat = (Category)request.getAttribute("category");
            int nItems = (Integer)request.getAttribute("nItems");
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
        
        <h3>Products of <%=cat.getName()%></h3>
        
        <img src="img/cart.gif">
        <small><%=nItems%> items</small>
        
        <%
            String view1 = "";
            String view2 = "";
            if (nItems > 0) {
                view1 = "View Cart";
                view2 = "Proceed to checkout";
            }
        %>
        <a href="javascript:categoryid1()">
            <small><%=view1%></small>
            <script>
                function categoryid1() {
                    document.sendCategoryId1.submit();
                }
            </script>
        </a>
        <form action="viewcart.do" method="post" name="sendCategoryId1">
            <input type="hidden" name="categoryid" value="<%=cat.getId()%>">
            <input type="hidden" name="idUser" value="<%=idUser%>">
        </form>
        <p></p> <!-- Enter (salto de linea) -->
        <a href="javascript:categoryid2()">
            <small><%=view2%></small>
            <script>
                function categoryid2() {
                    document.sendCategoryId2.submit();
                }
            </script>
        </a>
        <form action="checkout.do" method="post" name="sendCategoryId2">
            <input type="hidden" name="categoryid" value="<%=cat.getId()%>">
            <input type="hidden" name="idUser" value="<%=idUser%>">
        </form>
        <p></p>
        
        <table width="50%" border="1" bordercolordark="#000000" bordercolorlight="#FFFFFF" cellpadding="3" cellspacing="0">
            
            <tr> <font size="2" face="Verdana">
                <td width="14%" valign="center" align="middle">
                    <table width="100%" border="1" bordercolordark="#000000" bordercolorlight="#FFFFFF" cellpadding="3" cellspacing="0">
                        <%
                            List<Category> categories = (List<Category>)request.getAttribute("categories");

                            for(Category category : categories){
                        %>
                        <tr width="50%" valign="center" align="middle">
                            <td>
                                <a href="category.do?categoryid=<%=category.getId()%>&idUser=<%=idUser%>">
                                    <%=category.getName()%>
                                </a>
                            </td>
                        </tr>
                        <% } %>
                    </table>
                </td>
                <td width="14%" valign="center" align="middle">
                    <table width="100%" border="1" bordercolordark="#000000" bordercolorlight="#FFFFFF" cellpadding="3" cellspacing="0">
                        <%
                            List<Product> products = (List<Product>)request.getAttribute("products");
                            for(Product product : products){
                        %>
                        <tr valign="center" align="middle">
                            <td>
                                <img src="img/products/<%=product.getName()%>.png"
                                    alt="<%=product.getName()%>" >
                            </td>
                            <td >
                                <b><%=product.getName()%></b>
                                <p><%=product.getDescription()%></p>
                            </td>
                            <td>
                                <p><%=product.getPrice()%>€</p>
                            </td>
                            <td>
                                <form action="neworder.do" method="post">
                                    <input type="hidden" name="categoryid" value="<%=cat.getId()%>">
                                    <input type="hidden" name="productid" value="<%=product.getId()%>">
                                    <input type="hidden" name="idUser" value="<%=idUser%>">
                                    <input type="submit" name="submit" value="add to cart">
                                </form>
                            </td>
                        </tr>
                        <% } %>
                    </table>
                </td>
            </font> </tr>
        </table>
    </body>
</html>
