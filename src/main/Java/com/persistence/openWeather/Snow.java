package com.persistence.openWeather;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Snow{

	@JsonProperty("3h")
	private Object jsonMember3h;

	public void setJsonMember3h(Object jsonMember3h){
		this.jsonMember3h = jsonMember3h;
	}

	public Object getJsonMember3h(){
		return jsonMember3h;
	}

	@Override
 	public String toString(){
		return 
			"Snow{" + 
			"3h = '" + jsonMember3h + '\'' + 
			"}";
		}
}