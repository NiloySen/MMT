package com.mmt.qa.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FutureDate {

	public static void main(String[] args) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM");
		Date currentDate = new Date();
		System.out.println(dateFormat.format(currentDate));
		String currentDatePart = dateFormat.format(currentDate).substring(1, 2);
		System.out.println(currentDatePart);

		// convert date to calendar
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		// manipulate date
		// c.add(Calendar.YEAR, 1);
		c.add(Calendar.DAY_OF_MONTH, 120);


		// convert calendar to date
		Date futureDate = c.getTime();
		System.out.println(dateFormat.format(futureDate));
		String futureDatePart = dateFormat.format(futureDate).substring(1, 2);
		System.out.println(futureDatePart);

	}

}
