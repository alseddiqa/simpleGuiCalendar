package simpleGuiCalendar;

import java.util.GregorianCalendar;

enum MONTHS {
	January, February, March, April, May, June, July, August, September, October, November, December;
}

enum DAYS {
	Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday;
}

public class SimpleCalendar {

	public static void main(String[] args) {
		CalendarView cal = new CalendarView(new GregorianCalendar());	
	}
}
