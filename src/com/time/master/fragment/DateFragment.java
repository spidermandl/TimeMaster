package com.time.master.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;

import com.time.master.R;
import com.time.master.dialog.HumanDialogFragment;
import com.time.master.dialog.LocationDialogFragment;
import com.time.master.dialog.TimeDialogFragment;
import com.time.master.dialog.WheelDialogFragment;
import com.time.master.interfacer.WheelResultInterface;
import com.time.master.view.BasicEditText;

import com.time.master.view.BasicTextView;

/**
 * "日"面板
 * 
 * @author duanlei
 * 
 */
public class DateFragment extends Fragment implements OnTouchListener,
		OnClickListener {

	WheelDialogFragment dateFragment, locationFragment, humanFragment;
	BasicEditText dateSelector, locationSelector, humanSelector,
			lengthSelector;
	BasicTextView previousClick;
	private Handler stepTimeHandler;
	private Runnable mTicker;
	long startTime = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View layout = inflater.inflate(R.layout.date_layout, container, false);

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

		lengthSelector = (BasicEditText) layout.findViewById(R.id.plan_length);

		previousClick = (BasicTextView) layout.findViewById(R.id.plan_previous);
		previousClick.setOnClickListener(this);

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
			default:
				break;
			}
		}
		return false;
	}

	boolean flag = true;

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.plan_previous:

			if (flag) {
				flag = false;
				lengthSelector.setText("00:00:00");
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
				stepTimeHandler.removeCallbacks(mTicker);
			}
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

}
