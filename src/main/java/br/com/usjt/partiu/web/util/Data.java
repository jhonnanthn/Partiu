package br.com.usjt.partiu.web.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Data {
	public String getFirsLastDate(String tipo) {
		Calendar c = Calendar.getInstance();
		GregorianCalendar calendar = new GregorianCalendar();

		int firDay = c.getActualMinimum(Calendar.DAY_OF_MONTH);
		int lasDay = c.get(Calendar.DAY_OF_MONTH);
		int mes = calendar.get(2) + 1;
		int ano = calendar.get(1);
		
		String mMes = ( mes < 9 )?"0"+mes:""+mes;
		String data;
		
		if (tipo == "F") {
			if (firDay < 9) {
				data = "0" + firDay + "/" + mMes + "/" + ano;
			}
			else {
				data = firDay + "/" + mMes + "/" + ano;
			}
		}
		else if (lasDay < 9) {
			data = "0" + lasDay + "/" + mMes + "/" + ano;
		}
		else {
			data = lasDay + "/" + mMes + "/" + ano;
		}
		return data;
	}
}
