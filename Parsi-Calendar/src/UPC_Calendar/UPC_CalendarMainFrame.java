/*
 UPC
 */
package UPC_Calendar;

import static UPC_Calendar.UPC_MonthVeiwer.monthDays;
import UPC_Calendar_Data.UPC_CalendarSetting;
import UPC_Calendar_Data.UPC_Event;
import UPC_Calendar_Data.UPC_Note;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.ULocale;
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

/**
 *
 * @author UPC
 */
public class UPC_CalendarMainFrame extends JFrame {

	public static final int calendarMainFrameWidth = 1100;
	public static final int calendarMainFrameHeight = 1000;
	private JMenuBar mainFrameJMenuBar;
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
	private JMenu JMenuSetting;
	private JMenuItem JMenuExit;
	private JToolBar mainFrameJToolBar;
	private JButton JToolBarButtonInsertNote;
	private JButton JToolBarButtonInsertEvent;
	private JButton JToolBarButtonEditNote;
	private JButton JToolBarButtonEditEvent;
	private JButton JToolBarButtonDeleteNote;
	private JButton JToolBarButtonDeleteEvent;
	private JPanel mainContian;
	private UPC_DayInformationPanel showInformation;
	private UPC_MonthVeiwer thisMonth;
	private MenuAction menuAction;
	private LinkedList<Timer> eventList;

	public UPC_CalendarMainFrame() {

		this.eventList = new LinkedList();
		for (UPC_Event e : UPC_MonthVeiwer.events) {
			this.addAlarm(e);
		}
		//initializing frame------------------------------------------------------
		this.setTitle("UPC-Parsi Calendar");
		this.setSize(calendarMainFrameWidth, calendarMainFrameHeight);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(screenSize.width / 2 - this.getWidth() / 2, screenSize.height / 2 - this.getHeight() / 2);
		this.getContentPane().setBackground(UPC_CalendarSetting.calendarMainFrameColor);
		this.repaint();
		ImageIcon calendarMainFrameIcon = new ImageIcon(getClass().getResource("pic\\UPC_Calendar.png"));
		this.setIconImage(calendarMainFrameIcon.getImage());
		this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon(".\\src\\UPC_Calendar\\pic\\UPC_MainFrameCursor.png").getImage(),
				new Point(0, 0), "UPC_MainFrameCursor"));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		this.setUndecorated(true);
//		this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		mainContian = new JPanel(new BorderLayout());
		showInformation = new UPC_DayInformationPanel();
		thisMonth = new UPC_MonthVeiwer(showInformation, this);
		menuAction = new MenuAction();
		//initializing menu bar------------------------------------------------------
		mainFrameJMenuBar = new JMenuBar();
		mainFrameJMenuBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		mainFrameJMenuBar.setFont(UPC_CalendarSetting.mainFrameJMenuBarFont.deriveFont(UPC_CalendarSetting.JMenuBarFontSize));
		this.setJMenuBar(mainFrameJMenuBar);
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
		//---
		JMenuSetting = new JMenu("تنظیمات");
		JMenuSetting.setIcon(new ImageIcon(".\\src\\UPC_Calendar\\pic\\JMenuSetting.png"));
		JMenuSetting.setIconTextGap(10);
		JMenuSetting.setMnemonic('k');
		JMenuSetting.setDisplayedMnemonicIndex(1);
		JMenuSetting.setFont(UPC_CalendarSetting.mainFrameJMenuBarFont.deriveFont(UPC_CalendarSetting.JMenuBarFontSize));
		JMenuSetting.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		//---
		JMenuExit = new JMenuItem("خروج");
		JMenuExit.setToolTipText("از برنامه خارج میشود");
		JMenuExit.setHorizontalTextPosition(SwingConstants.RIGHT);
		JMenuExit.setIcon(new ImageIcon(".\\src\\UPC_Calendar\\pic\\JMenuExit.png"));
		JMenuExit.setIconTextGap(10);
		JMenuExit.setFont(UPC_CalendarSetting.mainFrameJMenuBarFont.deriveFont(UPC_CalendarSetting.JMenuBarFontSize));
		JMenuExit.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		JMenuExit.setMnemonic('o');
		JMenuExit.setDisplayedMnemonicIndex(0);
		JMenuExit.addActionListener(menuAction);
		//---
		mainFrameJMenuBar.add(JMenuInsert);
		mainFrameJMenuBar.add(JMenuEdit);
		//mainFrameJMenuBar.add(JMenuSetting);
		mainFrameJMenuBar.add(Box.createHorizontalGlue());
		mainFrameJMenuBar.add(JMenuExit);
		//initializing tool bar------------------------------------------------------
		mainFrameJToolBar = new JToolBar(JToolBar.VERTICAL);
		mainFrameJToolBar.setLayout(new GridLayout(2, 3, 5, 5));
		mainFrameJToolBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		mainFrameJToolBar.setFloatable(false);
		mainFrameJToolBar.setRollover(true);
		mainFrameJToolBar.setBackground(UPC_CalendarSetting.mainFrameJToolBarColor);
		mainFrameJToolBar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		//---
		JToolBarButtonInsertNote = new JButton(new ImageIcon(".\\src\\UPC_Calendar\\pic\\JToolBarButtonInsertNote.png"));
		JToolBarButtonInsertNote.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		JToolBarButtonInsertNote.setToolTipText("یک یادداشت به روز انتخاب شده اضافه میکند");
		//JToolBarButtonInsertNote.setBackground(mainFrameJToolBar.getBackground());
		JToolBarButtonInsertNote.addActionListener(menuAction);
		mainFrameJToolBar.add(JToolBarButtonInsertNote);
		//---
		JToolBarButtonEditNote = new JButton(new ImageIcon(".\\src\\UPC_Calendar\\pic\\JToolBarButtonEditNote.png"));
		JToolBarButtonEditNote.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		JToolBarButtonEditNote.setToolTipText("ویرایش کردن یادداشت های روز انتخاب شده");
		//JToolBarButtonEditNote.setBackground(mainFrameJToolBar.getBackground());
		JToolBarButtonEditNote.addActionListener(menuAction);
		mainFrameJToolBar.add(JToolBarButtonEditNote);
		//---
		JToolBarButtonDeleteNote = new JButton(new ImageIcon(".\\src\\UPC_Calendar\\pic\\JToolBarButtonDeleteNote.png"));
		JToolBarButtonDeleteNote.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		JToolBarButtonDeleteNote.setToolTipText("حذف کردن یادداشت های روز انتخاب شده");
		//JToolBarButtonDeleteNote.setBackground(mainFrameJToolBar.getBackground());
		JToolBarButtonDeleteNote.addActionListener(menuAction);
		mainFrameJToolBar.add(JToolBarButtonDeleteNote);
		//---
		JToolBarButtonInsertEvent = new JButton(new ImageIcon(".\\src\\UPC_Calendar\\pic\\JToolBarButtonInsertEvent.png"));
		JToolBarButtonInsertEvent.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		JToolBarButtonInsertEvent.setToolTipText("یک رویداد به روز انتخاب شده اضافه میکند");
		//JToolBarButtonInsertEvent.setBackground(mainFrameJToolBar.getBackground());
		JToolBarButtonInsertEvent.addActionListener(menuAction);
		mainFrameJToolBar.add(JToolBarButtonInsertEvent);
		//---
		JToolBarButtonEditEvent = new JButton(new ImageIcon(".\\src\\UPC_Calendar\\pic\\JToolBarButtonEditEvent.png"));
		JToolBarButtonEditEvent.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		JToolBarButtonEditEvent.setToolTipText("ویرایش کردن رویداد های روز انتخاب شده");
		//JToolBarButtonEditEvent.setBackground(mainFrameJToolBar.getBackground());
		JToolBarButtonEditEvent.addActionListener(menuAction);
		mainFrameJToolBar.add(JToolBarButtonEditEvent);
		//---
		JToolBarButtonDeleteEvent = new JButton(new ImageIcon(".\\src\\UPC_Calendar\\pic\\JToolBarButtonDeleteEvent.png"));
		JToolBarButtonDeleteEvent.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		JToolBarButtonDeleteEvent.setToolTipText("حذف کردن رویداد های روز انتخاب شده");
		//JToolBarButtonDeleteEvent.setBackground(mainFrameJToolBar.getBackground());
		JToolBarButtonDeleteEvent.addActionListener(menuAction);
		mainFrameJToolBar.add(JToolBarButtonDeleteEvent);
		this.add(mainFrameJToolBar, BorderLayout.SOUTH);
		//initializing pop up menu------------------------------------------------------
		mainContian.add(thisMonth, BorderLayout.CENTER);
		mainContian.add(showInformation, BorderLayout.SOUTH);
		add(mainContian, BorderLayout.CENTER);
		this.setVisible(
				true);
	}

	public void addAlarm(UPC_Event e) {
		Calendar pCalendar = Calendar.getInstance(new ULocale("fa_IR"));
		pCalendar.set(e.getYear(), e.getMonth(), e.getDay(), e.getHour(), e.getMinute());
		Date d = pCalendar.getTime();
		Date today = new Date();
		if (today.before(d)) {
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					UPC_MyDialogs.showAlarmFrame(UPC_CalendarMainFrame.this, e);
					//JOptionPane.showMessageDialog(null, e.getText(), "هشدار", JOptionPane.WARNING_MESSAGE, new ImageIcon(".\\src\\UPC_Calendar\\pic\\eventFrame\\alarmicon.png"));
				}
			}, d);
			eventList.add(timer);
		} else {
			JOptionPane.showMessageDialog(UPC_CalendarMainFrame.this, "زمان آلارم منقضی شده است", "خطا", JOptionPane.ERROR_MESSAGE, new ImageIcon(".\\src\\UPC_Calendar\\pic\\eventFrame\\alarmicon.png"));
		}
	}

	public void delAlarm(UPC_Event e) {
		for (Timer timer : eventList) {
			if ((timer.toString().compareTo(e.timerStr)) == 0) {
				timer.cancel();
				eventList.remove(timer);
				e.timerStr = "";
				break;
			}
		}
	}

	private class MenuAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem temp = null;
			if (e.getSource() instanceof JMenuItem) {
				temp = (JMenuItem) e.getSource();
			}
			if (temp == JMenuItemInsertNote) {
				UPC_Note n = new UPC_Note("", UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex].persianDate.get(Calendar.YEAR), UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex].persianDate.get(Calendar.MONTH), monthDays[UPC_MonthVeiwer.selectedDayIndex].persianDate.get(Calendar.DATE), UPC_CalendarSetting.dayDateNumber);
				UPC_MyDialogs.showNoteFrame(UPC_CalendarMainFrame.this, n);
				if (n != null) {
					UPC_CalendarSetting.dayDateNumber++;
					UPC_MonthVeiwer.notes.add(n);
					thisMonth.reValidateDayInformations(UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex]);
					thisMonth.reValidateToolTip();
					UPC_FileReadAndWrite.writeData(n);
				}
			} else if (temp == JMenuItemInsertEvent) {
				UPC_Event e1 = new UPC_Event("", UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex].persianDate.get(Calendar.YEAR), UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex].persianDate.get(Calendar.MONTH), monthDays[UPC_MonthVeiwer.selectedDayIndex].persianDate.get(Calendar.DATE), UPC_CalendarSetting.dayDateNumber, 0, 0);
				UPC_MyDialogs.showEventFrame(UPC_CalendarMainFrame.this, e1);
				if (e1 != null) {
					UPC_CalendarSetting.dayDateNumber++;
					UPC_MonthVeiwer.events.add(e1);
					thisMonth.reValidateDayInformations(UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex]);
					thisMonth.reValidateToolTip();
					UPC_FileReadAndWrite.writeData(e1);
					UPC_CalendarMainFrame.this.addAlarm(e1);
				}
			} else if (temp == JMenuItemEditNote) {
				for (UPC_Note note : UPC_MonthVeiwer.notes) {
					if (note.getText().equals(showInformation.getNoteText())) {
						UPC_MyDialogs.showNoteFrame(UPC_CalendarMainFrame.this, note);
						thisMonth.reValidateDayInformations(UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex]);
						thisMonth.reValidateToolTip();
						UPC_FileReadAndWrite.writeData(note);
					}
				}
				//int i = 0 ; i< UPC_MonthVeiwer.notes.size() ; i++
			} else if (temp == JMenuItemEditEvent) {
				for (UPC_Event event : UPC_MonthVeiwer.events) {
					if (event.getText().equals(showInformation.getEventText())) {
						UPC_MyDialogs.showEventFrame(UPC_CalendarMainFrame.this, event);
						thisMonth.reValidateDayInformations(UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex]);
						thisMonth.reValidateToolTip();
						UPC_FileReadAndWrite.writeData(event);
						UPC_CalendarMainFrame.this.delAlarm(event);
						UPC_CalendarMainFrame.this.addAlarm(event);
					}
				}

			} else if (temp == JMenuItemDeleteNote) {
				for (UPC_Note note : UPC_MonthVeiwer.notes) {
					if (note.getText().equals(showInformation.getNoteText())) {
						UPC_MonthVeiwer.notes.remove(note);
						thisMonth.reValidateDayInformations(UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex]);
						thisMonth.reValidateToolTip();
						UPC_FileReadAndWrite.deleteData(note);
					}
				}
			} else if (temp == JMenuItemDeleteEvent) {
				for (UPC_Event event : UPC_MonthVeiwer.events) {
					if (event.getText().equals(showInformation.getEventText())) {
						UPC_MonthVeiwer.events.remove(event);
						thisMonth.reValidateDayInformations(UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex]);
						thisMonth.reValidateToolTip();
						UPC_FileReadAndWrite.deleteData(event);
						UPC_CalendarMainFrame.this.delAlarm(event);
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
				StringSelection ss = new StringSelection(showInformation.getTimeLineText());
				cb.setContents(ss, ss);
			} else if (temp == JMenuExit) {
				System.err.println("wwww");
				System.exit(0);
			}
			JButton temp1 = null;
			if (e.getSource() instanceof JButton) {
				temp1 = (JButton) e.getSource();
			}
			if (temp1 == JToolBarButtonInsertNote) {
				UPC_Note n = new UPC_Note("", UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex].persianDate.get(Calendar.YEAR), UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex].persianDate.get(Calendar.MONTH), monthDays[UPC_MonthVeiwer.selectedDayIndex].persianDate.get(Calendar.DATE), UPC_CalendarSetting.dayDateNumber);
				UPC_MyDialogs.showNoteFrame(UPC_CalendarMainFrame.this, n);
				if (n != null) {
					UPC_CalendarSetting.dayDateNumber++;
					UPC_MonthVeiwer.notes.add(n);
					thisMonth.reValidateDayInformations(UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex]);
					thisMonth.reValidateToolTip();
					UPC_FileReadAndWrite.writeData(n);
				}
			} else if (temp1 == JToolBarButtonInsertEvent) {
				UPC_Event e1 = new UPC_Event("", UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex].persianDate.get(Calendar.YEAR), UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex].persianDate.get(Calendar.MONTH), monthDays[UPC_MonthVeiwer.selectedDayIndex].persianDate.get(Calendar.DATE), UPC_CalendarSetting.dayDateNumber, 0, 0);
				UPC_MyDialogs.showEventFrame(UPC_CalendarMainFrame.this, e1);
				if (e1 != null) {
					UPC_CalendarSetting.dayDateNumber++;
					UPC_MonthVeiwer.events.add(e1);
					thisMonth.reValidateDayInformations(UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex]);
					thisMonth.reValidateToolTip();
					UPC_FileReadAndWrite.writeData(e1);
					UPC_CalendarMainFrame.this.addAlarm(e1);
				}
			} else if (temp1 == JToolBarButtonEditNote) {
				for (UPC_Note note : UPC_MonthVeiwer.notes) {
					System.out.println(note.getText());
					System.out.println(showInformation.getNoteText());
					if (note.getText().equals(showInformation.getNoteText())) {
						UPC_MyDialogs.showNoteFrame(UPC_CalendarMainFrame.this, note);
						thisMonth.reValidateDayInformations(UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex]);
						thisMonth.reValidateToolTip();
						UPC_FileReadAndWrite.writeData(note);
					}
				}
			} else if (temp1 == JToolBarButtonEditEvent) {
				for (UPC_Event event : UPC_MonthVeiwer.events) {
					if (event.getText().equals(showInformation.getEventText())) {
						UPC_MyDialogs.showEventFrame(UPC_CalendarMainFrame.this, event);
						thisMonth.reValidateDayInformations(UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex]);
						thisMonth.reValidateToolTip();
						UPC_FileReadAndWrite.writeData(event);
						UPC_CalendarMainFrame.this.delAlarm(event);
						UPC_CalendarMainFrame.this.addAlarm(event);
					}
				}

			} else if (temp1 == JToolBarButtonDeleteNote) {
				for (UPC_Note note : UPC_MonthVeiwer.notes) {
					if (note.getText().equals(showInformation.getNoteText())) {
						UPC_MonthVeiwer.notes.remove(note);
						thisMonth.reValidateDayInformations(UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex]);
						thisMonth.reValidateToolTip();
						UPC_FileReadAndWrite.deleteData(note);
						break;
					}
				}
			} else if (temp1 == JToolBarButtonDeleteEvent) {
				for (UPC_Event event : UPC_MonthVeiwer.events) {
					if (event.getText().equals(showInformation.getEventText())) {
						UPC_MonthVeiwer.events.remove(event);
						thisMonth.reValidateDayInformations(UPC_MonthVeiwer.monthDays[UPC_MonthVeiwer.selectedDayIndex]);
						thisMonth.reValidateToolTip();
						UPC_FileReadAndWrite.deleteData(event);
						UPC_CalendarMainFrame.this.delAlarm(event);
						break;
					}
				}
			}
		}
	}

}
