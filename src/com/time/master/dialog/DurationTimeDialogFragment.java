package com.time.master.dialog;

import java.util.Calendar;

import com.time.master.R;
import com.time.master.wheel.adapters.NumericWheelAdapter;
import com.time.master.wheel.widget.OnWheelClickedListener;
import com.time.master.wheel.widget.OnWheelScrollListener;
import com.time.master.wheel.widget.UIWheelView;
import com.time.master.wheel.widget.WheelView;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * 持续时间选择器
 * @author xx
 */
public class DurationTimeDialogFragment extends WheelDialogFragment{
	
	
	public static final String TAG="DurationTimeDialogFragment";
	DateModel model;
	Calendar calendar;
	UIWheelView year,month,day,hour,minute;
	NumericWheelAdapter yearAdapter,monthAdapter,dayAdapter,hourAdapter,minAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Light);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setDialogStyle();
		model=new DateModel();
		calendar=Calendar.getInstance();
		model.day=calendar.get(Calendar.DAY_OF_MONTH);
		model.hour=calendar.get(Calendar.HOUR_OF_DAY);
		model.minute=calendar.get(Calendar.MINUTE);
		
		View layout=inflater.inflate(R.layout.timeduration_wheel_layout, container, false);
		
		editText=(EditText)layout.findViewById(R.id.duration_edit_date);
        confirm =(TextView)layout.findViewById(R.id.duration_time_confirm);
		
        day = (UIWheelView) layout.findViewById(R.id.duration_line2);
        dayAdapter = new NumericWheelAdapter(this.getActivity(), 0,365);
        dayAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
        dayAdapter.setItemTextResource(R.id.numeric_text);
        day.setViewAdapter(dayAdapter);
        day.setBackground(R.drawable.wheel_bg_full);
        day.setRightLineWidth(6);
        day.setCyclic(false);
        day.setCurrentItem(0);
        day.addScrollingListener(new OnWheelScrollListener() {
			
			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onScrollingFinished(WheelView wheel) {
				model.day=wheel.getCurrentItem()+1;
			//	editText.setText(getDateString());
			}
		});
		day.addClickingListener(clickListener);
        
		hour = (UIWheelView) layout.findViewById(R.id.duration_line3);
        hourAdapter = new NumericWheelAdapter(this.getActivity(), 0,23);
        hourAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
        hourAdapter.setItemTextResource(R.id.numeric_text);
        hour.setViewAdapter(hourAdapter);
        hour.setBackground(R.drawable.wheel_bg_full);
        hour.setCyclic(true);
        hour.setCurrentItem(0);
        hour.addScrollingListener(new OnWheelScrollListener() {
			
			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onScrollingFinished(WheelView wheel) {
				model.hour=wheel.getCurrentItem();
				//editText.setText(getDateString());
			}
		});
        hour.addClickingListener(clickListener);
		
        minute = (UIWheelView) layout.findViewById(R.id.duration_line4);
        minAdapter = new NumericWheelAdapter(this.getActivity(), 0, 59, "%02d");
        minAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
        minAdapter.setItemTextResource(R.id.numeric_text);
        minute.setViewAdapter(minAdapter);
        minute.setBackground(R.drawable.wheel_bg_full);
        minute.setRightLineWidth(0);
        minute.setCyclic(true);
        minute.setCurrentItem(0);
        minute.addScrollingListener(new OnWheelScrollListener() {
			
			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onScrollingFinished(WheelView wheel) {
				model.minute=wheel.getCurrentItem();
				//editText.setText(getDateString());
			}
		});
        minute.addClickingListener(clickListener);
        
        superInit();
		return layout;
	}
	
	private String getDateString(){
		return model.day+"日 "+model.hour+"时 "+model.minute+"分 ";
	}
	
	
	@Override
	protected String getSelectedString() {
		// TODO Auto-generated method stub
		return getDateString();
	}
	OnWheelClickedListener clickListener=new OnWheelClickedListener() {
		
		@Override
		public void onItemClicked(WheelView wheel, int itemIndex) {
			wheel.setCurrentItem(itemIndex, true);
			
		}
	};
	class DateModel{
		int durationStyle,day,hour,minute,timeStyle;
	}

}
