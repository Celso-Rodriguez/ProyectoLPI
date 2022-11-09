package util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Conversiones {

	public static Date toFecha(String fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fec = null;
		try {
			fec = new Date(sdf.parse(fecha).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return fec;
	}
	

	public static String getFechaYYYYMMddHHmmss(java.sql.Date fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //Brinda formato a la fecha
		return sdf.format(fecha);
	}
	
}