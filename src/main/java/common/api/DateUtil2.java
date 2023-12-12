package common.api;

import java.sql.Timestamp;
import java.util.Calendar;

public class DateUtil2 {
	public static Timestamp addDays(Timestamp date, int months, int weeks, int days, int hours, int minutes, int seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        cal.add(Calendar.WEEK_OF_YEAR, weeks);
        cal.add(Calendar.DAY_OF_MONTH, days); //minus number would decrement the days
        cal.add(Calendar.HOUR, hours);
        cal.add(Calendar.MINUTE, minutes);
        cal.add(Calendar.SECOND, seconds);
        return new Timestamp(cal.getTime().getTime());
}
	
	public static Timestamp addMilli(Timestamp d1, long diff) 
	{
		Calendar timeout = Calendar.getInstance();
        timeout.setTimeInMillis(d1.getTime() + diff);        
        Timestamp tsNew = new Timestamp(timeout.getTime().getTime());
        return tsNew;

}
}
