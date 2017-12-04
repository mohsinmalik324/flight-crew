package org.flightcrew.utils;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import org.flightcrew.beans.UserAccount;
//import org.flightcrew.beans.UserAccount.AccountType;
 
public class DBUtils {
 
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