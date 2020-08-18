package com.littleh322.springboot.springboot;

import java.sql.Date;
import java.sql.Timestamp;

public class DateUtils {
	public static Date generateRandomDate() {
		int startYear = 2020;
		int endYear = 1950;
		long start = Timestamp.valueOf(startYear + 1 + "-1-1 0:0:0").getTime();
		long end = Timestamp.valueOf(endYear + "-1-1 0:0:0").getTime();
		long ms = (long) ((end - start) * Math.random() + start);
		Date hireday = new Date(ms);
		return hireday;
	}
}
