/*
 UPC
 */
package UPC_Calendar_Data;

import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.ULocale;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Events defined by user
 * @author UPC
 */
public class UPC_Event extends UPC_DayData {

	private int eventHour;
	private int eventMinute;
	public String timerStr;

	public UPC_Event(String eventString, int eventYear, int eventMonth, int eventDay, int eventId, int eventHour, int eventMinute) {
		super(eventString, eventYear, eventMonth, eventDay, eventId);
		this.eventHour = eventHour;
		this.eventMinute = eventMinute;
		this.timerStr = "";
	}

	public int getHour() {
		return eventHour;
	}

	public int getMinute() {
		return eventMinute;
	}

	public void setHour(int newHour) {
		this.eventHour = newHour;
	}

	public void setMinute(int newMinute) {
		this.eventMinute = newMinute;
	}

	public void activeTimer() {
		Calendar pCalendar = Calendar.getInstance(new ULocale("fa_IR"));
		pCalendar.set(this.dataYear, this.dataMonth, this.dataDay, this.eventHour, this.eventMinute);
		Date d = pCalendar.getTime();
	}
}
