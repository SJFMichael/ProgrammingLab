/*
 UPC
 */
package UPC_Calendar;

import UPC_Calendar_Data.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

/**
 *
 * @author UPC
 */
public class UPC_MyDialogs {

	public static void showNoteFrame(JFrame parent, UPC_Note editIt) {
		JDialog noteFrame = new JDialog(parent, "صفحه ی یادداشت", true);
		String pre = editIt.getText();
		noteFrame.setSize(600, 800);
		noteFrame.setResizable(false);
		noteFrame.setLocation(parent.getLocation().x + parent.getWidth() / 2 - noteFrame.getWidth() / 2, parent.getLocation().y + parent.getHeight() / 2 - noteFrame.getHeight() / 2);
		noteFrame.getContentPane().setBackground(new Color(255, 255, 255));
		noteFrame.repaint();
		ImageIcon calendarMainFrameIcon = new ImageIcon(".\\src\\UPC_Calendar\\pic\\noteFrame\\noteicon.png");
		noteFrame.setIconImage(calendarMainFrameIcon.getImage());
		noteFrame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon(".\\src\\UPC_Calendar\\pic\\noteFrame\\cur1.png").getImage(),
				new Point(5, 5), "UPC_NoteFrameCursor"));
		noteFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//noteFrame.setUndecorated(true);
		//noteFrame.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		//noteFrame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		Box buttonBox = Box.createHorizontalBox();
		JTextArea noteEditor = new JTextArea(editIt.getText(), 10, 5);
		noteEditor.setLineWrap(true);
		noteEditor.setWrapStyleWord(true);
		noteEditor.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		noteEditor.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon(".\\src\\UPC_Calendar\\pic\\noteFrame\\cur1.png").getImage(),
				new Point(5, 5), "UPC_NoteFrameCursor"));
		noteEditor.setFont(UPC_CalendarSetting.noteEditorFont.deriveFont(UPC_CalendarSetting.noteEditorFontSize));
		JScrollPane editorContian = new JScrollPane(noteEditor, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		editorContian.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		JButton ok = new JButton("تایید");
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editIt.setText(noteEditor.getText());
				noteFrame.dispose();
			}
		});
		JButton cancel = new JButton("لغو");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!pre.equals("")) {
					editIt.setText(pre);
				} else {
					editIt.setText(null);
				}
				noteFrame.dispose();
			}
		});
		buttonBox.add(cancel);
		buttonBox.add(Box.createHorizontalGlue());
		buttonBox.add(ok);
		noteFrame.add(editorContian, BorderLayout.CENTER);
		noteFrame.add(buttonBox, BorderLayout.SOUTH);
		noteFrame.setVisible(true);
	}

	public static void showEventFrame(JFrame parent, UPC_Event editIt) {
		JDialog eventFrame = new JDialog(parent, "صفحه ی رویداد", true);
		String pre = editIt.getText();
		int preH = editIt.getHour();
		int preM = editIt.getMinute();
		eventFrame.setSize(800, 100);
		eventFrame.setResizable(false);
		eventFrame.setLocation(parent.getLocation().x + parent.getWidth() / 2 - eventFrame.getWidth() / 2, parent.getLocation().y + parent.getHeight() / 2 - eventFrame.getHeight() / 2);
		//eventFrame.getContentPane().setBackground(new Color(64, 0, 64));
		//eventFrame.repaint();
		ImageIcon calendarMainFrameIcon = new ImageIcon(".\\src\\UPC_Calendar\\pic\\eventFrame\\setalarmicon.png");
		eventFrame.setIconImage(calendarMainFrameIcon.getImage());
		eventFrame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon(".\\src\\UPC_Calendar\\pic\\eventFrame\\cur3.png").getImage(),
				new Point(0, 0), "UPC_EventFrameCursor"));
		eventFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//noteFrame.setUndecorated(true);
		//noteFrame.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		//noteFrame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		JTextField eventText = new JTextField(editIt.getText(), 20);
		eventText.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		eventText.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon(".\\src\\UPC_Calendar\\pic\\noteFrame\\cur1.png").getImage(),
				new Point(5, 5), "UPC_NoteFrameCursor"));
		eventText.setFont(UPC_CalendarSetting.noteEditorFont.deriveFont(UPC_CalendarSetting.noteEditorFontSize));
		SpinnerModel hourChooser = new SpinnerNumberModel(preH, 0, 23, 1);
		SpinnerModel minuteChooser = new SpinnerNumberModel(preM, 0, 59, 1);
		JSpinner JHourChooser = new JSpinner(hourChooser);
		JHourChooser.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		JHourChooser.setFont(UPC_CalendarSetting.noteEditorFont.deriveFont(UPC_CalendarSetting.noteEditorFontSize));
		JSpinner JMinuteChooser = new JSpinner(minuteChooser);
		JMinuteChooser.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		JMinuteChooser.setFont(UPC_CalendarSetting.noteEditorFont.deriveFont(UPC_CalendarSetting.noteEditorFontSize));
		JLabel LHour = new JLabel("ساعت");
		LHour.setHorizontalAlignment(SwingConstants.CENTER);
		LHour.setFont(UPC_CalendarSetting.noteEditorFont.deriveFont(UPC_CalendarSetting.noteEditorFontSize));
		JLabel LMinute = new JLabel("دقیقه");
		LMinute.setHorizontalAlignment(SwingConstants.CENTER);
		LMinute.setFont(UPC_CalendarSetting.noteEditorFont.deriveFont(UPC_CalendarSetting.noteEditorFontSize));
		Box frameLayout = Box.createHorizontalBox();
		frameLayout.add(JMinuteChooser);
		frameLayout.add(Box.createHorizontalStrut(10));
		frameLayout.add(LMinute);
		frameLayout.add(Box.createHorizontalStrut(50));
		frameLayout.add(JHourChooser);
		frameLayout.add(Box.createHorizontalStrut(10));
		frameLayout.add(LHour);
		frameLayout.add(Box.createHorizontalStrut(60));
		frameLayout.add(eventText);
		eventFrame.add(frameLayout, BorderLayout.NORTH);
		JButton ok = new JButton("تایید");
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editIt.setText(eventText.getText());
				editIt.setHour((int) JHourChooser.getValue());
				editIt.setMinute((int) JMinuteChooser.getValue());
				eventFrame.dispose();
			}
		});
		JButton cancel = new JButton("لغو");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!pre.equals("")) {
					editIt.setText(pre);
					editIt.setHour(preH);
					editIt.setMinute(preM);
				} else {
					editIt.setText(null);
				}
				eventFrame.dispose();
			}
		});
		Box buttonBox = Box.createHorizontalBox();
		buttonBox.add(cancel);
		buttonBox.add(Box.createHorizontalGlue());
		buttonBox.add(ok);
		eventFrame.add(buttonBox, BorderLayout.SOUTH);
		//---
		eventFrame.setVisible(true);
		//---
	}

	public static void showAlarmFrame(JFrame parent, UPC_Event editIt) {
		JOptionPane.showMessageDialog(null, editIt.getText(), "هشدار", JOptionPane.WARNING_MESSAGE, new ImageIcon(".\\src\\UPC_Calendar\\pic\\eventFrame\\alarmicon.png"));
	}
}
