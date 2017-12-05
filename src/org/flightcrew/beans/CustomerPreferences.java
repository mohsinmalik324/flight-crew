package org.flightcrew.beans;

public class CustomerPreferences {

	private int accountNo;
	private String preference;
	
	public CustomerPreferences(int accountNo, String preference) {
		this.accountNo = accountNo;
		this.preference = preference;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public String getPreference() {
		return preference;
	}

	public void setPreference(String preference) {
		this.preference = preference;
	}

	
}
