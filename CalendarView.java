package simpleGuiCalendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CalendarView extends JFrame {

	GregorianCalendar today;
	JPanel buttonPanel;
	JPanel calendarPanel;
	JPanel panelOfCalendar;
	JTextField titleTextField;
	JTextField dateTextFeild;
	JTextField timeFromTextFeild;
	JTextField timeToTextField;
	JComponent eventField;
	JPanel eventDetailPanel;
	JPanel dayArea;
	JButton lastClickedButton = null;

	public CalendarView(GregorianCalendar cal) {
		this.setSize(850, 700);
		setScreen(cal);
	}

	private void setScreen(GregorianCalendar cal) {
		// TODO Auto-generated method stub

		createTopButtonsPanel();

		calendarPanel = new JPanel();
		today = cal;
		getCalendarView(today);
		calendarPanel.add(panelOfCalendar);

		eventField = getEventField();
		eventField.setVisible(false);

		eventDetailPanel = new JPanel();
		setDayArea(today);
		eventDetailPanel.add(dayArea);

//		next.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//
//				panel.remove(entirePanel);
//				areaOfDay.remove(dayArea);
//				today.add(Calendar.DATE, 1);
//				getCalendarView(today);
//				setDayArea(today);
//				panel.add(entirePanel);
//				panel.validate();
//				panel.repaint();
//				areaOfDay.add(dayArea);
//				areaOfDay.validate();
//				areaOfDay.repaint();
//
//			}
//
//		});
//
//		prev.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//
//				panel.remove(entirePanel);
//				areaOfDay.remove(dayArea);
//				today.add(Calendar.DATE, -1);
//				getCalendarView(today);
//				setDayArea(today);
//				panel.add(entirePanel);
//				panel.validate();
//				panel.repaint();
//				areaOfDay.add(dayArea);
//				areaOfDay.validate();
//				areaOfDay.repaint();
//
//			}
//
//		});
//
//		create.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				eventField.setVisible(true);
//				eventField.validate();
//				eventField.repaint();
//
//			}
//		});

		setLayout(new BorderLayout());

		add(buttonPanel, BorderLayout.NORTH);
		add(eventField, BorderLayout.SOUTH);
		add(eventDetailPanel, BorderLayout.CENTER);

		add(calendarPanel, BorderLayout.WEST);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);
	}

	private void createTopButtonsPanel() {
		JPanel topButtonsPanel = new JPanel();
		JButton create = new JButton("Create");
		JButton prev = new JButton("Prev");
		JButton next = new JButton("Next");
		JButton quit = new JButton("Quit");
		topButtonsPanel.add(create);
		topButtonsPanel.add(prev);
		topButtonsPanel.add(next);
		topButtonsPanel.add(quit);
		
		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				calendarPanel.remove(panelOfCalendar);
				eventDetailPanel.remove(dayArea);
				today.add(Calendar.DATE, 1);
				getCalendarView(today);
				setDayArea(today);
				calendarPanel.add(panelOfCalendar);
				calendarPanel.validate();
				calendarPanel.repaint();
				eventDetailPanel.add(dayArea);
				eventDetailPanel.validate();
				eventDetailPanel.repaint();

			}

		});

		prev.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				calendarPanel.remove(panelOfCalendar);
				eventDetailPanel.remove(dayArea);
				today.add(Calendar.DATE, -1);
				getCalendarView(today);
				setDayArea(today);
				calendarPanel.add(panelOfCalendar);
				calendarPanel.validate();
				calendarPanel.repaint();
				eventDetailPanel.add(dayArea);
				eventDetailPanel.validate();
				eventDetailPanel.repaint();

			}

		});

		create.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				eventField.setVisible(true);
				eventField.validate();
				eventField.repaint();

			}
		});
		
		buttonPanel = topButtonsPanel;
	}

	public JComponent getEventField() {
		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

		titleTextField = new JTextField();
		titleTextField.setText("Enter event title");
		dateTextFeild = new JTextField();
		dateTextFeild.setText("Enter event date");
		timeFromTextFeild = new JTextField();
		timeFromTextFeild.setText("Enter event start time");
		timeToTextField = new JTextField();
		timeToTextField.setText("Enter event end time");
		emptyEventFields();

		JButton save = new JButton("Save");

		hGroup.addGroup(layout.createParallelGroup().addComponent(titleTextField).addComponent(dateTextFeild));

		hGroup.addGroup(layout.createParallelGroup().addComponent(timeFromTextFeild).addComponent(timeToTextField));
		layout.setHorizontalGroup(hGroup);

		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(titleTextField)
				.addComponent(timeFromTextFeild));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(dateTextFeild)
				.addComponent(timeToTextField));
		layout.setVerticalGroup(vGroup);
		JLabel eventDetails = new JLabel("Enter Event details in the specified fields");
		eventDetails.setFont(new Font("Verdana", 1, 20));

		JPanel entirePanel = new JPanel();
		entirePanel.setLayout(new BorderLayout());
		entirePanel.add(eventDetails, BorderLayout.NORTH);
		entirePanel.add(panel, BorderLayout.CENTER);
		entirePanel.add(save, BorderLayout.EAST);

		return entirePanel;
	}

	public void getCalendarView(Calendar c) {
		MONTHS[] arrayOfMonths = MONTHS.values();
		int daysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);

		JPanel pane = new JPanel();
		pane.setLayout(new BorderLayout());
		JLabel month = new JLabel("    " + arrayOfMonths[c.get(Calendar.MONTH)] + " " + c.get(Calendar.YEAR));
		month.setFont(new Font("Verdana", 1, 15));
		pane.add(month, BorderLayout.NORTH);

		GregorianCalendar temp = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
		int firstDayOfMonth = temp.get(Calendar.DAY_OF_WEEK) - 1;
		ArrayList<JButton> empty = new ArrayList<>();
		for (int i = 0; i < firstDayOfMonth; i++) {
			empty.add(new JButton(""));
		}

		ArrayList<String> daysString = getDaysList();
		boolean added = false;

		JPanel daysPanel = new JPanel();
		daysPanel.setLayout(new GridLayout(6, 7));
		for (int i = 0; i <= daysInMonth; i++) {
			if (i == 7 || added == true) {
				JButton dayButton = new JButton("" + i);
				if (c.get(Calendar.DATE) == i) {
					dayButton.setForeground(Color.RED);
					lastClickedButton = dayButton;
				}
				dayButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						c.set(Calendar.DATE, Integer.parseInt(dayButton.getActionCommand()));
						eventDetailPanel.remove(dayArea);
						setDayArea((GregorianCalendar) c);
						eventDetailPanel.add(dayArea);
						eventDetailPanel.validate();
						eventDetailPanel.repaint();
						if (lastClickedButton != null) {
							lastClickedButton.setForeground(Color.BLACK);
						}
						dayButton.setForeground(Color.RED);
						lastClickedButton = dayButton;
					}

				});
				daysPanel.add(dayButton);
			} else if (added == false) {
				JButton day = new JButton(daysString.get(i));
				daysPanel.add(day);
				if (i == 6) {
					added = true;
					i = 0;
					for (JButton b : empty) {
						daysPanel.add(b);
					}
				}
			}

		}

		panelOfCalendar = new JPanel();
		panelOfCalendar.setLayout(new BorderLayout());
		panelOfCalendar.add(pane, BorderLayout.CENTER);
		panelOfCalendar.add(daysPanel, BorderLayout.SOUTH);

	}

	public ArrayList<String> getDaysList() {
		ArrayList<String> daysString = new ArrayList<>();
		daysString.add("Su");
		daysString.add("Mo");
		daysString.add("Tu");
		daysString.add("We");
		daysString.add("Th");
		daysString.add("Fr");
		daysString.add("Sa");

		return daysString;
	}

	public void setDayArea(GregorianCalendar date) {
		// Getting the day info
		DAYS[] dArray = DAYS.values();
		int d = date.get(Calendar.DAY_OF_WEEK) - 1;
		String day = dArray[d].name();
		int month = date.get(Calendar.MONTH) + 1;
		day += ", " + month + "/" + date.get(Calendar.DATE);
		JLabel dayLabel = new JLabel(day);
		dayLabel.setFont(new Font("Verdana", 1, 15));
		JTextArea eventArea = new JTextArea(getDayEvents());

		dayArea = new JPanel();
		dayArea.setLayout(new BorderLayout());
		dayArea.add(dayLabel, BorderLayout.NORTH);
		dayArea.add(eventArea, BorderLayout.CENTER);
	}

	public String getDayEvents() {

		return "Events go here";
	}

	public void emptyEventFields() {
		titleTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void focusGained(FocusEvent e) {
				titleTextField.setText("");
			}
		});

		timeFromTextFeild.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void focusGained(FocusEvent e) {
				timeFromTextFeild.setText("");
			}
		});

		timeToTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void focusGained(FocusEvent e) {
				timeToTextField.setText("");
			}
		});

	}
}
