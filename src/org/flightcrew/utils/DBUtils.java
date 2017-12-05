
package org.flightcrew.utils;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.flightcrew.beans.Airport;
import org.flightcrew.beans.UserAccount;

//server: sql9.freemysqlhosting.net
//user: sql9208791
//pass: HALlmFZxtp

//TODO: Customer level transaction page
//TODO: Travel itinerary for a given reservation
//TODO: A customer's current bid on a given reverse auction
//TODO: The bid history for a given reverse auction
//TODO: A history of all current and past reservations a customer has made
//TODO: Personalized flight suggestion list
//TODO: Best-Seller list of flights

//TODO: Customer-Representative level transaction page
//TODO: Record a reservation
//TODO: Add, Edit and Delete information for a customer
//TODO: Produce customer mailing lists
//TODO: Produce a list of flight suggestions for a given customer (based on that customer's past reservations)

//TODO: Management level transaction page
//TODO: Add, Edit and Delete information for an employee
//TODO: Obtain a sales report for a particular month
//TODO: Produce a comprehensive listing of all flights
//TODO: Produce a list of reservations by flight number or by customer name
//TODO: Produce a summary listing of revenue generated by a particular flight, destination city, or customer
//TODO: Determine which customer representative generated most total revenue
//TODO: Determine which customer generated most total revenue
//TODO: Produce a list of most active flights
//TODO: Produce a list of all customers who have seats reserved on a given flight
//TODO: Produce a list of all flights for a given airport
 
public class DBUtils {
	
	public static boolean runQuery(Connection conn, String sql) {
		try {
			conn.prepareStatement(sql).execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static List<Airport> getAirports(Connection conn) {
		List<Airport> airports = new ArrayList<>();
		String sql = "SELECT * FROM Airport";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Airport airport = new Airport(rs.getString("Id"), rs.getString("Name"), rs.getString("City"), rs.getString("Country"));
				airports.add(airport);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return airports;
	}
	
	// Assumes user does not exist.
	public static boolean addUser(Connection conn, String fname, String lname, String address, String city, String state, int zip, String email, String username, String password) {
		int id = getNumberOfRecords(conn, "Person") + 1;
		int accountNumber = getNumberOfRecords(conn, "Customer") + 1;
		String sql = "INSERT INTO Person VALUES (" + id + ", '" + fname + "', '" + lname + "', '" + address + "', '" + city + "', '" + state + "', " + zip + ")";
		String sql2 = "INSERT INTO Customer VALUES (" + id + ", " + accountNumber + ", NULL, '" + email + "', NOW(), NULL, '" + username + "', '" + password + "')";
		return runQuery(conn, sql) && runQuery(conn, sql2);
	}
	
	public static int getNumberOfRecords(Connection conn, String table) {
		String sql = "SELECT COUNT(*) FROM " + table;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
 
    public static UserAccount findUser(Connection conn, //
            String userName, String password) throws SQLException {
 
        String sql = "Select a.Username, a.Password from Customer a " //
                + " where a.Username = ? and a.password= ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            //String type = rs.getString("AccountType");
            UserAccount user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            //user.setType(AccountType.valueOf(type));
            return user;
        }
        return null;
    }
 
    public static UserAccount findUser(Connection conn, String userName) throws SQLException {
 
        String sql = "Select a.Username, a.Password from Customer a "//
                + " where a.Username = ? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
 
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            String password = rs.getString("Password");
            //String type = rs.getString("AccountType");
            UserAccount user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            //user.setType(type);
            return user;
        }
        return null;
    }
}