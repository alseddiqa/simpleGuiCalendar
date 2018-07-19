package simpleGuiCalendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CalendarGUI extends JComponent {

	public CalendarGUI() {
		
	}

	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		GregorianCalendar c = new GregorianCalendar();
		int daysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		JPanel pane = new JPanel();
		
		pane.setLayout(new GridLayout(5,7));
		for(int i = 1; i<= daysInMonth; i++)
		{
			JButton day = new JButton(""+i);
			pane.add(day);
		}

	}

	public String printCalendar(Calendar c) {
		MONTHS[] arrayOfMonths = MONTHS.values();
		int date = 1;
		int daysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		GregorianCalendar temp = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
		int firstDayOfMonth = temp.get(Calendar.DAY_OF_WEEK) - 1;

		String cal = "";
		cal = cal + "    " + arrayOfMonths[c.get(Calendar.MONTH)] + " " + c.get(Calendar.YEAR) + "\n";
		cal = cal + "Su " + "Mo " + "Tu " + "We " + "Th " + "Fr " + "Sa" + "\n";

		System.out.println("    " + arrayOfMonths[c.get(Calendar.MONTH)] + " " + c.get(Calendar.YEAR));
		System.out.println("Su " + "Mo " + "Tu " + "We " + "Th " + "Fr " + "Sa");

		// Print initial empty days cells
		for (int i = 0; i < firstDayOfMonth; i++) {
			System.out.print("   ");
			cal = cal + "   ";
		}

		int i = firstDayOfMonth;
		while (date <= daysInMonth) {
			for (; i <= 6 && date <= daysInMonth; i++) {
				String day = "";
				if (date < 10) {
					if (date == c.get(Calendar.DATE)) {
						day = "[" + date + "]";
					} else {
						day = " " + date + " ";
					}

				} else {
					if (date == c.get(Calendar.DATE)) {
						day = "[" + date + "]";
					} else {
						day = date + " ";
					}

				}
				System.out.print(day);
				cal += day;
				date++;
			}
			i = 0;
			System.out.println();
			cal += "\n";

		}

		return cal + "\n";
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();

		CalendarGUI c = new CalendarGUI();
		frame.setLayout(new BorderLayout());
		frame.add(c,BorderLayout.NORTH);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);
	}
}
