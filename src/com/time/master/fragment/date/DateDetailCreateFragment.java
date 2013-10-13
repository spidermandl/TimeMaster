package com.time.master.fragment.date;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.time.master.R;
import com.time.master.dialog.*;
import com.time.master.dialog.HumanDialogFragment;
import com.time.master.dialog.LocationDialogFragment;
import com.time.master.dialog.RepeatDialogFragment;
import com.time.master.dialog.TimeDialogFragment;
import com.time.master.dialog.WheelDialogFragment;
import com.time.master.interfacer.WheelResultInterface;
import com.time.master.view.BasicEditText;
import com.time.master.view.BasicTextView;

import android.content.Context;
import android.graphics.Color;
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
import android.view.ViewGroup;
import android.view.View.OnTouchListener;

/**
 * 日---新增选项界面
 * 
 * @author Desmond
 * 
 */

public class DateDetailCreateFragment extends Fragment implements
		OnTouchListener, android.view.View.OnClickListener {
	WheelDialogFragment dateFragment, locationFragment, humanFragment,planTimePeroidFragment;
	DialogFragment repeatFragment;
	BasicEditText dateSelector, locationSelector, humanSelector,planPeroidSelector,
			lengthSelector, endSelector;// 结束日期时间
	BasicTextView previousClick,// 开始按钮
			plan_previou;// 承前按钮

	BasicTextView dateRepeat;
	BasicTextView tvdate, // 日期 /倒计 按钮
			tvduration;// 占用/期间 按钮
	private Handler stepTimeHandler;
	private Runnable mTicker;
	long startTime = 0;
	Context context;

	HashMap<Integer, Boolean> viewStatus = new HashMap<Integer, Boolean>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {


		View layout = inflater.inflate(R.layout.date_detail_create_page,
				container, false);


		dateSelector = (BasicEditText) layout
				.findViewById(R.id.plan_time_start);
		dateSelector.setInputType(InputType.TYPE_NULL);
		dateSelector.setOnTouchListener(this);

		locationSelector = (BasicEditText) layout
				.findViewById(R.id.plan_location);
		locationSelector.setInputType(InputType.TYPE_NULL);
		locationSelector.setOnTouchListener(this);

		humanSelector = (BasicEditText) layout.findViewById(R.id.plan_human);
		humanSelector.setInputType(InputType.TYPE_NULL);
		humanSelector.setOnTouchListener(this);

/**
 * @author
 */
		lengthSelector = (BasicEditText) layout.findViewById(R.id.plan_length);

		endSelector = (BasicEditText) layout.findViewById(R.id.plan_time_end);

		previousClick = (BasicTextView) layout.findViewById(R.id.plan_previous);
		previousClick.setOnClickListener(this);

		plan_previou = (BasicTextView) layout.findViewById(R.id.plan_previou);
		plan_previou.setOnClickListener(this);

		dateRepeat = (BasicTextView) layout.findViewById(R.id.plan_repeat);
	
		planPeroidSelector=(BasicEditText)layout.findViewById(R.id.plan_length);
		planPeroidSelector.setInputType(InputType.TYPE_NULL);
		planPeroidSelector.setOnTouchListener(this);

		dateRepeat.setOnClickListener(this);

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
					/** 设定获取滚轮内容接口 */
					dateFragment.setWheelInterface(new WheelResultInterface() {

						@Override
						public void getResult(String result) {
							dateSelector.setText(result);
						}
					});
				}
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

	boolean flag = true;

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.plan_repeat:
			repeatFragment = new RepeatDialogFragment();
			repeatFragment.setShowsDialog(true);
			showDialog(repeatFragment);
			break;
		case R.id.plan_model:
			if (viewStatus.get(R.id.plan_model)) {
				viewStatus.put(R.id.plan_model, false);

			} else {
				viewStatus.put(R.id.plan_model, true);
			}

			break;
		case R.id.plan_time_period:
			if (viewStatus.get(R.id.plan_time_period)) {
				viewStatus.put(R.id.plan_time_period, false);
			} else {
				viewStatus.put(R.id.plan_time_period, true);
			}
			break; 
		case R.id.plan_previous:

			if (flag) {
				flag = false;
				lengthSelector.setText("00:00:00");
				previousClick.setText("结束");
				stepTimeHandler = new Handler();
				startTime = System.currentTimeMillis();
				mTicker = new Runnable() {
					public void run() {
						String content = showTimeCount(System
								.currentTimeMillis() - startTime);
						lengthSelector.setText(content);

						long now = SystemClock.uptimeMillis();
						long next = now + (1000 - now % 1000);
						stepTimeHandler.postAtTime(mTicker, next);
					}
				};
				mTicker.run();
			} else {
				flag = true;
				previousClick.setText("继续");
				stepTimeHandler.removeCallbacks(mTicker);
				mTicker.run();
				String text = addTime(System.currentTimeMillis() - startTime);
				endSelector.setText(text);
			}
			break;
		case R.id.plan_previou:
//			Map<String, Object> map = this.save(context);
//			endSelector.setText((map.get("end")).toString());
//			dateSelector.setText((map.get("now")).toString());
//			lengthSelector.setText((map.get("t")).toString());
			File file = new File(this.getActivity().getFilesDir(), "Date.txt");

			try {
				FileOutputStream out = new FileOutputStream(file);
				String t = lengthSelector.getText().toString();
				String now = dateSelector.getText().toString();
				String end = endSelector.getText().toString();
				out.write((now + ";" + t + ";" + end).getBytes());

				save(context);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	public String showTimeCount(long time) {
		if (time >= 360000000) {
			return "00:00:00";
		}
		String timeCount = "";
		long hourc = time / 3600000;
		String hour = "0" + hourc;
		hour = hour.substring(hour.length() - 2, hour.length());

		long minutec = (time - hourc * 360000) / (60000);
		String minute = "0" + minutec;
		minute = minute.substring(minute.length() - 2, minute.length());

		long secc = (time - hourc * 3600000 - minutec * 60000) / 1000;
		String sec = "0" + secc;
		sec = sec.substring(sec.length() - 2, sec.length());

		timeCount = hour + ":" + minute + ":" + sec;

		return timeCount;

	}

	// 时间和
	Calendar calendar;

	public String addTime(long time) {
		String timeAdd = "";
		/*
		 * 1. dateSelector 得时间2. 时间的年，月，日，时，分，秒3. 条件判断;相加进位4. 得到end内容
		 */
		//
		calendar = Calendar.getInstance();
		long year = calendar.get(Calendar.YEAR);
		long month = calendar.get(Calendar.MONTH);
		long day = calendar.get(Calendar.DAY_OF_MONTH);
		long hour = calendar.get(Calendar.HOUR_OF_DAY);
		long min = calendar.get(Calendar.MINUTE);
		String res = year + "-" + month + "-" + day + "-" + hour + ":" + min;
		dateSelector.setText(res);

		long hourc = time / 3600000;
		String hou = "0" + hourc;
		hou = hou.substring(hou.length() - 2, hou.length());

		long minutec = (time - hourc * 360000) / (60000);
		String minute = "0" + minutec;
		minute = minute.substring(minute.length() - 2, minute.length());

		long secc = (time - hourc * 3600000 - minutec * 60000) / 1000;
		String sec = "0" + secc;
		sec = sec.substring(sec.length() - 2, sec.length());

		long endMin = min + minutec;
		long endHour = hourc + hour;
		long endDay = day;
		long endMon = month;
		long endYear = year;
		while (true) {
			if (secc == 30) {
				endMin++;
			}
			timeAdd = endYear + "-" + endMon + "-" + endDay + "-" + endHour
					+ ":" + endMin;
			return timeAdd;
		}

	}

	public Map<String, Object> save(Context context) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			FileInputStream in = new FileInputStream(
					"/data/data/com.time.master/files/Date.txt");
		    BufferedReader bf = new BufferedReader(new InputStreamReader(in));
			String tr = bf.readLine();
			String[] str = tr.split(";");
			map.put("now", str[0]);
			map.put("t", str[1]);
			map.put("end", str[2]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;

	}
}
