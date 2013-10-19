package com.time.master.calendar;

import java.util.Calendar;
/*
 * 谢冬
 */
/**
 * 日历控件样式绘制类
 * @Description: 日历控件样式绘制类
 */
public class DayStyle {
	private final static String[] vecStrWeekDayNames = getWeekDayNames();
//定义一个私有静态的字符串数组对象vecStrWeekDayNames，利用函数getWeekDayNames()获得数组对象的值，且每个数组只能赋一次值
	private static String[] getWeekDayNames() {//构造方法
		String[] vec = new String[10];//实例化字符串数组对象vec,字符串数组里有0~10一共11个对象

		vec[Calendar.SUNDAY] = "周日";
		vec[Calendar.MONDAY] = "周一";
		vec[Calendar.TUESDAY] = "周二";
		vec[Calendar.WEDNESDAY] = "周三";
		vec[Calendar.THURSDAY] = "周四";
		vec[Calendar.FRIDAY] = "周五";
		vec[Calendar.SATURDAY] = "周六";
		//赋值字符串数组对象vec的6个对象，如周一，Calendar.SUNDAY好如String[10]里的String[0]，只列出7个数对象
		return vec;//返回对象vec
	}

	public static String getWeekDayName(int iDay) {//方法，获得当前天数
		return vecStrWeekDayNames[iDay];//返回字符串数组 天[]
	}
	
	public static int getWeekDay(int index, int iFirstDayOfWeek) {
		//方法，获得当天星期几
		int iWeekDay = -1;//iWeekDay初始化值为-1，为了下面代码计算的礼拜几-1

		if (iFirstDayOfWeek == Calendar.MONDAY) {//如果工作日第一天为周一
			iWeekDay = index + Calendar.MONDAY;//当天所在星期范围为周一至周六索引，即周一至周六的某一天
			
			if (iWeekDay > Calendar.SATURDAY)//如果当天星期大于礼拜六
				iWeekDay = Calendar.SUNDAY;//当天星期天
		}

		if (iFirstDayOfWeek == Calendar.SUNDAY) {//如果工作日第一天为礼拜天
			iWeekDay = index + Calendar.SUNDAY;//当天所在星期范围为星期天至下一礼拜的星期五
		}

		return iWeekDay;//返回当前星期几
	}
}
