package com.mmt.qa.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FutureMonth {

	public static void main(String[] args) {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM");
		Date currentDate = new Date();
		System.out.println(dateFormat.format(currentDate));
		String currentMonthPart = dateFormat.format(currentDate).substring(3);
		System.out.println(currentMonthPart);

		// convert date to calendar 
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate); // manipulate date //c.add(Calendar.YEAR, 1);
		c.add(Calendar.DAY_OF_MONTH, 120); // c.add(Calendar.DATE, 30); // same with
		c.add(Calendar.DAY_OF_MONTH, 1);

		c.add(Calendar.HOUR, 1);
		c.add(Calendar.MINUTE, 1);
		c.add(Calendar.SECOND, 1);

		// convert calendar to date 
		Date futureDate = c.getTime();
		System.out.println(dateFormat.format(futureDate));
		String futureMonthPart = dateFormat.format(futureDate).substring(3);
		System.out.println(futureMonthPart);

		int monthDiff = Integer.parseInt(futureMonthPart) - Integer.parseInt(currentMonthPart);

		for (int i = 0; i < monthDiff; i++) {

		}

	}
}
