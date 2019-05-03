/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcatalog.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import shoppingcatalog.dao.StoreDAO;
import shoppingcatalog.dto.ItemDTO;
import shoppingcatalog.dto.ItemInfoDTO;

/**
 *
 * @author LENOVO
 */
public class StoreControllerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       RequestDispatcher rd = null;
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        System.out.println(username);
        try {
            if (username == null) {
                session.invalidate();
                rd = request.getRequestDispatcher("accessdenied.html");
                //rd.forward(request, response);
            }
            else{
            String itemType = request.getParameter("itemType");
            String itemId =  request.getParameter("itemId");
            System.out.println("item type: " + itemType + " and itemid:" + itemId);
            
            if (itemType == null && itemId == null) {
                List<String> itemList = (List<String>)StoreDAO.getItemTypes();
                rd = request.getRequestDispatcher("seestore.jsp");
                request.setAttribute("itemList", itemList);
            }
            else if (itemType!= null) {
                List<ItemInfoDTO> itemList =(List<ItemInfoDTO>) StoreDAO.getItemsByType(itemType);

                rd = request.getRequestDispatcher("showitemsbytype.jsp");
                request.setAttribute("itemList", itemList);
            }
            else  if (itemId!= null) {
                ItemDTO item = StoreDAO.getItemDetails(Integer.parseInt(itemId));
                rd = request.getRequestDispatcher("showitemdetails.jsp");
                request.setAttribute("item", item);

            } 
            }
            
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
            request.setAttribute("exception", ex);
            rd = request.getRequestDispatcher("showexception.jsp");
        }
        finally {
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
