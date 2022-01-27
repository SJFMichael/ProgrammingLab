/*
 UPC
 */
package UPC_Calendar;

import UPC_Calendar_Data.UPC_CalendarSetting;
import UPC_Calendar_Data.UPC_DayData;
import UPC_Calendar_Data.UPC_Event;
import UPC_Calendar_Data.UPC_Note;
import UPC_Calendar_Data.UPC_TimeLine;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author UPC
 */
public class UPC_FileReadAndWrite {

	public static void readTimeLines() {
		UPC_TimeLine temp;
		UPC_TimeLine.CalendarType tempCalendarType;
		String timeLineName;
		boolean isHoliday;
		int month;
		int day;
		File readingFile = null;
		try {
			BufferedReader br;
			tempCalendarType = UPC_TimeLine.CalendarType.CPERSIAN;
			String address = ".\\src\\UPC_Calendar\\TimeLines\\persian\\";
			for (int i = 0; i < 12; i++) {
				readingFile = new File(address + i);
				br = new BufferedReader(new FileReader(readingFile));
				String line = br.readLine();
				String[] data;
				while (line != null) {
					data = line.split("=");
					if(data.length == 3) {
						month = i;
						day = Integer.parseInt(data[0]);
						timeLineName = data[1];
						isHoliday = data[2].equals("+");
						temp = new UPC_TimeLine(tempCalendarType, timeLineName, isHoliday, month, day);
						UPC_MonthVeiwer.timelines.add(temp);
					}
					line = br.readLine();
				}
				br.close();
			}
			tempCalendarType = UPC_TimeLine.CalendarType.CISLAMIC;
			address = ".\\src\\UPC_Calendar\\TimeLines\\islamic\\";
			for (int i = 0; i < 12; i++) {
				readingFile = new File(address + i);
				br = new BufferedReader(new FileReader(readingFile));
				String line = br.readLine();
				String[] data;
				while (line != null) {
					data = line.split("=");
					if(data.length == 3) {
						month = i;
						day = Integer.parseInt(data[0]);
						timeLineName = data[1];
						isHoliday = data[2].equals("+");
						temp = new UPC_TimeLine(tempCalendarType, timeLineName, isHoliday, month, day);
						UPC_MonthVeiwer.timelines.add(temp);
					}
					line = br.readLine();
				}
				br.close();
			}
			tempCalendarType = UPC_TimeLine.CalendarType.CGREGORIAN;
			address = ".\\src\\UPC_Calendar\\TimeLines\\gregorian\\";
			for (int i = 0; i < 12; i++) {
				readingFile = new File(address + i);
				br = new BufferedReader(new FileReader(readingFile));
				String line = br.readLine();
				String[] data;
				while (line != null) {
					data = line.split("=");
					if(data.length == 3) {
						month = i;
						day = Integer.parseInt(data[0]);
						timeLineName = data[1];
						isHoliday = data[2].equals("+");
						temp = new UPC_TimeLine(tempCalendarType, timeLineName, isHoliday, month, day);
						UPC_MonthVeiwer.timelines.add(temp);
					}
					line = br.readLine();
				}
				br.close();
			}
		} catch (FileNotFoundException ex) {
			Logger.getLogger(UPC_FileReadAndWrite.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(UPC_FileReadAndWrite.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public static void readNotes() {
		File notesDirectory = new File(".\\src\\UPC_Calendar\\Notes");
		File[] notesFile = notesDirectory.listFiles();
		if(notesFile.length == 0) {
			return;
		}
		ObjectInputStream ois;
		for (File noteFile : notesFile) {
			try {
				ois = new ObjectInputStream(new FileInputStream(noteFile));
				UPC_MonthVeiwer.notes.add((UPC_Note) ois.readObject());
				UPC_CalendarSetting.dayDateNumber++;
				ois.close();
			} catch (IOException | ClassNotFoundException ex) {
				Logger.getLogger(UPC_FileReadAndWrite.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	
	public static void readEvents() {
		File eventsDirectory = new File(".\\src\\UPC_Calendar\\Events");
		File[] eventsFile = eventsDirectory.listFiles();
		if(eventsFile.length == 0) {
			return;
		}
		ObjectInputStream ois;
		for (File eventFile : eventsFile) {
			try {
				ois = new ObjectInputStream(new FileInputStream(eventFile));
				UPC_MonthVeiwer.events.add((UPC_Event) ois.readObject());
				UPC_CalendarSetting.dayDateNumber++;
				ois.close();
			} catch (IOException | ClassNotFoundException ex) {
				Logger.getLogger(UPC_FileReadAndWrite.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	
	public static void writeData(UPC_DayData n) {
		String fileName = n.getYear() + "_" + n.getMonth()+ "_" + n.getDay()+ "_" + n.getId();
		File wFile;
		ObjectOutputStream oos;
		if(n instanceof UPC_Note) {
			wFile = new File(".\\src\\UPC_Calendar\\Notes\\" + fileName);
		} else{
			wFile = new File(".\\src\\UPC_Calendar\\Events\\" + fileName);
		}
		if(wFile.exists()) {
			wFile.delete();
		}
		try {
			wFile.createNewFile();
			oos = new ObjectOutputStream(new FileOutputStream(wFile));
			oos.writeObject(n);
			oos.close();
		} catch (IOException ex) {
			Logger.getLogger(UPC_FileReadAndWrite.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public static void deleteData(UPC_DayData n) {
		String fileName = n.getYear() + "_" + n.getMonth()+ "_" + n.getDay()+ "_" + n.getId();
		File dFile;
		if(n instanceof UPC_Note) {
			dFile = new File(".\\src\\UPC_Calendar\\Notes\\" + fileName);
		} else{
			dFile = new File(".\\src\\UPC_Calendar\\Events\\" + fileName);
		}
		if(dFile.exists()) {
			dFile.delete();
		}
	}
}
