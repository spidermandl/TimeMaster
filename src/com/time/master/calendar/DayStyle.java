package com.time.master.calendar;

import java.util.Calendar;

/**
 * 日历控件样式绘制类
 * @Description: 日历控件样式绘制类
 */
public class DayStyle {
	private final static String[] vecStrWeekDayNames = getWeekDayNames();

	private static String[] getWeekDayNames() {
		String[] vec = new String[10];

		vec[Calendar.SUNDAY] = "周日";
		vec[Calendar.MONDAY] = "周一";
		vec[Calendar.TUESDAY] = "周二";
		vec[Calendar.WEDNESDAY] = "周三";
		vec[Calendar.THURSDAY] = "周四";
		vec[Calendar.FRIDAY] = "周五";
		vec[Calendar.SATURDAY] = "周六";
		
		return vec;
	}

	public static String getWeekDayName(int iDay) {
		return vecStrWeekDayNames[iDay];
	}
	
	public static int getWeekDay(int index, int iFirstDayOfWeek) {
		int iWeekDay = -1;  //将周一放置第一位

		if (iFirstDayOfWeek == Calendar.MONDAY) {  //如果一周的第一天是周一
			iWeekDay = index + Calendar.MONDAY;
			
			if (iWeekDay > Calendar.SATURDAY)
				iWeekDay = Calendar.SUNDAY;
		}

		if (iFirstDayOfWeek == Calendar.SUNDAY) {  //如果一周的第一天是周日
			iWeekDay = index + Calendar.SUNDAY;
		}

		return iWeekDay;
	}
}
