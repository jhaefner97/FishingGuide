package com.persistence.openWeather;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenWeatherForecastData{

	@JsonProperty("city")
	private City city;

	@JsonProperty("cnt")
	private int cnt;

	@JsonProperty("cod")
	private String cod;

	@JsonProperty("message")
	private int message;

	@JsonProperty("list")
	private List<ListItem> list;

	public void setCity(City city){
		this.city = city;
	}

	public City getCity(){
		return city;
	}

	public void setCnt(int cnt){
		this.cnt = cnt;
	}

	public int getCnt(){
		return cnt;
	}

	public void setCod(String cod){
		this.cod = cod;
	}

	public String getCod(){
		return cod;
	}

	public void setMessage(int message){
		this.message = message;
	}

	public int getMessage(){
		return message;
	}

	public void setList(List<ListItem> list){
		this.list = list;
	}

	public List<ListItem> getList(){
		return list;
	}

	@Override
 	public String toString(){
		return 
			"OpenWeatherForecastData{" + 
			"city = '" + city + '\'' + 
			",cnt = '" + cnt + '\'' + 
			",cod = '" + cod + '\'' + 
			",message = '" + message + '\'' + 
			",list = '" + list + '\'' + 
			"}";
		}
}