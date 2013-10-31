package com.time.master.dialog;

import java.util.Calendar;

import android.R.integer;
import android.R.layout;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

import com.time.master.R;
import com.time.master.TimeMasterApplication;
import com.time.master.tool.ChineseCalendar;
import com.time.master.view.BasicTextView;
import com.time.master.view.SelectedTextView;

/***
 * 重复选日期界面
 * 
 * @author shaojunmei
 * 
 */
public class SelectDayFragment extends WheelDialogFragment implements
		OnClickListener {

	public static final String tag = "RepeatDialogFragment";
	private BasicTextView btvup;
	private BasicTextView btvdown;
	private BasicTextView cumonth;
	private SelectedTextView one,two,three,four,five,six,seven,eight,nine,ten,
						     eleven,twelve,thirteen,fourteen,fifteen,sixteen,seventeen,eighteen,nineteen,twenty,
						     twentyone,twentytwo,twentythree,twentyfour,twentyfive,twentysix,twentyseven,twentyeight,
							 thirtyone,thirty,tweentynine;
	public  SelectedTextView emonth,pmonth;
	public SelectedTextView eyear,pyear;
	Calendar calendar;
	ChineseCalendar chineseCalendar;
	int year, month, day, maxDay;
	int dayStyle=1;
	static private final String[] months = { "一", "二", "三", "四", "五", "六", "七",
			"八", "九", "十", "十一", "十二" };

	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Light);
		super.onCreate(savedInstanceState);

	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		setDialogStyle();

		View layout = inflater.inflate(R.layout.dayselected, container, false);
		cumonth = (BasicTextView) layout.findViewById(R.id.yue);
		emonth=(SelectedTextView)layout.findViewById(R.id.emonth);
		eyear=(SelectedTextView)layout.findViewById(R.id.eyear);
		pmonth=(SelectedTextView)layout.findViewById(R.id.pmonth);
		pyear=(SelectedTextView)layout.findViewById(R.id.pyear);
		
		emonth.setOnClickListener(this);
		eyear.setOnClickListener(this);
		pmonth.setOnClickListener(this);
		pyear.setOnClickListener(this);
		
		pmonth.setSelected();
		
		confirm=(TextView)layout.findViewById(R.id.day_selected_conform);
		confirm.setOnClickListener(this);
		
		one=(SelectedTextView)layout.findViewById(R.id.one);
		two=(SelectedTextView)layout.findViewById(R.id.two);
		three=(SelectedTextView)layout.findViewById(R.id.three);
		four=(SelectedTextView)layout.findViewById(R.id.four);
		five=(SelectedTextView)layout.findViewById(R.id.five);
		six=(SelectedTextView)layout.findViewById(R.id.six);
		seven=(SelectedTextView)layout.findViewById(R.id.seven);
		eight=(SelectedTextView)layout.findViewById(R.id.eight);
		nine=(SelectedTextView)layout.findViewById(R.id.nine);
		ten=(SelectedTextView)layout.findViewById(R.id.ten);
		eleven=(SelectedTextView)layout.findViewById(R.id.one_one);
		twelve=(SelectedTextView)layout.findViewById(R.id.one_two);
		thirteen=(SelectedTextView)layout.findViewById(R.id.ten_three);
		fourteen=(SelectedTextView)layout.findViewById(R.id.one_four);
		fifteen=(SelectedTextView)layout.findViewById(R.id.ten_five);
		sixteen=(SelectedTextView)layout.findViewById(R.id.one_six);
		seventeen=(SelectedTextView)layout.findViewById(R.id.one_seven);
		eighteen=(SelectedTextView)layout.findViewById(R.id.ten_eight);
		nineteen=(SelectedTextView)layout.findViewById(R.id.one_nine);
		twenty=(SelectedTextView)layout.findViewById(R.id.two_ten);
		twentyone=(SelectedTextView)layout.findViewById(R.id.two_one);
		twentytwo=(SelectedTextView)layout.findViewById(R.id.two_two);
		twentythree=(SelectedTextView)layout.findViewById(R.id.two_three);
		twentyfour=(SelectedTextView)layout.findViewById(R.id.two_four);
		twentyfive=(SelectedTextView)layout.findViewById(R.id.two_five);
		twentysix=(SelectedTextView)layout.findViewById(R.id.two_six);
		twentyseven=(SelectedTextView)layout.findViewById(R.id.two_seven);
		twentyeight=(SelectedTextView)layout.findViewById(R.id.two_eight);
		tweentynine =(SelectedTextView) layout.findViewById(R.id.two_nine);
		thirty = (SelectedTextView) layout.findViewById(R.id.three_zero);
		thirtyone = (SelectedTextView) layout.findViewById(R.id.three_one);
		
		init();
	  
		calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH) + 1;
		day = calendar.get(Calendar.DAY_OF_MONTH);
		maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		cumonth.setText(months[month - 1] + "月");

		btvup = (BasicTextView) layout.findViewById(R.id.shang);
		btvup.setOnClickListener(this);

		btvdown = (BasicTextView) layout.findViewById(R.id.xia);
		btvdown.setOnClickListener(this);
		changeDay(31 - maxDay);

		confirm=(BasicTextView)layout.findViewById(R.id.day_selected_conform);
		if(TimeMasterApplication.getInstance().getDatabaseHelper().getIntervalMonthTime()==null){
		TimeMasterApplication.getInstance().getDatabaseHelper().insertIntervalMonthTime(new ChineseCalendar(year, month, day));
		TimeMasterApplication.getInstance().getDatabaseHelper().insertEveryMonthTime(new ChineseCalendar(year, month, day));
		TimeMasterApplication.getInstance().getDatabaseHelper().insertIntervalYearTime(new ChineseCalendar(year, month, day));
		TimeMasterApplication.getInstance().getDatabaseHelper().insertEveryYearTime(new ChineseCalendar(year, month, day));
		}
		else {
			chineseCalendar=TimeMasterApplication.getInstance().getDatabaseHelper().getIntervalMonthTime();
			 year=chineseCalendar.get(ChineseCalendar.YEAR);
			 month=chineseCalendar.get(ChineseCalendar.MONTH);
			 day=chineseCalendar.get(ChineseCalendar.DATE);
			 setSelectDay(day);
			 maxDay = chineseCalendar.getActualMaximum(ChineseCalendar.DAY_OF_MONTH);
			 changeDay(31 - maxDay);
			 cumonth.setText(months[month - 1] + "月");
		}
		super.superInit();
		return layout;
	}

	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
	
		case R.id.shang:

			month++;
			if (month > 12) {
				month = 1;
			}
			calendar.set(year, month - 1, day);
			maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			changeDay(31 - maxDay);
			cumonth.setText(months[month - 1] + "月");
			
			if(dayStyle==1){
				if((month-chineseCalendar.get(ChineseCalendar.MONTH))%2==0||(chineseCalendar.get(ChineseCalendar.MONTH)-month%2)==0){
					setSelectDay(day);
				}
				else {
					setNaturalBackground(day);
				}
			}
			else if(dayStyle==3||dayStyle==4){
				if(month==chineseCalendar.get(ChineseCalendar.MONTH)){
					setSelectDay(day);
				}
				else {
					setNaturalBackground(day);
				}
			}
			
			
			break;

		case R.id.xia:
			month--;
			if (month < 1) {
				month = 12;
			}
			calendar.set(year, month - 1, day);
			maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			changeDay(31 - maxDay);
			cumonth.setText(months[month - 1] + "月");
			
			if(dayStyle==1){
				if((month-chineseCalendar.get(ChineseCalendar.MONTH))%2==0||(chineseCalendar.get(ChineseCalendar.MONTH)-month)%2==0){
					setSelectDay(day);
				}
				else {
					setNaturalBackground(day);
				}
			}
			else if(dayStyle==3||dayStyle==4){
				if(month==chineseCalendar.get(ChineseCalendar.MONTH)){
					setSelectDay(day);
				}
				else {
					setNaturalBackground(day);
				}
			}
			
			break;
		case R.id.pmonth:
			dayStyle=1;
			chineseCalendar=TimeMasterApplication.getInstance().getDatabaseHelper().getIntervalMonthTime();
			 year=chineseCalendar.get(ChineseCalendar.YEAR);
			 month=chineseCalendar.get(ChineseCalendar.MONTH);
			 day=chineseCalendar.get(ChineseCalendar.DATE);
			 setSelectDay(day);
			 maxDay = chineseCalendar.getActualMaximum(ChineseCalendar.DAY_OF_MONTH);
			 changeDay(31 - maxDay);
			 cumonth.setText(months[month - 1] + "月");
			break;
		case R.id.emonth:
			dayStyle=2;
			chineseCalendar=TimeMasterApplication.getInstance().getDatabaseHelper().getEveryMonthTime();
			year=chineseCalendar.get(ChineseCalendar.YEAR);
			month=chineseCalendar.get(ChineseCalendar.MONTH);
			day=chineseCalendar.get(ChineseCalendar.DATE);
			 setSelectDay(day);
			 maxDay = chineseCalendar.getActualMaximum(ChineseCalendar.DAY_OF_MONTH);
			 changeDay(31 - maxDay);
			 cumonth.setText(months[month - 1] + "月");
			break;
		case R.id.pyear:
			dayStyle=3;
			chineseCalendar=TimeMasterApplication.getInstance().getDatabaseHelper().getIntervalYearTime();
			 year=chineseCalendar.get(ChineseCalendar.YEAR);
			 month=chineseCalendar.get(ChineseCalendar.MONTH);
			 day=chineseCalendar.get(ChineseCalendar.DATE);
			 setSelectDay(day);
			 maxDay = chineseCalendar.getActualMaximum(ChineseCalendar.DAY_OF_MONTH);
			 changeDay(31 - maxDay);
			 cumonth.setText(months[month - 1] + "月");
			break;
		case R.id.eyear:
			dayStyle=4;
			chineseCalendar=TimeMasterApplication.getInstance().getDatabaseHelper().getEveryYearTime();
			 year=chineseCalendar.get(ChineseCalendar.YEAR);
			 month=chineseCalendar.get(ChineseCalendar.MONTH);
			 day=chineseCalendar.get(ChineseCalendar.DATE);
			 setSelectDay(day);
			 maxDay = chineseCalendar.getActualMaximum(ChineseCalendar.DAY_OF_MONTH);
			 changeDay(31 - maxDay);
			 cumonth.setText(months[month - 1] + "月");
			break;
		case R.id.one:
			day=1;
			break;
		case R.id.two:
			day=2;
			break;
		case R.id.three:
			day=3;
			break;
		case R.id.four:
			day=4;
			break;
		case R.id.five:
			day=5;
			break;
		case R.id.six:
			day=6;
			break;
		case R.id.seven:
			day=7;
			break;
		case R.id.eight:
			day=8;
			break;
		case R.id.nine:
			day=9;
			break;
		case R.id.ten:
			day=10;
			break;
		case R.id.one_one:
			day=11;
			break;
		case R.id.one_two:
			day=12;
			break;
		case R.id.ten_three:
			day=13;
			break;
		case R.id.one_four:
			day=14;
			break;
		case R.id.ten_five:
			day=15;
			break;
		case R.id.one_six:
			day=16;
			break;
		case R.id.one_seven:
			day=17;
			break;
		case R.id.ten_eight:
			day=18;
			break;
		case R.id.one_nine:
			day=19;
			break;
		case R.id.two_ten:
			day=20;
			break;
		case R.id.two_one:
			day=21;
			break;
		case R.id.two_two:
			day=22;
			break;
		case R.id.two_three:
			day=23;
			break;
		case R.id.two_four:
			day=24;
			break;
		case R.id.two_five:
			day=25;
			break;
		case R.id.two_six:
			day=26;
			break;
		case R.id.two_seven:
			day=27;
			break;
		case R.id.two_eight:
			day=28;
			break;
		case R.id.two_nine:
			day=29;
			break;
		case R.id.three_zero:
			day=30;
			break;
		case R.id.three_one:
			day=31;
			break;
	
		}

	}

	private void changeDay(int day) {
		// TODO Auto-generated method stub
		tweentynine.setVisibility(View.VISIBLE);
		thirty.setVisibility(View.VISIBLE);
		thirtyone.setVisibility(View.VISIBLE);

		switch (day) {

		case 1:
			thirtyone.setVisibility(View.GONE);
			break;
		case 2:
			thirtyone.setVisibility(View.GONE);
			thirty.setVisibility(View.GONE);

			break;
		case 3:
			thirtyone.setVisibility(View.GONE);
			thirty.setVisibility(View.GONE);
			tweentynine.setVisibility(View.GONE);

			break;

		default:
			break;
		}
	}

	public void changedefaultcolor(int fatherdialog) {
		switch (fatherdialog) {
		case 1: //emonth.setBackgroundColor(0xeeff0000);
			break;
		case 2://eyear.setBackgroundColor(0xeeff0000);
			break;

		default:
			break;
		}
	}

	@Override
	protected String getSelectedString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void pushConfirm() {
		// TODO Auto-generated method stub
		switch (dayStyle) {
		case 1:
			TimeMasterApplication.getInstance().getDatabaseHelper().updateIntervalMonthTime(new ChineseCalendar(year, month, day));
			break;
		case 2:
			TimeMasterApplication.getInstance().getDatabaseHelper().updateEveryMonthTime(new ChineseCalendar(year, month, day));
			break;
		case 3:
			TimeMasterApplication.getInstance().getDatabaseHelper().updateIntervalYearTime(new ChineseCalendar(year, month, day));
			break;
		case 4:
			TimeMasterApplication.getInstance().getDatabaseHelper().updateEveryYearTime(new ChineseCalendar(year, month, day));
			break;
		}
	}
	private void init(){
		
		
		one.setOnClickListener(this);
		two.setOnClickListener(this);
		three.setOnClickListener(this);
		four.setOnClickListener(this);
		five.setOnClickListener(this);
		six.setOnClickListener(this);
		seven.setOnClickListener(this);
		eight.setOnClickListener(this);
		nine.setOnClickListener(this);
		ten.setOnClickListener(this);
	    eleven.setOnClickListener(this);
	    twelve.setOnClickListener(this);
	    thirteen.setOnClickListener(this);
	    fourteen.setOnClickListener(this);
	    fifteen.setOnClickListener(this);
	    sixteen.setOnClickListener(this);
	    seventeen.setOnClickListener(this);
	    eighteen.setOnClickListener(this);
	    nineteen.setOnClickListener(this);
	    twenty.setOnClickListener(this);
	    twentyone.setOnClickListener(this);
	    twentytwo.setOnClickListener(this);
	    twentythree.setOnClickListener(this);
	    twentyfour.setOnClickListener(this);
	    twentyfive.setOnClickListener(this);
	    twentysix.setOnClickListener(this);
	    twentyseven.setOnClickListener(this);
	    twentyeight.setOnClickListener(this);
	    tweentynine.setOnClickListener(this);
	    thirty.setOnClickListener(this);
	    thirtyone.setOnClickListener(this);
	}
	private void setSelectDay(int day) {
		// TODO Auto-generated method stub
		switch (day) {
		case 1:
			one.setSelected();
			break;
		case 2:
			two.setSelected();
			break;
		case 3:
			three.setSelected();
			break;
		case 4:
			four.setSelected();
			break;
		case 5:
			five.setSelected();
			break;
		case 6:
			six.setSelected();
			break;
		case 7:
			seven.setSelected();
			break;
		case 8:
			eight.setSelected();
			break;
		case 9:
			nine.setSelected();
			break;
		case 10:
			ten.setSelected();
			break;
		case 11:
			eleven.setSelected();
			break;
		case 12:
			twelve.setSelected();
			break;
		case 13:
			thirteen.setSelected();
			break;
		case 14:
			fourteen.setSelected();
			break;
		case 15:
			fifteen.setSelected();
			break;
		case 16:
			sixteen.setSelected();
			break;
		case 17:
			seventeen.setSelected();
			break;
		case 18:
			eighteen.setSelected();
			break;
		case 19:
			nineteen.setSelected();
			break;
		case 20:
			twenty.setSelected();
			break;
		case 21:
			twentyone.setSelected();
			break;
		case 22:
			twentytwo.setSelected();
			break;
		case 23:
			twentythree.setSelected();
			break;
		case 24:
			twentyfour.setSelected();
			break;
		case 25:
			twentyfive.setSelected();
			break;
		case 26:
			twentysix.setSelected();
			break;
		case 27:
			twentyseven.setSelected();
			break;
		case 28:
			twentyeight.setSelected();
			break;
		case 29:
			tweentynine.setSelected();
			break;
		case 30:
			thirty.setSelected();
			break;
		case 31:
			thirtyone.setSelected();
			break;
		}    
	}
	private void setNaturalBackground(int day){
		switch (day) {
		case 1:
			one.setNaturalBackground();
			break;
		case 2:
			two.setNaturalBackground();
			break;
		case 3:
			three.setNaturalBackground();
			break;
		case 4:
			four.setNaturalBackground();
			break;
		case 5:
			five.setNaturalBackground();
			break;
		case 6:
			six.setNaturalBackground();
			break;
		case 7:
			seven.setNaturalBackground();
			break;
		case 8:
			eight.setNaturalBackground();
			break;
		case 9:
			nine.setNaturalBackground();
			break;
		case 10:
			ten.setNaturalBackground();
			break;
		case 11:
			eleven.setNaturalBackground();
			break;
		case 12:
			twelve.setNaturalBackground();
			break;
		case 13:
			thirteen.setNaturalBackground();
			break;
		case 14:
			fourteen.setNaturalBackground();
			break;
		case 15:
			fifteen.setNaturalBackground();
			break;
		case 16:
			sixteen.setNaturalBackground();
			break;
		case 17:
			seventeen.setNaturalBackground();
			break;
		case 18:
			eighteen.setNaturalBackground();
			break;
		case 19:
			nineteen.setNaturalBackground();
			break;
		case 20:
			twenty.setNaturalBackground();
			break;
		case 21:
			twentyone.setNaturalBackground();
			break;
		case 22:
			twentytwo.setNaturalBackground();
			break;
		case 23:
			twentythree.setNaturalBackground();
			break;
		case 24:
			twentyfour.setNaturalBackground();
			break;
		case 25:
			twentyfive.setNaturalBackground();
			break;
		case 26:
			twentysix.setNaturalBackground();
			break;
		case 27:
			twentyseven.setNaturalBackground();
			break;
		case 28:
			twentyeight.setNaturalBackground();
			break;
		case 29:
			tweentynine.setNaturalBackground();
			break;
		case 30:
			thirty.setNaturalBackground();
			break;
		case 31:
			thirtyone.setNaturalBackground();
			break;
		} 
	}
}
