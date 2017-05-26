package com.psquickit.converter.xsd;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.DatatypeConverter;

public class CommonTypeConverter {

	public static Date parseDate(String s) {
		return DatatypeConverter.parseDate(s).getTime();
	}

	public static String printDate(Date dt) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(dt);
		return DatatypeConverter.printDate(cal);
	}
	
	public static Date parseTimestamp(String s) {
		return DatatypeConverter.parseDateTime(s).getTime();
	}

	public static String printTimestamp(Date dt) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(dt);
		return DatatypeConverter.printDateTime(cal);
	}

}
