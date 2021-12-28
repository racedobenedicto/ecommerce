<%@page import="java.util.ArrayList"%>
<%@page import="model.ProductModel"%>
<%@page import="entity.Product"%>
<%@page import="entity.Comanda"%>
<%@page import="entity.User"%>
<%@ page import="entity.Category" %>
<%@ page import="java.util.List" %>

<head>
    <meta http-equiv="Expires" CONTENT="0">
    <meta http-equiv="Cache-Control" CONTENT="no-cache">
    <meta http-equiv="Pragma" CONTENT="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
    <title>eCommerce Sample</title>
</head>

<body>

    <%
        User user = (User)request.getAttribute("user");
        int nItems = (Integer)request.getAttribute("nItems");
        int idUser = -1;
        if(user == null) {            
    %>

    <form action="login.do" method="post">
        <input type="submit" name="submit" value="LOGIN">
    </form>

    <% } else { 
        String username = user.getUsername();
        idUser = user.getIduser();
    %>

    <p>Hello <%=username%>!</p>
    <form action="logout.do" method="post">
        <input type="hidden" name="idUser" value="<%=idUser%>">
        <input type="submit" name="submit" value="LOGOUT">
    </form>
    
    <%
        String view1 = "";
        if (nItems > 0) {
            view1 = "View Cart";
        }
    %>
    <img src="img/cart.gif">
    <small><%=nItems%> items</small>

    <a href="javascript:categoryid1()">
        <small><%=view1%></small>
        <script>
            function categoryid1() {
                document.sendCategoryId1.submit();
            }
        </script>
    </a>
    <form action="viewcart.do" method="post" name="sendCategoryId1">
        <input type="hidden" name="categoryid" value="1">
        <input type="hidden" name="idUser" value="<%=idUser%>">
    </form>
    <p></p>

    <% } %>

    <h2>Welcome to the online home of our virtual grocery</h2>

    <h3> Our unique home delivery service brings you fresh organic produce,
    dairy, meats, breads and other delicious and healthy items direct
    to your doorstep. </h3>


    <table width="50%" border="1" bordercolordark="#000000" bordercolorlight="#FFFFFF" cellpadding="3" cellspacing="0">

        <tr> <font size="2" face="Verdana">

        <%
        List<Category> categories = (List<Category>)request.getAttribute("categories");

        for(Category category : categories){

        %>

        <td width="14%" valign="center" align="middle">
            <a href="category.do?categoryid=<%=category.getId()%>&idUser=<%=idUser%>">
                <img src="img/categories/<%=category.getName()%>.jpg"
                     alt="<%=category.getName()%>" >
                <%=category.getName()%>
            </a>
        </td>

       <% } %>

        </font> </tr>

    </table>

    <%
        List<Comanda> lastOrders = (List<Comanda>)request.getAttribute("lastOrders");
        if(user != null && !lastOrders.isEmpty()) {
    %>
    <h3>Last seen and/or bought products</h3>
    
    <table width="50%" border="1" bordercolordark="#000000" bordercolorlight="#FFFFFF" cellpadding="3" cellspacing="0">
        <tr> <font size="4" face="Verdana">
            <td valign="center" align="middle">
                <b>Category</b>
            </td>
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
            </td>
        </tr>
        <%
            List<Comanda> comands = new ArrayList<Comanda>();
            Product product = null;
            Category category = null;
            ProductModel productModel = (ProductModel)request.getAttribute("productModel");
            for(Comanda c1 : lastOrders) {
                boolean find = false;
                for(Comanda c2 : comands) {
                    if(c2.getIdProduct() == c1.getIdProduct()) {
                        //c2.setQuantity(c2.getQuantity()+c1.getQuantity());
                        find = true;
                    }
                }
                if(!find) {
                    comands.add(c1);
                }
            }
            for(Comanda c : comands) {
                product = productModel.retrieve(c.getIdProduct());
                category = product.getCategory();
        %>
        <tr> <font size="2" face="Verdana">
            <td valign="center" align="middle">
                <p><%=category.getName()%></p>
            </td>
            <td valign="center" align="middle">
                <img src="img/products/<%=product.getName()%>.png"
                    alt="<%=product.getName()%>" >
            </td>
            <td valign="center" align="middle">
                <b><%=product.getName()%></b>
                <p><%=product.getDescription()%></p>
            </td>
            <td valign="center" align="middle">
                <p><%=product.getPrice()%></p>
            </td>
            <td valign="center" align="middle">
                <form action="historic.do" method="post">
                    <input type="hidden" name="productid" value="<%=product.getId()%>">
                    <input type="hidden" name="idUser" value="<%=idUser%>">
                    <input type="submit" name="submit" value="add to cart">
                </form>
            </td>
        </font> </tr>
        
        <% } %>
    </table>
        
    <% } %>
</body>