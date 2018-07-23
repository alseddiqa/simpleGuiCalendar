package simpleGuiCalendar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;


public class MyCalendar {

	
	private ArrayList<Event> events;
	public MyCalendar() {
		events = new ArrayList<Event>();
	}
	
	public void addEvent(Event e) {
		for(Event event : events)
		{
			if(event.equals(e))
			{
				return;
			}
		}
		events.add(e);
		Collections.sort(events);
	}
	
	public ArrayList<Event> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<Event> events) {
		this.events = events;
	}

}
