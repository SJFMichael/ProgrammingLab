/*
 UPC
 */
package UPC_Calendar;

import UPC_Calendar_Data.UPC_CalendarSetting;
import UPC_Calendar_Data.UPC_Event;
import java.awt.FontFormatException;
import java.io.IOException;

/**
 *
 * @author UPC
 */
public class UPC_CalendarManager {
	public static void main(String[] args) throws FontFormatException, IOException {
		UPC_CalendarSetting.set();
		UPC_FileReadAndWrite.readTimeLines();
		UPC_FileReadAndWrite.readNotes();
		UPC_FileReadAndWrite.readEvents();
		UPC_CalendarMainFrame a = new UPC_CalendarMainFrame();
		//UPC_Event b = new UPC_Event("سلام", 13, 1, 1, 1,1,1);
		//UPC_MyDialogs.showAlarmFrame(a,b);
		//System.out.println(b.getText());
//		for (UPC_TimeLine timeline : UPC_MonthVeiwer.timelines) {
//			System.out.print(timeline.getCalendarType()+"\t");
//			System.out.print(timeline.getMonth()+"\t");
//			System.out.print(timeline.getDay()+"\t");
//			System.out.print(timeline.getTimeLineName()+"\t");
//			System.out.print(timeline.isHoliday()+"\n");
//		}
	}
}
