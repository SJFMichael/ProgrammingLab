/*
 UPC
 */
package UPC_Calendar;

import UPC_Calendar_Data.UPC_CalendarSetting;
import UPC_Calendar_Data.UPC_DayData;
import UPC_Calendar_Data.UPC_Event;
import UPC_Calendar_Data.UPC_Note;
import UPC_Calendar_Data.UPC_TimeLine;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.IslamicCalendar;
import com.ibm.icu.util.ULocale;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

/**
 *
 * @author UPC
 */
public class UPC_MonthVeiwer extends JPanel {

	private JLabel[] weekDaysName;
	public static UPC_DayButton[] monthDays;
	public static int selectedDayIndex;
	private int[] myIslamicCalendar = {29, 30, 30, 30, 29, 29, 29, 30, 29, 29, 30, 30};
	private int startMyIslamicDay = 10;
	private int startMyIslamicMonth = 5;
	private int startMyIslamicYear = 1437;
	//
	public static ArrayList<UPC_TimeLine> timelines = new ArrayList<>();
	public static ArrayList<UPC_Note> notes = new ArrayList<>();
	public static ArrayList<UPC_Event> events = new ArrayList<>();
	//
	private Calendar persianCalendar;
	private Calendar gregorianCalendar;
	private IslamicCalendar islamicCalendar;
	//
	private UPC_DayInformationPanel dayInformationPanel;
	//
	private JPanel topshowStateLabel;
	private MovingLabel persianYearLabel;
	private MovingLabel persianMonthLabel;
	private MovingLabel islamicYearLabel;
	private MovingLabel islamicMonthLabel;
	private MovingLabel gregorianYearLabel;
	private MovingLabel gregorianMonthLabel;
	//
	private JPanel thisMonthVeiwer;
	private ImageIcon daysIcon;
	//
	private MovingButtonAction movingButtonHandler;
	private DaysAction daysHandler;
	//
	private JMenu JMenuInsert;
	private JMenuItem JMenuItemInsertNote;
	private JMenuItem JMenuItemInsertEvent;
	private JMenu JMenuEdit;
	private JMenuItem JMenuItemEditNote;
	private JMenuItem JMenuItemEditEvent;
	private JMenuItem JMenuItemDeleteNote;
	private JMenuItem JMenuItemDeleteEvent;
	private JMenuItem JMenuItemCopyDateText;
	private JMenuItem JMenuItemCopyTimeLineText;
	private JPopupMenu mainFrameJMenuBar;
	private MenuAction menuAction;
	private JFrame parent;
	//
	
	public UPC_MonthVeiwer(UPC_DayInformationPanel dayInformationPanel , JFrame parent) {
		this.parent = parent;
		menuAction = new MenuAction();
		mainFrameJMenuBar = new JPopupMenu();
		mainFrameJMenuBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		mainFrameJMenuBar.setFont(UPC_CalendarSetting.mainFrameJMenuBarFont.deriveFont(UPC_CalendarSetting.JMenuBarFontSize));
		//---
		JMenuInsert = new JMenu("درج");
		JMenuInsert.setIcon(new ImageIcon(".\\src\\UPC_Calendar\\pic\\JMenuInsert.png"));
		JMenuInsert.setIconTextGap(10);
		JMenuInsert.setFont(UPC_CalendarSetting.mainFrameJMenuBarFont.deriveFont(UPC_CalendarSetting.JMenuBarFontSize));
		JMenuInsert.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		JMenuInsert.setMnemonic('n');
		JMenuInsert.setDisplayedMnemonicIndex(0);
		JMenuItemInsertNote = new JMenuItem("اضافه کردن یادداشت");
		JMenuItemInsertNote.setToolTipText("یک یادداشت به روز انتخاب شده اضافه میکند");
		JMenuItemInsertNote.setFont(UPC_CalendarSetting.mainFrameJMenuBarFont.deriveFont(UPC_CalendarSetting.JMenuBarFontSize));
		JMenuItemInsertNote.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		JMenuItemInsertNote.setMnemonic('j');
		JMenuItemInsertNote.setDisplayedMnemonicIndex(17);
		JMenuItemInsertNote.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_0, ActionEvent.CTRL_MASK));
		JMenuItemInsertNote.addActionListener(menuAction);
		JMenuInsert.add(JMenuItemInsertNote);
		JMenuItemInsertEvent = new JMenuItem("اضافه کردن رویداد");
		JMenuItemInsertEvent.setToolTipText("یک رویداد به روز انتخاب شده اضافه میکند");
		JMenuItemInsertEvent.setFont(UPC_CalendarSetting.mainFrameJMenuBarFont.deriveFont(UPC_CalendarSetting.JMenuBarFontSize));
		JMenuItemInsertEvent.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		JMenuItemInsertEvent.setMnemonic('h');
		JMenuItemInsertEvent.setDisplayedMnemonicIndex(15);
		JMenuItemInsertEvent.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_1, ActionEvent.CTRL_MASK));
		JMenuItemInsertEvent.addActionListener(menuAction);
		JMenuInsert.add(JMenuItemInsertEvent);
		//---
		JMenuEdit = new JMenu("ویرایش");
		JMenuEdit.setIcon(new ImageIcon(".\\src\\UPC_Calendar\\pic\\JMenuEdit.png"));
		JMenuEdit.setIconTextGap(10);
		JMenuEdit.setFont(UPC_CalendarSetting.mainFrameJMenuBarFont.deriveFont(UPC_CalendarSetting.JMenuBarFontSize));
		JMenuEdit.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		JMenuEdit.setMnemonic('a');
		JMenuEdit.setDisplayedMnemonicIndex(5);
		JMenuItemEditNote = new JMenuItem("ویرایش یادداشت");
		JMenuItemEditNote.setToolTipText("ویرایش کردن یادداشت های روز انتخاب شده");
		JMenuItemEditNote.setFont(UPC_CalendarSetting.mainFrameJMenuBarFont.deriveFont(UPC_CalendarSetting.JMenuBarFontSize));
		JMenuItemEditNote.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		JMenuItemEditNote.setMnemonic('j');
		JMenuItemEditNote.setDisplayedMnemonicIndex(13);
		JMenuItemEditNote.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_2, ActionEvent.CTRL_MASK));
		JMenuItemEditNote.addActionListener(menuAction);
		JMenuEdit.add(JMenuItemEditNote);
		JMenuItemEditEvent = new JMenuItem("ویرایش رویداد");
		JMenuItemEditEvent.setToolTipText("ویرایش کردن رویداد های روز انتخاب شده");
		JMenuItemEditEvent.setFont(UPC_CalendarSetting.mainFrameJMenuBarFont.deriveFont(UPC_CalendarSetting.JMenuBarFontSize));
		JMenuItemEditEvent.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		JMenuItemEditEvent.setMnemonic('h');
		JMenuItemEditEvent.setDisplayedMnemonicIndex(11);
		JMenuItemEditEvent.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_3, ActionEvent.CTRL_MASK));
		JMenuItemEditEvent.addActionListener(menuAction);
		JMenuEdit.add(JMenuItemEditEvent);
		JMenuEdit.addSeparator();
		JMenuItemDeleteNote = new JMenuItem("حذف یادداشت");
		JMenuItemDeleteNote.setToolTipText("حذف کردن یادداشت های روز انتخاب شده");
		JMenuItemDeleteNote.setFont(UPC_CalendarSetting.mainFrameJMenuBarFont.deriveFont(UPC_CalendarSetting.JMenuBarFontSize));
		JMenuItemDeleteNote.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		JMenuItemDeleteNote.setMnemonic('p');
		JMenuItemDeleteNote.setDisplayedMnemonicIndex(0);
		JMenuItemDeleteNote.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_4, ActionEvent.CTRL_MASK));
		JMenuItemDeleteNote.addActionListener(menuAction);
		JMenuEdit.add(JMenuItemDeleteNote);
		JMenuItemDeleteEvent = new JMenuItem("حذف رویداد");
		JMenuItemDeleteEvent.setToolTipText("حذف کردن رویداد های روز انتخاب شده");
		JMenuItemDeleteEvent.setFont(UPC_CalendarSetting.mainFrameJMenuBarFont.deriveFont(UPC_CalendarSetting.JMenuBarFontSize));
		JMenuItemDeleteEvent.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		JMenuItemDeleteEvent.setMnemonic('t');
		JMenuItemDeleteEvent.setDisplayedMnemonicIndex(2);
		JMenuItemDeleteEvent.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_5, ActionEvent.CTRL_MASK));
		JMenuItemDeleteEvent.addActionListener(menuAction);
		JMenuEdit.add(JMenuItemDeleteEvent);
		JMenuEdit.addSeparator();
		JMenuItemCopyDateText = new JMenuItem("کپی متن تاریخ");
		JMenuItemCopyDateText.setToolTipText("کپی کردن تاریخ روز انتخاب شده");
		JMenuItemCopyDateText.setFont(UPC_CalendarSetting.mainFrameJMenuBarFont.deriveFont(UPC_CalendarSetting.JMenuBarFontSize));
		JMenuItemCopyDateText.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		JMenuItemCopyDateText.setMnemonic(';');
		JMenuItemCopyDateText.setDisplayedMnemonicIndex(0);
		JMenuItemCopyDateText.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_6, ActionEvent.CTRL_MASK));
		JMenuItemCopyDateText.addActionListener(menuAction);
		JMenuEdit.add(JMenuItemCopyDateText);
		JMenuItemCopyTimeLineText = new JMenuItem("کپی متن مناسبت");
		JMenuItemCopyTimeLineText.setToolTipText("کپی کردن مناسبتهای روز انتخاب شده");
		JMenuItemCopyTimeLineText.setFont(UPC_CalendarSetting.mainFrameJMenuBarFont.deriveFont(UPC_CalendarSetting.JMenuBarFontSize));
		JMenuItemCopyTimeLineText.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		JMenuItemCopyTimeLineText.setMnemonic('l');
		JMenuItemCopyTimeLineText.setDisplayedMnemonicIndex(4);
		JMenuItemCopyTimeLineText.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_7, ActionEvent.CTRL_MASK));
		JMenuItemCopyTimeLineText.addActionListener(menuAction);
		JMenuEdit.add(JMenuItemCopyTimeLineText);
		mainFrameJMenuBar.add(JMenuInsert);
		mainFrameJMenuBar.add(JMenuEdit);
		//
		this.dayInformationPanel = dayInformationPanel;
		movingButtonHandler = new MovingButtonAction();
		daysHandler = new DaysAction();
		persianCalendar = Calendar.getInstance(new ULocale("fa_IR"));
		islamicCalendar = new IslamicCalendar(new ULocale("@calendar=islamic_civil"));
		islamicCalendar.setCalculationType(IslamicCalendar.CalculationType.ISLAMIC_CIVIL);
		gregorianCalendar = Calendar.getInstance();
		islamicCalendar.setTime(persianCalendar.getTime());
		gregorianCalendar.setTime(persianCalendar.getTime());
		this.setLayout(new BorderLayout());
		topshowStateLabel = new JPanel();
		makeTopShowStatePanel();
		this.add(topshowStateLabel, BorderLayout.NORTH);
		thisMonthVeiwer = new JPanel();
		makeBottomShowMonthPanel();
		reValidatePersianMonth();
		ActionEvent e = new ActionEvent(monthDays[selectedDayIndex], 0, "");
		daysHandler.actionPerformed(e);
		persianCalendar.add(Calendar.MONTH, -1);
		islamicCalendar.setTime(persianCalendar.getTime());
		gregorianCalendar.setTime(persianCalendar.getTime());
		this.add(thisMonthVeiwer, BorderLayout.CENTER);
	}

	private boolean isTimeLineMatch(UPC_TimeLine tl, UPC_DayButton db) {
		int day = -1;
		int month = -1;
		switch (tl.getCalendarType()) {
			case CPERSIAN:
				day = db.persianDate.get(Calendar.DATE);
				month = db.persianDate.get(Calendar.MONTH);
				break;
			case CISLAMIC:
				day = db.correctIslamicDate.get(Calendar.DATE);
				month = db.correctIslamicDate.get(Calendar.MONTH);
				break;
			case CGREGORIAN:
				Calendar greTemp = Calendar.getInstance();
				greTemp.setTime(db.persianDate.getTime());
				day = greTemp.get(Calendar.DATE);
				month = greTemp.get(Calendar.MONTH);
				break;
		}
		return (tl.getDay() == day && tl.getMonth() == month);
	}

	private boolean isDayDataMatch(UPC_DayData dd, UPC_DayButton db) {
		return (dd.getYear() == db.persianDate.get(Calendar.YEAR) && dd.getMonth() == db.persianDate.get(Calendar.MONTH) && dd.getDay() == db.persianDate.get(Calendar.DATE));
	}

	public void reValidateToolTip() {
		int tln = 0;
		int nn = 0;
		int en = 0;
		for (UPC_DayButton monthDay : monthDays) {
			for (UPC_TimeLine timeline : timelines) {
				if (isTimeLineMatch(timeline, monthDay)) {
					tln++;
				}
			}
			for (UPC_Note note : notes) {
				if (isDayDataMatch(note, monthDay)) {
					nn++;
				}
			}
			for (UPC_Event event : events) {
				if(isDayDataMatch(event, monthDay)) {
					en++;
				}
			}
			monthDay.setToolTipText(tln+" مناسبت/" + nn +" یادداشت/" + en +"رویداد" );
			tln = 0;
			nn = 0;
			en = 0;
		}
	}

	private void reValidateHolidays() {
		for (UPC_DayButton monthDay : monthDays) {
			for (UPC_TimeLine timeline : timelines) {
				if (isTimeLineMatch(timeline, monthDay)) {
					if (timeline.isHoliday()) {
						monthDay.makeHoliday(true);
					}
				}
			}
		}
	}

	private void reValidatePersianMonth() {
		//
		persianMonthLabel.setText(UPC_MonthVeiwer.getPersianMonthName(persianCalendar.get(Calendar.MONTH)));
		persianYearLabel.setText(Integer.toString(persianCalendar.get(Calendar.YEAR)));
		//
		islamicMonthLabel.setText(UPC_MonthVeiwer.getIslamicMonthName(islamicCalendar.get(Calendar.MONTH)));
		islamicYearLabel.setText(Integer.toString(islamicCalendar.get(Calendar.YEAR)));

		//
		gregorianMonthLabel.setText(UPC_MonthVeiwer.getGregorianMonthName(gregorianCalendar.get(Calendar.MONTH)));
		gregorianYearLabel.setText(Integer.toString(gregorianCalendar.get(Calendar.YEAR)));
		//
		persianCalendar.set(Calendar.DATE, 1);
		setSeasonPhoto();
		int firstDayOfMonthInWeek = persianCalendar.get(Calendar.DAY_OF_WEEK);
		int startIndex = 0;
		int endIndex;
		switch (firstDayOfMonthInWeek) {
			case 7:
				startIndex = 0;
				break;
			case 1:
				startIndex = 1;
				break;
			case 2:
				startIndex = 2;
				break;
			case 3:
				startIndex = 3;
				break;
			case 4:
				startIndex = 4;
				break;
			case 5:
				startIndex = 5;
				break;
			case 6:
				startIndex = 6;
				break;
		}
		endIndex = startIndex + persianCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		persianCalendar.add(Calendar.MONTH, -1);
		persianCalendar.set(Calendar.DAY_OF_MONTH, persianCalendar.getActualMaximum(Calendar.DAY_OF_MONTH) - startIndex + 1);
		setSeasonPhoto();
		islamicCalendar.setTime(persianCalendar.getTime());
		gregorianCalendar.setTime(persianCalendar.getTime());
		for (int i = 0; i < startIndex; i++) {
			monthDays[i].reset();
			monthDays[i].setText(Integer.toString(persianCalendar.get(Calendar.DAY_OF_MONTH)), Integer.toString(setTheCorrectIslamicCalendar(i)), Integer.toString(gregorianCalendar.get(Calendar.DAY_OF_MONTH)), daysIcon);
			monthDays[i].setEnabled(false);
			if (persianCalendar.isWeekend()) {
				monthDays[i].makeHoliday(true);
			}
			monthDays[i].persianDate.setTime(persianCalendar.getTime());
			persianCalendar.add(Calendar.DAY_OF_MONTH, 1);
			islamicCalendar.add(Calendar.DAY_OF_MONTH, 1);
			gregorianCalendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		setSeasonPhoto();
		for (int i = startIndex; i < endIndex; i++) {
			monthDays[i].reset();
			monthDays[i].setText(Integer.toString(persianCalendar.get(Calendar.DAY_OF_MONTH)), Integer.toString(setTheCorrectIslamicCalendar(i)), Integer.toString(gregorianCalendar.get(Calendar.DAY_OF_MONTH)), daysIcon);
			monthDays[i].setEnabled(true);
			if (persianCalendar.isWeekend()) {
				monthDays[i].makeHoliday(true);
			}
			monthDays[i].persianDate.setTime(persianCalendar.getTime());
			persianCalendar.add(Calendar.DAY_OF_MONTH, 1);
			islamicCalendar.add(Calendar.DAY_OF_MONTH, 1);
			gregorianCalendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		setSeasonPhoto();
		for (int i = endIndex; i < monthDays.length; i++) {
			monthDays[i].reset();
			monthDays[i].setText(Integer.toString(persianCalendar.get(Calendar.DAY_OF_MONTH)), Integer.toString(setTheCorrectIslamicCalendar(i)), Integer.toString(gregorianCalendar.get(Calendar.DAY_OF_MONTH)), daysIcon);
			monthDays[i].setEnabled(false);
			if (persianCalendar.isWeekend()) {
				monthDays[i].makeHoliday(true);
			}
			monthDays[i].persianDate.setTime(persianCalendar.getTime());
			persianCalendar.add(Calendar.DAY_OF_MONTH, 1);
			islamicCalendar.add(Calendar.DAY_OF_MONTH, 1);
			gregorianCalendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		reValidateHolidays();
		reValidateToolTip();
	}

	public void reValidateDayInformations(UPC_DayButton monthDay) {
		dayInformationPanel.resetTimeLines();
		for (UPC_TimeLine timeline : timelines) {
			if (isTimeLineMatch(timeline, monthDay)) {
				dayInformationPanel.addTimeLine(timeline);
			}
		}

		dayInformationPanel.resetNotes();
		for (UPC_Note note : notes) {
			if (isDayDataMatch(note, monthDay)) {
				dayInformationPanel.addNote(note);
			}
		}
		dayInformationPanel.resetEvents();
		for (UPC_Event event : events) {
			if (isDayDataMatch(event, monthDay)) {
				dayInformationPanel.addEvent(event);
			}
		}
	}

	private int setTheCorrectIslamicCalendar(int index) {
		if (persianCalendar.get(Calendar.YEAR) == 1395) {
			monthDays[index].correctIslamicDate.setCalculationType(IslamicCalendar.CalculationType.ISLAMIC);
			int days = persianCalendar.get(Calendar.DAY_OF_YEAR);
			int start = startMyIslamicDay;
			int i = start;
			int sum = 0;
			int movingMonth = startMyIslamicMonth;
			int year = startMyIslamicYear;
			for (int j = 0; j <= myIslamicCalendar.length; j++) {
				for (i = start; i <= myIslamicCalendar[movingMonth]; i++) {
					sum++;
					if (sum == days) {
						monthDays[index].correctIslamicDate.set(year, movingMonth, i);
						return i;
					}
				}
				movingMonth++;
				start = 1;
				if (movingMonth == 12) {
					movingMonth = 0;
					year++;
				}
			}
			return 0;
		} else {
			monthDays[index].correctIslamicDate.setCalculationType(IslamicCalendar.CalculationType.ISLAMIC_CIVIL);
			monthDays[index].correctIslamicDate.setTime(islamicCalendar.getTime());
			return islamicCalendar.get(Calendar.DATE);
		}
	}

	private void makeBottomShowMonthPanel() {
		//setting labels
		weekDaysName = new JLabel[7];
		thisMonthVeiwer.setLayout(new GridLayout(7, 7, 10, 10));
		for (int i = 0; i < 7; i++) {
			weekDaysName[i] = new JLabel(UPC_MonthVeiwer.getDayOfWeekName(i + 1));
			weekDaysName[i].setHorizontalAlignment(SwingConstants.CENTER);
			weekDaysName[i].setVerticalAlignment(SwingConstants.BOTTOM);
			weekDaysName[i].setFont(UPC_CalendarSetting.persianDayFont.deriveFont(UPC_CalendarSetting.persianDayFontSize));
			weekDaysName[i].setForeground(Color.gray);
		}
		thisMonthVeiwer.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		thisMonthVeiwer.add(weekDaysName[6]);
		thisMonthVeiwer.add(weekDaysName[0]);
		thisMonthVeiwer.add(weekDaysName[1]);
		thisMonthVeiwer.add(weekDaysName[2]);
		thisMonthVeiwer.add(weekDaysName[3]);
		thisMonthVeiwer.add(weekDaysName[4]);
		thisMonthVeiwer.add(weekDaysName[5]);
		//setting days button
		monthDays = new UPC_DayButton[42];
		persianCalendar.set(Calendar.DATE, 1);
		islamicCalendar.setTime(persianCalendar.getTime());
		gregorianCalendar.setTime(persianCalendar.getTime());
		for (int i = 0; i < 42; i++) {
			monthDays[i] = new UPC_DayButton("", "", "", null);
			monthDays[i].addActionListener(daysHandler);
			monthDays[i].add(mainFrameJMenuBar);
			monthDays[i].addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
				}

				@Override
				public void mousePressed(MouseEvent e) {
					if(e.isPopupTrigger()) {
						mainFrameJMenuBar.show(e.getComponent(), e.getX(), e.getY());
					}
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					if(e.isPopupTrigger()) {
						mainFrameJMenuBar.show(e.getComponent(), e.getX(), e.getY());
					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {
				}

				@Override
				public void mouseExited(MouseEvent e) {
				}
			});
			thisMonthVeiwer.add(monthDays[i]);
		}
	}

	private void setSeasonPhoto() {
		int season = persianCalendar.get(Calendar.MONTH) / 3;
		switch (season) {
			case 0:
				daysIcon = new ImageIcon(".\\src\\UPC_Calendar\\pic\\season0.png");
				break;
			case 1:
				daysIcon = new ImageIcon(".\\src\\UPC_Calendar\\pic\\season1.png");
				break;
			case 2:
				daysIcon = new ImageIcon(".\\src\\UPC_Calendar\\pic\\season2.png");
				break;
			case 3:
				daysIcon = new ImageIcon(".\\src\\UPC_Calendar\\pic\\season3.png");
				break;
		}
	}

	private void makeTopShowStatePanel() {
		persianYearLabel = new MovingLabel(Integer.toString(persianCalendar.get(Calendar.YEAR)));
		persianYearLabel.setFont(UPC_CalendarSetting.persianDayFont.deriveFont(UPC_CalendarSetting.persianDayFontSize - 20));
		persianYearLabel.next.setActionCommand("1");
		persianYearLabel.next.addActionListener(movingButtonHandler);
		persianYearLabel.previous.setActionCommand("2");
		persianYearLabel.previous.addActionListener(movingButtonHandler);
		persianMonthLabel = new MovingLabel(UPC_MonthVeiwer.getPersianMonthName(persianCalendar.get(Calendar.MONTH)));
		persianMonthLabel.setFont(UPC_CalendarSetting.persianDayFont.deriveFont(UPC_CalendarSetting.persianDayFontSize - 20));
		persianMonthLabel.next.setActionCommand("3");
		persianMonthLabel.next.addActionListener(movingButtonHandler);
		persianMonthLabel.previous.setActionCommand("4");
		persianMonthLabel.previous.addActionListener(movingButtonHandler);
		//
		islamicYearLabel = new MovingLabel(Integer.toString(islamicCalendar.get(Calendar.YEAR)));
		islamicYearLabel.setFont(UPC_CalendarSetting.islamicDayFont.deriveFont(UPC_CalendarSetting.islamicDayFontSize - 1));
		islamicYearLabel.next.setVisible(false);
		islamicYearLabel.previous.setVisible(false);
		islamicMonthLabel = new MovingLabel(UPC_MonthVeiwer.getIslamicMonthName(islamicCalendar.get(Calendar.MONTH)));
		islamicMonthLabel.setFont(UPC_CalendarSetting.islamicDayFont.deriveFont(UPC_CalendarSetting.islamicDayFontSize - 1));
		islamicMonthLabel.next.setVisible(false);
		islamicMonthLabel.previous.setVisible(false);
		//
		gregorianYearLabel = new MovingLabel(Integer.toString(gregorianCalendar.get(Calendar.YEAR)));
		gregorianYearLabel.setFont(UPC_CalendarSetting.gregorianDayFont.deriveFont(UPC_CalendarSetting.gregorianDayFontSize - 7));
		gregorianYearLabel.next.setVisible(false);
		gregorianYearLabel.previous.setVisible(false);
		gregorianMonthLabel = new MovingLabel(UPC_MonthVeiwer.getGregorianMonthName(gregorianCalendar.get(Calendar.MONTH)));
		gregorianMonthLabel.setFont(UPC_CalendarSetting.gregorianDayFont.deriveFont(UPC_CalendarSetting.gregorianDayFontSize - 7));
		gregorianMonthLabel.next.setVisible(false);
		gregorianMonthLabel.previous.setVisible(false);
		//
		topshowStateLabel.setLayout(new GridBagLayout());
		addComponent(topshowStateLabel, gregorianYearLabel, 0, 0, 1, 1);
		addComponent(topshowStateLabel, gregorianMonthLabel, 0, 1, 1, 1);
		addComponent(topshowStateLabel, persianYearLabel, 1, 0, 1, 1);
		addComponent(topshowStateLabel, persianMonthLabel, 1, 1, 1, 1);
		addComponent(topshowStateLabel, islamicYearLabel, 2, 0, 1, 1);
		addComponent(topshowStateLabel, islamicMonthLabel, 2, 1, 1, 1);

	}

	private void addComponent(JPanel thisPanel, JComponent c, int x, int y, int width, int height) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		gbc.weightx = 100;
		gbc.weighty = 100;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(1, 80, 1, 80);
		thisPanel.add(c, gbc);
	}

	private class MovingLabel extends JLabel {

		public JButton next;
		public JButton previous;

		public MovingLabel(String text) {
			this.setLayout(new BorderLayout(0, 10));
			this.setText(text);
			this.setPreferredSize(new Dimension(100, 30));
			this.setHorizontalAlignment(SwingConstants.CENTER);
			next = new JButton(new ImageIcon(".\\src\\UPC_Calendar\\pic\\next.png"));
			previous = new JButton(new ImageIcon(".\\src\\UPC_Calendar\\pic\\previous.png"));
			next.setPreferredSize(new Dimension(27, 200));
			next.setBorderPainted(false);
			previous.setPreferredSize(new Dimension(27, 200));
			previous.setBorderPainted(false);
			this.add(next, BorderLayout.WEST);
			this.add(previous, BorderLayout.EAST);
		}
	}

	private class DaysAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			UPC_DayButton temp = (UPC_DayButton) e.getSource();
			islamicYearLabel.setText(Integer.toString(temp.correctIslamicDate.get(Calendar.YEAR)));
			islamicMonthLabel.setText(UPC_MonthVeiwer.getIslamicMonthName(temp.correctIslamicDate.get(Calendar.MONTH)));
			Calendar greTemp = Calendar.getInstance();
			greTemp.setTime(temp.persianDate.getTime());
			gregorianYearLabel.setText(Integer.toString(greTemp.get(Calendar.YEAR)));
			gregorianMonthLabel.setText(UPC_MonthVeiwer.getGregorianMonthName(greTemp.get(Calendar.MONTH)));
			for (int i = 0; i < monthDays.length; i++) {
				if (monthDays[i] == temp) {
					selectedDayIndex = i;
					temp.makeSelected(true);
				} else {
					monthDays[i].makeSelected(false);
				}
			}
			reValidateDayInformations(temp);
		}

	}

	private class MovingButtonAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String buttonNumber = e.getActionCommand();
			switch (buttonNumber) {
				case "1":
					int selectedDay = Integer.parseInt(monthDays[selectedDayIndex].getText());
					persianCalendar.add(Calendar.YEAR, 1);
					islamicCalendar.setTime(persianCalendar.getTime());
					gregorianCalendar.setTime(persianCalendar.getTime());
					reValidatePersianMonth();
					persianCalendar.add(Calendar.MONTH, -1);
					persianCalendar.set(Calendar.DATE, selectedDay);
					for (UPC_DayButton dayButton : monthDays) {
						if (dayButton.persianDate.get(Calendar.DATE) == selectedDay && dayButton.isEnabled()) {
							ActionEvent myE = new ActionEvent(dayButton, 0, "");
							daysHandler.actionPerformed(myE);
							break;
						}
					}
					islamicCalendar.setTime(persianCalendar.getTime());
					gregorianCalendar.setTime(persianCalendar.getTime());
					return;
				case "2":
					selectedDay = Integer.parseInt(monthDays[selectedDayIndex].getText());
					persianCalendar.add(Calendar.YEAR, -1);
					islamicCalendar.setTime(persianCalendar.getTime());
					gregorianCalendar.setTime(persianCalendar.getTime());
					reValidatePersianMonth();
					persianCalendar.add(Calendar.MONTH, -1);
					persianCalendar.set(Calendar.DATE, selectedDay);
					for (UPC_DayButton dayButton : monthDays) {
						if (dayButton.persianDate.get(Calendar.DATE) == selectedDay && dayButton.isEnabled()) {
							ActionEvent myE = new ActionEvent(dayButton, 0, "");
							daysHandler.actionPerformed(myE);
							break;
						}
					}
					islamicCalendar.setTime(persianCalendar.getTime());
					gregorianCalendar.setTime(persianCalendar.getTime());
					return;
				case "3":
					selectedDay = Integer.parseInt(monthDays[selectedDayIndex].getText());
					persianCalendar.add(Calendar.MONTH, 1);
					islamicCalendar.setTime(persianCalendar.getTime());
					gregorianCalendar.setTime(persianCalendar.getTime());
					reValidatePersianMonth();
					persianCalendar.add(Calendar.MONTH, -1);
					persianCalendar.set(Calendar.DATE, selectedDay);
					for (UPC_DayButton dayButton : monthDays) {
						if (dayButton.persianDate.get(Calendar.DATE) == selectedDay && dayButton.isEnabled()) {
							ActionEvent myE = new ActionEvent(dayButton, 0, "");
							daysHandler.actionPerformed(myE);
							break;
						}
					}
					islamicCalendar.setTime(persianCalendar.getTime());
					gregorianCalendar.setTime(persianCalendar.getTime());
					return;
				case "4":
					selectedDay = Integer.parseInt(monthDays[selectedDayIndex].getText());
					persianCalendar.add(Calendar.MONTH, -1);
					islamicCalendar.setTime(persianCalendar.getTime());
					gregorianCalendar.setTime(persianCalendar.getTime());
					reValidatePersianMonth();
					persianCalendar.add(Calendar.MONTH, -1);
					persianCalendar.set(Calendar.DATE, selectedDay);
					for (UPC_DayButton dayButton : monthDays) {
						if (dayButton.persianDate.get(Calendar.DATE) == selectedDay && dayButton.isEnabled()) {
							ActionEvent myE = new ActionEvent(dayButton, 0, "");
							daysHandler.actionPerformed(myE);
							break;
						}
					}
					islamicCalendar.setTime(persianCalendar.getTime());
					gregorianCalendar.setTime(persianCalendar.getTime());
					return;
			}
		}
	}
	
	private class MenuAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem temp = null;
			temp = (JMenuItem) e.getSource();
			if (temp == JMenuItemInsertNote) {
				UPC_Note n = new UPC_Note("", UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex].persianDate.get(Calendar.YEAR), UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex].persianDate.get(Calendar.MONTH), monthDays[UPC_MonthVeiwer.selectedDayIndex].persianDate.get(Calendar.DATE), UPC_CalendarSetting.dayDateNumber);
				UPC_MyDialogs.showNoteFrame(parent, n);
				if (n != null) {
					UPC_CalendarSetting.dayDateNumber++;
					UPC_MonthVeiwer.notes.add(n);
					UPC_MonthVeiwer.this.reValidateDayInformations(UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex]);
					UPC_MonthVeiwer.this.reValidateToolTip();
					UPC_FileReadAndWrite.writeData(n);
				}
			} else if (temp == JMenuItemInsertEvent) {
				UPC_Event e1 = new UPC_Event("", UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex].persianDate.get(Calendar.YEAR), UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex].persianDate.get(Calendar.MONTH), monthDays[UPC_MonthVeiwer.selectedDayIndex].persianDate.get(Calendar.DATE), UPC_CalendarSetting.dayDateNumber, 0, 0);
				UPC_MyDialogs.showEventFrame(parent, e1);
				if (e1 != null) {
					UPC_CalendarSetting.dayDateNumber++;
					UPC_MonthVeiwer.events.add(e1);
					UPC_MonthVeiwer.this.reValidateDayInformations(UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex]);
					UPC_MonthVeiwer.this.reValidateToolTip();
					UPC_FileReadAndWrite.writeData(e1);
				}
			} else if (temp == JMenuItemEditNote) {
				for (UPC_Note note : UPC_MonthVeiwer.notes) {
					if (note.getText().equals(dayInformationPanel.getNoteText())) {
						UPC_MyDialogs.showNoteFrame(parent, note);
						UPC_MonthVeiwer.this.reValidateDayInformations(UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex]);
						UPC_MonthVeiwer.this.reValidateToolTip();
						UPC_FileReadAndWrite.writeData(note);
					}
				}
			} else if (temp == JMenuItemEditEvent) {
				for (UPC_Event event : UPC_MonthVeiwer.events) {
					if (event.getText().equals(dayInformationPanel.getEventText())) {
						UPC_MyDialogs.showEventFrame(parent, event);
						UPC_MonthVeiwer.this.reValidateDayInformations(UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex]);
						UPC_MonthVeiwer.this.reValidateToolTip();
						UPC_FileReadAndWrite.writeData(event);
					}
				}

			} else if (temp == JMenuItemDeleteNote) {
				for (UPC_Note note : UPC_MonthVeiwer.notes) {
					if (note.getText().equals(dayInformationPanel.getNoteText())) {
						UPC_MonthVeiwer.notes.remove(note);
						UPC_MonthVeiwer.this.reValidateDayInformations(UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex]);
						UPC_MonthVeiwer.this.reValidateToolTip();
						UPC_FileReadAndWrite.deleteData(note);
					}
				}
			} else if (temp == JMenuItemDeleteEvent) {
				for (UPC_Event event : UPC_MonthVeiwer.events) {
					if (event.getText().equals(dayInformationPanel.getEventText())) {
						UPC_MonthVeiwer.events.remove(event);
						UPC_MonthVeiwer.this.reValidateDayInformations(UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex]);
						UPC_MonthVeiwer.this.reValidateToolTip();
						UPC_FileReadAndWrite.deleteData(event);
					}
				}
			} else if (temp == JMenuItemCopyDateText) {
				Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
				int y = UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex].persianDate.get(Calendar.YEAR);
				int m = UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex].persianDate.get(Calendar.MONTH);
				int d = UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex].persianDate.get(Calendar.DATE);
				StringSelection ss = new StringSelection(y + "/" + m + "/" + d);
				cb.setContents(ss, ss);

			} else if (temp == JMenuItemCopyTimeLineText) {
				Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
				StringSelection ss = new StringSelection(dayInformationPanel.getTimeLineText());
				cb.setContents(ss, ss);
			}
		}
	}

	public static String getPersianMonthName(int month) {
		switch (month) {
			case 0:
				return "فروردین";
			case 1:
				return "اردیبهشت";
			case 2:
				return "خرداد";
			case 3:
				return "تیر";
			case 4:
				return "مرداد";
			case 5:
				return "شهریور";
			case 6:
				return "مهر";
			case 7:
				return "آبان";
			case 8:
				return "آذر";
			case 9:
				return "دی";
			case 10:
				return "بهمن";
			case 11:
				return "اسفند";
		}
		return null;
	}

	public static String getIslamicMonthName(int month) {
		switch (month) {
			case 0:
				return "محرم";
			case 1:
				return "صفر";
			case 2:
				return "ربیع الاول";
			case 3:
				return "ربیع الثانی";
			case 4:
				return "جمادی الاول";
			case 5:
				return "جمادی الثانی";
			case 6:
				return "رجب";
			case 7:
				return "شعبان";
			case 8:
				return "رمضان";
			case 9:
				return "شوال";
			case 10:
				return "ذیقعده";
			case 11:
				return "ذیحجه";
			default:
				System.out.println("2371816516561561767184");
		}
		return null;
	}

	public static String getGregorianMonthName(int month) {
		switch (month) {
			case 0:
				return "January";
			case 1:
				return "February";
			case 2:
				return "March";
			case 3:
				return "April";
			case 4:
				return "May";
			case 5:
				return "June";
			case 6:
				return "July";
			case 7:
				return "August";
			case 8:
				return "September";
			case 9:
				return "October";
			case 10:
				return "November";
			case 11:
				return "December";
		}
		return null;
	}

	public static String getDayOfWeekName(int day) {
		switch (day) {
			case 7:
				return "شنبه";
			case 1:
				return "یکشنبه";
			case 2:
				return "دوشنبه";
			case 3:
				return "سه شنبه";
			case 4:
				return "چهارشنبه";
			case 5:
				return "پنج شنبه";
			case 6:
				return "جمعه";
		}
		return null;
	}
}
