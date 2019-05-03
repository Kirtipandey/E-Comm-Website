<%@page contentType="text/html" pageEncoding="UTF-8" import="shoppingcatalog.*, shoppingcatalog.dao.*, shoppingcatalog.dto.*, java.util.*, java.text.*"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="styles/stylesheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Orders Page</title>
    </head>
    <body>
        <%@ include file="logo.html"%>
        <%
            String username=(String)session.getAttribute("username");
            if(username==null)
            {
                session.invalidate();
                response.sendRedirect("accessdenied.html");
            }
            else
            {
                ArrayList<OrderDTO> orderList=StoreDAO.getOrdersByCustomer(username);
                StringBuffer displayBlock=new StringBuffer("<h1>MY Store-My Orders</h1>");
                displayBlock.append("<div style='float:left;'>");
                if(orderList.isEmpty())
                    displayBlock.append("<p><strong>You have not placed any orders yet!</strong></p>");
                else
                {
                    SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
                    displayBlock.append("<table border='1'>");
                    displayBlock.append("<tr><th>Order Id</th><th>Order Amount</th><th>Order Date</th></tr>");
                    for(OrderDTO order:orderList)
                    {
                        String dateStr=sdf.format(order.getOrderDate());    
                        displayBlock.append("<tr><td>"+order.getOrderId()+"</td><td>"+order.getOrderAmount()+"</td><td>"+dateStr+"</td></tr>");
                    }
                }
                displayBlock.append("<p><a href='StoreControllerServlet'>Show Categories</a></p></div>");
                displayBlock.append("<h4 id='logout'><a href='LoginControllerServlet?logout=logout'>Logout</a></h4>");
                out.println(displayBlock);
            }
        %>
    </body>
</html>