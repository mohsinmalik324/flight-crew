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

import org.flightcrew.beans.UserAccount;
import org.flightcrew.utils.DBUtils;
import org.flightcrew.utils.MyUtils;
import org.flightcrew.utils.Utils;
 
@WebServlet(urlPatterns = { "/signup" })
public class signupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public signupServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        // Forward to /WEB-INF/views/accountView.jsp
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/signupView.jsp");
 
        dispatcher.forward(request, response);
    }
 
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String fname = request.getParameter("fname");
    	String lname = request.getParameter("lname");
    	String address = request.getParameter("address");
    	String city = request.getParameter("city");
    	String state = request.getParameter("state");
    	String zipString = request.getParameter("zipcode");
    	int zipcode = 0;
    	try {
    		zipcode = Integer.valueOf(zipString);
    	} catch(NumberFormatException e) {
    		response.sendRedirect("signup?err=1");
    		return;
    	}
    	if(zipcode < 1) {
    		response.sendRedirect("signup?err=1");
    		return;
    	}
    	String email = request.getParameter("email");
    	if(!Utils.isEmailValid(email)) {
    		response.sendRedirect("signup?err=5");
    		return;
    	}
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	String cpassword = request.getParameter("cpassword");
    	if(!password.equals(cpassword)) {
    		response.sendRedirect("signup?err=2");
    		return;
    	}
    	Connection conn = MyUtils.getStoredConnection(request);
    	try {
			UserAccount user = DBUtils.findUser(conn, username);
			if(user != null) {
				response.sendRedirect("signup?err=4");
				return;
			}
			DBUtils.addUser(conn, fname, lname, address, city, state, zipcode, email, username, cpassword);
			response.sendRedirect("home");
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("signup?err=3");
		}
    }
 
}