package org.flightcrew.beans;

public class Leg {

	private String airlineID;
	private int flightNo;
	private int legNo;
	private String depAirportID;
	private String arrAirportID;
	private String arrTime;
	private String depTime;
	
	public Leg(String airlineID, int flightNo, int legNo, String depAirportID, String arrAirportID, String arrTime,
			String depTime) {
		this.airlineID = airlineID;
		this.flightNo = flightNo;
		this.legNo = legNo;
		this.depAirportID = depAirportID;
		this.arrAirportID = arrAirportID;
		this.arrTime = arrTime;
		this.depTime = depTime;
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

	public int getLegNo() {
		return legNo;
	}

	public void setLegNo(int legNo) {
		this.legNo = legNo;
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

	public String getArrTime() {
		return arrTime;
	}

	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}

	public String getDepTime() {
		return depTime;
	}

	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}
	
	
}
