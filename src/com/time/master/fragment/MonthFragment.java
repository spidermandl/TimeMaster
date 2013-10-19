/**
 * @注释 梁丽丽
          实现逻辑操作。
   1、日期缓存。
   2、
 */
package com.time.master.fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import com.time.master.R;
import com.time.master.TimeMasterApplication;
import com.time.master.calendar.DateWidgetDayCell;
import com.time.master.calendar.DateWidgetDayHeader;
import com.time.master.calendar.DayStyle;
import com.time.master.tool.Constant;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

/**
 * 月界面
 * 
 * @author Desmond 
 * 
 */
public class MonthFragment extends Fragment {

	// 生成日历，外层容器
	private LinearLayout layContent = null;//定义布局属性并初始化
	private ArrayList<DateWidgetDayCell> days = new ArrayList<DateWidgetDayCell>();

	// 日期变量
	public static Calendar calStartDate = Calendar.getInstance();  //得到每个月开始日期
	private Calendar calToday = Calendar.getInstance(); // 得到当日对象
	private Calendar calCalendar = Calendar.getInstance(); //
	private Calendar calSelected = Calendar.getInstance();  //得到被选中的单元格对象

	// 当前操作日期
	private int iMonthViewCurrentMonth = 0;  //当前月
	private int iMonthViewCurrentYear = 0;   //当前年
	private int iFirstDayOfWeek = Calendar.MONDAY;  //每周从周一开始

	private int Calendar_Width = 0;  //日历宽
	private int Cell_Width = 0;   //单元格宽

	// 页面控件
	TextView Top_Date = null; //头部文本
	Button btn_pre_month = null; //下个月按钮
	Button btn_next_month = null;  //上个月按钮
	TextView arrange_text = null;   // 
	LinearLayout mainLayout = null;  // 背景颜色为白色部分
	LinearLayout arrange_layout = null;  // 单元格布局

	// 数据源
	ArrayList<String> Calendar_Source = null;
	Hashtable<Integer, Integer> calendar_Hashtable = new Hashtable<Integer, Integer>();
	Boolean[] flag = null;
	Calendar startDate = null;//当前日历第一天
	Calendar endDate = null; //当前日历最后一天
	int dayvalue = -1;

	String UserName = "";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mainLayout = (LinearLayout) inflater.inflate(R.layout.month_layout,
				container, false);

		// 获得屏幕宽和高，并計算出屏幕寬度分七等份的大小
		Calendar_Width = TimeMasterApplication.getInstance().getScreen_W();
		Cell_Width = Calendar_Width / 7 + 1;

		// 声明控件，并绑定事件


		Top_Date = (TextView) mainLayout.findViewById(R.id.Top_Date);
		btn_pre_month = (Button) mainLayout.findViewById(R.id.btn_pre_month);//上月按钮
		btn_next_month = (Button) mainLayout.findViewById(R.id.btn_next_month); //下月按钮
		btn_pre_month.setOnClickListener(new Pre_MonthOnClickListener());
		btn_next_month.setOnClickListener(new Next_MonthOnClickListener());

		// 计算本月日历中的第一天(一般是上月的某天)，并更新日历
		calStartDate = getCalendarStartDate();  //当前日历第一天
		mainLayout.addView(generateCalendarMain()); //monthFragement下布局文件
		DateWidgetDayCell daySelected = updateCalendar();

		if (daySelected != null)//如果日被选中
			daySelected.requestFocus();//执行响应
		//实例化俩个布局容器
		LinearLayout.LayoutParams Param1 = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);


		ScrollView view = new ScrollView(this.getActivity());
		arrange_layout = createLayout(LinearLayout.VERTICAL);
		arrange_layout.setPadding(5, 2, 0, 0);
		arrange_text = new TextView(this.getActivity());
		mainLayout.setBackgroundColor(Color.WHITE);  //背景色为白色部分
		arrange_text.setTextColor(Color.BLACK);
		arrange_text.setTextSize(18);
		arrange_layout.addView(arrange_text); //日 的布局


		startDate = GetStartDate();
		calToday = GetTodayDate();

		endDate = GetEndDate(startDate);
		view.addView(arrange_layout, Param1);//向容器中加入布局
		mainLayout.addView(view);//像布局中加入控件

		// 新建线程
		new Thread() {
			@Override
			public void run() {
				int day = GetNumFromDate(calToday, startDate);

				if (calendar_Hashtable != null
						&& calendar_Hashtable.containsKey(day)) {
					dayvalue = calendar_Hashtable.get(day);
				}
			}

		}.start();
//把各属性的颜色返回在布局中
		Constant.Calendar_WeekBgColor = this.getResources().getColor(
				R.color.Calendar_WeekBgColor);
		Constant.Calendar_DayBgColor = this.getResources().getColor(
				R.color.Calendar_DayBgColor);
		Constant.isHoliday_BgColor = this.getResources().getColor(
				R.color.isHoliday_BgColor);
		Constant.unPresentMonth_FontColor = this.getResources().getColor(
				R.color.unPresentMonth_FontColor);
		Constant.isPresentMonth_FontColor = this.getResources().getColor(
				R.color.isPresentMonth_FontColor);
		Constant.isToday_BgColor = this.getResources().getColor(
				R.color.isToday_BgColor);
		Constant.special_Reminder = this.getResources().getColor(
				R.color.specialReminder);
		Constant.common_Reminder = this.getResources().getColor(
				R.color.commonReminder);
		Constant.Calendar_WeekFontColor = this.getResources().getColor(
				R.color.Calendar_WeekFontColor);
		return mainLayout;
	}
	// 得到全称
	protected String GetDateShortString(Calendar date) {
		String returnString = date.get(Calendar.YEAR) + "/";
		returnString += date.get(Calendar.MONTH) + 1 + "/";
		returnString += date.get(Calendar.DAY_OF_MONTH);

		return returnString;
	}

	// 得到当天在日历中的序号
	private int GetNumFromDate(Calendar now, Calendar returnDate) {
		Calendar cNow = (Calendar) now.clone();  //当日期
		Calendar cReturnDate = (Calendar) returnDate.clone();   //日历
		setTimeToMidnight(cNow);
		setTimeToMidnight(cReturnDate);

		long todayMs = cNow.getTimeInMillis();
		long returnMs = cReturnDate.getTimeInMillis();
		long intervalMs = todayMs - returnMs;  //距离差
		int index = millisecondsToDays(intervalMs);

		return index;
	}

//分辨率

	//得到序号

	private int millisecondsToDays(long intervalMs) {
		return Math.round((intervalMs / (1000 * 86400)));
	}
//传入时间
	private void setTimeToMidnight(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	}

	// 生成布局
	private LinearLayout createLayout(int iOrientation) {
		LinearLayout lay = new LinearLayout(this.getActivity());
		lay.setLayoutParams(new LayoutParams(
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		lay.setOrientation(iOrientation);

		return lay;
	}

	// 生成日历头部
	private View generateCalendarHeader() {
		LinearLayout layRow = createLayout(LinearLayout.HORIZONTAL);  //水平布局
		// layRow.setBackgroundColor(Color.argb(255, 207, 207, 205));

<<<<<<< HEAD
		for (int iDay = 0; iDay < 7; iDay++) { //遍历
			DateWidgetDayHeader day = new DateWidgetDayHeader(this.getActivity(), Cell_Width,
					35);
=======
		for (int iDay = 0; iDay < 7; iDay++) {
			DateWidgetDayHeader day = new DateWidgetDayHeader(
					this.getActivity(), Cell_Width, 35);
>>>>>>> c6668860d3c549eb3fbe02ed695310be2cf65344

			final int iWeekDay = DayStyle.getWeekDay(iDay, iFirstDayOfWeek);  //从周一开始  加7天
			day.setData(iWeekDay); //设置数据
			layRow.addView(day); //添加
		}

		return layRow;
	}

	// 生成日历主体
	private View generateCalendarMain() {
		layContent = createLayout(LinearLayout.VERTICAL); //垂直布局
		// layContent.setPadding(1, 0, 1, 0);
		layContent.setBackgroundColor(Color.argb(255, 105, 105, 103)); //完全显示
		layContent.addView(generateCalendarHeader()); //添加头部
		days.clear();

		for (int iRow = 0; iRow < 6; iRow++) {
			layContent.addView(generateCalendarRow()); //主体添加6个头部布局
		}

		return layContent;
	}

	// 生成日历中的一行，仅画矩形
	private View generateCalendarRow() {
		LinearLayout layRow = createLayout(LinearLayout.HORIZONTAL);// 创建水平的线性布局。
		// 日历中的每一行的方格为7个。
		for (int iDay = 0; iDay < 7; iDay++) {
<<<<<<< HEAD
			DateWidgetDayCell dayCell = new DateWidgetDayCell(this.getActivity(), Cell_Width,
					Cell_Width);
			dayCell.setItemClick(mOnDayCellClick); //设置点击事件 
			days.add(dayCell);
			layRow.addView(dayCell); //为行 添加矩形 
=======
			// 日历控件单元格绘制类实例对象来调用画矩形的方法，以及矩形的宽和高。
			DateWidgetDayCell dayCell = new DateWidgetDayCell(
					this.getActivity(), Cell_Width, Cell_Width);
			dayCell.setItemClick(mOnDayCellClick);// 调用点击事件
			days.add(dayCell);// 把单元格加到数组集合中。
			layRow.addView(dayCell);// 把数组集合中单元格加到布局里。
>>>>>>> c6668860d3c549eb3fbe02ed695310be2cf65344
		}

		return layRow;
	}

	// 设置当天日期和被选中日期
	private Calendar getCalendarStartDate() {
		calToday.setTimeInMillis(System.currentTimeMillis());
		calToday.setFirstDayOfWeek(iFirstDayOfWeek);

		if (calSelected.getTimeInMillis() == 0) {
			calStartDate.setTimeInMillis(System.currentTimeMillis());
			calStartDate.setFirstDayOfWeek(iFirstDayOfWeek);
		} else {
			calStartDate.setTimeInMillis(calSelected.getTimeInMillis());
			calStartDate.setFirstDayOfWeek(iFirstDayOfWeek);
		}

		UpdateStartDateForMonth();
		return calStartDate;
	}

	// 由于本日历上的日期都是从周一开始的，此方法可推算出上月在本月日历中显示的天数
	private void UpdateStartDateForMonth() {
		iMonthViewCurrentMonth = calStartDate.get(Calendar.MONTH);  //当前月
		iMonthViewCurrentYear = calStartDate.get(Calendar.YEAR); //当前年
		calStartDate.set(Calendar.DAY_OF_MONTH, 1);  //当前日
		calStartDate.set(Calendar.HOUR_OF_DAY, 0);
		calStartDate.set(Calendar.MINUTE, 0);
		calStartDate.set(Calendar.SECOND, 0);
		// update days for week
		UpdateCurrentMonthDisplay();
		int iDay = 0;
		int iStartDay = iFirstDayOfWeek;

		if (iStartDay == Calendar.MONDAY) { { //从周一开始 得到星期 和距离本月天数--》可得到日期
			iDay = calStartDate.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY;
			if (iDay < 0)
				iDay = 6;
		}

		if (iStartDay == Calendar.SUNDAY) { //从周日开始 得到星期 和距离本月天数--》可得到日期
			iDay = calStartDate.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY;
			if (iDay < 0)
				iDay = 6;
		}

		calStartDate.add(Calendar.DAY_OF_WEEK, -iDay);
		}
	}

	// 更新日历
	private DateWidgetDayCell updateCalendar() {
		DateWidgetDayCell daySelected = null;
<<<<<<< HEAD
		boolean bSelected = false;  //默认没选中
		final boolean bIsSelection = (calSelected.getTimeInMillis() != 0);
		final int iSelectedYear = calSelected.get(Calendar.YEAR); //被选中所在年
		final int iSelectedMonth = calSelected.get(Calendar.MONTH);  //被选中所在月
		final int iSelectedDay = calSelected.get(Calendar.DAY_OF_MONTH); //被选中所在日
		calCalendar.setTimeInMillis(calStartDate.getTimeInMillis()); //设置日历当前

		for (int i = 0; i < days.size(); i++) {
			final int iYear = calCalendar.get(Calendar.YEAR);  //得到单元格所在年
			final int iMonth = calCalendar.get(Calendar.MONTH);  //单元格所在月
			final int iDay = calCalendar.get(Calendar.DAY_OF_MONTH);  //单元格所在日
			final int iDayOfWeek = calCalendar.get(Calendar.DAY_OF_WEEK); //所在星期
			DateWidgetDayCell dayCell = days.get(i);  //将日期放在对应单元格内

			// 判断是否当天
			boolean bToday = false; //验证
=======
		boolean bSelected = false;// 当前没有选中的单元格
		final boolean bIsSelection = (calSelected.getTimeInMillis() != 0);
		// Field number for get and set indicating the year.
		final int iSelectedYear = calSelected.get(Calendar.YEAR);
		// Field number for get and set indicating(指示，表明) the month.
		final int iSelectedMonth = calSelected.get(Calendar.MONTH);
		// Field number for get and set indicating the day of the month.
		final int iSelectedDay = calSelected.get(Calendar.DAY_OF_MONTH);
		// Sets the time of this Calendar.(the time of this Calendar.)
		calCalendar.setTimeInMillis(calStartDate.getTimeInMillis());
		// array=object[60],for循环遍历，获得更新时间
		for (int i = 0; i < days.size(); i++) {
			final int iYear = calCalendar.get(Calendar.YEAR);
			final int iMonth = calCalendar.get(Calendar.MONTH);
			final int iDay = calCalendar.get(Calendar.DAY_OF_MONTH);
			// Field number for get and set indicating the day of the week.
			final int iDayOfWeek = calCalendar.get(Calendar.DAY_OF_WEEK);
			DateWidgetDayCell dayCell = days.get(i);//返回获得单元格

			// 判断是否当天
			boolean bToday = false;//当 获得年月日为当天的时间 ，则设置bToday = true。
>>>>>>> c6668860d3c549eb3fbe02ed695310be2cf65344

			if (calToday.get(Calendar.YEAR) == iYear) {   // calToday所在年是否为今年
				if (calToday.get(Calendar.MONTH) == iMonth) {   //是否为当月
					if (calToday.get(Calendar.DAY_OF_MONTH) == iDay) {  //是否为当天
						bToday = true; //如果三个条件都满足则为当天 返回true
					}
				}
			}

			// check holiday
			boolean bHoliday = false;//获取的日期等于周六，周天，则判断为holiday
			if ((iDayOfWeek == Calendar.SATURDAY)
					|| (iDayOfWeek == Calendar.SUNDAY))  //日期所在星期为周六或周日 那么此日为假期
				bHoliday = true;
<<<<<<< HEAD
			if ((iMonth == Calendar.JANUARY) && (iDay == 1)) // 日期为1月1日为假期
=======
			if ((iMonth == Calendar.JANUARY) && (iDay == 1))//获取的日期等于1月1日，则判断为holiday
>>>>>>> c6668860d3c549eb3fbe02ed695310be2cf65344
				bHoliday = true;

			// 是否被选中
			bSelected = false;
<<<<<<< HEAD

			if (bIsSelection) //如果有可能被选中
=======
            //当选中的年月日为当前年月日  则判断被选中
			if (bIsSelection)
>>>>>>> c6668860d3c549eb3fbe02ed695310be2cf65344
				if ((iSelectedDay == iDay) && (iSelectedMonth == iMonth)
						&& (iSelectedYear == iYear)) {   //当日被选中
					bSelected = true;
				}

			dayCell.setSelected(bSelected);//设置单元格被选中

			// 是否有记录

			boolean hasRecord = true;//判断记录内容是否为空，不为空则为有记录。


			if (flag != null && flag[i] == true && calendar_Hashtable != null
					&& calendar_Hashtable.containsKey(i)) {
				// hasRecord = flag[i];
				hasRecord = Calendar_Source.get(calendar_Hashtable.get(i))
						.contains(UserName);  // 日志存在且不为空 记录内容和i形成键值对 记录内容包含用户名
			}

			if (bSelected)
				daySelected = dayCell; //为被选中赋日期

			dayCell.setData(iYear, iMonth, iDay, bToday, bHoliday,
					iMonthViewCurrentMonth, hasRecord); //设置日期属性 

			calCalendar.add(Calendar.DAY_OF_MONTH, 1); //为日历添加 日
		}

		layContent.invalidate();

		return daySelected;
	}

	// 更新日历标题上显示的年月
	private void UpdateCurrentMonthDisplay() {
		String date = calStartDate.get(Calendar.YEAR) + "年"
				+ (calStartDate.get(Calendar.MONTH) + 1) + "月";
		Top_Date.setText(date); //设置文本内容
	}

	// 点击上月按钮，触发事件
	class Pre_MonthOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			arrange_text.setText(""); //日 设置文本
			calSelected.setTimeInMillis(0);
			iMonthViewCurrentMonth--; //点击一次 月份减一

			if (iMonthViewCurrentMonth == -1) { //当年月份读完 年减一
				iMonthViewCurrentMonth = 11;
				iMonthViewCurrentYear--;
			}

			calStartDate.set(Calendar.DAY_OF_MONTH, 1); //设置日
			calStartDate.set(Calendar.MONTH, iMonthViewCurrentMonth); //设置月
			calStartDate.set(Calendar.YEAR, iMonthViewCurrentYear);  //设置年
			calStartDate.set(Calendar.HOUR_OF_DAY, 0);
			calStartDate.set(Calendar.MINUTE, 0);
			calStartDate.set(Calendar.SECOND, 0);
			calStartDate.set(Calendar.MILLISECOND, 0);
			UpdateStartDateForMonth(); //更新月份开始日期

			startDate = (Calendar) calStartDate.clone(); //和calStartDate雷同
			endDate = GetEndDate(startDate); //与startDate呼应

			// 新建线程
			new Thread() {
				@Override
				public void run() {

					int day = GetNumFromDate(calToday, startDate);//得到相差天数

					if (calendar_Hashtable != null
							&& calendar_Hashtable.containsKey(day)) {
						dayvalue = calendar_Hashtable.get(day);
					}
				}
			}.start();  //上个月在这个月的遗留

			updateCalendar(); //更新日历
		}

	}

	// 点击下月按钮，触发事件
	class Next_MonthOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			arrange_text.setText("");
			calSelected.setTimeInMillis(0);
			iMonthViewCurrentMonth++;   //点击一次 月份加一

			if (iMonthViewCurrentMonth == 12) {
				iMonthViewCurrentMonth = 0;
				iMonthViewCurrentYear++;  //当年月份读完 年减一
			}

			calStartDate.set(Calendar.DAY_OF_MONTH, 1);
			calStartDate.set(Calendar.MONTH, iMonthViewCurrentMonth);
			calStartDate.set(Calendar.YEAR, iMonthViewCurrentYear);
			UpdateStartDateForMonth();

			startDate = (Calendar) calStartDate.clone();
			endDate = GetEndDate(startDate);

			// 新建线程
			new Thread() {
				@Override
				public void run() {
					int day = 5;

					if (calendar_Hashtable != null
							&& calendar_Hashtable.containsKey(day)) {
						dayvalue = calendar_Hashtable.get(day);
					}
				}
			}.start();

			updateCalendar();
		}
	}

	// 点击日历，触发事件
	private DateWidgetDayCell.OnItemClick mOnDayCellClick = new DateWidgetDayCell.OnItemClick() {
		public void OnClick(DateWidgetDayCell item) {
			// Sets the time of this Calendar.
			calSelected.setTimeInMillis(item.getDate().getTimeInMillis());
<<<<<<< HEAD
			int day = GetNumFromDate(calSelected, startDate); //被选中天数和起始日期相差天数

=======
			int day = GetNumFromDate(calSelected, startDate);
			// Returns true if this Hashtable contains the specified object as a
			// key of one of the key/value pairs.
			// 如果日历表中指定对象不为空且日历表中指定对象存在
>>>>>>> c6668860d3c549eb3fbe02ed695310be2cf65344
			if (calendar_Hashtable != null
					&& calendar_Hashtable.containsKey(day)) {
				// 将指定的值显示出来
				arrange_text.setText(Calendar_Source.get(calendar_Hashtable
						.get(day)));  //为单元格文本设置内容
			} else {
				// 否则显示暂无数据记录。
				arrange_text.setText("暂无数据记录");
			}

			item.setSelected(true); //单元格可以被选中
			updateCalendar();
		}
	};
	//得到当日日期
	public Calendar GetTodayDate() {
		Calendar cal_Today = Calendar.getInstance();
		cal_Today.set(Calendar.HOUR_OF_DAY, 0);
		cal_Today.set(Calendar.MINUTE, 0);
		cal_Today.set(Calendar.SECOND, 0);
		cal_Today.setFirstDayOfWeek(Calendar.MONDAY);

		return cal_Today;
	}

	// 得到当前日历中的第一天
	public Calendar GetStartDate() {
		int iDay = 0;
		Calendar cal_Now = Calendar.getInstance();
		cal_Now.set(Calendar.DAY_OF_MONTH, 1); //只需日期
		cal_Now.set(Calendar.HOUR_OF_DAY, 0);
		cal_Now.set(Calendar.MINUTE, 0);
		cal_Now.set(Calendar.SECOND, 0);
		cal_Now.setFirstDayOfWeek(Calendar.MONDAY);

		iDay = cal_Now.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY; 

		if (iDay < 0) {
			iDay = 6;
		}

		cal_Now.add(Calendar.DAY_OF_WEEK, -iDay);  //得到星期和距离本月天数--》可得到日期

		return cal_Now;
	}

	public Calendar GetEndDate(Calendar startDate) {
		// Calendar end = GetStartDate(enddate);
		Calendar endDate = Calendar.getInstance();
		endDate = (Calendar) startDate.clone();  
		endDate.add(Calendar.DAY_OF_MONTH, 41);  //由startDate得到类似endDate日期 ，且位居第42格
		return endDate;
	}

}
