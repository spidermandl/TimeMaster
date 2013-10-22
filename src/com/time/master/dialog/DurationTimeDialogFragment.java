package com.time.master.dialog;

import java.util.Calendar;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.time.master.R;
import com.time.master.TimeMasterApplication;
import com.time.master.tool.ChineseCalendar;
import com.time.master.wheel.adapters.ArrayWheelAdapter;
import com.time.master.wheel.adapters.NumericWheelAdapter;
import com.time.master.wheel.widget.OnWheelClickedListener;
import com.time.master.wheel.widget.OnWheelScrollListener;
import com.time.master.wheel.widget.TimeWheelView;
import com.time.master.wheel.widget.UIWheelView;
import com.time.master.wheel.widget.WheelView;

/**
 * 持续时间选择器
 * 
 * @author xianrui
 */
public class DurationTimeDialogFragment extends WheelDialogFragment {

	public static final String TAG = "DurationTimeDialogFragment";
	public static final int TIME_LIST_NUMBER = 7;
	DateModel model;

	private ChineseCalendar chineseCalendar;// 当前选中时间

	Calendar calendar;
	TimeWheelView line1, line2, line3, line4, line5;
	NumericWheelAdapter line2Adapter, line3Adapter, line4Adapter;

	LinearLayout timeWheels;

	ArrayWheelAdapter<String> line1Adapter, line5Adapter;
	static final String[] timeMode = { "提前", "倒计", "持续" };
	static final String[] timeStyles = { "分钟", "小时", "天", "天时", "年月" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Light);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setDialogStyle();
		model = new DateModel();
		calendar = Calendar.getInstance();
		model.year = calendar.get(Calendar.YEAR);
		model.month = calendar.get(Calendar.MONTH);
		model.day = calendar.get(Calendar.DAY_OF_MONTH);
		model.hour = calendar.get(Calendar.HOUR_OF_DAY);
		model.minute = calendar.get(Calendar.MINUTE);

		chineseCalendar = new ChineseCalendar(calendar.getTime());

		View layout = inflater.inflate(R.layout.timeduration_wheel_layout,
				container, false);

		editText = (EditText) layout.findViewById(R.id.duration_edit_date);
		confirm = (TextView) layout.findViewById(R.id.duration_time_confirm);

		timeWheels = (LinearLayout) layout
				.findViewById(R.id.duration_date_selector_wheel);
		int padding = TimeMasterApplication.getInstance().getScreen_W() / 36;
		timeWheels.setPadding(padding, 0, padding, padding);
		mode=(TextView)layout.findViewById(R.id.time_type);
		line1 = (TimeWheelView) layout.findViewById(R.id.duration_line1);
		line1Adapter = new ArrayWheelAdapter<String>(getActivity(), timeMode);
		line1Adapter.setItemResource(R.layout.wheel_nemeric_text_item);
		line1Adapter.setItemTextResource(R.id.numeric_text);
		line1.setVisibleItems(TIME_LIST_NUMBER);
		line1.setViewAdapter(line1Adapter);
		// line1.setBackground(R.drawable.wheel_bg_full);
		line1.setCyclic(false);
		line1.setCurrentItem(5000);

		line1.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				model.year = calendar.get(Calendar.YEAR)
						+ wheel.getCurrentItem() - 5000;
				System.out.println(wheel.getCurrentItem());
				// freshDayWheel();

				editText.setText(getDateString());

			}

		});
		line1.addClickingListener(clickListener);

		line2 = (TimeWheelView) layout.findViewById(R.id.duration_line2);
		line2Adapter = new NumericWheelAdapter(this.getActivity(), 0, 365);
		line2Adapter.setItemResource(R.layout.wheel_nemeric_text_item);
		line2Adapter.setItemTextResource(R.id.numeric_text);

		line1.setVisibleItems(TIME_LIST_NUMBER);
		line2.setViewAdapter(line2Adapter);
		// line2.setBackground(R.drawable.wheel_bg_full);
		// /line2.setRightLineWidth(6);
		line2.setCyclic(false);
		line2.setCurrentItem(5000);
		line2.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				model.day = wheel.getCurrentItem();
				editText.setText(getDateString());
			}
		});
		line2.addClickingListener(clickListener);

		line3 = (TimeWheelView) layout.findViewById(R.id.duration_line3);
		line3Adapter = new NumericWheelAdapter(this.getActivity(), 0, 23,
				"%02d");
		line3Adapter.setItemResource(R.layout.wheel_nemeric_text_item);
		line3Adapter.setItemTextResource(R.id.numeric_text);
		line1.setVisibleItems(TIME_LIST_NUMBER);
		line3Adapter.setSpeicalString("", ":");
		line3.setViewAdapter(line3Adapter);
		// line3.setBackground(R.drawable.wheel_bg_full);
		// line3.setCyclic(true);
		line3.setCurrentItem(5000);
		line3.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				model.hour = wheel.getCurrentItem();
				editText.setText(getDateString());
			}
		});
		line3.addClickingListener(clickListener);

		line4 = (TimeWheelView) layout.findViewById(R.id.duration_line4);
		line4Adapter = new NumericWheelAdapter(this.getActivity(), 0, 59,
				"%02d");
		line4Adapter.setItemResource(R.layout.wheel_nemeric_text_item);
		line4Adapter.setItemTextResource(R.id.numeric_text);
		line1.setVisibleItems(TIME_LIST_NUMBER);
		// line4Adapter.setTimeInterval(5);
		line4.setViewAdapter(line4Adapter);
		// line4.setBackground(R.drawable.wheel_bg_full);
		line4.setCyclic(true);
		line4.setCurrentItem(0);
		line4.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				model.minute = wheel.getCurrentItem();
				editText.setText(getDateString());
			}
		});
		line4.addClickingListener(clickListener);

		line5Adapter = new ArrayWheelAdapter<String>(getActivity(), timeStyles);
		line5Adapter.setItemResource(R.layout.wheel_nemeric_text_item);
		line5Adapter.setItemTextResource(R.id.numeric_text);
		line1.setVisibleItems(TIME_LIST_NUMBER);
		line5 = (TimeWheelView) layout.findViewById(R.id.duration_line5);
		line5.setViewAdapter(line5Adapter);
		// line5.setBackground(R.drawable.wheel_bg_full);
		line5.setCyclic(false);
		line5.setCurrentItem(3);
		line5.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				changeStyle(wheel.getCurrentItem());
			}
		});
		line5.addClickingListener(clickListener);

		superInit();
		return layout;
	}

	private String getDateString() {
		return model.day + "/ " + model.hour + "/" + model.minute;
	}

	@Override
	protected String getSelectedString() {
		// TODO Auto-generated method stub
		return getDateString();
	}

	OnWheelClickedListener clickListener = new OnWheelClickedListener() {

		@Override
		public void onItemClicked(WheelView wheel, int itemIndex) {
			wheel.setCurrentItem(itemIndex, true);

		}
	};

	class DateModel {
		int durationStyle, day, hour, minute, timeStyle, year, month;
	}

	private void changeStyle(int styleNumber) {
		switch (styleNumber) {
		case 0:
			line2.setVisibility(View.GONE);
			line3.setVisibility(View.GONE);
			line4.setVisibility(View.VISIBLE);
			line4.setCyclic(true);
			// setViewLayoutParams(line4, 3.0f);
			line4Adapter = new NumericWheelAdapter(this.getActivity(), 0, 59,
					"%02d");
			line4Adapter.setItemResource(R.layout.wheel_nemeric_text_item);
			line4Adapter.setItemTextResource(R.id.numeric_text);
			line4.setViewAdapter(line4Adapter);
			break;
		case 1:
			line2.setVisibility(View.VISIBLE);
			line3.setVisibility(View.VISIBLE);
			line4.setVisibility(View.GONE);
			line2.setCyclic(false);
			line3.setCyclic(true);
			// setViewLayoutParams(line2, 2.0f);
			// setViewLayoutParams(line3, 1.0f);
			line2Adapter = new NumericWheelAdapter(this.getActivity(), 0, 24);
			line2Adapter.setItemResource(R.layout.wheel_nemeric_text_item);
			line2Adapter.setItemTextResource(R.id.numeric_text);
			line2.setViewAdapter(line2Adapter);
			line3Adapter = new NumericWheelAdapter(this.getActivity(), 0, 9);
			line3Adapter.setItemResource(R.layout.wheel_nemeric_text_item);
			line3Adapter.setItemTextResource(R.id.numeric_text);
			line3Adapter.setSpeicalString(".", "");
			line3.setViewAdapter(line3Adapter);
			break;
		case 2:
			line2.setVisibility(View.VISIBLE);
			line3.setVisibility(View.VISIBLE);
			line4.setVisibility(View.GONE);
			line2.setCyclic(false);
			line3.setCyclic(true);
			// setViewLayoutParams(line2, 2.0f);
			// setViewLayoutParams(line3, 1.0f);
			line2Adapter = new NumericWheelAdapter(this.getActivity(), 0, 100);
			line2Adapter.setItemResource(R.layout.wheel_nemeric_text_item);
			line2Adapter.setItemTextResource(R.id.numeric_text);
			line2.setViewAdapter(line2Adapter);
			line3Adapter = new NumericWheelAdapter(this.getActivity(), 0, 9);
			line3Adapter.setItemResource(R.layout.wheel_nemeric_text_item);
			line3Adapter.setItemTextResource(R.id.numeric_text);
			line3Adapter.setSpeicalString(".", "");
			line3.setViewAdapter(line3Adapter);
			break;
		case 3:
			line2.setVisibility(View.VISIBLE);
			line3.setVisibility(View.VISIBLE);
			line4.setVisibility(View.VISIBLE);
			line2.setCyclic(false);
			line3.setCyclic(true);
			line4.setCyclic(true);
			// setViewLayoutParams(line2, 1.0f);
			// setViewLayoutParams(line3, 1.0f);
			// setViewLayoutParams(line4, 1.0f);
			line2Adapter = new NumericWheelAdapter(this.getActivity(), 0, 365);
			line2Adapter.setItemResource(R.layout.wheel_nemeric_text_item);
			line2Adapter.setItemTextResource(R.id.numeric_text);
			line2.setViewAdapter(line2Adapter);
			line3Adapter = new NumericWheelAdapter(this.getActivity(), 0, 23,
					"%02d");
			line3Adapter.setItemResource(R.layout.wheel_nemeric_text_item);
			line3Adapter.setItemTextResource(R.id.numeric_text);
			line3Adapter.setSpeicalString("", ":");
			line3.setViewAdapter(line3Adapter);
			line4Adapter = new NumericWheelAdapter(this.getActivity(), 0, 59,
					"%02d");
			line4Adapter.setItemResource(R.layout.wheel_nemeric_text_item);
			line4Adapter.setItemTextResource(R.id.numeric_text);
			line4Adapter.setTimeInterval(5);
			line4.setViewAdapter(line4Adapter);
			break;
		case 4:
			line2.setVisibility(View.VISIBLE);
			line3.setVisibility(View.VISIBLE);
			line4.setVisibility(View.VISIBLE);
			line2.setCyclic(false);
			line3.setCyclic(true);
			line4.setCyclic(true);
			// setViewLayoutParams(line2, 1.0f);
			// setViewLayoutParams(line3, 1.0f);
			// setViewLayoutParams(line4, 1.0f);
			line2Adapter = new NumericWheelAdapter(this.getActivity(), 0, 365);
			line2Adapter.setItemResource(R.layout.wheel_nemeric_text_item);
			line2Adapter.setItemTextResource(R.id.numeric_text);
			line2Adapter.setSpeicalString("", "年");
			line2.setViewAdapter(line2Adapter);
			line3Adapter = new NumericWheelAdapter(this.getActivity(), 0, 12);
			line3Adapter.setItemResource(R.layout.wheel_nemeric_text_item);
			line3Adapter.setItemTextResource(R.id.numeric_text);
			line3Adapter.setSpeicalString("", "月");
			line3.setViewAdapter(line3Adapter);
			line4Adapter = new NumericWheelAdapter(this.getActivity(), 0, 32);
			line4Adapter.setItemResource(R.layout.wheel_nemeric_text_item);
			line4Adapter.setItemTextResource(R.id.numeric_text);
			line4Adapter.setSpeicalString("", "日");
			line4.setViewAdapter(line4Adapter);
		default:
			break;
		}
	}

	private void setViewLayoutParams(UIWheelView view, float weight) {
		LayoutParams param = new LinearLayout.LayoutParams(0,
				LayoutParams.MATCH_PARENT, weight);
		view.setLayoutParams(param);
	}

	@Override
	protected void pushConfirm() {
		// TODO Auto-generated method stub

	}

}
