package org.flightcrew.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.flightcrew.beans.UserAccount;
import org.flightcrew.beans.UserAccount.AccountType;
 
 
@WebServlet(urlPatterns = { "/account" })
public class accountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public accountServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        // Retrieve account from current session.
    	UserAccount ua = (UserAccount) request.getSession().getAttribute("loginedUser");
    	AccountType accountType = null;
    	RequestDispatcher dispatcher;
    	
    	// Find what type of account the current user has.
    	if(ua != null) {
    		accountType = ua.getType();
    	}
    	
    	// Redirect to appropriate page based on type of account or direct to login page if user is not logged in.
    	if(accountType == AccountType.Customer) {
    		dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/accountView.jsp");
    	} else if(accountType == AccountType.Representative) {
    		dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/repView.jsp");
    	} else if(accountType == AccountType.Manager) {
    		dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/managerView.jsp");
    	} else {
            String errorString = "It appears you're not logged in.";
            request.setAttribute("errorString", errorString);
    		dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
    	}
 
        dispatcher.forward(request, response);
    }
 
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	doGet(request, response);
    }
 
}