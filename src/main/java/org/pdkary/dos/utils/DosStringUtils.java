package org.pdkary.dos.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DosStringUtils {
	
	public static SimpleDateFormat dosDateFormat = new SimpleDateFormat("dd-MM-yyy");
	
	public static Date toSqlDate(String strDate) throws ParseException {
		java.util.Date utilDate = dosDateFormat.parse(strDate);
		return new Date(utilDate.getTime());
	}
	
	public static String fromSqlDate(Date sqlDate) {
		return dosDateFormat.format(sqlDate);
	}
	
	public static int toDosTime(String hhhh) {
		int hour = Integer.valueOf(hhhh.substring(0,2))*2;
		int half = Integer.valueOf(hhhh.substring(2)) == 30 ? 1 : 0;
		return hour + half;
	}
	
	public static String fromDosTime(int dosTime) {
		if(dosTime % 2 == 0) {
			return String.format("%d00",dosTime/2);
		}else {
			return String.format("%d30",(dosTime-1)/2);
		}
	}
	
}
