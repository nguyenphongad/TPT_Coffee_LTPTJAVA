package util;

import java.time.LocalTime;

public class ConvertTime {
	public static String convertIntToString(int number) {
		if (number < 10)
			return "0" + number;
		return number + "";
	}
	public static String convertLocalTimeToString(LocalTime time) {
		return String.format("%s:%s:%s",
		convertIntToString(time.getHour()), 
		convertIntToString(time.getMinute()),
		convertIntToString(time.getSecond()));
	}

}
