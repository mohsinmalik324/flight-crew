package org.flightcrew.beans;

public class Passenger {

	private int id;
	private int accountNo;
	
	public Passenger(int id, int accountNo) {
		this.id = id;
		this.accountNo = accountNo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	
	
	
}
