/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Main.Http;

/**
 *
 * @author UPC
 */
public class Weather {
	
	private static final String baseUrl = "https://api.openweathermap.org/data/2.5/weather?";
	private static final String apiKey = "55f9c465c6e8a326cbe1107d16b4aad3";
	private String city;
	Http http;
	
	public Weather (String city) {
		this.city = city;
		http = new Http();
	}
	
	public String getCurrent() {
		String s = "";
		try {
			s = http.readUrl(Weather.baseUrl + "q=" + this.city + "&appid=" + Weather.apiKey);
		} catch (Exception e) {
			s = e.toString();
		}
		return s;
	}
}
