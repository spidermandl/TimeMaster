package com.time.master.dialog;

import java.util.Calendar;
import java.util.HashMap;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.time.master.R;
import com.time.master.tool.ChineseCalendar;
import com.time.master.wheel.adapters.ArrayWheelAdapter;
import com.time.master.wheel.adapters.NumericWheelAdapter;
import com.time.master.wheel.widget.OnWheelClickedListener;
import com.time.master.wheel.widget.OnWheelScrollListener;
import com.time.master.wheel.widget.UIWheelView;
import com.time.master.wheel.widget.WheelView;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.time.master.R;
import com.time.master.calendar.DayStyle;
import com.time.master.fragment.NewIssueFragment;
import com.time.master.wheel.adapters.NumericWheelAdapter;
import com.time.master.wheel.widget.OnWheelClickedListener;
import com.time.master.wheel.widget.OnWheelScrollListener;
import com.time.master.wheel.widget.UIWheelView;
import com.time.master.wheel.widget.WheelView;

/**
 * 时间选择器
 * @author duanlei
 *
 */
public class TimeDialogFragment extends WheelDialogFragment implements OnClickListener{
	
	public static final String TAG="TimeDialogFragment";
	//WorldTimeDialogFragment worldtime=new WorldTimeDialogFragment();
	
	private int dayModle=0;
	HashMap<Integer, Boolean> viewStatus=new HashMap<Integer, Boolean>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME,android.R.style.Theme_Light);
	}
	
	@SuppressWarnings("deprecation")
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
		
		View layout=inflater.inflate(R.layout.time_wheel_layout, container, false);

        editText=(EditText)layout.findViewById(R.id.edit_date);
        confirm =(TextView)layout.findViewById(R.id.time_confirm);
        mode=(TextView)layout.findViewById(R.id.time_type);
        mode.setOnClickListener(this);
        mode.setText(R.string.date_solar_lunar_2);
        mode.setBackgroundColor(Color.YELLOW);
        
        
        
        editText.setOnClickListener(this);
        
        
        
		year = (UIWheelView) layout.findViewById(R.id.year);
        yearAdapter = new NumericWheelAdapter(this.getActivity(), model.year-5000, model.year+5000);
        yearAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
        yearAdapter.setItemTextResource(R.id.numeric_text);
        year.setViewAdapter(yearAdapter);
        year.setBackground(R.drawable.wheel_bg_full);
        year.setCurrentItem(5000);
        year.addScrollingListener(new OnWheelScrollListener() {
			
			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onScrollingFinished(WheelView wheel) {
				model.year=calendar.get(Calendar.YEAR)+wheel.getCurrentItem()-5000;
				editText.setText(getDateString());
			}
		});
        year.addClickingListener(clickListener);
        
        month = (UIWheelView) layout.findViewById(R.id.month);
        monthAdapter = new NumericWheelAdapter(this.getActivity(), 1,12);
        monthAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
        monthAdapter.setItemTextResource(R.id.numeric_text);
        month.setViewAdapter(monthAdapter);
        month.setBackground(R.drawable.wheel_bg_full);
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
				if(dayModle==0){
				
				
		        Calendar calendar = Calendar.getInstance();
		        calendar.set(Calendar.YEAR, model.year);
		        calendar.set(Calendar.MONTH, model.month-1);
		        
		        int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		        dayAdapter=new NumericWheelAdapter(TimeDialogFragment.this.getActivity(), 1, maxDays);
		        dayAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
		        dayAdapter.setItemTextResource(R.id.numeric_text);
		        
		        day.setViewAdapter(dayAdapter);
		        int curDay = Math.min(maxDays, day.getCurrentItem() + 1);
		        day.setCurrentItem(curDay - 1, true);
		        model.day=curDay;}

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
        
        day = (UIWheelView) layout.findViewById(R.id.day);
        dayAdapter = new NumericWheelAdapter(this.getActivity(), 1,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        dayAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
        dayAdapter.setItemTextResource(R.id.numeric_text);
        day.setViewAdapter(dayAdapter);
        day.setBackground(R.drawable.wheel_bg_full);
        day.setRightLineWidth(6);
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
        
        hour = (UIWheelView) layout.findViewById(R.id.hour);
        hourAdapter = new NumericWheelAdapter(this.getActivity(), 0,23);
        hourAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
        hourAdapter.setItemTextResource(R.id.numeric_text);
        hour.setViewAdapter(hourAdapter);
        hour.setBackground(R.drawable.wheel_bg_full);
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
        
        minute = (UIWheelView) layout.findViewById(R.id.minute);
        minAdapter = new NumericWheelAdapter(this.getActivity(), 0, 59, "%02d");
        minAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
        minAdapter.setItemTextResource(R.id.numeric_text);
        minute.setViewAdapter(minAdapter);
        minute.setBackground(R.drawable.wheel_bg_full);
        minute.setRightLineWidth(0);
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
        editText.setText(getDateString());
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
	UIWheelView year,month,day,hour,minute;
	NumericWheelAdapter yearAdapter,monthAdapter,dayAdapter,hourAdapter,minAdapter;
	OnWheelClickedListener clickListener=new OnWheelClickedListener() {
		
		@Override
		public void onItemClicked(WheelView wheel, int itemIndex) {
			wheel.setCurrentItem(itemIndex, true);
			
		}
	};
	
	private String getDateString(){
		if(dayModle==0){
			int a[]=ChineseCalendar.sCalendarSolarToLundar(model.year, model.month, model.day);
			return a[0]+"年"+ChineseCalendar.getChinaMonth(a[1]-1)
					+ChineseCalendar.getChinaDay(a[2])+model.hour+"时 "+model.minute+"分 ";
		}
		else{
			int a[]=ChineseCalendar.sCalendarLundarToSolar(model.year, model.month, model.day);
			return a[0]+"年 "+(a[1]-1)+"月 "+(a[2]+1)+"日 "+model.hour+"时 "+model.minute+"分 ";
		}
			
	}

	@Override
	protected String getSelectedString() {
		return getDateString();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.time_type:
			if(dayModle==0){
				int a[]=ChineseCalendar.sCalendarSolarToLundar(model.year, model.month, model.day);
				dayModle=1;
				ArrayWheelAdapter<String> monthArrayWheelAdapter=new ArrayWheelAdapter<String>(getActivity(),
						ChineseCalendar.getChinaMonth());
				monthArrayWheelAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
				monthArrayWheelAdapter.setItemTextResource(R.id.numeric_text);
		        month.setViewAdapter(monthArrayWheelAdapter);
		        month.setCurrentItem(a[1]-1);
		        ArrayWheelAdapter<String> dayArrayWheelAdapter=new ArrayWheelAdapter<String>(getActivity(),
						ChineseCalendar.getChinaDay());
				dayArrayWheelAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
				dayArrayWheelAdapter.setItemTextResource(R.id.numeric_text);
		        day.setViewAdapter(dayArrayWheelAdapter);
		        day.setCurrentItem(a[2]);
		        editText.setText(getDateString());
		        mode.setText(R.string.date_solar_lunar_1);
			}
			else {
				int a[]=ChineseCalendar.sCalendarLundarToSolar(model.year, model.month, model.day);
				dayModle=0;
				dayAdapter = new NumericWheelAdapter(this.getActivity(), 1,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			    dayAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
			    dayAdapter.setItemTextResource(R.id.numeric_text);
			    day.setViewAdapter(dayAdapter);
			    day.setCurrentItem(a[2]+1);
			    monthAdapter = new NumericWheelAdapter(this.getActivity(), 1,12);
		        monthAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
		        monthAdapter.setItemTextResource(R.id.numeric_text);
		        month.setViewAdapter(monthAdapter);
		        month.setCurrentItem(a[1]);
		        editText.setText(getDateString());
		        mode.setText(R.string.date_solar_lunar_2);
			}
			
			break;
		case R.id.edit_date:
			this.dismiss();
			showDialog(new WorldTimeDialogFragment());
			break;
		default:
			break;
		}
	}

}
