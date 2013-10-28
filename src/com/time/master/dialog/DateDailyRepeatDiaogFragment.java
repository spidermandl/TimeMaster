package com.time.master.dialog;

import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.time.master.R;
import com.time.master.TimeMasterApplication;
import com.time.master.view.BasicTextView;
import com.time.master.wheel.adapters.NumericWheelAdapter;
import com.time.master.wheel.adapters.SecondNumericWheelAdapter;
import com.time.master.wheel.adapters.TimeNumericWheelAdapter;
import com.time.master.wheel.widget.OnWheelClickedListener;
import com.time.master.wheel.widget.OnWheelScrollListener;
import com.time.master.wheel.widget.TimeWheelView;
import com.time.master.wheel.widget.WheelView;

/**
 * 每日重复dialog
 * 
 * @author xujing
 * 
 */
public class DateDailyRepeatDiaogFragment extends WheelDialogFragment implements
		View.OnClickListener {

	DialogFragment datetopconfirmFragment;

	public static final String TAG = "DateDailyRepeatDiaogFragment";

	private Day aboutDay;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Light);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		setDialogStyle();

		aboutDay = new Day();

		HashMap<String, String> cache=TimeMasterApplication.getInstance().getCacheModel().tmpResultsCache;
		/**判断缓存是否写入过*/
		if(cache.containsKey(TAG)){
			aboutDay.day=Integer.parseInt(cache.get(TAG+"day"));
			aboutDay.add_times=Integer.parseInt(cache.get(TAG+"addtimes"));
			aboutDay.day_times=Integer.parseInt(cache.get(TAG+"daytimes"));
			aboutDay.space=Integer.parseInt(cache.get(TAG+"space"));
			aboutDay.whatever=Integer.parseInt(cache.get(TAG+"whatever"));
		}

		View layout = inflater.inflate(R.layout.date_daily_repeat_dialog,container, false);

		timeWheels = (LinearLayout) layout.findViewById(R.id.day_selector_wheel);
		
		int padding = TimeMasterApplication.getInstance().getScreen_W() / 36;
		timeWheels.setPadding(padding, 0, padding, padding);

		confirm = (BasicTextView) layout.findViewById(R.id.date_top_fifth);

		day = (TimeWheelView) layout.findViewById(R.id.day);
		dayAdapter = new NumericWheelAdapter(getActivity(), 0, 100);
		dayAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
		dayAdapter.setItemTextResource(R.id.numeric_text);
		day.setViewAdapter(dayAdapter);
		// day.setBackground(R.drawable.wheel_bg_full);
		day.setCurrentItem(aboutDay.day);
		day.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				aboutDay.day = wheel.getCurrentItem();
				changeTimes();
			}
		});
		day.addClickingListener(clickListener);

		add_times = (TimeWheelView) layout.findViewById(R.id.add_times);
		addtimesAdapter = new NumericWheelAdapter(getActivity(), 0, 100);
		addtimesAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
		addtimesAdapter.setItemTextResource(R.id.numeric_text);
		add_times.setViewAdapter(addtimesAdapter);
		add_times.setCurrentItem(aboutDay.day*aboutDay.day_times);
		add_times.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				aboutDay.add_times = wheel.getCurrentItem();
				changedays();
			}
		});
		add_times.addClickingListener(clickListener);

		day_times = (TimeWheelView) layout.findViewById(R.id.day_times);
		daytimesAdapter = new NumericWheelAdapter(getActivity(), 0, 100);
		daytimesAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
		daytimesAdapter.setItemTextResource(R.id.numeric_text);
		day_times.setViewAdapter(daytimesAdapter);
		// day_times.setBackground(R.drawable.wheel_bg_full);
		day_times.setCurrentItem(aboutDay.day_times);
		day_times.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				aboutDay.day_times =wheel.getCurrentItem();
			}
		});
		day_times.addClickingListener(clickListener);

		space = (TimeWheelView) layout.findViewById(R.id.space);
		spaceAdapter = new TimeNumericWheelAdapter(getActivity(), 0, 24);
		spaceAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
		spaceAdapter.setItemTextResource(R.id.numeric_text);
		spaceAdapter.setSuffix(":");
		space.setViewAdapter(spaceAdapter);
		// space.setBackground(R.drawable.wheel_bg_full);
		space.setCurrentItem(aboutDay.space);
		space.setCyclic(true);
		space.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				aboutDay.space = wheel.getCurrentItem();
			}
		});
		space.addClickingListener(clickListener);

		whatever = (TimeWheelView) layout.findViewById(R.id.whatever);
		whateverAdapter = new SecondNumericWheelAdapter(getActivity(), 0, 11);
		whateverAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
		whateverAdapter.setItemTextResource(R.id.numeric_text);
		whateverAdapter.getItemText(5);
		whatever.setViewAdapter(whateverAdapter);
		// whatever.setBackground(R.drawable.wheel_bg_full);
		whatever.setCurrentItem(aboutDay.whatever);
		whatever.setCyclic(true);
		whatever.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				aboutDay.whatever = wheel.getCurrentItem();

			}
		});
		whatever.addClickingListener(clickListener);

		superInit();
		return layout;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		default:
			break;
		}
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 时间数据模型，天数、总次数、日次数、间隔、确认
	 */
	class Day {
		int day = 0, add_times = 0, day_times = 0, space = 0,whatever = 0;
	}

	LinearLayout timeWheels;
	TimeWheelView day, add_times, day_times, space, whatever;
	NumericWheelAdapter dayAdapter, addtimesAdapter, daytimesAdapter;
	TimeNumericWheelAdapter spaceAdapter;
	SecondNumericWheelAdapter whateverAdapter;
	OnWheelClickedListener clickListener = new OnWheelClickedListener() {
		@Override
		public void onItemClicked(WheelView wheel, int itemIndex) {
			// TODO Auto-generated method stub
			wheel.setCurrentItem(itemIndex, true);

		}
	};

	public void changeTimes() {
		int day_value = day.getCurrentItem();
		int times_value = day_times.getCurrentItem();
		if (day_value * times_value <= 100) {
			add_times.setCurrentItem(day_value * times_value);
		} else {
			add_times.setCurrentItem(add_times.getCurrentItem());
		}
	}

	public void changedays() {
		int add_value = add_times.getCurrentItem();
		int times_value = day_times.getCurrentItem();
		if (times_value != 0) {
			if (add_value % times_value != 0) {
				day.setCurrentItem(add_value / times_value + 1);
			} else {
				day.setCurrentItem(add_value / times_value);
			}
		}
	}

	@Override
	protected void pushConfirm() {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getSelectedString() {
		int second = aboutDay.whatever * 5;
		String sec = null;
		if (second < 10) {
			sec = "0" + second;
		} else {
			sec = "" + second;
		}
        HashMap<String, String> cache=TimeMasterApplication.getInstance().getCacheModel().tmpResultsCache;
        cache.put(TAG,"每日" + (aboutDay.day_times) + "次\n间隔"+ aboutDay.space + ":" + sec);
        cache.put(TAG+"day",String.valueOf(aboutDay.day));
        cache.put(TAG+"addtimes",String.valueOf(aboutDay.add_times));
        cache.put(TAG+"daytimes",String.valueOf(aboutDay.day_times));
		cache.put(TAG+"space",String.valueOf(aboutDay.space));
		cache.put(TAG+"whatever",String.valueOf(aboutDay.whatever));


		return null;
	}



}
