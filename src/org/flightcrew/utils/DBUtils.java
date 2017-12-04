package org.flightcrew.utils;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.flightcrew.beans.UserAccount;
 
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