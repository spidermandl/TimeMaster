package com.time.master.dialog;

import java.util.Calendar;

import android.R.layout;
import android.graphics.Color;
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
import com.time.master.view.BasicTextView;
import com.time.master.view.SelectedTextView;

/***
 * 重复选日期界面
 * 
 * @author shaojunmei
 * 
 */
public class SelectDayFragment extends WheelDialogFragment implements
		OnClickListener {

	public static final String tag = "RepeatDialogFragment";
	private BasicTextView btvup;
	private BasicTextView btvdown;
	private BasicTextView cumonth;
	private SelectedTextView thirtyone;
	private SelectedTextView thirty;
	private SelectedTextView tweentynine;
	public  SelectedTextView emonth;
	public SelectedTextView eyear;
	Calendar calendar;
	int year, month, day, maxDay;
	static private final String[] months = { "一", "二", "三", "四", "五", "六", "七",
			"八", "九", "十", "十一", "十二" };

	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Light);
		super.onCreate(savedInstanceState);

	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		setDialogStyle();

		View layout = inflater.inflate(R.layout.dayselected, container, false);
		cumonth = (BasicTextView) layout.findViewById(R.id.yue);
		thirtyone = (SelectedTextView) layout.findViewById(R.id.three_one);
		thirty = (SelectedTextView) layout.findViewById(R.id.three_zero);
		tweentynine =(SelectedTextView) layout.findViewById(R.id.two_nine);
		emonth=(SelectedTextView)layout.findViewById(R.id.emonth);
		eyear=(SelectedTextView)layout.findViewById(R.id.eyear);

		calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH) + 1;
		day = calendar.get(Calendar.DAY_OF_MONTH);
		maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		cumonth.setText(months[month - 1] + "月");

		btvup = (BasicTextView) layout.findViewById(R.id.shang);
		btvup.setOnClickListener(this);

		btvdown = (BasicTextView) layout.findViewById(R.id.xia);
		btvdown.setOnClickListener(this);
		changeDay(31 - maxDay);

		confirm=(BasicTextView)layout.findViewById(R.id.day_selected_conform);
		
		super.superInit();
		return layout;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.shang:

			month++;
			if (month > 12) {
				month = 1;
			}
			calendar.set(year, month - 1, day);
			maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			changeDay(31 - maxDay);

			cumonth.setText(months[month - 1] + "月");

			break;

		case R.id.xia:
			month--;
			if (month < 1) {
				month = 12;
			}
			calendar.set(year, month - 1, day);
			maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			changeDay(31 - maxDay);
			cumonth.setText(months[month - 1] + "月");
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

	public void changedefaultcolor(int fatherdialog) {
		switch (fatherdialog) {
		case 1: //emonth.setBackgroundColor(0xeeff0000);
			break;
		case 2://eyear.setBackgroundColor(0xeeff0000);
			break;

		default:
			break;
		}
	}

	@Override
	protected String getSelectedString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void pushConfirm() {
		// TODO Auto-generated method stub
		
	}

}
