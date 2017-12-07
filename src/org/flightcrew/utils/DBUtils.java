package org.flightcrew.utils;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flightcrew.beans.Airline;
import org.flightcrew.beans.Airport;
import org.flightcrew.beans.Customer;
import org.flightcrew.beans.Flight;
import org.flightcrew.beans.Includes;
import org.flightcrew.beans.Leg;
import org.flightcrew.beans.Person;
import org.flightcrew.beans.Reservation;
import org.flightcrew.beans.UserAccount;
import org.flightcrew.beans.UserAccount.AccountType;

//server: sql9.freemysqlhosting.net
//user: sql9208791
//pass: HALlmFZxtp


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
	
	public static Airport getAirport(Connection conn, String airportID) {
		String sql = "SELECT Name,City,Country FROM Airport WHERE Id = '" + airportID + "'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String name = rs.getString("Name");
				String city = rs.getString("City");
				String country = rs.getString("Country");
				return new Airport(airportID, name, city, country);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Airline getAirline(Connection conn, String airlineID) {
		String sql = "SELECT Name FROM Airline WHERE Id = '" + airlineID + "'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return new Airline(airlineID, rs.getString("Name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
   	
   	public static Map<Flight, List<Leg>> getFlightsAndLegs(Connection conn, String origin, String dest, String deptDate) {
   		List<Leg> legsToUse = new ArrayList<>();
		try {
			String sql = "SELECT * FROM Leg WHERE DepAirportID = '" + origin + "' OR ArrAirportID = '" + dest + "'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			Map<String, List<Leg>> legs = new HashMap<>();
			while(rs.next()) {
				String airlineID = rs.getString("AirlineID");
				int flightNo = rs.getInt("FlightNo");
				int legNo = rs.getInt("LegNo");
				String depAirportID = rs.getString("DepAirportID");
				String arrAirportID = rs.getString("ArrAirportID");
				String depTime = rs.getString("DepTime");
				String arrTime = rs.getString("ArrTime");
				String key = airlineID + "," + flightNo;
				if(!legs.containsKey(key)) {
					legs.put(key, new ArrayList<Leg>());
				}
				legs.get(key).add(new Leg(airlineID, flightNo, legNo, depAirportID, arrAirportID, depTime, arrTime));
			}
			//List<Leg> legsToInclude = new ArrayList<>();
			for(String key : legs.keySet()) {
				List<Leg> list = legs.get(key);
				if(list.size() == 1) {
					Leg leg = list.get(0);
					System.out.println(0);
					if(leg.getDepAirportID().equals(origin) && leg.getArrAirportID().equals(dest)) {
						System.out.println(1);
						String[] legDepTime = leg.getDepTime().split(" ")[0].split("-");
						String[] deptDateSplit = deptDate.split("-");
						for(String s : legDepTime) {
							System.out.print(s);
						}
						System.out.println();
						for(String s : deptDateSplit) {
							System.out.print(s);
						}
						if(legDepTime[0].equals(deptDateSplit[0]) && legDepTime[1].equals(deptDateSplit[1]) && legDepTime[2].equals(deptDateSplit[2])) {
							System.out.println(2);
							legsToUse.add(leg);
						}
					}
				} else if(list.size() == 2) {
					Leg first = list.get(0);
					Leg second = list.get(1);
					if(first.getLegNumber() > second.getLegNumber()) {
						Leg tmp = first;
						first = second;
						second = tmp;
					}
					if(first.getDepAirportID().equals(origin) && second.getArrAirportID().equals(dest)) {
						String[] legDepTime = first.getDepTime().split(" ")[0].split("-");
						String[] deptDateSplit = deptDate.split("-");
						if(legDepTime[0].equals(deptDateSplit[0]) && legDepTime[1].equals(deptDateSplit[1]) && legDepTime[2].equals(deptDateSplit[2])) {
							legsToUse.add(first);
							if(first.getLegNumber() != second.getLegNumber() - 1) {
								sql = "SELECT * FROM Leg WHERE AirlineID = '' AND FlightNo = '' AND LegNo > " + first.getLegNumber() + " AND LegNo < " + second.getLegNumber();
								PreparedStatement ps2 = conn.prepareStatement(sql);
								ResultSet rs2 = ps2.executeQuery();
								while(rs2.next()) {
									String airlineID = rs.getString("AirlineID");
									int flightNo = rs.getInt("FlightNo");
									int legNo = rs.getInt("LegNo");
									String depAirportID = rs.getString("DepAirportID");
									String arrAirportID = rs.getString("ArrAirportID");
									String depTime = rs.getString("DepTime");
									String arrTime = rs.getString("ArrTime");
									Leg leg = new Leg(airlineID, flightNo, legNo, depAirportID, arrAirportID, depTime, arrTime);
									legsToUse.add(leg);
								}
							}
							legsToUse.add(second);
						}
					}
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		Map<String, Flight> flights = new HashMap<>();
		Map<Flight, List<Leg>> flightsAndLegs = new HashMap<>();
		for(Leg leg : legsToUse) {
			String airlineID = leg.getAirlineID();
			int flightNo = leg.getFlightNumber();
			String key = airlineID + "," + flightNo;
			if(!flights.containsKey(key)) {
				String sql = "SELECT NoOfSeats,DaysOperating,MinLengthOfStay,MaxLengthOfStay FROM Flight WHERE AirlineID = '" + airlineID + "' AND FlightNo = " + flightNo;
				try {
					PreparedStatement ps = conn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						int numSeats = rs.getInt("NoOfSeats");
						String daysOperating = rs.getString("DaysOperating");
						int minLengthOfStay = rs.getInt("MinLengthOfStay");
						int maxLengthOfStay = rs.getInt("MaxLengthOfStay");
						flights.put(key, new Flight(airlineID, flightNo, numSeats, daysOperating, minLengthOfStay, maxLengthOfStay));
					}
				} catch (SQLException e) {
					e.printStackTrace();
					continue;
				}
			}
			if(flights.containsKey(key)) {
				Flight flight = flights.get(key);
				if(!flightsAndLegs.containsKey(flight)) {
					flightsAndLegs.put(flight, new ArrayList<Leg>());
				}
				flightsAndLegs.get(flight).add(leg);
			}
		}
		return flightsAndLegs;
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
		}
		return airports;
	}
	
	//TODO: Add, Edit and Delete information for a customer
	// Assumes user does not exist.
	public static boolean addUser(Connection conn, String fname, String lname, String address, String city, String state, int zip, String email, String username, String password) {
		int id = getNumberOfRecords(conn, "Person") + 1;
		int accountNumber = getNumberOfRecords(conn, "Customer") + 1;
		String sql = "INSERT INTO Person VALUES (" + id + ", '" + fname + "', '" + lname + "', '" + address + "', '" + city + "', '" + state + "', " + zip + ")";
		String sql2 = "INSERT INTO Customer VALUES (" + id + ", " + accountNumber + ", NULL, '" + email + "', NOW(), NULL, '" + username + "', '" + password + "')";
		return runQuery(conn, sql) && runQuery(conn, sql2);
	}
	
	public static boolean updateUser(Connection conn, int accountID, String fname, String lname, String address, String city, String state, int zip, String email, String username, String password, String ccNo, int rating, String curUser) {
		String sql = "UPDATE Person SET FirstName = " + fname + ", LastName = " + lname + ", Address = " + address + ", City = " + city + ", State = " + state + ", ZipCode = " + zip + " WHERE Id = " + accountID;
		String sql2 = "UPDATE Customer SET CreditCardNo = " + ccNo + ", Email = " + email + ", Password = " + password + "WHERE Username = " + curUser;
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
 
    	// Try finding a customer account first.
        String sql = "Select a.Username, a.Password from Customer a " //
                + " where a.Username = ? and a.password= ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            UserAccount user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            user.setType(AccountType.Customer);
            return user;
        } else {
        	// If no customer account exists, try employee account.
        	sql = "Select a.Username, a.Password, a.IsManager from Employee a " //
                    + " where a.Username = ? and a.password= ?";
     
        	pstm = null;
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, userName);
            pstm.setString(2, password);
            rs = null;
            rs = pstm.executeQuery();
     
            if (rs.next()) {
                UserAccount user = new UserAccount();
                user.setUserName(userName);
                user.setPassword(password);
                if(rs.getInt("IsManager") > 0) user.setType(AccountType.Manager);
                else user.setType(AccountType.Representative);
                return user;
            }
        }
        // Login doesn't match customer or employee account.
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
    
  //TODO: Customer level transaction page
    
	//TODO: Travel itinerary for a given reservation
	public static Leg getTravelItinerary() {
    	//String sql = "Select * from Legs"//
                //+ " where a.Username = ? ";
   		//return new Leg("", 0, 0, "", "", "", "");
		throw new UnsupportedOperationException("Not supported yet.");
   	}
	
	  //TODO: A customer's current bid on a given reverse auction
	public static double getCurrentBid() {
		
//        String sql = "Select NYOP from Auctions" //
//                + " where AccountNo = ? and AirlineID = ? and FlightNo = ? and Date = ?";
// 
//        PreparedStatement pstm = conn.prepareStatement(sql);
//        pstm.setString(1, userName);
//        pstm.setString(2, password);
//        ResultSet rs = pstm.executeQuery();
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
	  //TODO: The bid history for a given reverse auction
	
	
	//TODO: CHECK
	//TODO: A history of all current and past reservations a customer has made
	public static ArrayList<Reservation> getReservations(Connection conn, int accountNo) throws SQLException {
		
        ArrayList<Reservation> reservations = new ArrayList<>();
		
		String sql = "Select * from Reservation" //
                + " where AccountNo = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, accountNo);
        ResultSet rs = pstm.executeQuery();
        
		while(rs.next()) {
			Reservation reservation = new Reservation(rs.getInt("ResrNo"), rs.getDate("ResrDate").toString(), rs.getDouble("BookingFee"), rs.getDouble("TotalFare"), rs.getInt("RepSSN"), rs.getInt("AccountNo"));
			reservations.add(reservation);
		}
		
		return reservations;
	}
	
	  //TODO: CHECK
	  //TODO: Best-Seller list of flights  
	public static List<Includes> getBestSellerList(Connection conn) throws SQLException {
		List<Includes> bestSellers = new ArrayList<>();
		
		String sql = "Select * from Includes" //
                + " Group by AirlineID, FlightNo" //
                + " Order by Count(*) DESC";
		
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        
		while(rs.next()) {
			Includes bestSeller = new Includes(rs.getInt("ResrNo"), rs.getString("AirlineID"), rs.getInt("FlightNo"), rs.getInt("LegNo"), rs.getDate("Date").toString());
			bestSellers.add(bestSeller);
		}
		
		return bestSellers;
	}
	
	//TODO: Customer-Representative level transaction page
	
	//CHECK
	//TODO: Record a reservation
	public static boolean createReservation(Connection conn, double bookingFee, double totalFare, int repSSN, int accountNo) throws SQLException {
		
		int resrNo = getNumberOfRecords(conn, "Reservation") + 1;
		String sql = "INSERT INTO Reservation (ResrNo, ResrDate, BookingFee, TotalFare, RepSSN, AccountNo)"//
                + " VALUES (?, NOW(), ?, ?, ?, ?)";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, resrNo);
        pstm.setDouble(2, bookingFee);
        pstm.setDouble(3, totalFare);
        pstm.setInt(4, repSSN);
        pstm.setInt(5, accountNo);
        
        return pstm.execute();
	}
	

	
	//TODO: COMPLETED Produce customer mailing lists
	public static List<String> getMailingList(Connection conn) throws SQLException {
		List<String> emails = new ArrayList<>();
		
		String sql = "SELECT email FROM Customer";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        
		while(rs.next()) {
			String email = new String(rs.getString("Email"));
			emails.add(email);
		}
		
		return emails;
	}
	
	//TODO: Produce a list of flight suggestions for a given customer (based on that customer's past reservations)
	//TODO: Personalized flight suggestion list
	public static List<Flight> getPersonalizedFlights(Connection conn, int accountNo) {
	
//		List<Flight> flights = new ArrayList<>();
//		
//		String sql = "Select DISTINCT * from Reservation" //
//              + " where AccountNo = ?";
//		
//		return flights;
		
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
	
	public static Customer getCustomer(Connection conn, String username) throws SQLException {
		String sql = "SELECT * from Customer WHERE Username = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, username);
        ResultSet rs = pstm.executeQuery();
        rs.next();
        return new Customer(rs.getInt("Id"), rs.getInt("AccountNo"), rs.getString("CreditCardNo"), rs.getString("Email"), rs.getString("CreditCardNo"), rs.getInt("Rating"), rs.getString("Username"), rs.getString("Password"));
	}
	
	public static Person getPerson(Connection conn, String username) throws SQLException {
		String sql = "SELECT * from Person WHERE Id = (select Id from Customer Where Username = ?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, username);
        ResultSet rs = pstm.executeQuery();
        rs.next();
        return new Person(rs.getInt("Id"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Address"), rs.getString("City"), rs.getString("State"), rs.getInt("ZipCode"));
	}
	
	
}