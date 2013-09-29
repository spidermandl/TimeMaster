/**
 * @梁丽丽
 */

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
