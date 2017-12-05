package org.flightcrew.beans;

public class Customer {

	private int id;
	private int accountNo;
	private String creditCardNo;
	private String email;
	private String creationDate;
	private int rating;
	private String username;
	private String password;
	
	public Customer(int id, int accountNo, String creditCardNo, String email, String creationDate, int rating,
			String username, String password) {
		this.id = id;
		this.accountNo = accountNo;
		this.creditCardNo = creditCardNo;
		this.email = email;
		this.creationDate = creationDate;
		this.rating = rating;
		this.username = username;
		this.password = password;
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

	public String getCreditCardNo() {
		return creditCardNo;
	}

	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
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
