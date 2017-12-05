package org.flightcrew.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.flightcrew.beans.UserAccount;
import org.flightcrew.utils.DBUtils;
import org.flightcrew.utils.MyUtils;
 
 
@WebServlet(urlPatterns = { "/logout" })
public class loggedOutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public loggedOutServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
    	// Logs out the user and redirects to home page.
    	request.getSession().removeAttribute("loginedUser");
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");
 
        dispatcher.forward(request, response);
    }
 
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       doGet(request, response);
    }

}