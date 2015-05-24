package com.panchoriz.myapp.repositories.documents;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="venue")
@TypeAlias("venue")
public class VenueDocument extends AbstractDocument<String> {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = -8787073068141526872L;
	
	private String venueName;
	private String contact;
	private String streetAddress;
	// TODO: this will be replaced by db entries
	private String city;
	// TODO: this will be replaced by db entries
	private String province;
	
	private double rate;
	private int courtType;
	private int flooringType;
	
	private long longitude;
	private long latitude;
	
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
	public long getLongitude() {
		return longitude;
	}
	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}
	public long getLatitude() {
		return latitude;
	}
	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}
}
