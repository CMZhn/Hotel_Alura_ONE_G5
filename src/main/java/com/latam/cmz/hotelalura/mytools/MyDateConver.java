package com.latam.cmz.hotelalura.mytools;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class MyDateConver {
	
	/**
	 * Constidor de tipo java.util.Date a java.time.LocalDate
	 * @param date
	 * @return java.time.LocalDate
	 */
	public static LocalDate toLocalDate (Date date) {
		if (date==null) {return null;}
		LocalDate local = date.toInstant().
				atZone(ZoneId.systemDefault())
                .toLocalDate();;
		return local;
	}
	
	/**
	 * Convertidor de java.time.LocalDate a java.util.Date
	 * @param local_date
	 * @return java.util.Date
	 */
	public static Date toDate (LocalDate local_date) {
		if (local_date==null) {return null;}
		ZoneId zoneId = ZoneId.systemDefault();
		Date date = Date.from(local_date.atStartOfDay(zoneId).toInstant());
		return date;
	}


}
