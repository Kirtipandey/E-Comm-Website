<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,shoppingcatalog.dto.*"%>
<%
    String remove=(String)request.getParameter("itemId");
    Enumeration en=session.getAttributeNames();
    System.out.println("remove="+remove);
    while(en.hasMoreElements())
    {
        Object o=en.nextElement();
        System.out.println("o.toString="+o.toString());
         if(remove.equalsIgnoreCase(o.toString()))
        {
            session.removeAttribute(o.toString());
            response.sendRedirect("placeorder.jsp");
        }
    }
%>

