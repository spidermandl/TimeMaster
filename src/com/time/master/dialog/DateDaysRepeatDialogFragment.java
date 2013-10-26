package com.time.master.dialog;

import java.util.Calendar;
import java.util.HashMap;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.time.master.R;
import com.time.master.TimeMasterApplication;
import com.time.master.dialog.DateDailyRepeatDiaogFragment.Day;
import com.time.master.interfacer.WheelResultInterface;
import com.time.master.tool.ChineseCalendar;
import com.time.master.view.BasicTextView;
import com.time.master.wheel.adapters.ArrayWheelAdapter;
import com.time.master.wheel.adapters.NumericWheelAdapter;
import com.time.master.wheel.adapters.TimeNumericWheelAdapter;
import com.time.master.wheel.adapters.TimeNumericWheelAdapter.WeekendTextInterface;
import com.time.master.wheel.widget.OnWheelClickedListener;
import com.time.master.wheel.widget.OnWheelScrollListener;
import com.time.master.wheel.widget.TimeWheelView;
import com.time.master.wheel.widget.WheelView;

/**
 * 天重复dialog
 * 
 * @author梁丽丽
 * 
 */
public class DateDaysRepeatDialogFragment extends WheelDialogFragment implements
		View.OnClickListener {

	DialogFragment datecenterconfirmFragment;

	public static final String TAG = "DateDaysRepeatDialogFragment";
	private DateModel dateModel;
	public static final int TIME_LIST_NUMBER = 7;
	private int dayModel = 0;// 0:滚轮阳历；1：滚轮农历
	private ChineseCalendar chineseCalendar;// 当前选中时间
	private BasicTextView date_center_second;// 更换农阳历

	// date_center_first;确认

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Light);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		setDialogStyle();
		
		viewStatus = savedInstanceState;
		if (viewStatus == null) {
			viewStatus = new Bundle();
		}
		
		model = new DateModel();
		calendar = Calendar.getInstance();
		model.yearc = calendar.get(Calendar.YEAR);
		model.monthc = calendar.get(Calendar.MONTH) + 1;
		model.dayc = calendar.get(Calendar.DAY_OF_MONTH);
		chineseCalendar = new ChineseCalendar(calendar.getTime());
		
		dateModel=new DateModel();
		HashMap<String, Integer> hashmap = getString();

		if (hashmap.isEmpty()) {
			getSelectedString();
			hashmap = getString();
		}
		View layout = inflater.inflate(R.layout.date_days_repeat_dialog,
				container, false);

		timeWheels = (LinearLayout) layout
				.findViewById(R.id.day_center_selector_wheel);
		int padding = TimeMasterApplication.getInstance().getScreen_W() / 36;
		timeWheels.setPadding(padding, 0, padding, padding);

		mode = (TextView) layout.findViewById(R.id.date_center_second);
		mode.setOnClickListener(this);

		

		mode.setText(R.string.date_top_center_lunar_2);
		mode.setBackgroundColor(Color.YELLOW);

		year = (TimeWheelView) layout.findViewById(R.id.yearone);
		yearAdapter = new NumericWheelAdapter(this.getActivity(),
				model.yearc - 5000, model.yearc + 5000);
		yearAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
		yearAdapter.setItemTextResource(R.id.numeric_text);
		year.setVisibleItems(TIME_LIST_NUMBER);
		year.setViewAdapter(yearAdapter);
		year.setCurrentItem(5000);
		year.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				model.yearc = calendar.get(Calendar.YEAR)
						+ wheel.getCurrentItem() - 5000;
				System.out.println(wheel.getCurrentItem());
				freshDayWheel();
			}
		});
		year.addClickingListener(clickListener);

		month = (TimeWheelView) layout.findViewById(R.id.monthone);
		monthAdapter = new NumericWheelAdapter(this.getActivity(), 1, 12);
		monthAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
		monthAdapter.setItemTextResource(R.id.numeric_text);
		month.setVisibleItems(TIME_LIST_NUMBER);
		month.setViewAdapter(monthAdapter);
		month.setCyclic(true);
		month.setCurrentItem(hashmap.get("month"));
		month.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
			
				dateModel.setMonthc( wheel.getCurrentItem()+1);
				if (dayModel == 0) {
					Calendar calendar = Calendar.getInstance();
					calendar.set(Calendar.YEAR, model.yearc);
					calendar.set(Calendar.MONTH, model.monthc - 1);

					int maxDays = calendar
							.getActualMaximum(Calendar.DAY_OF_MONTH);
					dayAdapter = new TimeNumericWheelAdapter(
							DateDaysRepeatDialogFragment.this.getActivity(), 1,
							maxDays);
					dayAdapter
							.setItemResource(R.layout.wheel_nemeric_text_item);
					dayAdapter.setItemTextResource(R.id.numeric_text);

					day.setViewAdapter(dayAdapter);
					int curDay = Math.min(maxDays, day.getCurrentItem() + 1);
					day.setCurrentItem(curDay - 1, true);
					model.dayc = curDay;
					freshDayWheel();
				}
			}
		});
		month.addClickingListener(clickListener);

		day = (TimeWheelView) layout.findViewById(R.id.dayone);
		dayAdapter = new TimeNumericWheelAdapter(this.getActivity(), 1,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		dayAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
		dayAdapter.setItemTextResource(R.id.numeric_text);
		dayAdapter.setTextInterface(textInterface);
		day.setVisibleItems(TIME_LIST_NUMBER);
		day.setViewAdapter(dayAdapter);
		day.setCyclic(true);
		day.setCurrentItem(hashmap.get("day"));
		day.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
			
				dateModel.setDayc( wheel.getCurrentItem());
				freshDayWheel();

			}
		});
		day.addClickingListener(clickListener);

		daynumber = (TimeWheelView) layout.findViewById(R.id.daynumber);
		daynumberAdapter = new TimeNumericWheelAdapter(this.getActivity(), 0,
				100);
		daynumberAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
		daynumberAdapter.setItemTextResource(R.id.numeric_text);
		daynumber.setVisibleItems(TIME_LIST_NUMBER);
		daynumber.setViewAdapter(daynumberAdapter);
		daynumber.setCurrentItem(hashmap.get("daynumber"));
		daynumber.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {

		
				dateModel.setDaynumberc( wheel.getCurrentItem());
				changDay();
			}

		});
		daynumber.addClickingListener(clickListener);

		totalcount = (TimeWheelView) layout.findViewById(R.id.totalcount);
		totalcountAdapter = new NumericWheelAdapter(this.getActivity(), 0, 200);
		totalcountAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
		totalcountAdapter.setItemTextResource(R.id.numeric_text);
		totalcount.setVisibleItems(TIME_LIST_NUMBER);
		totalcount.setViewAdapter(totalcountAdapter);
		totalcount.setCurrentItem(model.totalcountc);
		totalcount.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				model.totalcountc = wheel.getCurrentItem();

			}
		});
		totalcount.addClickingListener(clickListener);

		confirm = (BasicTextView) layout.findViewById(R.id.date_center_first);
		superInit();
		return layout;
	}

	public void changDay() {

		int num = daynumber.getCurrentItem();
		int daynum = (day.getCurrentItem() + num);
		int monthnum = (month.getCurrentItem());
		day.setCurrentItem(daynum);
//		for (int i = 0; i < 5; i++) {
//
//			if (daynum > 30 * i) {
//				month.setCurrentItem(monthnum + i);
//				day.setCurrentItem(daynum - 30);
//			}
//		}

	}

	/**
	 * 时间数据模型，年、月、日、天数、总次数
	 */
	class DateModel {
		private int yearc, monthc, dayc, totalcountc,daynumberc;

		public int getYearc() {
			return yearc;
		}

		public void setYearc(int yearc) {
			this.yearc = yearc;
		}

		public int getMonthc() {
			return monthc;
		}

		public void setMonthc(int monthc) {
			this.monthc = monthc;
		}

		public int getDayc() {
			return dayc;
		}

		public void setDayc(int dayc) {
			this.dayc = dayc;
		}

		public int getTotalcountc() {
			return totalcountc;
		}

		public void setTotalcountc(int totalcountc) {
			this.totalcountc = totalcountc;
		}

		public int getDaynumberc() {
			return daynumberc;
		}

		public void setDaynumberc(int daynumberc) {
			this.daynumberc = daynumberc;
		}

	}

	DateModel model;
	Calendar calendar;
	TimeWheelView year, month, day, daynumber, totalcount;
	NumericWheelAdapter yearAdapter, monthAdapter, totalcountAdapter;
	ArrayWheelAdapter<String> monthArrayWheelAdapter, dayArrayWheelAdapter;
	TimeNumericWheelAdapter dayAdapter, daynumberAdapter;
	LinearLayout timeWheels;

	OnWheelClickedListener clickListener = new OnWheelClickedListener() {

		@Override
		public void onItemClicked(WheelView wheel, int itemIndex) {
			wheel.setCurrentItem(itemIndex, true);

		}
	};

	@Override
	protected void pushConfirm() {

	}

	WeekendTextInterface textInterface = new WeekendTextInterface() {

		@Override
		public void changeText(int index, TextView textView) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(model.yearc, model.monthc - 1, index + 1);
		}
	};

	/** 刷新日滚轮 */
	private void freshDayWheel() {
		if (dayModel == 0) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, model.yearc);
			calendar.set(Calendar.MONTH, model.monthc - 1);

			int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			dayAdapter = new TimeNumericWheelAdapter(
					DateDaysRepeatDialogFragment.this.getActivity(), 1, maxDays);
			dayAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
			dayAdapter.setItemTextResource(R.id.numeric_text);
			dayAdapter.setTextInterface(textInterface);
			day.setViewAdapter(dayAdapter);
			int curDay = Math.min(maxDays, day.getCurrentItem() + 1);
			day.setCurrentItem(curDay - 1, true);
			model.dayc = curDay;
		} else {
			chineseCalendar = new ChineseCalendar(true, model.yearc,
					model.monthc, model.dayc);
			int maxDays = ChineseCalendar.daysInChineseMonth(model.yearc,
					model.monthc);

			String[] chineseDateName = new String[maxDays];
			System.arraycopy(ChineseCalendar.chineseDateNames_1, 0,
					chineseDateName, 0, maxDays);
			ArrayWheelAdapter<String> dayArrayWheelAdapter = new ArrayWheelAdapter<String>(
					getActivity(), chineseDateName);
			dayArrayWheelAdapter
					.setItemResource(R.layout.wheel_nemeric_text_item);
			dayArrayWheelAdapter.setItemTextResource(R.id.numeric_text);
			day.setViewAdapter(dayArrayWheelAdapter);
			int curDay = Math.min(maxDays, day.getCurrentItem() + 1);
			day.setCurrentItem(curDay - 1, true);
			model.dayc = curDay;
		}

	}

	private String getDateString() {

		if (dayModel == 0) {
			chineseCalendar.set(ChineseCalendar.YEAR, model.yearc);
			chineseCalendar.set(ChineseCalendar.MONTH, model.monthc - 1);
			chineseCalendar.set(ChineseCalendar.DAY_OF_MONTH, model.dayc);
			chineseCalendar.set(ChineseCalendar.HOUR_OF_DAY, model.daynumberc);
			chineseCalendar.set(ChineseCalendar.MINUTE, model.totalcountc);
			return chineseCalendar.get(ChineseCalendar.CHINESE_YEAR)
					+ "年"
					+ chineseCalendar.getChinese(ChineseCalendar.CHINESE_MONTH)
					+ chineseCalendar.getChinese(ChineseCalendar.CHINESE_DATE)
					+ model.daynumberc
					+ ":"
					+ (model.totalcountc < 10 ? "0" + model.totalcountc
							: model.totalcountc);
		} else {
			chineseCalendar.set(ChineseCalendar.CHINESE_DATE, model.yearc);
			chineseCalendar.set(ChineseCalendar.CHINESE_MONTH, model.monthc);
			chineseCalendar.set(ChineseCalendar.CHINESE_DATE, model.dayc);
			chineseCalendar.set(ChineseCalendar.HOUR_OF_DAY, model.daynumberc);
			chineseCalendar.set(ChineseCalendar.MINUTE, model.totalcountc);
			return chineseCalendar.get(ChineseCalendar.YEAR)
					+ "/"
					+ (chineseCalendar.get(ChineseCalendar.MONTH) + 1)
					+ "/"
					+ chineseCalendar.get(ChineseCalendar.DAY_OF_MONTH)
					+ "  "
					+ model.daynumberc
					+ ":"
					+ (model.totalcountc < 10 ? "0" + model.totalcountc
							: model.totalcountc);
		}

	}

	/** 滚轮阴阳模式切换 */
	private void changeTimeStyle(int dayModel) {
		if (dayModel == 0) {
			// 阳历滚轮变成阴历滚轮
			chineseCalendar.set(model.yearc, model.monthc - 1, model.dayc);
			model.yearc = chineseCalendar.get(ChineseCalendar.CHINESE_YEAR);
			model.monthc = chineseCalendar.get(ChineseCalendar.CHINESE_MONTH);
			model.dayc = chineseCalendar.get(ChineseCalendar.CHINESE_DATE);
			this.dayModel = 1;

			ArrayWheelAdapter<String> monthArrayWheelAdapter = new ArrayWheelAdapter<String>(
					getActivity(), ChineseCalendar.chineseMonthNames_1);
			monthArrayWheelAdapter
					.setItemResource(R.layout.wheel_nemeric_text_item);
			monthArrayWheelAdapter.setItemTextResource(R.id.numeric_text);
			month.setViewAdapter(monthArrayWheelAdapter);
			month.setCurrentItem(model.monthc - 1);

			ArrayWheelAdapter<String> dayArrayWheelAdapter = new ArrayWheelAdapter<String>(
					getActivity(), ChineseCalendar.chineseDateNames_1);
			dayArrayWheelAdapter
					.setItemResource(R.layout.wheel_nemeric_text_item);
			dayArrayWheelAdapter.setItemTextResource(R.id.numeric_text);
			day.setViewAdapter(dayArrayWheelAdapter);
			day.setCurrentItem(model.dayc - 1);

			mode.setText(R.string.date_top_center_lunar_1);
		} else {
			// 阴历滚轮变成阳历滚轮
			chineseCalendar = new ChineseCalendar(true, model.yearc,
					model.monthc, model.dayc);
			this.dayModel = 0;
			model.yearc = chineseCalendar.get(ChineseCalendar.YEAR);
			model.monthc = (chineseCalendar.get(ChineseCalendar.MONTH) + 1);
			model.dayc = chineseCalendar.get(ChineseCalendar.DATE);
			dayAdapter = new TimeNumericWheelAdapter(this.getActivity(), 1,
					calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			dayAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
			dayAdapter.setItemTextResource(R.id.numeric_text);
			dayAdapter.setTextInterface(textInterface);
			day.setViewAdapter(dayAdapter);
			day.setCurrentItem(model.dayc - 1);
			monthAdapter = new NumericWheelAdapter(this.getActivity(), 1, 12);
			monthAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
			monthAdapter.setItemTextResource(R.id.numeric_text);
			month.setViewAdapter(monthAdapter);
			month.setCurrentItem(model.monthc - 1);

			mode.setText(R.string.date_top_center_lunar_2);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.date_center_second:
			changeTimeStyle(dayModel);
			break;

		case R.id.date_center_first:

			datecenterconfirmFragment = new RepeatDialogFragment1();
			datecenterconfirmFragment.setShowsDialog(true);
			showDialog(datecenterconfirmFragment);

			break;

		default:
			break;
		}
	}
	final static String DAY_NUM = TAG+"_DAY_NUM";
	@Override
	protected String getSelectedString() {
		TimeMasterApplication.getInstance().getCacheModel().tmpResultsCache
				.put(TAG, "重复" + dateModel.getDaynumberc() + "天\n至" + model.yearc + "/"
						+ dateModel.getMonthc() + "/" + dateModel.getDayc());
		TimeMasterApplication.getInstance().getCacheModel().tmpResultCache.put("daynumber",dateModel.getDaynumberc());
//		TimeMasterApplication.getInstance().getCacheModel().tmpResultCache.put("year",dateModel.getYearc());
		TimeMasterApplication.getInstance().getCacheModel().tmpResultCache.put("month",dateModel.getMonthc());
		TimeMasterApplication.getInstance().getCacheModel().tmpResultCache.put("day",dateModel.getDayc());
		
		return null;
	}
	public HashMap<String, Integer> getString() {
		 
		 TimeMasterApplication.getInstance().getCacheModel().tmpResultCache.get("daynumber");
//		 TimeMasterApplication.getInstance().getCacheModel().tmpResultCache.get("year");
		 TimeMasterApplication.getInstance().getCacheModel().tmpResultCache.get("month");
		 TimeMasterApplication.getInstance().getCacheModel().tmpResultCache.get("day");
		 
		return TimeMasterApplication.getInstance().getCacheModel().tmpResultCache;
	}
	

		

	
}
