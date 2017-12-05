package org.flightcrew.beans;

public class ReservationPassenger {

	private int resrNo;
	private int id;
	private int accountNo;
	private String seatNo;
	private String classType;
	private String meal;
	
	public ReservationPassenger(int resrNo, int id, int accountNo, String seatNo, String classType, String meal) {
		this.resrNo = resrNo;
		this.id = id;
		this.accountNo = accountNo;
		this.seatNo = seatNo;
		this.classType = classType;
		this.meal = meal;
	}

	public int getResrNo() {
		return resrNo;
	}

	public void setResrNo(int resrNo) {
		this.resrNo = resrNo;
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

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getMeal() {
		return meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}
	
	
}
