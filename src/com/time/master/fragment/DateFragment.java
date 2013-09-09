package com.time.master.fragment;

import java.util.Calendar;
import java.util.Currency;
import java.util.Locale;

import com.time.master.R;
import com.time.master.wheel.adapters.NumericWheelAdapter;
import com.time.master.wheel.widget.OnWheelClickedListener;
import com.time.master.wheel.widget.UIWheelView;
import com.time.master.wheel.widget.OnWheelScrollListener;
import com.time.master.wheel.widget.WheelView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class DateFragment extends Fragment {
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		model=new DateModel();
		calendar = Calendar.getInstance();
		model.year=calendar.get(Calendar.YEAR);
		model.month=calendar.get(Calendar.MONTH)+1;
		model.day=calendar.get(Calendar.DAY_OF_MONTH);
		model.hour=calendar.get(Calendar.HOUR_OF_DAY);
		model.minute=calendar.get(Calendar.MINUTE);
		
		View layout=inflater.inflate(R.layout.date_layout, container, false);
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
				
		        Calendar calendar = Calendar.getInstance();
		        calendar.set(Calendar.YEAR, model.year);
		        calendar.set(Calendar.MONTH, model.month-1);
		        
		        int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		        dayAdapter=new NumericWheelAdapter(DateFragment.this.getActivity(), 1, maxDays);
		        dayAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
		        dayAdapter.setItemTextResource(R.id.numeric_text);
		        day.setViewAdapter(dayAdapter);
		        int curDay = Math.min(maxDays, day.getCurrentItem() + 1);
		        day.setCurrentItem(curDay - 1, true);
		        model.day=curDay;

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
        
        editText=(EditText)layout.findViewById(R.id.edit_date);
        editText.setInputType(InputType.TYPE_NULL);
        editText.setText(getDateString());
		return layout;
	}
	
	class DateModel{
		int year,month,day,hour,minute;
	}
	DateModel model;
	EditText editText;
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
		return model.year+"年 "+model.month+"月 "+model.day+"日 "+model.hour+"时 "+model.minute+"分 ";
	}
}
