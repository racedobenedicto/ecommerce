<%-- 
    Document   : login
    Created on : 30-oct-2021, 18:16:55
    Author     : raquel
--%>

<%@page import="entity.Category"%>
<%@page import="java.util.List"%>
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
        <h2>Welcome to the online home of our virtual grocery</h2>
        
        <%
            List<Category> categories = (List<Category>)request.getAttribute("categories");
            String error = (String)request.getAttribute("error");
        %>
        
        <br>
        <h3>Have you an account? Login</h3>
        <p></p>
        <form action="checklogin.do" method="post">
            <p>Username: <input type="text" name="user" size="20" placeholder="Enter your username"></p>
            <p>Password: <input type="password" name="pass" size="20" placeholder="Enter your password"></p>
            <input type="hidden" name="categories" value="<%=categories%>">
            <input type="submit" name="submit" value="Login">
        </form>
        <br>
        <hr>
        <br>
        <h3>If not, Sign in</h3>
        <p></p>
        <form action="signin.do" method="post">
            <p>Username: <input type="text" name="user" size="20" placeholder="Enter your username"></p>
            <p>Password: <input type="password" name="pass" size="20" placeholder="Enter your password"></p>
            <input type="hidden" name="categories" value="<%=categories%>">
            <input type="submit" name="submit" value="Sign in">
        </form>
            
        <%
            if(error != null) {
        %>  
        
        <br>
        <hr>
        <p style="color:red;"><%=error%></p>
        
        <% } %>
    </body>
</html>
