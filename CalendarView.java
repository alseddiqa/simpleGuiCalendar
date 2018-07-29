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

	private MyCalendar calendarModel;
	JLabel message;
	GregorianCalendar today;
	JPanel topButtonPanel;
	JPanel calendarPanel;
	JPanel panelOfCalendar;
	JTextField titleTextField;
	JTextField dateTextFeild;
	JTextField timeFromTextFeild;
	JTextField timeToTextField;
	JComponent eventFieldPanel;
	JPanel eventDetailPanel;
	JPanel dayArea;
	JButton save;
	JButton lastClickedButton = null;

	public CalendarView(GregorianCalendar cal) {
		this.setSize(850, 700);
		calendarModel = new MyCalendar();
		today = cal;
		setScreen();

	}

	private void setScreen() {
		createTopButtonsPanel();
		createCalendarPanel();
		createEventFieldsPanel();
		createEventDetailPanel();

		setLayout(new BorderLayout());

		add(topButtonPanel, BorderLayout.NORTH);
		add(eventFieldPanel, BorderLayout.SOUTH);
		add(eventDetailPanel, BorderLayout.CENTER);

		add(calendarPanel, BorderLayout.WEST);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);

	}

	private void createEventDetailPanel() {
		eventDetailPanel = new JPanel();
		setDayArea(today);
		eventDetailPanel.add(dayArea);

	}

	private void createEventFieldsPanel() {
		eventFieldPanel = getEventField();
		eventFieldPanel.setVisible(false);
	}

	private void createCalendarPanel() {

		calendarPanel = new JPanel();
		getCalendarView(today);
		calendarPanel.add(panelOfCalendar);

	}

	private void createTopButtonsPanel() {
		topButtonPanel = new JPanel();
		JButton create = new JButton("Create");
		JButton prev = new JButton("Prev");
		JButton next = new JButton("Next");
		JButton quit = new JButton("Quit");
		topButtonPanel.add(create);
		topButtonPanel.add(prev);
		topButtonPanel.add(next);
		topButtonPanel.add(quit);

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
				eventFieldPanel.setVisible(true);
				eventFieldPanel.validate();
				eventFieldPanel.repaint();

			}
		});

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

		save = new JButton("Save");
		saveEvent();

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
		message = new JLabel("");
		
		JPanel entirePanel = new JPanel();
		entirePanel.setLayout(new BorderLayout());
		entirePanel.add(eventDetails, BorderLayout.NORTH);
		entirePanel.add(panel, BorderLayout.CENTER);
		entirePanel.add(save, BorderLayout.EAST);
		entirePanel.add(message,BorderLayout.SOUTH);

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
				day.setEnabled(false);
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
		String datetext = date.get(Calendar.MONTH) + 1 + "/" + date.get(Calendar.DATE) + "/" + date.get(Calendar.YEAR);
		dateTextFeild.setText(datetext);
		int d = date.get(Calendar.DAY_OF_WEEK) - 1;
		String day = dArray[d].name();
		int month = date.get(Calendar.MONTH) + 1;
		day += ", " + month + "/" + date.get(Calendar.DATE);
		JLabel dayLabel = new JLabel(day);
		dayLabel.setFont(new Font("Verdana", 1, 15));
		JTextArea eventArea = new JTextArea(getDayEvents(date));
		eventArea.setSize(300, 350);

		dayArea = new JPanel();
		dayArea.setLayout(new BoxLayout(dayArea, BoxLayout.Y_AXIS));
		dayArea.add(dayLabel);
		dayArea.add(eventArea);
	}

	public String getDayEvents(GregorianCalendar date) {

		return calendarModel.getEventsList(date);
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

	public void saveEvent() {

		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String eventTitle = titleTextField.getText();
				String eventDate = dateTextFeild.getText();
				String[] parts = eventDate.split("/");
				int m = Integer.parseInt(parts[0]) - 1; // -1 because January is
				int d = Integer.parseInt(parts[1]);
				int y = Integer.parseInt(parts[2]);

				String eventStartTime = timeFromTextFeild.getText();
				String start_am_pm = eventStartTime.substring(eventStartTime.length() - 2, eventStartTime.length());
				eventStartTime = eventStartTime.substring(0, eventStartTime.length()-2);
				String[] time = eventStartTime.split(":");
				int hr = Integer.parseInt(time[0]);
				int min = Integer.parseInt(time[1]);

				// Initiating a date with time
				GregorianCalendar dateOfEvent = new GregorianCalendar(y, m, d, hr, min);
				if (start_am_pm.toLowerCase().equals("am")) {
					dateOfEvent.set(Calendar.AM_PM, Calendar.AM);
				} else {
					dateOfEvent.set(Calendar.AM_PM, Calendar.PM);
				}

				String eventEndTime = timeToTextField.getText();
				start_am_pm = eventEndTime.substring(eventEndTime.length() - 2, eventEndTime.length());
				eventEndTime = eventEndTime.substring(0, eventEndTime.length()-2);
				String[] time1 = eventEndTime.split(":");
				int h1 = Integer.parseInt(time1[0]);
				int min1 = Integer.parseInt(time1[1]);
				GregorianCalendar end = new GregorianCalendar(y, m, d, h1, min1);
				
				if (start_am_pm.toLowerCase().equals("am")) {
					end.set(Calendar.AM_PM, Calendar.AM);
				} else {
					end.set(Calendar.AM_PM, Calendar.PM);
				}
				Event e = new Event(eventTitle, dateOfEvent, end);
				boolean addStatus = calendarModel.addEvent(e);
				if(addStatus == true)
				{
					message.setForeground(Color.GREEN);
					message.setText("Event Added Successfully");
				}
				else
				{
					message.setForeground(Color.RED);
					message.setText("There is time conflict with other event");
				}
			}
		});
	}
}
