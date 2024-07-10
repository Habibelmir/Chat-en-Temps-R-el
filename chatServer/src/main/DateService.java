package main;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateService {
	public String getDate() {
		Date currentDate = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String formattedDate = dateFormatter.format(currentDate);
		return formattedDate;
	}
}
