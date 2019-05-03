<%@page import="shoppingcatalog.dao.StoreDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/stylesheet.css"> 
        <script type="text/javascript" src="scripts/jquery.js">
        </script>
        <script type="text/javascript" src="scripts/ShowOptions.js?v=1"></script>

        <title>Store Items</title>
    </head>
    <body>
        <%@include file="logo.html"%>
        <%
            String username = (String) session.getAttribute("username");
            System.out.println("inside adminoptions username is " + username);
            if (username == null) {
                response.sendRedirect("accessdenied.html");

            } else {
                StringBuffer sb=new StringBuffer();
                ArrayList<Integer>list=StoreDAO.getAllProductId();
                 sb.append("<div id='"+list+"'></div>");
                 out.println(sb);
              
            
            }
        %>
    </body>
</html>

