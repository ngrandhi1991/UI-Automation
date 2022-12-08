package generics;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
//Generic class for add date
public class AddDate 
{
	static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	static DateFormat dateFormat2 = new SimpleDateFormat("dd.MM.yyyy");
		public static String currentDate(String format)
		
		{	// TODO Auto-generated method stub
			
			Date Date = new Date();
			String currentDate = ""; 
			if(format.equals("normal"))
				currentDate = dateFormat.format(Date);
			else
				currentDate = dateFormat2.format(Date);
			System.out.println(currentDate);               //2016-11-16 
			return currentDate;
		}
		
		public static String addingDays(int days,String format)
		{
		
			Calendar c=new GregorianCalendar();
			c.add(Calendar.DATE, days);
			Date addedDate=c.getTime();
			String date = "";
			if(format.equals("normal"))
				date=dateFormat.format(addedDate);
			else
				date = dateFormat2.format(addedDate);
			
			return date;
		}
	
	public static void main(String[] args)
	{
		currentDate("FI");
		int days = (int) Math.round(60.3030303030303);
		String date = addingDays(days,"FI");
		System.out.println("Number of days to add: " +days+date);
	}

}