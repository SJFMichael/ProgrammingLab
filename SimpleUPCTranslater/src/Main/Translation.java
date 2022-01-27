/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.IOException;

/**
 *
 * @author UPC
 */
public class Translation {
	private String inputTXT;
	private String outputTXT;
	private String inputLan;
	private String outputLan;

	public Translation(String inputTXT) {
		this.inputTXT = inputTXT;
		this.inputLan = "fa";
		this.outputLan = "en";
	}

	public Translation(String inputTXT, String inputLan, String outputLan) {
		this.inputTXT = inputTXT;
		this.inputLan = inputLan;
		this.outputLan = outputLan;
	}

	public void setInputTXT(String inputTXT) {
		this.inputTXT = inputTXT;
	}

	public void setOutputLan(String outputLan) {
		this.outputLan = outputLan;
	}

	public void setInputLan(String inputLan) {
		this.inputLan = inputLan;
	}

	public String getInputTXT() {
		return inputTXT;
	}

	public String getOutputTXT() {
		return outputTXT;
	}

	public String getInputLan() {
		return inputLan;
	}

	public String getOutputLan() {
		return outputLan;
	}
	
	public void setOutputTXT() throws IOException {
		this.outputTXT = Http.readResult(this);
	}
}
