/*
 UPC
 */
package UPC_Calendar_Data;



/**
 * Calendar occasions that can be read from files
 * can be updated
 * @author UPC
 */
public class UPC_TimeLine {

	public enum CalendarType {

		CPERSIAN, CISLAMIC, CGREGORIAN
	};
	private CalendarType timeLineCalendarType;
	private String timeLineName;
	private boolean isHoliday;
	private int month;
	private int day;

	public UPC_TimeLine(CalendarType timeLineCalendarType, String timeLineName, boolean isHoliday, int month , int day) {
		this.timeLineCalendarType = timeLineCalendarType;
		this.isHoliday = isHoliday;
		this.timeLineName = timeLineName;
		this.month = month;
		this.day = day;
	}
	
	public boolean isHoliday() {
		return isHoliday;
	}
	
	public CalendarType getCalendarType() {
		return timeLineCalendarType;
	}
	
	public String getTimeLineName() {
		return timeLineName;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getDay() {
		return day;
	}
}
