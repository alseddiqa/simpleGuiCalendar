package simpleGuiCalendar;

import java.util.Calendar;
import java.util.GregorianCalendar;

enum MONTHSS {
	January, February, March, April, May, June, July, August, September, October, November, December;
}

enum DAYSS {
	Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday;
}

/**
 * This Event class is used as helper to manage Calendar system
 * 
 * @author Abdullah Alseddiq
 * @version 1.0
 */
public class Event implements Comparable<Event> {

	private String title;
	private GregorianCalendar startTime;
	private GregorianCalendar endTime;

	/**
	 * The constructor of event that has title,startTime, endTime
	 * 
	 * @param title
	 *            of the event
	 * @param startTime
	 *            stores the date of event and the startTime
	 * @param endTime
	 *            stores end time of event
	 */
	public Event(String title, GregorianCalendar startTime, GregorianCalendar endTime) {
		this.title = title;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * A constructor of event with no endTime
	 * 
	 * @param title
	 *            of the event
	 * @param startTime
	 *            stores the date of event and the startTime
	 */
	public Event(String title, GregorianCalendar startTime) {
		super();
		this.title = title;
		this.startTime = startTime;
		this.endTime = null;
	}

	/**
	 * A getter to return the title of the event
	 * 
	 * @return the title of the event
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * A setter to change the title of the event
	 * 
	 * @param title
	 *            to be changed
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * A getter to of the date of event
	 * 
	 * @return gregorianCalendar of date and time of event
	 */
	public GregorianCalendar getStartTime() {
		return startTime;
	}

	/**
	 * A setter of date and time of event
	 * 
	 * @param startTime
	 */
	public void setStartTime(GregorianCalendar startTime) {
		this.startTime = startTime;
	}

	/**
	 * 
	 * @return
	 */
	public GregorianCalendar getEndTime() {
		return endTime;
	}

	public void setEndTime(GregorianCalendar endTime) {
		this.endTime = endTime;
	}

	public int getYearOfEvent() {
		return startTime.get(Calendar.YEAR);
	}

	public int getEventDate() {
		return startTime.get(Calendar.DATE);
	}

	/**
	 * A helper method to get the name of the event day
	 * 
	 * @return the name of the event day
	 */
	public String getDayOfEvent() {
		DAYSS[] dArray = DAYSS.values();
		int day = this.startTime.get(Calendar.DAY_OF_WEEK) - 1;
		String date = dArray[day].name();
		return date;
	}

	/**
	 * A helper method to get the name of the month of event
	 * 
	 * @return the name of the event month
	 */
	public String getMonthOfEvent() {

		MONTHSS[] mArray = MONTHSS.values();
		int m = this.startTime.get(Calendar.MONTH);
		String month = mArray[m].name();
		return month;
	}

	/**
	 * A helper method to convert event endTime to string
	 * 
	 * @return a string of the startTime of event
	 */
	public String getEventStartTime() {
		startTime.getInstance();
		String am_pm = "am";
		if(startTime.get(Calendar.AM_PM) == Calendar.PM)
		{
			am_pm = "pm";
		}
		int hr = startTime.get(Calendar.HOUR);
		int min = startTime.get(Calendar.MINUTE);
		if (min == 0 && hr == 0) {
			return hr + "0:0" + min + am_pm;
		} else if (min == 0 && hr != 0) {
			return hr + ":0" + min +  am_pm;
		} else if (hr == 0 && min != 0) {
			return hr + "0:" + min + am_pm;
		}
		String time = hr + ":" + min + am_pm;
		return time;
	}

	/**
	 * A helper method to convert event startTime to string
	 * 
	 * @return a string of the startTime of event
	 */
	public String getEventEndTime() {
		if (endTime == null) {
			return null;
		}
		
		String am_pm = "am";
		if(endTime.get(Calendar.AM_PM) == Calendar.PM)
		{
			am_pm = "pm";
		}
		
		endTime.getInstance();
		int hr = endTime.get(Calendar.HOUR);
		int min = endTime.get(Calendar.MINUTE);
		if (min == 0 && hr == 0) {
			return hr + "0:0" + min + am_pm;
		} else if (min == 0) {
			return hr + ":0" + min +am_pm;
		} else if (hr == 0) {
			return hr + "0:" + min +am_pm;
		}
		String time = hr + ":" + min +am_pm;
		return time;
	}

	/**
	 * 
	 * @return the title of the event
	 */
	public String getEventTitle() {
		return title;
	}

	/**
	 * A method to change the title of event
	 * 
	 * @param newTitle
	 */
	public void setEventTitle(String newTitle) {
		title = newTitle;
	}

	/**
	 * 
	 * @param e
	 *            - the event to check if two events are the same or not
	 * @return true if event are same or false if events are diffrent
	 */
	@Override
	public boolean equals(Object e) {
		Event event = (Event) e;
		if (this.compareTo(event) == 0) {
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(Event other) {

		int compare = Integer.compare(this.startTime.get(Calendar.YEAR), other.startTime.get(Calendar.YEAR));
		if (compare != 0) {
			return compare;
		}

		compare = Integer.compare(this.startTime.get(Calendar.MONTH), other.startTime.get(Calendar.MONTH));
		if (compare != 0) {
			return compare;
		}

		compare = Integer.compare(this.startTime.get(Calendar.DATE), other.startTime.get(Calendar.DATE));
		if (compare != 0) {
			return compare;
		}
		int hr1 = startTime.get(Calendar.HOUR);
		int hr2 = other.startTime.get(Calendar.HOUR);
		compare = Integer.compare(hr1, hr2);

		return compare;

	}
	
	@Override
	public String toString()
	{
		String eventDetail = getEventTitle() + " " + getEventDate() + " "+ getMonthOfEvent()+ " " + getEventStartTime()+ " " + getEventEndTime();
		return eventDetail;
	}
}
