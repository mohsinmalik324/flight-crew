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

import org.flightcrew.beans.Customer;
import org.flightcrew.beans.UserAccount;
import org.flightcrew.utils.DBUtils;
import org.flightcrew.utils.MyUtils;
 
 
@WebServlet(urlPatterns = { "/login" })
public class loginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public loginServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        // Forward to /WEB-INF/views/accountView.jsp
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
 
        dispatcher.forward(request, response);
    }
 
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String userName = request.getParameter("Username");
        String password = request.getParameter("Password");
 
        UserAccount user = null;
        boolean hasError = false;
        String errorString = null;
 
        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            try {
                // Find the user in the DB.
                user = DBUtils.findUser(conn, userName, password);
 
                if (user == null) {
                    hasError = true;
                    errorString = "User Name or password invalid";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        // If error, forward to /WEB-INF/views/login.jsp
        if (hasError) {
        	user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
 
            // Store information in request attribute, before forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);
        	
            // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
 
            dispatcher.forward(request, response);
        }
        // If no error
        // Store user information in Session
        // And redirect to userInfo page.
        else {
            HttpSession session = request.getSession();
            MyUtils.storeLoginedUser(session, user);
            try {
				MyUtils.storeCustomer(session, DBUtils.getCustomer(MyUtils.getStoredConnection(request), userName));
				MyUtils.storePerson(session, DBUtils.getPerson(MyUtils.getStoredConnection(request), userName));
				Customer cust = (Customer)session.getAttribute("customer");
				MyUtils.storeReservations(session, DBUtils.getReservations(MyUtils.getStoredConnection(request), cust.getAccountNo()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 
            // Redirect to userInfo page.
            response.sendRedirect(request.getContextPath() + "/account");
        }
    }
 
}