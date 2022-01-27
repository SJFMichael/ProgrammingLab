/*
 UPC
 */
package UPC_Calendar;

import UPC_Calendar_Data.UPC_CalendarSetting;
import UPC_Calendar_Data.UPC_Event;
import UPC_Calendar_Data.UPC_Note;
import UPC_Calendar_Data.UPC_TimeLine;
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;



/**
 *
 * @author UPC
 */
public class UPC_DayInformationPanel extends JPanel{
	private JTabbedPane mainPanel;
	//
	private JScrollPane timeLinesPanel;
	private JScrollPane notesPanel;
	private JScrollPane eventsPanel;
	//
	private JList timeLinesList;
	private JList notesList;
	private JList eventsList;
	//
	private DefaultListModel timeLinesListItem;
	private DefaultListModel notesListItem;
	private DefaultListModel eventsListItem;
	//
	
	public UPC_DayInformationPanel() {
		super(new BorderLayout());
		timeLinesListItem = new DefaultListModel();
		notesListItem = new DefaultListModel();
		eventsListItem = new DefaultListModel();
		//
		timeLinesList = new JList(timeLinesListItem);
		timeLinesList.setVisibleRowCount(3);
		timeLinesList.setFixedCellHeight(50);
		timeLinesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		timeLinesList.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		timeLinesList.setFont(UPC_CalendarSetting.dayInformationFont.deriveFont(UPC_CalendarSetting.dayInformationFontSize + 8));
		notesList = new JList(notesListItem);
		notesList.setVisibleRowCount(3);
		notesList.setFixedCellHeight(50);
		notesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		notesList.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		eventsList = new JList(eventsListItem);
		eventsList.setVisibleRowCount(3);
		eventsList.setFixedCellHeight(50);
		eventsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		eventsList.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		//
		timeLinesPanel = new JScrollPane(timeLinesList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		timeLinesPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		notesPanel = new JScrollPane(notesList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		notesPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		eventsPanel = new JScrollPane(eventsList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		eventsPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		//
		mainPanel = new JTabbedPane(SwingConstants.BOTTOM, JTabbedPane.SCROLL_TAB_LAYOUT);
		mainPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		mainPanel.addTab("مناسبت ها", null, timeLinesPanel, "مناسبت های روز انتخاب شده");
		mainPanel.setFont(UPC_CalendarSetting.dayInformationFont.deriveFont(UPC_CalendarSetting.dayInformationFontSize));
		mainPanel.addTab("یادداشت ها", null, notesPanel, "یادداشت های روز انتخاب شده");
		mainPanel.addTab("رویداد ها", null, eventsPanel, "رویداد های روز انتخاب شده");
		this.add(mainPanel , BorderLayout.CENTER);
	}
	
	public void resetTimeLines() {
		timeLinesListItem.clear();
	}
	
	public void addTimeLine(UPC_TimeLine tl) {
		timeLinesListItem.addElement(tl.getTimeLineName());
	}
	
	public void resetNotes() {
		notesListItem.clear();
	}
	
	public void addNote(UPC_Note n) {
		notesListItem.addElement(n.getText());
	}
	
	public void resetEvents() {
		eventsListItem.clear();
	}
	
	public void addEvent(UPC_Event e) {
		eventsListItem.addElement(e.getText());
	}
	
	public String getTimeLineText() {
		return (String) timeLinesList.getSelectedValue();
	}
	
	public String getEventText() {
		return (String) eventsList.getSelectedValue();
	}
	
	public String getNoteText() {
		return (String) notesList.getSelectedValue();
	}
}
