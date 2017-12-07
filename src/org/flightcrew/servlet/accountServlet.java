package org.flightcrew.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.util.ParameterMap;
import org.flightcrew.beans.Customer;
import org.flightcrew.beans.UserAccount;
import org.flightcrew.beans.UserAccount.AccountType;
import org.flightcrew.utils.DBUtils;
import org.flightcrew.utils.MyUtils;
 
 
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
    		String accountNoString = request.getParameter("acc-no-input");
			if(accountNoString != null) {
				boolean validInput = true;
				if(accountNoString == "") {
					request.getSession().setAttribute("modify_cust_error", "Enter an account number.");
					validInput = false;
				}
				Integer accountNo = null;
				if(validInput) {
					try {
						accountNo = Integer.valueOf(accountNoString);
					} catch(NumberFormatException e) {
						request.getSession().setAttribute("modify_cust_error", "Account number needs to be an integer.");
						validInput = false;
					}
				}
				if(validInput) {
					try {
						MyUtils.storeCustomer(request.getSession(), DBUtils.getCustomer(MyUtils.getStoredConnection(request), accountNo));
					} catch(SQLException e) {
						request.getSession().removeAttribute("customer");
						request.getSession().setAttribute("modify_cust_error", "Could not find account number " + accountNoString + ".");
					}
				}
			}
			
			String ccStr = request.getParameter("acc-cc-in");
	    	String emailStr = request.getParameter("acc-email-in");
	    	String unameStr = request.getParameter("acc-uname-in");
	    	String pwordStr = request.getParameter("acc-pword-in");
			
	    	if(ccStr != null || emailStr != null || unameStr != null || pwordStr != null) {
	    		Customer cust = (Customer) request.getSession().getAttribute("savedCustomer");
	    		if(cust != null) {
		    		if(ccStr == null || ccStr.equals("")) ccStr = cust.getCreditCardNo();
		    		if(emailStr == null || emailStr.equals("")) emailStr = cust.getEmail();
		    		if(unameStr == null || unameStr.equals("")) unameStr = cust.getUsername();
		    		if(pwordStr == null || pwordStr.equals("")) pwordStr = cust.getPassword();
		    		
		    		DBUtils.updateCustomer(MyUtils.getStoredConnection(request), cust.getAccountNo(), emailStr, unameStr, pwordStr, ccStr);
		    		request.getSession().removeAttribute("customer");
					request.getSession().setAttribute("modify_cust_success", "Succesfully made changes to account number "
							+ String.valueOf(cust.getAccountNo()) + ".");
		    		request.getSession().removeAttribute("savedCustomer");
	    		}
	    	}
	    	
	    	
	    	if(request.getParameter("gen-ml-submit") != null) {
	    	}
	    		
	    	if(request.getParameter("res-submit") != null) {
				String resdateStr = request.getParameter("res-date-in");
				String bookfeeStr = request.getParameter("book-fee-in");
				String totfareStr = request.getParameter("tot-fare-in");
				String repidStr = request.getParameter("rep-id-in");
				String accnoStr = request.getParameter("acc-no3-in");
				String airidStr = request.getParameter("air-id-in");
				String flightnoStr = request.getParameter("flight-no-in");
				String legnoStr = request.getParameter("leg-no-in");
				String flightdateStr = request.getParameter("flight-date-in");
				
				boolean user, rep;
				try {
					Connection conn = MyUtils.getStoredConnection(request);
					user = DBUtils.userExists(conn, accnoStr);
					rep = DBUtils.repExists(conn, repidStr);
					if(user && rep) {
						int resrNo, repSSN, accountNo, flightNo, legNo;
						double bookingFee, totalFare;
						try {
							repSSN = Integer.valueOf(repidStr);
							accountNo = Integer.valueOf(accnoStr);
							bookingFee = Double.valueOf(bookfeeStr);
							totalFare = Double.valueOf(totfareStr);
							flightNo = Integer.valueOf(flightnoStr);
							legNo = Integer.valueOf(legnoStr);
							resrNo = DBUtils.createReservation(conn, resdateStr, bookingFee, totalFare, repSSN, accountNo);
							DBUtils.addInclude(conn, resrNo, airidStr, flightNo, legNo, flightdateStr);
						} catch (NumberFormatException e) {
							e.printStackTrace();
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
	    	}
	    	
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