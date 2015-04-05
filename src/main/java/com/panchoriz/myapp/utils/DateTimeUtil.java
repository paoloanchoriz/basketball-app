package com.panchoriz.myapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
	
	private DateTimeUtil() {	
	}
	
	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("ddMMMyyyy");
	
	public static SimpleDateFormat getDateFormatter() {
		return FORMAT;
	}
	
	public static Date getTodaysDate() {
		return Calendar.getInstance().getTime();
	}
	
	public static String getStringTodaysDate() {
		return FORMAT.format(getTodaysDate());
	}
	
	public static Date getParsedDate(String date) {
		try{
			return FORMAT.parse(date);
		}catch(ParseException e) {
			return getTodaysDate();
		}
	}
	
	public static String getFormattedDate(Date date) {
		return FORMAT.format(date);
	}
	
}
