package com.time.master.fragment.date;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.time.master.R;
import com.time.master.activity.FrameActivity;
import com.time.master.dialog.*;
import com.time.master.TimeMasterApplication;
import com.time.master.dialog.DurationTimeDialogFragment;
import com.time.master.dialog.HumanDialogFragment;
import com.time.master.dialog.LocationDialogFragment;
import com.time.master.dialog.RepeatDialogFragment;
import com.time.master.dialog.TimeDialogFragment;
import com.time.master.dialog.WheelDialogFragment;
import com.time.master.interfacer.WheelResultInterface;
import com.time.master.model.CacheModel;
import com.time.master.tool.ChineseCalendar;
import com.time.master.view.BasicEditText;
import com.time.master.view.BasicTextView;

import android.R.string;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.webkit.DateSorter;

/**
 * 日---新增选项界面
 * 
 * @author Desmond
 * 
 */
public class DateDetailCreateFragment extends Fragment implements OnTouchListener,android.view.View.OnClickListener{
	WheelDialogFragment dateFragment, locationFragment, humanFragment,planTimePeroidFragment;
	DialogFragment repeatFragment;
	BasicEditText dateSelector,//开始时间输入框
	              locationSelector,
	              humanSelector,
	              planPeroidSelector,
	              lengthSelector,
	              endSelector;//结束时间输入框

	BasicTextView 	startClick,//开始按钮
	                tvdate, // 日期 /倒计 按钮
			        tvduration,// 占用/期间 按钮
                    dateRepeat,//重复按钮
                    dateWarning;//提醒按钮

	private ChineseCalendar startChineseDate,//开始时间
	                        endChineseDate;//结束时间
	
	private Handler countTimeHandler;//计时handler
	private Runnable mTicker;//计时runnable
	
	HashMap<Integer, Boolean> viewStatus = new HashMap<Integer, Boolean>();


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View layout = inflater.inflate(R.layout.date_detail_create_page, container, false);
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
		
		endSelector = (BasicEditText) layout.findViewById(R.id.plan_time_end);
		endSelector.setInputType(InputType.TYPE_NULL);
		endSelector.setOnTouchListener(this);
		
		startClick = (BasicTextView) layout.findViewById(R.id.plan_start);
		startClick.setOnClickListener(this);


		planPeroidSelector=(BasicEditText)layout.findViewById(R.id.plan_length);
		planPeroidSelector.setInputType(InputType.TYPE_NULL);
		planPeroidSelector.setOnTouchListener(this);
		
		dateRepeat=(BasicTextView)layout.findViewById(R.id.plan_repeat);

		dateRepeat.setOnClickListener(this);
		
		dateWarning=(BasicTextView)layout.findViewById(R.id.plan_warning);
		dateWarning.setOnClickListener(this);

		tvdate = (BasicTextView) layout.findViewById(R.id.plan_model);
		tvdate.setOnClickListener(this);

		viewStatus.put(tvdate.getId(), false);

		// tvdate.setBackgroundColor(R.color.dateforcolor);
		String dateString = (String) getText(R.string.date_layout_plan_model_1);
		SpannableStringBuilder datestyle = new SpannableStringBuilder(
				dateString);
		datestyle.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 3,
				Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
		datestyle.setSpan(new ForegroundColorSpan(Color.WHITE), 3, 5,
				Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
		tvdate.setText(datestyle);
		
		tvduration = (BasicTextView) layout.findViewById(R.id.plan_time_period);
		tvduration.setOnClickListener(this);
		viewStatus.put(tvduration.getId(), false);
		// tvduration.setBackgroundColor(R.color.dateforcolor);
		String durationString = (String) getText(R.string.date_plan_time_period_1);
		SpannableStringBuilder durationstyle = new SpannableStringBuilder(
				durationString);
		durationstyle.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 3,
				Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
		durationstyle.setSpan(new ForegroundColorSpan(Color.WHITE), 3, 5,
				Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
		tvduration.setText(durationstyle);
		return layout;
	}

	void showDialog(DialogFragment dialogFragment) {

		// DialogFragment.show() will take care of adding the fragment
		// in a transaction. We also want to remove any currently showing
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

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			switch (v.getId()) {
			case R.id.plan_time_start:
				if (dateFragment == null) {
					dateFragment = new TimeDialogFragment();
				}
				/** 设定获取滚轮内容接口 */
				dateFragment.setWheelInterface(new WheelResultInterface() {

					@Override
					public void getResult(String result) {
						dateSelector.setText(result);
						CacheModel model=TimeMasterApplication.getInstance().getCacheModel();
						startChineseDate=model.currentTime;
						model.startTime=startChineseDate;
					}
				});
				showDialog(dateFragment);
				break;
			case R.id.plan_time_end:
				if (dateFragment == null) {
					dateFragment = new TimeDialogFragment();
				}
				/** 设定获取滚轮内容接口 */
				dateFragment.setWheelInterface(new WheelResultInterface() {

					@Override
					public void getResult(String result) {
						endSelector.setText(result);
						CacheModel model=TimeMasterApplication.getInstance().getCacheModel();
						endChineseDate=model.currentTime;
						model.endTime=endChineseDate;
					}
				});
				showDialog(dateFragment);
				break;
			case R.id.plan_location:
				if (locationFragment == null) {
					locationFragment = new LocationDialogFragment();
					/** 设定获取滚轮内容接口 */
					locationFragment
							.setWheelInterface(new WheelResultInterface() {

								@Override
								public void getResult(String result) {
									locationSelector.setText(result);
								}
							});
				}
				showDialog(locationFragment);
				break;
			case R.id.plan_human:
				if (humanFragment == null) {
					humanFragment = new HumanDialogFragment();
					/** 设定获取滚轮内容接口 */
					humanFragment.setWheelInterface(new WheelResultInterface() {

						@Override
						public void getResult(String result) {
							humanSelector.setText(result);
						}
					});
				}
				showDialog(humanFragment);
				break;
			case R.id.plan_length:
				if(planTimePeroidFragment==null){
					planTimePeroidFragment=new DurationTimeDialogFragment();
					planTimePeroidFragment.setWheelInterface(new WheelResultInterface() {
						
						@Override
						public void getResult(String result) {
							// TODO Auto-generated method stub
							planPeroidSelector.setText(result);
						}
					});
				}
				showDialog(planTimePeroidFragment);
			default:
				break;
			}
		}
		return false;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		Class T;
		FrameActivity activity=(FrameActivity)getActivity();
		switch (view.getId()) {
		case R.id.plan_repeat:
			repeatFragment = new RepeatDialogFragment();
			repeatFragment.setShowsDialog(true);
			showDialog(repeatFragment);
			break;
		case R.id.plan_warning:
//			warningFragment=new DateWarningFragment();
			T=DateWarningFragment.class;
			activity.showNext(this.getId(),T, R.layout.date_warning);			
			break;
		case R.id.plan_model:
			CacheModel model=TimeMasterApplication.getInstance().getCacheModel();
			if (viewStatus.get(R.id.plan_model)) {
				viewStatus.put(R.id.plan_model, false);
				dateSelector.setText(getChineseDateString(model.startTime));
				endSelector.setText(getChineseDateString(model.endTime));
			} else {
				viewStatus.put(R.id.plan_model, true);
				dateSelector.setText(getCountdownDateString(model.startTime));
				endSelector.setText(getCountdownDateString(model.endTime));
			}

			break;
		case R.id.plan_time_period:
			if (viewStatus.get(R.id.plan_time_period)) {

				viewStatus.put(R.id.plan_time_period, false);
			} else {
				viewStatus.put(R.id.plan_time_period, true);
			}
//			else {
//				viewStatus.put(R.id.plan_time_period,true);
//
//			}
			break;
		case R.id.plan_start://开始计时按钮
			if(countTimeHandler==null){
				/**初始化计时handler*/
				countTimeHandler=new Handler(){
					public void handleMessage(android.os.Message msg) {
						switch(msg.what){
						case 1://计时状态
							lengthSelector.setText((String)msg.obj);
							mTicker.run();
							break;
						case 2://禁止状态
							countTimeHandler.removeCallbacks(mTicker);
							break;
						}
					};
				};
			}
			if(mTicker==null){
				/**初始化计时runnable*/
				mTicker = new Runnable() {
					public void run() {
						CacheModel m=TimeMasterApplication.getInstance().getCacheModel();
						m.tickingTime+=1000L;
						countTimeHandler.postDelayed(mTicker, 1000L);
						lengthSelector.setText(getTimeGap(m.tickingTime,true));
//						Message msg=new Message();
//						msg.what=1;
//						msg.obj=getTimeGap(m.tickingTime,true);
//						countTimeHandler.sendMessageDelayed(msg, 1000L);
					}
				};
			}
			model=TimeMasterApplication.getInstance().getCacheModel();
			if(model.startTime==null)
				model.startTime=new ChineseCalendar(new Date());
			if(dateSelector.getText().toString()==null||dateSelector.getText().toString().equals("")){//开始按钮为空
				dateSelector.setText(getChineseDateString(model.startTime));
			}
			BasicTextView v=(BasicTextView)view;
			switch (v.getStatus()){
			case 0:
				v.setStatus(1);
				startClick.setText("结束");
				startClick.setBackgroundColor(0xFFFF0000);
				mTicker.run();
				break;
			case 1://停止计时
				v.setStatus(2);
				startClick.setText("继续");
				startClick.setBackgroundColor(0xFF00FF00);
				countTimeHandler.sendEmptyMessage(2);
				
				if(model.endTime==null||(model.startTime.getTimeInMillis()+model.tickingTime)>model.endTime.getTimeInMillis()){
					model.endTime=new ChineseCalendar(new Date(model.startTime.getTimeInMillis()+model.tickingTime));
					endSelector.setText(getChineseDateString(model.endTime));
				}else{
					if(endSelector.getText().toString()==null||endSelector.getText().toString().equals("")){
						endSelector.setText(getChineseDateString(model.endTime));
					}
				}
				break;
			case 2:
			    v.setStatus(1);
			    startClick.setText("结束");
			    startClick.setBackgroundColor(0xFFFF0000);
			    mTicker.run();
			    break;
			default:
				v.setStatus(0);
				break;
			}
			break;
		}
	}
	
	private String getChineseDateString(ChineseCalendar date) {
		if(date==null)
			date=new ChineseCalendar(new Date());
		int minute=date.get(ChineseCalendar.MINUTE);
		return date.get(ChineseCalendar.YEAR)+"/"
	            +(date.get(ChineseCalendar.MONTH)+1)+"/"
				+date.get(ChineseCalendar.DAY_OF_MONTH)
				+"  "+date.get(ChineseCalendar.HOUR_OF_DAY)+":"
				+(minute<10?"0"+minute:minute);
	}
	
	private String getCountdownDateString(ChineseCalendar date){
		if(date==null)
			return "0天  00:00";
		else{
			StringBuffer stringB=new StringBuffer();
			long now=new Date().getTime(),
			future=date.getTime().getTime(),
			between=future-now;
			if(between<0)
				stringB.append("-");
			between=between>0?between:-between;
	        stringB.append(getTimeGap(between,false));
	        return stringB.toString();
		}
	}
	
	private String getTimeGap(long diff,boolean hasSeconds){
		StringBuffer stringB=new StringBuffer();
		long day = diff / (24 * 60 * 60 * 1000);
        long hour = (diff / (60 * 60 * 1000) - day * 24);
        long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long seconds=((diff / (1000)) - day * 24 * 60 * 60 - hour * 60 * 60-min*60);
        //day + "天" + hour + "小时" + min + "分" + s + "秒" + ms;
        stringB.append(day).append("天  ").append(hour<10?"0"+hour:hour).append(":").append(min<10?"0"+min:min);
        if(hasSeconds)
        	stringB.append(":").append(seconds<10?"0"+seconds:seconds);
        return stringB.toString();
	}
}


