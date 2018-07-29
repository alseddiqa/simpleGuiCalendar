package simpleGuiCalendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;


public class MyCalendar {

	
	private ArrayList<Event> events;
	public MyCalendar() {
		events = new ArrayList<Event>();
	}
	
	public boolean addEvent(Event e) {
		for(Event event : events)
		{
			System.out.println(event.equals(e));
			if(event.equals(e))
			{
				return false;
			}
		}
		events.add(e);
		Collections.sort(events);
		return true;
	}
	
	public ArrayList<Event> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<Event> events) {
		this.events = events;
	}

	public String getEventsList(GregorianCalendar c)
	{
		String eventsOnDay = "";
		boolean found = false;
		int y = c.get(Calendar.YEAR);
		int m = c.get(Calendar.MONTH) + 1;
		int d = c.get(Calendar.DATE);
		for(Event e : events)
		{
			System.out.println(e);
			if(m == (e.getStartTime().get(Calendar.MONTH))+1 && y == e.getStartTime().get(Calendar.YEAR) && d == e.getStartTime().get(Calendar.DATE)){
				eventsOnDay += e.getTitle()+" "+e.getEventStartTime()+" "+e.getEventEndTime()+"\n";
				found = true;
			}
		}
		
		if(found == false)
		{
			eventsOnDay = "No Events for this day";
		}
		
		return eventsOnDay;	
	}
}
