package org.flightcrew.beans;

public class Employee {

	private int id;
	private int ssn;
	private int isManager;
	private String startDate;
	private double hourlyRate;
	private String username;
	private String password;
	
	public Employee(int id, int ssn, int isManager, String startDate, double hourlyRate, String username,
			String password) {
		this.id = id;
		this.ssn = ssn;
		this.isManager = isManager;
		this.startDate = startDate;
		this.hourlyRate = hourlyRate;
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public int getIsManager() {
		return isManager;
	}

	public void setIsManager(int isManager) {
		this.isManager = isManager;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public double getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
