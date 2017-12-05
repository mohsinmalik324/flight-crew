package org.flightcrew.beans;

public class Auctions {

	private int accountNo;
	private String airlineID;
	private int flightNo;
	private String classType;
	private String date;
	private double NYOP;
	
	public Auctions(int accountNo, String airlineID, int flightNo, String classType, String date, double nYOP) {
		this.accountNo = accountNo;
		this.airlineID = airlineID;
		this.flightNo = flightNo;
		this.classType = classType;
		this.date = date;
		NYOP = nYOP;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
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

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getNYOP() {
		return NYOP;
	}

	public void setNYOP(double nYOP) {
		NYOP = nYOP;
	}
	
	
}
