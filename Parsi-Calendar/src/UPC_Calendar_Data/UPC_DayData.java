/*
 UPC
 */
package UPC_Calendar_Data;

import java.io.Serializable;


/**
 * Any Data that can be store on a specific day
 * examples : Notes, Events, Occasions
 * @author UPC
 */
public class UPC_DayData implements Serializable{
	protected String dataText;
	protected int dataDay;
	protected int dataMonth;
	protected int dataYear;
	protected int dataId;
	
	protected UPC_DayData(String dataString , int dataYear , int dataMonth , int dataDay , int dataId) {
		this.dataText = dataString;
		this.dataYear = dataYear;
		this.dataMonth = dataMonth;
		this.dataDay = dataDay;
		this.dataId = dataId;
	}
	
	public String getText() {
		return dataText;
	}
	
	public int getYear() {
		return dataYear;
	}
	
	public int getMonth() {
		return dataMonth;
	}
	
	public int getDay() {
		return dataDay;
	}
	
	public int getId() {
		return dataId;
	}
	
	public void setText(String newText) {
		this.dataText = newText;
	}
	
//	public void setYear(int newYear) {
//		this.dataText = newText;
//	}
//	
//	public void setMonth(String newText) {
//		this.dataText = newText;
//	}
//	
//	public void setDay(String newText) {
//		this.dataText = newText;
//	}
}
