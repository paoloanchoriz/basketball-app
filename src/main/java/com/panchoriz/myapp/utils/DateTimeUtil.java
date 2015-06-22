package com.panchoriz.myapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.LocalDate;

public class DateTimeUtil {
	
	private DateTimeUtil() {	
	}
	
	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("ddMMMyyyy");
	
	private static final SimpleDateFormat WITH_TIME = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
	private static final SimpleDateFormat TIME = new SimpleDateFormat("HH:mm");
	
	public static SimpleDateFormat getDateFormatter() {
		return FORMAT;
	}
	
	public static Date getTodaysDate() {
		return new LocalDate().toDate();
	}
	
	public static String getStringTodaysDate() {
		return FORMAT.format(getTodaysDate());
	}
	
	public static Date getParsedDate(String date) {
		return getParsedDate(date, FORMAT);
	}
	
	public static Date getParsedDate(String date, SimpleDateFormat sdf) {
		try{
			return FORMAT.parse(date);
		}catch(ParseException e) {
			return getTodaysDate();
		}
	}
	
	public static String getFormattedDate(Date date) {
		return FORMAT.format(date);
	}
	
	public static String getFormattedTime(Date date) {
		return TIME.format(date);
	}
	
	public static String getFormattedDateWithTime(Date date) {
		return WITH_TIME.format(date);
	}
	
	public static String getStringTodaysDateWithTime() {
		return getFormattedDateWithTime(Calendar.getInstance().getTime());
	}
}
