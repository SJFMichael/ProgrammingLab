/*
 UPC
 */
package UPC_Calendar_Data;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

/**
 * Contains all needed Graphical properties
 * Fonts, Colors
 * @author UPC
 */
public class UPC_CalendarSetting {

	public static Font mainFrameJMenuBarFont;
	public static float JMenuBarFontSize;
	public static Color calendarMainFrameColor;
	public static Color mainFrameJToolBarColor;
	//----------------------------------------------------------------
	public static Font persianDayFont;
	public static Font islamicDayFont;
	public static Font gregorianDayFont;
	//
	public static float persianDayFontSize;
	public static float islamicDayFontSize;
	public static float gregorianDayFontSize;
	//
	public static Color defaultButtonColor;
	public static Color holidayButtonColor;
	public static Color selectedButtonColor;
	public static Color defaultButtonColorText;
	public static Color holidayButtonColorText;
	public static Color selectedButtonColorText;
	//---------------------------------------------------------------------
	public static Font monthPersianLabelFont;
	public static Font monthIslamicLabelFont;
	public static Font monthGregorianLabelFont;
	public static float monthPersianLabelFontSize;
	public static float monthGreAndIsmLabelFontSize;
	public static Color monthToolBarColor;
	//
	public static Font dayInformationFont;
	public static float dayInformationFontSize;
	//
	public static Font noteEditorFont;
	public static float noteEditorFontSize;
	//
	public static int dayDateNumber;
	public static void set() throws FontFormatException, IOException {
		//---------------------------------------------------------------------
		calendarMainFrameColor = new Color(0, 110, 110);
		mainFrameJToolBarColor = new Color(0, 0, 0);
		mainFrameJMenuBarFont = Font.createFont(Font.TRUETYPE_FONT, new File(".\\src\\UPC_Calendar\\font\\BKoodkBd.ttf"));
		JMenuBarFontSize = 20f;
		//---------------------------------------------------------------------
		persianDayFont = Font.createFont(Font.TRUETYPE_FONT, new File(".\\src\\UPC_Calendar\\font\\BVAHIDBD.ttf"));
		islamicDayFont = Font.createFont(Font.TRUETYPE_FONT, new File(".\\src\\UPC_Calendar\\font\\BArabics.ttf"));
		gregorianDayFont = Font.createFont(Font.TRUETYPE_FONT, new File(".\\src\\UPC_Calendar\\font\\BELLI.ttf"));
		persianDayFontSize = 45f;
		islamicDayFontSize = 26f;
		gregorianDayFontSize = 24f;
		selectedButtonColor = new Color(0, 0, 0);
		selectedButtonColorText = new Color(255, 255, 255);
		//holidayButtonColor = new Color(255, 255, 255);
		holidayButtonColorText = new Color(255, 0, 0);
		//defaultButtonColor = Color.BLUE;
		//defaultButtonColorText = Color.YELLOW;
		//--------------------------------------------------------------------- 
		dayInformationFont = Font.createFont(Font.TRUETYPE_FONT, new File(".\\src\\UPC_Calendar\\font\\BKOUROSH.ttf"));
		dayInformationFontSize = 16f;
		//
		
		noteEditorFont = new Font(Font.SANS_SERIF, Font.LAYOUT_RIGHT_TO_LEFT, 26);
		noteEditorFontSize = 26f;
		//
		dayDateNumber=0;
	}
}
