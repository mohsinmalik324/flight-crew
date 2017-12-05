package org.flightcrew.beans;

public class AdvPurchaseDiscount {

	private String airlineID;
	private int days;
	private double discountRate;
	
	public String getAirlineID() {
		return airlineID;
	}



	public void setAirlineID(String airlineID) {
		this.airlineID = airlineID;
	}



	public int getDays() {
		return days;
	}



	public void setDays(int days) {
		this.days = days;
	}



	public double getDiscountRate() {
		return discountRate;
	}



	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}



	public AdvPurchaseDiscount() {
	}



	public AdvPurchaseDiscount(String airlineID, int days, double discountRate) {
		this.airlineID = airlineID;
		this.days = days;
		this.discountRate = discountRate;
	} 
	
}
