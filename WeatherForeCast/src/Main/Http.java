/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author UPC
 */
public class Http {

	public String readUrl(String urlstring) throws Exception {

		BufferedReader reader = null;
		String temp = "";
		try {

			URL url = new URL(urlstring);
			//System.err.println("tttttt");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			//System.out.println(url);
			connection.connect();
			if (connection.getResponseCode() == 200) {
				Scanner scanner = new Scanner(url.openStream());
				while (scanner.hasNext()) {
					temp += scanner.nextLine();

				}
			}
		} finally {
			if(reader != null) {
				reader.close();
			}
		}
		return temp;
	}
}
