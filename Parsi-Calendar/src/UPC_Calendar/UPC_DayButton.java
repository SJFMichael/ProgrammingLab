/*
 UPC
 */
package UPC_Calendar;

import UPC_Calendar_Data.UPC_CalendarSetting;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.IslamicCalendar;
import com.ibm.icu.util.ULocale;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author UPC
 */
public class UPC_DayButton extends JButton {

	private JLabel mainLabel;
	private JLabel islamicDayLabel;
	private JLabel gregorianDayLabel;
	private boolean selected;
	private boolean holiday;
	public Calendar persianDate;
	public IslamicCalendar correctIslamicDate;
	public UPC_DayButton(String persianDay, String islamicDay, String gregorianDay , ImageIcon seasonIcon) {
		selected = false;
		holiday = false;
		this.setText(persianDay);
		this.setLayout(new BorderLayout());
		correctIslamicDate = new IslamicCalendar(new ULocale("@Calendar=islamic"));
		persianDate = Calendar.getInstance(new ULocale("fa_IR"));
		UPC_CalendarSetting.defaultButtonColor = this.getBackground();
		UPC_CalendarSetting.defaultButtonColorText = this.getForeground();
		this.setFont(UPC_CalendarSetting.persianDayFont.deriveFont(UPC_CalendarSetting.persianDayFontSize));
		mainLabel = new JLabel(seasonIcon , SwingConstants.CENTER);
		mainLabel.setLayout(new GridLayout(1, 0));
		islamicDayLabel = new JLabel(islamicDay, SwingConstants.LEFT);
		islamicDayLabel.setFont(UPC_CalendarSetting.islamicDayFont.deriveFont(UPC_CalendarSetting.islamicDayFontSize));
		mainLabel.add(islamicDayLabel);
		gregorianDayLabel = new JLabel(gregorianDay, SwingConstants.RIGHT);
		gregorianDayLabel.setFont(UPC_CalendarSetting.gregorianDayFont.deriveFont(UPC_CalendarSetting.gregorianDayFontSize));
		mainLabel.add(gregorianDayLabel);
		this.add(mainLabel, BorderLayout.SOUTH);
	}

	@Override
	public void setEnabled(boolean b) {
		super.setEnabled(b);
		mainLabel.setEnabled(b);
		islamicDayLabel.setEnabled(b);
		gregorianDayLabel.setEnabled(b);
	}

	public void makeSelected(boolean b) {
		if (b) {
			selected = true;
			this.setBackground(UPC_CalendarSetting.selectedButtonColor);
			this.setForeground(UPC_CalendarSetting.selectedButtonColorText);
			islamicDayLabel.setForeground(UPC_CalendarSetting.selectedButtonColorText);
			gregorianDayLabel.setForeground(UPC_CalendarSetting.selectedButtonColorText);
		} else {
			selected = false;
			this.setBackground(UPC_CalendarSetting.defaultButtonColor);
			this.setForeground(UPC_CalendarSetting.defaultButtonColorText);
			islamicDayLabel.setForeground(UPC_CalendarSetting.defaultButtonColorText);
			gregorianDayLabel.setForeground(UPC_CalendarSetting.defaultButtonColorText);
			if(holiday) {
				makeHoliday(true);
			}
		}
	}
	
	public void makeHoliday(boolean b) {
		if (b) {
			holiday = true;
			this.setBackground(UPC_CalendarSetting.holidayButtonColor);
			this.setForeground(UPC_CalendarSetting.holidayButtonColorText);
			islamicDayLabel.setForeground(UPC_CalendarSetting.holidayButtonColorText);
			gregorianDayLabel.setForeground(UPC_CalendarSetting.holidayButtonColorText);
		} else {
			holiday = false;
			this.setBackground(UPC_CalendarSetting.defaultButtonColor);
			this.setForeground(UPC_CalendarSetting.defaultButtonColorText);
			islamicDayLabel.setForeground(UPC_CalendarSetting.defaultButtonColorText);
			gregorianDayLabel.setForeground(UPC_CalendarSetting.defaultButtonColorText);
		}
	}
	
	public boolean isSelect() {
		return selected;
	}
	
	public boolean isHoliday() {
		return holiday;
	}
	
	public void setText(String persianDay, String islamicDay, String gregorianDay , ImageIcon seasonIcon) {
		this.setText(persianDay);
		mainLabel.setIcon(seasonIcon);
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		islamicDayLabel.setText(islamicDay);
		islamicDayLabel.setHorizontalAlignment(SwingConstants.LEFT);
		gregorianDayLabel.setText(gregorianDay);
		gregorianDayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	}
	
	public void reset() {
		selected = false;
		holiday = false;
	}
}
