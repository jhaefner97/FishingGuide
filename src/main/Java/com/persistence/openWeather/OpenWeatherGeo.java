package com.persistence.openWeather;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherGeo{

	@JsonProperty("zip")
	private String zip;

	@JsonProperty("country")
	private String country;

	@JsonProperty("name")
	private String name;

	@JsonProperty("lon")
	private String lon;

	@JsonProperty("lat")
	private String lat;

	public void setZip(String zip){
		this.zip = zip;
	}

	public String getZip(){
		return zip;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setLon(String lon){
		this.lon = lon;
	}

	public String getLon(){
		return lon;
	}

	public void setLat(String lat){
		this.lat = lat;
	}

	public String getLat(){
		return lat;
	}

	@Override
 	public String toString(){
		return 
			"OpenWeatherGeo{" + 
			"zip = '" + zip + '\'' + 
			",country = '" + country + '\'' + 
			",name = '" + name + '\'' + 
			",lon = '" + lon + '\'' + 
			",lat = '" + lat + '\'' + 
			"}";
		}
}