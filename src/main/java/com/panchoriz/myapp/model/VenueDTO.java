package com.panchoriz.myapp.model;

public class VenueDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7832395660338015163L;
	
	private String venueId;
	
	private String venueName;
	private String contact;
	private String streetAddress;
	private String city;
	private String province;
	
	private double rate;
	private int courtType;
	private int flooringType;
	
	private double longitude;
	private double latitude;
	
	public String getVenueName() {
		return venueName;
	}
	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String region) {
		this.province = region;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public int getCourtType() {
		return courtType;
	}
	public void setCourtType(int courtType) {
		this.courtType = courtType;
	}
	public int getFlooringType() {
		return flooringType;
	}
	public void setFlooringType(int flooringType) {
		this.flooringType = flooringType;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getVenueId() {
		return venueId;
	}
	public void setVenueId(String id) {
		this.venueId = id;
	}
	
}
