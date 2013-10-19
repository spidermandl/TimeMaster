package com.time.master.fragment;	

<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
=======
import com.time.master.R;
import com.time.master.activity.FrameActivity;

import com.time.master.fragment.date.DateAddFragment;
import com.time.master.fragment.date.DateSportFragment;
import com.time.master.fragment.date.DateHabbitFragment;

import com.time.master.view.BasicTextView;
import com.time.master.view.BasicViewGroup;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.annotation.SuppressLint;
import android.graphics.Color;
>>>>>>> c6668860d3c549eb3fbe02ed695310be2cf65344
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
<<<<<<< HEAD
import android.view.ViewGroup;

import com.time.master.R;
import com.time.master.dialog.HumanDialogFragment;
import com.time.master.dialog.LocationDialogFragment;
import com.time.master.dialog.TimeDialogFragment;
import com.time.master.dialog.WheelDialogFragment;
import com.time.master.interfacer.WheelResultInterface;
import com.time.master.view.BasicEditText;
import com.time.master.view.BasicTextView;
=======
import android.webkit.WebView.FindListener;

>>>>>>> c6668860d3c549eb3fbe02ed695310be2cf65344
/**
 * "日"面板
 * 
 * @author duanlei
 * 
 */
<<<<<<< HEAD
public class DateFragment extends Fragment implements OnTouchListener,OnClickListener{

	WheelDialogFragment dateFragment, locationFragment, humanFragment;
	BasicEditText dateSelector,locationSelector,humanSelector,lengthSelector,endSelector;
	BasicTextView previousClick,plan_previou;
	private Handler stepTimeHandler;
	private Runnable mTicker;
	long startTime=0;
	
	Context context;
	//int ihour , imin , isec;
	
	@Override
=======
public class DateFragment extends Fragment implements android.view.View.OnClickListener {

	@Override	
>>>>>>> c6668860d3c549eb3fbe02ed695310be2cf65344
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View layout = inflater.inflate(R.layout.date_layout, container, false);
		BasicViewGroup viewGroup=(BasicViewGroup)layout.findViewById(R.id.date_front_page);
		BasicTextView add=(BasicTextView)layout.findViewById(R.id.main_page_add);
		BasicTextView fitness=(BasicTextView)layout.findViewById(R.id.main_page_fitness);
		BasicTextView walk=(BasicTextView)layout.findViewById(R.id.main_page_walk);

<<<<<<< HEAD
		dateSelector = (BasicEditText) layout.findViewById(R.id.plan_time_start);
		dateSelector.setInputType(InputType.TYPE_NULL);
		dateSelector.setOnTouchListener(this);

		locationSelector = (BasicEditText) layout.findViewById(R.id.plan_location);
		locationSelector.setInputType(InputType.TYPE_NULL);
		locationSelector.setOnTouchListener(this);

		humanSelector = (BasicEditText) layout.findViewById(R.id.plan_human);
		humanSelector.setInputType(InputType.TYPE_NULL);
		humanSelector.setOnTouchListener(this);
		
		lengthSelector = (BasicEditText) layout.findViewById(R.id.plan_length);
		
		previousClick = (BasicTextView) layout.findViewById(R.id.plan_previous);
		previousClick.setOnClickListener(this);
		
		plan_previou=(BasicTextView)layout.findViewById(R.id.plan_previou);
		plan_previou.setOnClickListener(this);
		
		endSelector=(BasicEditText)layout.findViewById(R.id.plan_time_end);
		 
		Map<String, Object> map=this.save(context);
		endSelector.setText((map.get("end")).toString());
		dateSelector.setText((map.get("now")).toString());
		lengthSelector.setText((map.get("t")).toString());
//			
		
		
=======
		viewGroup.setOnClickListener(this);
		add.setOnClickListener(this);
		fitness.setOnClickListener(this);
		walk.setOnClickListener(this);
>>>>>>> c6668860d3c549eb3fbe02ed695310be2cf65344
		return layout;

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		FrameActivity activity=(FrameActivity)getActivity();
		Class T;
		switch (v.getId()) {
		case R.id.main_page_add:
			T=DateAddFragment.class;
			activity.showNext(R.id.date_fragment,T, R.layout.date_add_page);
			break;
		case R.id.main_page_fitness:
			T=DateSportFragment.class;
			activity.showNext(R.id.date_fragment,T, R.layout.date_sports_page);
			break;
		case R.id.main_page_walk:
			T=DateHabbitFragment.class;
			activity.showNext(R.id.date_fragment,T, R.layout.date_habbit_page);
			break;
		default:

			break;
		}
<<<<<<< HEAD
		return false;
	}
	boolean flag=true;
	@Override
	public void onClick(View v) {
		
		switch(v.getId()){
		case R.id.plan_previous:
//			lengthSelector.setText(ihour+"小时"+imin+"分"+isec+"秒");
//			isec++;
//			if(isec==60){
//				imin+=1;
//				isec=0;
//			}
//			if(imin==60){
//				ihour+=1;
//				imin=0;
//			}
//			if(ihour==24){
//				ihour=0;
//			}
//			
			if(flag){
				flag=false;
			lengthSelector.setText("00:00:00");
			previousClick.setText("结束");
			stepTimeHandler = new Handler();
			startTime = System.currentTimeMillis();
			mTicker = new Runnable(){
				public void run(){
					String content = showTimeCount(System.currentTimeMillis()-startTime);
					lengthSelector.setText(content);
					
					long now = SystemClock.uptimeMillis();
					long next = now + (1000- now%1000);
					stepTimeHandler.postAtTime(mTicker, next);
				}
			};
			mTicker.run();
			}else{
				flag=true;
				previousClick.setText("继续");
				stepTimeHandler.removeCallbacks(mTicker);
				String text=addTime(System.currentTimeMillis()-startTime);
				endSelector.setText(text);
			}
			break;
		case R.id.plan_previou:
			//plan_previou.setText("nihao");
			FileOutputStream out=null;
			File file=new File(this.getActivity().getFilesDir(),"Date.txt");
			
			try {
				out=new FileOutputStream(file);
				String t=lengthSelector.getText().toString();
				String now=dateSelector.getText().toString();
				String end= endSelector.getText().toString(); 
				out.write((now+";"+t+";"+end).getBytes());
				
				save(context);
				}
			 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		
		
	}
	public String showTimeCount(long time){
		if(time>=360000000){
			return "00:00:00";
		}
		String timeCount="";
		long hourc = time/3600000;
		String hour = "0"+hourc;
		hour = hour.substring(hour.length()-2, hour.length());
		
		
		long minutec = (time-hourc*360000)/(60000);
		String minute="0"+minutec;
		minute = minute.substring(minute.length()-2, minute.length());
		
		
		long secc = (time-hourc*3600000-minutec*60000)/1000;
		String sec = "0"+secc;
		sec = sec.substring(sec.length()-2, sec.length());
		
		timeCount = hour +":"+ minute +":"+ sec;
		
		return timeCount;
		
	}
	//时间和
	Calendar calendar;
	public String addTime(long time){
		String timeAdd="";
		/*
		 *1. dateSelector 得时间
		 *2. 时间的年，月，日，时，分，秒
		 *3. 条件判断;相加进位
		 *4. 得到end内容
		 */
	    //
		calendar=Calendar.getInstance();
		long year=calendar.get(Calendar.YEAR);
		long month =calendar.get(Calendar.MONTH);
		long day = calendar.get(Calendar.DAY_OF_MONTH);
		long hour =calendar.get(Calendar.HOUR_OF_DAY);
		long min = calendar.get(Calendar.MINUTE);
		String res=year+"-"+month+"-"+day+"-"+hour+":"+min;
		dateSelector.setText(res);
		
		
		long hourc = time/3600000;
		String hou = "0"+hourc;
		hou = hou.substring(hou.length()-2, hou.length());
		
		
		long minutec = (time-hourc*360000)/(60000);
		String minute="0"+minutec;
		minute = minute.substring(minute.length()-2, minute.length());
		
		
		long secc = (time-hourc*3600000-minutec*60000)/1000;
		String sec = "0"+secc;
		sec = sec.substring(sec.length()-2, sec.length());
		
		long endMin =min+minutec;
		long endHour=hourc+hour;
		long endDay =day;
		long endMon=month;
		long endYear=year;
		while(true){
		if(secc==30){
			endMin++;
		}
		timeAdd=endYear+"-"+endMon+"-"+endDay+"-"+endHour+":"+endMin;
		return timeAdd;
		}
		
	}
	public Map<String,Object> save(Context context){
		FileInputStream in=null;
		Map<String,Object> map=new HashMap<String,Object>();
		BufferedReader bf=null;
		try {
			in=new FileInputStream("/data/data/com.time.master/files/Date.txt");
			bf=new BufferedReader(new InputStreamReader(in));
			String tr=bf.readLine();
			String[] str=tr.split(";");
			map.put("now",str[0]);
			map.put("t",str[1]);
			map.put("end",str[2]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
		
=======

>>>>>>> c6668860d3c549eb3fbe02ed695310be2cf65344
	}
}
