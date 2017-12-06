package org.flightcrew.beans;

public class Leg {
	
	private String airlineID = null;
	private int flightNumber = 0;
	private int legNumber = 0;
	private String depAirportID = null;
	private String arrAirportID = null;
	private String depTime = null;
	private String arrTime = null;
	
	public Leg(String airlineID, int flightNumber, int legNumber, String depAirportID, String arrAirportID, String depTime, String arrTime) {
		this.setAirlineID(airlineID);
		this.setFlightNumber(flightNumber);
		this.setLegNumber(legNumber);
		this.setDepAirportID(depAirportID);
		this.setArrAirportID(arrAirportID);
		this.setDepTime(depTime);
		this.setArrTime(arrTime);
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

	public int getLegNumber() {
		return legNumber;
	}

	public void setLegNumber(int legNumber) {
		this.legNumber = legNumber;
	}

	public String getDepAirportID() {
		return depAirportID;
	}

	public void setDepAirportID(String depAirportID) {
		this.depAirportID = depAirportID;
	}

	public String getArrAirportID() {
		return arrAirportID;
	}

	public void setArrAirportID(String arrAirportID) {
		this.arrAirportID = arrAirportID;
	}

	public String getDepTime() {
		return depTime;
	}

	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}

	public String getArrTime() {
		return arrTime;
	}

	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}
	
}