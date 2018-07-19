package simpleGuiCalendar;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CalendarView extends JFrame {

	public CalendarView()
	{
		this.setSize(1000 , 1000);
		setScreen();
	}

	private void setScreen() {
		// TODO Auto-generated method stub
		
		JPanel buttonPanel = new JPanel();
		JButton create = new JButton("Create");
		JButton prev = new JButton("Prev");
		JButton next = new JButton("Next");
		JButton quit = new JButton("Quit");
		buttonPanel.add(create);
		buttonPanel.add(prev);
		buttonPanel.add(next);
		buttonPanel.add(quit);
		
		JPanel panel = new JPanel();
		GregorianCalendar today = new GregorianCalendar();
		//JTextArea c = new JTextArea(printCalendar(today));
		JComponent calendarGUI = getCalendarView(today);
		//c.setFont(new Font("Verdana",1,12));
		panel.add(calendarGUI);
		
		next.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				today.add(Calendar.DATE, 1);
			//	c.setText(printCalendar(today));
				getCalendarView(today);
			}
		});
		
		prev.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				today.add(Calendar.DATE, -1);
				//c.setText(printCalendar(today));
				getCalendarView(today);
			}

			
		});
		
		create.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				JComponent eventField = getEventField();
				add(eventField,BorderLayout.SOUTH);
			//	c.repaint();
			
			}
		});
		
		setLayout(new BorderLayout());
		
		add(buttonPanel, BorderLayout.NORTH);
		
		add(panel, BorderLayout.WEST);
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);		
	}
	
	public JComponent getEventField()
	{
		JPanel pane = new JPanel();
		
		JTextField title = new JTextField();
		JTextField date = new JTextField();
		JTextField timefrom = new JTextField();
		JTextField timeTo = new JTextField();
		JButton save = new JButton("Save");
		
		pane.setLayout(new BorderLayout());
		pane.add(title, BorderLayout.NORTH);
		pane.add(date, BorderLayout.WEST);
		
		pane.add(timefrom, BorderLayout.CENTER);
		
		pane.add(timeTo);
		pane.add(save, BorderLayout.EAST);
		
		return pane;
	}
	
	public JComponent getCalendarView(Calendar c)
	{
		MONTHS[] arrayOfMonths = MONTHS.values();
		int daysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		JPanel pane = new JPanel();
		pane.setLayout(new BorderLayout());
		JLabel month = new JLabel("    " + arrayOfMonths[c.get(Calendar.MONTH)] + " " + c.get(Calendar.YEAR));
		JLabel days = new JLabel("Su " + "Mo " + "Tu " + "We " + "Th " + "Fr " + "Sa");
		pane.add(month, BorderLayout.NORTH);
		pane.add(days, BorderLayout.CENTER);
		
		JPanel daysPanel = new JPanel();
		daysPanel.setLayout(new GridLayout(5,7));
		for(int i = 1; i<= daysInMonth; i++)
		{
			JButton day = new JButton(""+i);
			daysPanel.add(day);
		}
		
		JPanel entirePanel = new JPanel();
		entirePanel.add(pane,BorderLayout.NORTH);
		entirePanel.add(daysPanel, BorderLayout.SOUTH);
		
		return entirePanel;
	}
	
	
	public String printCalendar(Calendar c)
	{
		MONTHS[] arrayOfMonths = MONTHS.values();
		int date = 1;
		int daysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		GregorianCalendar temp = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
		int firstDayOfMonth = temp.get(Calendar.DAY_OF_WEEK) - 1;
		
		String cal = "";
		cal = cal + "    " + arrayOfMonths[c.get(Calendar.MONTH)] + " " + c.get(Calendar.YEAR)+"\n";
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
}
