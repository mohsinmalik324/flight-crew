package org.flightcrew.beans;

public class Fare {

	private String airlineID;
	private int flightNo;
	private String fareType;
	private String classType;
	private double fare;
	
	public Fare(String airlineID, int flightNo, String fareType, String classType, double fare) {
		this.airlineID = airlineID;
		this.flightNo = flightNo;
		this.fareType = fareType;
		this.classType = classType;
		this.fare = fare;
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

	public String getFareType() {
		return fareType;
	}

	public void setFareType(String fareType) {
		this.fareType = fareType;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}
	
	
	
}
