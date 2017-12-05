package org.flightcrew.beans;

public class Includes {

	private int resrNo;
	private String airlineID;
	private int flightNo;
	private int legNo;
	private String date;
	
	public Includes(int resrNo, String airlineID, int flightNo, int legNo, String date) {
		this.resrNo = resrNo;
		this.airlineID = airlineID;
		this.flightNo = flightNo;
		this.legNo = legNo;
		this.date = date;
	}
	public int getResrNo() {
		return resrNo;
	}
	public void setResrNo(int resrNo) {
		this.resrNo = resrNo;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
