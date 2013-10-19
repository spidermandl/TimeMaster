package com.time.master.dialog;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;

import android.R.integer;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import com.time.master.R;
import com.time.master.TimeMasterApplication;
import com.time.master.model.CacheModel;
import com.time.master.tool.ChineseCalendar;
import com.time.master.wheel.adapters.ArrayWheelAdapter;

import com.time.master.wheel.adapters.NumericWheelAdapter;
import com.time.master.wheel.adapters.TimeNumericWheelAdapter;
import com.time.master.wheel.adapters.TimeNumericWheelAdapter.WeekendTextInterface;
import com.time.master.wheel.widget.OnWheelClickedListener;
import com.time.master.wheel.widget.OnWheelScrollListener;
import com.time.master.wheel.widget.TimeWheelView;
import com.time.master.wheel.widget.WheelView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 时间选择器
 * @author Desmond
 *
 */
public class TimeDialogFragment extends WheelDialogFragment implements OnClickListener{
	
	public static final String TAG="TimeDialogFragment";
	public static final int TIME_LIST_NUMBER=7;
	public static int dayModelStatus;
	private  int  dayModel=0;//0:滚轮阳历；1：滚轮农历
	private ChineseCalendar chineseCalendar;//当前选中时间
	
	HashMap<Integer, Boolean> viewStatus=new HashMap<Integer, Boolean>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME,android.R.style.Theme_Light);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		setDialogStyle();
	
		model=new DateModel();
		calendar = Calendar.getInstance();
		model.year=calendar.get(Calendar.YEAR);
		model.month=calendar.get(Calendar.MONTH)+1;
		model.day=calendar.get(Calendar.DAY_OF_MONTH);
		model.hour=calendar.get(Calendar.HOUR_OF_DAY);
		model.minute=calendar.get(Calendar.MINUTE);
		//chineseCalendar=new ChineseCalendar(false,model.year, model.month-1, model.day);//阳历月减一
		chineseCalendar = new ChineseCalendar(calendar.getTime());
		View layout=inflater.inflate(R.layout.time_wheel_layout, container, false);

        editText=(EditText)layout.findViewById(R.id.edit_date);
        confirm =(TextView)layout.findViewById(R.id.time_confirm);
        timeWheels = (LinearLayout)layout.findViewById(R.id.date_selector_wheel);
        int padding=TimeMasterApplication.getInstance().getScreen_W()/36;
        timeWheels.setPadding(padding, 0, padding, padding);
        mode=(TextView)layout.findViewById(R.id.time_type);
        mode.setOnClickListener(this);
        mode.setText(R.string.date_solar_lunar_2);
        mode.setBackgroundColor(Color.YELLOW);
        
        
        
        editText.setOnClickListener(this);
        
        
        
		year = (TimeWheelView) layout.findViewById(R.id.year);
        yearAdapter = new NumericWheelAdapter(this.getActivity(), model.year-5000, model.year+5000);
        yearAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
        yearAdapter.setItemTextResource(R.id.numeric_text);
        
        year.setVisibleItems(TIME_LIST_NUMBER);
        year.setViewAdapter(yearAdapter);
        //year.setBackground(R.drawable.wheel_left_bg);
        year.setCurrentItem(5000);
        year.addScrollingListener(new OnWheelScrollListener() {
			
			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onScrollingFinished(WheelView wheel) {
				model.year=calendar.get(Calendar.YEAR)+wheel.getCurrentItem()-5000;
				System.out.println(wheel.getCurrentItem());
				freshDayWheel();
				
				editText.setText(getDateString());
				
			}
		});
        year.addClickingListener(clickListener);
        
        month = (TimeWheelView)layout.findViewById(R.id.month);
        monthAdapter = new NumericWheelAdapter(this.getActivity(), 1,12);
        monthAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
        monthAdapter.setItemTextResource(R.id.numeric_text);
        month.setVisibleItems(TIME_LIST_NUMBER);
        month.setViewAdapter(monthAdapter);
        //month.setBackground(R.drawable.wheel_middle_bg);
        month.setCyclic(true);
        month.setCurrentItem(model.month-1);
        month.addScrollingListener(new OnWheelScrollListener() {
			
			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onScrollingFinished(WheelView wheel) {
				model.month=wheel.getCurrentItem()+1;
				if(dayModel==0){
			        Calendar calendar = Calendar.getInstance();
			        calendar.set(Calendar.YEAR, model.year);
			        calendar.set(Calendar.MONTH, model.month-1);
			        
			        int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			        dayAdapter=new TimeNumericWheelAdapter(TimeDialogFragment.this.getActivity(), 1, maxDays);
			        dayAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
			        dayAdapter.setItemTextResource(R.id.numeric_text);
			        
			        day.setViewAdapter(dayAdapter);
			        int curDay = Math.min(maxDays, day.getCurrentItem() + 1);
			        day.setCurrentItem(curDay - 1, true);
			        model.day=curDay;
				}
				freshDayWheel();
				editText.setText(getDateString());
//				int max=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//				dayAdapter=new NumericWheelAdapter(DateFragment.this.getActivity(), 1,max);
//				day.setViewAdapter(dayAdapter);
//				if(model.day>=max)
//					model.day=max;
//				day.setCurrentItem(model.day-1);
			}
		});
        month.addClickingListener(clickListener);
        
        day = (TimeWheelView) layout.findViewById(R.id.day);
        dayAdapter = new TimeNumericWheelAdapter(this.getActivity(), 1,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        dayAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
        dayAdapter.setItemTextResource(R.id.numeric_text);
        dayAdapter.setTextInterface(textInterface);
        day.setVisibleItems(TIME_LIST_NUMBER);
        day.setViewAdapter(dayAdapter);
        //day.setBackground(R.drawable.wheel_middle_bg);
        day.setCyclic(true);
        day.setCurrentItem(model.day-1);
        day.addScrollingListener(new OnWheelScrollListener() {
			
			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onScrollingFinished(WheelView wheel) {
				model.day=wheel.getCurrentItem()+1;
				editText.setText(getDateString());
			}
		});
        day.addClickingListener(clickListener);
        
        
        hour = (TimeWheelView) layout.findViewById(R.id.hour);
        hourAdapter = new TimeNumericWheelAdapter(this.getActivity(), 0,23);
        hourAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
        hourAdapter.setItemTextResource(R.id.numeric_text);
        hourAdapter.setSuffix(":");
        hour.setVisibleItems(TIME_LIST_NUMBER);
        hour.setViewAdapter(hourAdapter);
        //hour.setBackground(R.drawable.wheel_middle_bg);
        hour.setCyclic(true);
        hour.setCurrentItem(model.hour);
        hour.addScrollingListener(new OnWheelScrollListener() {
			
			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onScrollingFinished(WheelView wheel) {
				model.hour=wheel.getCurrentItem();
				editText.setText(getDateString());
			}
		});
        hour.addClickingListener(clickListener);
        
        minute = (TimeWheelView) layout.findViewById(R.id.minute);
        minAdapter = new NumericWheelAdapter(this.getActivity(), 0, 59, "%02d");
        minAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
        minAdapter.setItemTextResource(R.id.numeric_text);
        minute.setVisibleItems(TIME_LIST_NUMBER);
        minute.setViewAdapter(minAdapter);
        //minute.setBackground(R.drawable.wheel_right_bg);
        minute.setCyclic(true);
        minute.setCurrentItem(model.minute);
        minute.addScrollingListener(new OnWheelScrollListener() {
			
			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onScrollingFinished(WheelView wheel) {
				model.minute=wheel.getCurrentItem();
				editText.setText(getDateString());
			}
		});
        minute.addClickingListener(clickListener);
        String str=getDateString();
        editText.setText(str);
        superInit();
		return layout;
	}
	/**
	 * 时间数据模型，年、月、日、小时、分钟
	 */
	class DateModel{
		int year,month,day,hour,minute;
	}
	DateModel model;
	Calendar calendar;
	TimeWheelView year,month,day,hour,minute;
	NumericWheelAdapter yearAdapter,monthAdapter,minAdapter;
	ArrayWheelAdapter<String> monthArrayWheelAdapter,dayArrayWheelAdapter;
	TimeNumericWheelAdapter dayAdapter,hourAdapter;
	LinearLayout timeWheels;
	OnWheelClickedListener clickListener=new OnWheelClickedListener() {
		
		@Override
		public void onItemClicked(WheelView wheel, int itemIndex) {
			wheel.setCurrentItem(itemIndex, true);
			
		}
	};
	/***
	 * 周末日期滚轮字体颜色变化
	 */
	WeekendTextInterface textInterface=new WeekendTextInterface() {
		
		@Override
		public void changeText(int index,TextView textView) {
			Calendar calendar=Calendar.getInstance();
			calendar.set(model.year, model.month-1, index+1);
			int weekend=calendar.get(Calendar.DAY_OF_WEEK);
			if(weekend==Calendar.SUNDAY||weekend==Calendar.SATURDAY){
				textView.setTextColor(0xFFFF0000);
			}
			else{
				textView.setTextColor(0xFF000000);
			}
			
		}
	};
	private String getDateString(){
		if(dayModel==0){
			chineseCalendar.set(ChineseCalendar.YEAR, model.year);
			chineseCalendar.set(ChineseCalendar.MONTH, model.month-1);
			chineseCalendar.set(ChineseCalendar.DAY_OF_MONTH, model.day);
			chineseCalendar.set(ChineseCalendar.HOUR_OF_DAY, model.hour);
			chineseCalendar.set(ChineseCalendar.MINUTE, model.minute);
			return chineseCalendar.get(ChineseCalendar.CHINESE_YEAR)+"年"+chineseCalendar.getChinese(ChineseCalendar.CHINESE_MONTH)
					+chineseCalendar.getChinese(ChineseCalendar.CHINESE_DATE)+model.hour+":"+(model.minute<10?"0"+model.minute:model.minute);
		}
		else{
			chineseCalendar.set(ChineseCalendar.CHINESE_DATE, model.year);
			chineseCalendar.set(ChineseCalendar.CHINESE_MONTH, model.month);
			chineseCalendar.set(ChineseCalendar.CHINESE_DATE, model.day);
			chineseCalendar.set(ChineseCalendar.HOUR_OF_DAY, model.hour);
			chineseCalendar.set(ChineseCalendar.MINUTE, model.minute);
			return chineseCalendar.get(ChineseCalendar.YEAR)+"/"
		            +(chineseCalendar.get(ChineseCalendar.MONTH)+1)+"/"
					+chineseCalendar.get(ChineseCalendar.DAY_OF_MONTH)
					+"  "+model.hour+":"+(model.minute<10?"0"+model.minute:model.minute);
		}
			
	}

	/**刷新日滚轮*/
	private void freshDayWheel(){
		if(dayModel==0){
	        Calendar calendar = Calendar.getInstance();
	        calendar.set(Calendar.YEAR, model.year);
	        calendar.set(Calendar.MONTH, model.month-1);
	        
	        int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	        dayAdapter=new TimeNumericWheelAdapter(TimeDialogFragment.this.getActivity(), 1, maxDays);
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
	@Override
	protected String getSelectedString() {
		return chineseCalendar.get(ChineseCalendar.YEAR)+"/"
	            +(chineseCalendar.get(ChineseCalendar.MONTH)+1)+"/"
				+chineseCalendar.get(ChineseCalendar.DAY_OF_MONTH)
				+"  "+model.hour+":"+(model.minute<10?"0"+model.minute:model.minute);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.time_type:
			changeTimeStyle(dayModel);
			break;
		case R.id.edit_date:
			this.dismiss();
			dayModelStatus=dayModel;
			showDialog(new WorldTimeDialogFragment());
			break;
		default:
			break;
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
	        editText.setText(getDateString());
	        mode.setText(R.string.date_solar_lunar_1);
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
	        
	        editText.setText(getDateString());
	        mode.setText(R.string.date_solar_lunar_2);
		}
		
	}

	@Override
	protected void pushConfirm() {
		CacheModel model=TimeMasterApplication.getInstance().getCacheModel();
		model.currentTime=chineseCalendar;
		
	}

}
