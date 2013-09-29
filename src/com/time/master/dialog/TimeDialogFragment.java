package com.time.master.dialog;

import java.util.Calendar;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.time.master.R;
import com.time.master.wheel.adapters.NumericWheelAdapter;
import com.time.master.wheel.widget.OnWheelClickedListener;
import com.time.master.wheel.widget.OnWheelScrollListener;
import com.time.master.wheel.widget.UIWheelView;
import com.time.master.wheel.widget.WheelView;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.time.master.R;
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
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME,android.R.style.Theme_Light);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		/****************************************************
		 * 设置对话框属性，高度、宽度、动画、背景
		 ****************************************************/
		getDialog().setCanceledOnTouchOutside(true);//点击dialog以外区域，关闭dialog
        Window window = getDialog().getWindow();
        window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置  
        window.setWindowAnimations(R.style.wheelAnimation);  //添加动画 
        WindowManager.LayoutParams para=(WindowManager.LayoutParams)window.getAttributes();
        para.height=LayoutParams.WRAP_CONTENT;
        para.width=LayoutParams.MATCH_PARENT;
        window.setAttributes(para);
        window.clearFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND | WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        
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
		        dayAdapter=new NumericWheelAdapter(TimeDialogFragment.this.getActivity(), 1, maxDays);
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
		return model.year+"年 "+model.month+"月 "+model.day+"日 "+model.hour+"时 "+model.minute+"分 ";
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
			this.dismiss();
			showDialog(new WorldTimeDialogFragment());
			
			break;

		default:
			break;
		}
	}
    void showDialog(DialogFragment dialogFragment) {  
    	
        // DialogFragment.show() will take care of adding the fragment  
        // in a transaction.  We also want to remove any currently showing  
        // dialog, so make our own transaction and take care of that here.  
        FragmentTransaction ft = getFragmentManager().beginTransaction();  
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");  
        if (prev != null) {  
            ft.remove(prev);  
        }  
        ft.addToBackStack(null);  
  
        // Create and show the dialog. 
        dialogFragment.show(ft, "dialog");  
    }

}
