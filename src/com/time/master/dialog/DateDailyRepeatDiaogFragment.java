package com.time.master.dialog;

import java.util.HashMap;

import android.R.integer;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.time.master.R;
import com.time.master.R.layout;
import com.time.master.TimeMasterApplication;
import com.time.master.view.BasicTextView;
import com.time.master.wheel.adapters.NumericWheelAdapter;
import com.time.master.wheel.adapters.SecondNumericWheelAdapter;
import com.time.master.wheel.adapters.TimeNumericWheelAdapter;
import com.time.master.wheel.widget.OnWheelClickedListener;
import com.time.master.wheel.widget.OnWheelScrollListener;
import com.time.master.wheel.widget.TimeWheelView;
import com.time.master.wheel.widget.WheelView;
import com.time.master.dialog.RepeatDialogFragment;

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
	HashMap<Integer, Boolean> viewStatus = new HashMap<Integer, Boolean>();

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

		View layout = inflater.inflate(R.layout.date_daily_repeat_dialog,
				container, false);

		timeWheels = (LinearLayout) layout
				.findViewById(R.id.day_selector_wheel);
		int padding = TimeMasterApplication.getInstance().getScreen_W() / 36;
		timeWheels.setPadding(padding, 0, padding, padding);

		confirm = (BasicTextView) layout.findViewById(R.id.date_top_fifth);

		day = (TimeWheelView) layout.findViewById(R.id.day);
		dayAdapter = new NumericWheelAdapter(getActivity(), 0, 100);
		dayAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
		dayAdapter.setItemTextResource(R.id.numeric_text);
		day.setCyclic(true);
		day.setViewAdapter(dayAdapter);
		// day.setBackground(R.drawable.wheel_bg_full);
		day.setCurrentItem(0);
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
		add_times.setCurrentItem(0);
		add_times.setCyclic(true);
		add_times.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
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
		day_times.setCurrentItem(0);
		day_times.setCyclic(true);
		day_times.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub
//				String[] s = getInfo();
//				day_times.setCurrentItem(Integer.parseInt(s[0]));
			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				aboutDay.day_times = wheel.getCurrentItem();
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
		space.setCurrentItem(0);
		space.setCyclic(true);
		space.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub
//				String[] s = getInfo();
//				space.setCurrentItem(Integer.parseInt(s[1]));
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
		whatever.setCurrentItem(0);
		whatever.setCyclic(true);
		whatever.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
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

	class Day {
		int day, add_times, day_times, space, whatever;

		public int getDay() {
			return day;
		}

		public void setDay(int day) {
			this.day = day;
		}

		public int getAdd_times() {
			return add_times;
		}

		public void setAdd_times(int add_times) {
			this.add_times = add_times;
		}

		public int getDay_times() {
			return day_times;
		}

		public void setDay_times(int day_times) {
			this.day_times = day_times;
		}

		public int getSpace() {
			return space;
		}

		public void setSpace(int space) {
			this.space = space;
		}

		public int getWhatever() {
			return whatever;
		}

		public void setWhatever(int whatever) {
			this.whatever = whatever;
		}

	}

	/**
	 * 时间数据模型，天数、总次数、日次数、间隔、确认
	 */
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
		if (add_value % times_value != 0) {
			day.setCurrentItem(add_value / times_value + 1);
		} else {
			day.setCurrentItem(add_value / times_value);
		}
	}

	@Override
	protected void pushConfirm() {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getSelectedString() {
		TimeMasterApplication.getInstance().getCacheModel().tmpResultsCache.put(
				TAG, 
				"每日"+(aboutDay.getDay_times())+"次\n间隔"+aboutDay.getSpace()+":"+aboutDay.getWhatever());
		return null;
	}
	
//	public String[] getInfo(){
//		String str=TimeMasterApplication.getInstance().getCacheModel().tmpResultsCache.get(TAG);
//		String[] s=str.split("\n");
//		for(int i=0;i<s.length;i++){
//			System.out.println(s[i]);
//		}
//		return s;
//	}

	@Override
	protected int getSelectedInt() {
		// TODO Auto-generated method stub
		return 0;
	}
}
