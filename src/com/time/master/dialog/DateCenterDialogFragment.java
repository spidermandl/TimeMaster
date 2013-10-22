package com.time.master.dialog;

import java.util.Calendar;
import java.util.HashMap;

import android.R.integer;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.time.master.R;
import com.time.master.TimeMasterApplication;
import com.time.master.dialog.TimeDialogFragment.DateModel;
import com.time.master.model.CacheModel;
import com.time.master.tool.ChineseCalendar;
import com.time.master.view.BasicTextView;
import com.time.master.wheel.adapters.ArrayWheelAdapter;
import com.time.master.wheel.adapters.NumericWheelAdapter;
import com.time.master.wheel.adapters.TimeNumericWheelAdapter;
import com.time.master.wheel.adapters.TimeNumericWheelAdapter.WeekendTextInterface;
import com.time.master.wheel.widget.OnWheelClickedListener;
import com.time.master.wheel.widget.OnWheelScrollListener;
import com.time.master.wheel.widget.TimeWheelView;
import com.time.master.wheel.widget.WheelView;

public class DateCenterDialogFragment extends TimeDialogFragment implements
		View.OnClickListener {

	public static final String TAG = "DateCenterDialogFragment";
	DialogFragment datecenterconfirmFragment;
	public static final int TIME_LIST_NUMBER = 7;
	private int dayModel = 0;// 0:滚轮阳历；1：滚轮农历
	private ChineseCalendar chineseCalendar;// 当前选中时间
	private BasicTextView date_center_second,//更换农阳历
	                      date_center_first;//确认
	HashMap<Integer, Boolean> viewStatus = new HashMap<Integer, Boolean>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Light);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		setDialogStyle();

		model = new DateModel();
		calendar = Calendar.getInstance();
		model.year = calendar.get(Calendar.YEAR);
		model.month = calendar.get(Calendar.MONTH) + 1;
		model.day = calendar.get(Calendar.DAY_OF_MONTH);
		chineseCalendar = new ChineseCalendar(calendar.getTime());
		View layout = inflater.inflate(R.layout.date_top_center_layout,container, false);

		timeWheels = (LinearLayout) layout.findViewById(R.id.day_center_selector_wheel);
		int padding = TimeMasterApplication.getInstance().getScreen_W() / 36;
		timeWheels.setPadding(padding, 0, padding, padding);
		
		mode=(TextView)layout.findViewById(R.id.date_center_second);
        mode.setOnClickListener(this);
        
        date_center_first=(BasicTextView)layout.findViewById(R.id.date_center_first);
        date_center_first.setOnClickListener(this);
        
        mode.setText(R.string.date_top_center_lunar_2);
        mode.setBackgroundColor(Color.YELLOW);
        
		year = (TimeWheelView) layout.findViewById(R.id.yearone);
		yearAdapter = new NumericWheelAdapter(this.getActivity(),model.year - 5000, model.year + 5000);
		yearAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
		yearAdapter.setItemTextResource(R.id.numeric_text);
		year.setVisibleItems(TIME_LIST_NUMBER);
		year.setViewAdapter(yearAdapter);
		year.setCurrentItem(5000);
		year.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}
            @Override
			public void onScrollingFinished(WheelView wheel) {
				model.year = calendar.get(Calendar.YEAR)+ wheel.getCurrentItem() - 5000;
				System.out.println(wheel.getCurrentItem());
				freshDayWheel();
				}
		});
		year.addClickingListener(clickListener);
		

		month = (TimeWheelView) layout.findViewById(R.id.monthone);
		monthAdapter = new NumericWheelAdapter(this.getActivity(), 1, 12);
		monthAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
		monthAdapter.setItemTextResource(R.id.numeric_text);
		month.setVisibleItems(TIME_LIST_NUMBER);
		month.setViewAdapter(monthAdapter);
		month.setCyclic(true);
		month.setCurrentItem(model.month - 1);
		month.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
					}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				model.month = wheel.getCurrentItem() + 1;

				if (dayModel == 0) {
					Calendar calendar = Calendar.getInstance();
					calendar.set(Calendar.YEAR, model.year);
					calendar.set(Calendar.MONTH, model.month - 1);

					int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
					dayAdapter = new TimeNumericWheelAdapter(DateCenterDialogFragment.this.getActivity(), 1,maxDays);
					dayAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
					dayAdapter.setItemTextResource(R.id.numeric_text);

					day.setViewAdapter(dayAdapter);
					int curDay = Math.min(maxDays, day.getCurrentItem() + 1);
					day.setCurrentItem(curDay - 1, true);
					model.day = curDay;
					freshDayWheel();
				}
			}
		});
		month.addClickingListener(clickListener);

		day = (TimeWheelView) layout.findViewById(R.id.dayone);
		dayAdapter = new TimeNumericWheelAdapter(this.getActivity(), 1,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		dayAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
		dayAdapter.setItemTextResource(R.id.numeric_text);
		dayAdapter.setTextInterface(textInterface);
		day.setVisibleItems(TIME_LIST_NUMBER);
		day.setViewAdapter(dayAdapter);
		day.setCyclic(true);
		day.setCurrentItem(model.day - 1 + model.daynumber);
		day.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				model.day = wheel.getCurrentItem() + 1;
				freshDayWheel();
				
				}
		});
		day.addClickingListener(clickListener);

		daynumber = (TimeWheelView) layout.findViewById(R.id.daynumber);
		daynumberAdapter = new TimeNumericWheelAdapter(this.getActivity(), 0,100);
		daynumberAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
		daynumberAdapter.setItemTextResource(R.id.numeric_text);
		daynumber.setVisibleItems(TIME_LIST_NUMBER);
		daynumber.setViewAdapter(daynumberAdapter);
		daynumber.setCyclic(true);
		daynumber.setCurrentItem(1);
		daynumber.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				model.daynumber = wheel.getCurrentItem();
				changDay();
			}
		});
		daynumber.addClickingListener(clickListener);

		totalcount = (TimeWheelView) layout.findViewById(R.id.totalcount);
		totalcountAdapter = new NumericWheelAdapter(this.getActivity(), 0, 200);
		totalcountAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
		totalcountAdapter.setItemTextResource(R.id.numeric_text);
		totalcount.setVisibleItems(TIME_LIST_NUMBER);
		totalcount.setViewAdapter(totalcountAdapter);
		// minute.setBackground(R.drawable.wheel_right_bg);
		totalcount.setCyclic(true);
		totalcount.setCurrentItem(model.totalcount);
		totalcount.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				model.totalcount = wheel.getCurrentItem();

			}
		});
		totalcount.addClickingListener(clickListener);
		superInit();
		return layout;
	}

	public void changDay() {
		
		int num=daynumber.getCurrentItem();
		int daynum=(day.getCurrentItem()+num);
		
		int monthnum=(month.getCurrentItem());
		day.setCurrentItem(daynum);
		for (int i = 0; i < 5; i++) {
			
			if (daynum>30*i) {
				month.setCurrentItem(monthnum+i);
				day.setCurrentItem(daynum-30);
			}
		}
		
	}

	/**
	 * 时间数据模型，年、月、日、小时、分钟
	 */
	class DateModel {
		int year, month, day, daynumber, totalcount;
	}

	DateModel model;
	Calendar calendar;
	TimeWheelView year, month, day, daynumber, totalcount;
	NumericWheelAdapter yearAdapter, monthAdapter, totalcountAdapter;
	ArrayWheelAdapter<String> monthArrayWheelAdapter, dayArrayWheelAdapter;
	TimeNumericWheelAdapter dayAdapter, daynumberAdapter;
	LinearLayout timeWheels;

	OnWheelClickedListener clickListener = new OnWheelClickedListener() {

		@Override
		public void onItemClicked(WheelView wheel, int itemIndex) {
			wheel.setCurrentItem(itemIndex, true);

		}
	};

	@Override
	protected void pushConfirm() {
		CacheModel model = TimeMasterApplication.getInstance().getCacheModel();
		model.currentTime = chineseCalendar;

	}
	WeekendTextInterface textInterface=new WeekendTextInterface() {
		
		@Override
		public void changeText(int index,TextView textView) {
			Calendar calendar=Calendar.getInstance();
			calendar.set(model.year, model.month-1, index+1);
		}
	};

	/**刷新日滚轮*/
	private void freshDayWheel(){
		if(dayModel==0){
	        Calendar calendar = Calendar.getInstance();
	        calendar.set(Calendar.YEAR, model.year);
	        calendar.set(Calendar.MONTH, model.month-1);
	        
	        int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	        dayAdapter=new TimeNumericWheelAdapter(DateCenterDialogFragment.this.getActivity(), 1, maxDays);
	        dayAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
	        dayAdapter.setItemTextResource(R.id.numeric_text);
	        dayAdapter.setTextInterface(textInterface);
	        day.setViewAdapter(dayAdapter);
	        int curDay = Math.min(maxDays, day.getCurrentItem() + 1);
	        day.setCurrentItem(curDay - 1, true);
	        model.day=curDay;
        }else {
			chineseCalendar=new ChineseCalendar(true,model.year,model.month,model.day);
			int maxDays=ChineseCalendar.daysInChineseMonth(model.year,model.month);
			
			String[] chineseDateName=new String[maxDays];
			System.arraycopy(ChineseCalendar.chineseDateNames_1,0,chineseDateName,0,maxDays);
			ArrayWheelAdapter<String> dayArrayWheelAdapter=new ArrayWheelAdapter<String>(getActivity()
					,chineseDateName);
			dayArrayWheelAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
			dayArrayWheelAdapter.setItemTextResource(R.id.numeric_text);
	        day.setViewAdapter(dayArrayWheelAdapter);
	        int curDay = Math.min(maxDays, day.getCurrentItem() + 1);
	        day.setCurrentItem(curDay - 1, true);
	        model.day=curDay;
		}
		
	}
	private String getDateString(){

		if(dayModel==0){
			chineseCalendar.set(ChineseCalendar.YEAR, model.year);
			chineseCalendar.set(ChineseCalendar.MONTH, model.month-1);
			chineseCalendar.set(ChineseCalendar.DAY_OF_MONTH, model.day);
			chineseCalendar.set(ChineseCalendar.HOUR_OF_DAY, model.daynumber);
			chineseCalendar.set(ChineseCalendar.MINUTE, model.totalcount);
			return chineseCalendar.get(ChineseCalendar.CHINESE_YEAR)+"年"+chineseCalendar.getChinese(ChineseCalendar.CHINESE_MONTH)
					+chineseCalendar.getChinese(ChineseCalendar.CHINESE_DATE)+model.daynumber+":"+(model.totalcount<10?"0"+model.totalcount:model.totalcount);
		}
		else{
			chineseCalendar.set(ChineseCalendar.CHINESE_DATE, model.year);
			chineseCalendar.set(ChineseCalendar.CHINESE_MONTH, model.month);
			chineseCalendar.set(ChineseCalendar.CHINESE_DATE, model.day);
			chineseCalendar.set(ChineseCalendar.HOUR_OF_DAY, model.daynumber);
			chineseCalendar.set(ChineseCalendar.MINUTE, model.totalcount);
			return chineseCalendar.get(ChineseCalendar.YEAR)+"/"
		            +(chineseCalendar.get(ChineseCalendar.MONTH)+1)+"/"
					+chineseCalendar.get(ChineseCalendar.DAY_OF_MONTH)
					+"  "+model.daynumber+":"+(model.totalcount<10?"0"+model.totalcount:model.totalcount);
		}
			
	}
	/**滚轮阴阳模式切换*/
	private void changeTimeStyle(int dayModel){
		if(dayModel==0){
			//阳历滚轮变成阴历滚轮
			chineseCalendar.set(model.year,model.month-1,model.day);
			model.year=chineseCalendar.get(ChineseCalendar.CHINESE_YEAR);
			model.month=chineseCalendar.get(ChineseCalendar.CHINESE_MONTH);
			model.day=chineseCalendar.get(ChineseCalendar.CHINESE_DATE);
			this.dayModel=1;
			
			ArrayWheelAdapter<String> monthArrayWheelAdapter=new ArrayWheelAdapter<String>(getActivity(),
					ChineseCalendar.chineseMonthNames_1);
			monthArrayWheelAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
			monthArrayWheelAdapter.setItemTextResource(R.id.numeric_text);
	        month.setViewAdapter(monthArrayWheelAdapter);
	        month.setCurrentItem(model.month-1);
	        
	        ArrayWheelAdapter<String> dayArrayWheelAdapter=new ArrayWheelAdapter<String>(getActivity(),
					ChineseCalendar.chineseDateNames_1);
			dayArrayWheelAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
			dayArrayWheelAdapter.setItemTextResource(R.id.numeric_text);
	        day.setViewAdapter(dayArrayWheelAdapter);
	        day.setCurrentItem(model.day-1);
	        
	        mode.setText(R.string.date_top_center_lunar_1);
		}
		else {
			//阴历滚轮变成阳历滚轮
			chineseCalendar=new ChineseCalendar(true,model.year,model.month,model.day);
			this.dayModel=0;
			model.year=chineseCalendar.get(ChineseCalendar.YEAR);
			model.month=(chineseCalendar.get(ChineseCalendar.MONTH)+1);
			model.day=chineseCalendar.get(ChineseCalendar.DATE);
			dayAdapter = new TimeNumericWheelAdapter(this.getActivity(), 1,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		    dayAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
		    dayAdapter.setItemTextResource(R.id.numeric_text);
		    dayAdapter.setTextInterface(textInterface);
		    day.setViewAdapter(dayAdapter);
		    day.setCurrentItem(model.day-1);
		    monthAdapter = new NumericWheelAdapter(this.getActivity(), 1,12);
	        monthAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
	        monthAdapter.setItemTextResource(R.id.numeric_text);
	        month.setViewAdapter(monthAdapter);
	        month.setCurrentItem(model.month-1);
	        
	      
	        mode.setText(R.string.date_top_center_lunar_2);
		}
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.date_center_second:
			changeTimeStyle(dayModel);
			break;
	    case R.id.date_center_first:
	    	datecenterconfirmFragment = new RepeatDialogFragment();
	    	((RepeatDialogFragment) datecenterconfirmFragment).changePage();
	    	datecenterconfirmFragment.setShowsDialog(true);
			showDialog(datecenterconfirmFragment);
			
			break;
		default:
			break;
		}
	}
}
