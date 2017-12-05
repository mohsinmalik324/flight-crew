package org.flightcrew.beans;

public class Flight {

	private String airlineID;
	private int flightNo;
	private int noOfSeats;
	private String daysOperating;
	private int minLengthOfStay;
	private int maxLengthOfStay;
	
	public Flight(String airlineID, int flightNo, int noOfSeats, String daysOperating, int minLengthOfStay,
			int maxLengthOfStay) {
		this.airlineID = airlineID;
		this.flightNo = flightNo;
		this.noOfSeats = noOfSeats;
		this.daysOperating = daysOperating;
		this.minLengthOfStay = minLengthOfStay;
		this.maxLengthOfStay = maxLengthOfStay;
	}
	public String getAirlineID() {
		return airlineID;
	}
	public void setAirlineID(String airlineID) {
		this.airlineID = airlineID;
	}
	public int getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(int flightNo) {
		this.flightNo = flightNo;
	}
	public int getNoOfSeats() {
		return noOfSeats;
	}
	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
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
	
}
