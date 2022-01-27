/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

/**
 *
 * @author UPC
 */
public class Http {

	public static String readResult(Translation requestTXT) throws MalformedURLException, IOException {
		BufferedReader reader = null;
		String temp = "";
		try {
			String urlStr = "https://script.google.com/macros/s/AKfycbygKa-tLeDvZsCMiDgpHTFQuSVNjtJZu9cdIOECli6BHaxG8klGs0HYA_iix4DZ8Z7KIg/exec"
					+ "?q=" + URLEncoder.encode(requestTXT.getInputTXT(), "UTF-8")
					+ "&target=" + requestTXT.getOutputLan()
					+ "&source=" + requestTXT.getInputLan();
			URL url = new URL(urlStr);
			//System.err.println("tttttt");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			System.out.println(url);
			connection.connect();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			while ((inputLine = reader.readLine()) != null) {
				temp += inputLine;
			}
			/*
			if (connection.getResponseCode() == 300) {
				Scanner scanner = new Scanner(url.openStream());
				while (scanner.hasNext()) {
					temp += scanner.nextLine();
				}
			}
			*/
			System.out.println(temp);
			reader.close();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		return temp;
	}
}
