package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class adminoptions_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/logo.html");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"styles/stylesheet.css\"> \n");
      out.write("        <script type=\"text/javascript\" src=\"scripts/jquery.js\">\n");
      out.write("         </script>\n");
      out.write("        <script type=\"text/javascript\" src=\"scripts/ShowOptions.js?v=1\"></script>\n");
      out.write("         \n");
      out.write("        <title>Store Items</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div id=\"logo\">\r\n");
      out.write("         <img src=\"images/shopping_logo5.png\" >\r\n");
      out.write("</div>");
      out.write("\n");
      out.write("        ");

          String username=(String)session.getAttribute("username");
          System.out.println("inside adminoptions username is "+username);
          if(username==null)
          {
             response.sendRedirect("accessdenied.html");
                      
          }
          else
          {
              
              StringBuffer displayBlock=new StringBuffer("<h1>Admin Options</h1><p>Select a category to take an action.</p>");
              displayBlock.append("<div id='productcontainer' style='float:right;padding-right: 700px;border=solid 2px'></div>");
              
              String[]options=new String[]{"Products","Users","View"};
              for(String option: options){
                  displayBlock.append("<p id='"+option+"'><strong><a href='#' onclick=getItemNames('"+option+"')><span>+"+option+"</span></strong></a></p>");
          
              }
              System.out.println("final to client");
               System.out.println(displayBlock);
               displayBlock.append("<h4 id='logout'><a href='LoginControllerServlet?logout=logout'>Logout</a></h4>");
              out.println(displayBlock);
          }
        
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
