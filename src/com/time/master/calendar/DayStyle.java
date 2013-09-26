/**
 * @梁丽丽
 */

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
	/**
	 * 判断当天是星期几。
	 * @param index  指针索引  用于指向下一个地址（星期几）
	 * @param iFirstDayOfWeek  本周的第一天
	 * @return
	 */
	public static int getWeekDay(int index, int iFirstDayOfWeek) {
		
		//一周有7天  , iweekday是用来获取当天是星期几
		int iWeekDay = -1;
       //判断 本周的第一天是星期一，此时iweekday=星期一，一直到周六是都满足 ，当>周六时。跳到下一个判断。
		if (iFirstDayOfWeek == Calendar.MONDAY) {
			iWeekDay = index + Calendar.MONDAY;
			 //iweekday>星期六（6），就将iweekday=0(表示星期日) 
			if (iWeekDay > Calendar.SATURDAY)
				iWeekDay = Calendar.SUNDAY;
		}
		 //判断 本周的第一天是星期日，则iweekday=0（星期日）
		if (iFirstDayOfWeek == Calendar.SUNDAY) {
			iWeekDay = index + Calendar.SUNDAY;
		}
        
		return iWeekDay;//返回周几。
	}
}
