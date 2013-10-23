package com.time.master.dialog;

import java.util.Calendar;

import android.R.color;
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

import com.time.master.R;
import com.time.master.dialog.TimeDialogFragment.DateModel;
import com.time.master.view.BasicTextView;


public class SelectDayFragment  extends DialogFragment implements OnClickListener{
	public static final String tag="RepeatDialogFragment";
	private BasicTextView btvup;
	private BasicTextView btvdown;
	private BasicTextView cumonth;
	private BasicTextView  thirtyone;
	private BasicTextView  thirty;
	private BasicTextView  tweentynine;
	Calendar calendar;
	int year,month,day,maxDay;
	static private final String[] months={"一","二","三","四","五","六","七","八","九","十","十一","十二"}; 
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setStyle(DialogFragment.STYLE_NO_FRAME,android.R.style.Theme_Light);
		super.onCreate(savedInstanceState);
		
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View layout=inflater.inflate(R.layout.repeat2, container, false);
		cumonth=(BasicTextView)layout.findViewById(R.id.yue);
		thirtyone=(BasicTextView )layout.findViewById(R.id.three_one);
		thirty=(BasicTextView )layout.findViewById(R.id.three_zero);
		tweentynine=(BasicTextView )layout.findViewById(R.id.two_nine);
		
		
		calendar = Calendar.getInstance();
		year=calendar.get(Calendar.YEAR);
	    month=calendar.get(Calendar.MONTH)+1;
	    day=calendar.get(Calendar.DAY_OF_MONTH);
	    maxDay=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	    
	    cumonth.setText(months[month-1]+"月");
	   
	    btvup=(BasicTextView)layout.findViewById(R.id.shang);
	    btvup.setOnClickListener(this);
	    
	    btvdown=(BasicTextView)layout.findViewById(R.id.xia);
	    btvdown.setOnClickListener(this);
	    changeDay(31-maxDay);
	    
		getDialog().setCanceledOnTouchOutside(true);
		Window window=getDialog().getWindow();
		window.setGravity(Gravity.BOTTOM);
		WindowManager.LayoutParams para=(WindowManager.LayoutParams)window.getAttributes();
		para.height=LayoutParams.WRAP_CONTENT;
		para.width=LayoutParams.MATCH_PARENT;
		window.setAttributes(para);
		window.clearFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND | WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		
		
		return layout;
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.shang:
			
			month++;
			if(month>12){month=1;}
			calendar.set(year, month-1, day);
			maxDay=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			changeDay(31-maxDay);
		  
			cumonth.setText(months[month-1]+"月");
			
				
				
			break;
			
		case R.id.xia:
			month--;
			if(month<1)
			{month=12;}
			calendar.set(year, month-1, day);
			maxDay=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			changeDay(31-maxDay);
			cumonth.setText(months[month-1]+"月");
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
	
}
