package com.tsakirogf.RestExample.Model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	private String streetName;
	private int streetNumber;
	private String cityName;
	private String ZIP;
	
	public Address(String streetName, int streetNumber, String cityName, String ZIP) 
	{
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.cityName = cityName;
		this.ZIP = ZIP;
	}
	
	public Address() 
	{
		
	}

	//Getters and Setters///////////////
	public String getStreetName() 
	{
		return streetName;
	}
	public void setStreetName(String streetName) 
	{
		this.streetName = streetName;
	}
	public int getStreetNumber() 
	{
		return streetNumber;
	}
	public void setStreetNumber(int streetNumber) 
	{
		this.streetNumber = streetNumber;
	}
	public String getCityName() 
	{
		return cityName;
	}
	public void setCityName(String cityName) 
	{
		this.cityName = cityName;
	}
	public String getZIP() 
	{
		return ZIP;
	}
	public void setZIP(String ZIP) 
	{
		this.ZIP = ZIP;
	}
	
	
}
