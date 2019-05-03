<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="shoppingcatalog.dto.ItemDTO"%>
<%@page import="java.util.Enumeration"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="styles/stylesheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Place Order Page</title>
    </head>
       <%@include file="logo.html" %>
<%
    String username = (String) session.getAttribute("username");
    if (username == null) {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
    } else {

        StringBuffer displayBlock = new StringBuffer("<h1>My Store-Oreder Details</h1>");
        displayBlock.append("<div style='float:left'>");
        Enumeration en = session.getAttributeNames();
        displayBlock.append("<table border='1'>");
        displayBlock.append("<tr><th>Item name</th><th>Item Price</th><th>Remove Item</th></tr>");
        double totalAmount = 0.0;
        while (en.hasMoreElements()) {
            Object o = en.nextElement();
            if (o.equals("username") == false) {
                ItemDTO item = (ItemDTO) session.getAttribute(o.toString());
                displayBlock.append("<tr><td>" + item.getItemName() + "</td><td>" + item.getItemPrice()+"</td><td><a href='remove.jsp?itemId="+item.getItemId()+"'>remove</a></td</tr>");
                totalAmount += item.getItemPrice();
            }
        }
        displayBlock.append("</table>");
        displayBlock.append("<p><strong>Total Amount To Pay:</strong>" + totalAmount + "</p>");
        displayBlock.append("<p><a href='StoreControllerServlet'>Continue Shopping</a>&nbsp;&nbsp;&nbsp;&nbsp;");
        displayBlock.append("<a href='checkout.jsp?totalAmount=" + totalAmount + "'>Checkout</a></p></div>");
        displayBlock.append("<h4 id='logout'><a href='myorders.jsp'>My Orders</a>&nbsp;&nbsp;&nbsp;<a href='LoginControllerServlet?logout=logout'>Logout</a></h4>");
        out.println(displayBlock);
    }
%>
    
</html>