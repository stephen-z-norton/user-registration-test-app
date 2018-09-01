package com.test.model;

import java.util.Date;

public class UserRegistrationDataModel {
	public UserRegistrationDataModel(String firstNameIn, String lastNameIn, String address1In, String address2In, String cityIn, String stateIn, String zipCodeIn, String countryIn) {
		setFirstName(firstNameIn);
		setLastName(lastNameIn);
		setAddress1(address1In);
		setAddress2(address2In);
		setCity(cityIn);
		setState(stateIn);
		setZipCode(zipCodeIn);
		setCountry(countryIn);	
	}
	public UserRegistrationDataModel() {
		
	}

	private String firstName = null;
	private String lastName = null;
	private String address1 = null;
	private String address2 = null;
	private String city = null;
	private String state = null;
	private String zipCode = null;
	private String country = null;
	private java.util.Date registrationDate;
	
	public void setFirstName(String valueIn) {
		firstName = valueIn;
	}
	public String getFirstName() {
		return firstName;
	}
	
	public void setLastName(String valueIn) {
		lastName = valueIn;
	}
	public String getLastName() {
		return lastName;
	}
	
	public void setAddress1(String valueIn) {
		address1 = valueIn;
	}
	public String getAddress1() {
		return address1;
	}
	
	public void setAddress2(String valueIn) {
		address2 = valueIn;
	}
	public String getAddress2() {
		return address2;
	}
	
	public void setCity(String valueIn) {
		city = valueIn;
	}
	public String getCity() {
		return city;
	}
	
	public void setState(String valueIn) {
		state = valueIn;
	}
	public String getState() {
		return state;
	}
	
	public void setZipCode(String valueIn) {
		zipCode = valueIn;
	}
	public String getZipCode() {
		return zipCode;
	}
	
	public void setCountry(String valueIn) {
		country = valueIn;
	}
	public String getCountry() {
		return country;
	}
	
	public java.util.Date getRegistrationDate() {
		if(registrationDate != null) {
			return new Date(registrationDate.getTime());
		} else {
			return null;
		}
	}
	public void setRegistrationDate(java.sql.Timestamp dateIn) {
		
		if(dateIn != null) {
			registrationDate =  new Date(dateIn.getTime());
		} else {
			registrationDate = null;
		}
	}	
}
