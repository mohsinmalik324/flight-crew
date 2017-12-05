package org.flightcrew.beans;

public class Airport {
	
	private String id = null;
	private String name = null;
	private String city = null;
	private String country = null;
	
	public Airport(String id, String name, String city, String country) {
		this.setId(id);
		this.setName(name);
		this.setCity(city);
		this.setCountry(country);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}