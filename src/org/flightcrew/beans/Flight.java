package org.flightcrew.beans;

public class Flight {
	
	public static enum FlightClass {
		Economy, Business, First
	}
	
	private String airlineID = null;
	private int flightNumber = 0;
	private int numSeats = 0;
	private String daysOperating = null;
	private int minLengthOfStay = 0;
	private int maxLengthOfStay = 0;
	
	public Flight(String airlineID, int flightNumber, int numSeats, String daysOperating, int minLengthOfStay, int maxLengthOfStay) {
		this.setAirlineID(airlineID);
		this.setFlightNumber(flightNumber);
		this.setNumSeats(numSeats);
		this.setDaysOperating(daysOperating);
		this.setMinLengthOfStay(minLengthOfStay);
		this.setMaxLengthOfStay(maxLengthOfStay);
	}

	public String getAirlineID() {
		return airlineID;
	}

	public void setAirlineID(String airlineID) {
		this.airlineID = airlineID;
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	public int getNumSeats() {
		return numSeats;
	}

	public void setNumSeats(int numSeats) {
		this.numSeats = numSeats;
	}

	public String getDaysOperating() {
		return daysOperating;
	}

	public void setDaysOperating(String daysOperating) {
		this.daysOperating = daysOperating;
	}

	public int getMinLengthOfStay() {
		return minLengthOfStay;
	}

	public void setMinLengthOfStay(int minLengthOfStay) {
		this.minLengthOfStay = minLengthOfStay;
	}

	public int getMaxLengthOfStay() {
		return maxLengthOfStay;
	}

	public void setMaxLengthOfStay(int maxLengthOfStay) {
		this.maxLengthOfStay = maxLengthOfStay;
	}
	
	public boolean operatesOnDay(int day) {
		return daysOperating.charAt(day) == '1';
	}
	
	public boolean sunday() {
		return daysOperating.charAt(0) == '1';
	}
	
	public boolean monday() {
		return daysOperating.charAt(1) == '1';
	}

	public boolean tuesday() {
		return daysOperating.charAt(2) == '1';
	}
	
	public boolean wednesday() {
		return daysOperating.charAt(3) == '1';
	}
	
	public boolean thursday() {
		return daysOperating.charAt(4) == '1';
	}
	
	public boolean friday() {
		return daysOperating.charAt(5) == '1';
	}
	
	public boolean saturday() {
		return daysOperating.charAt(6) == '1';
	}
	
}