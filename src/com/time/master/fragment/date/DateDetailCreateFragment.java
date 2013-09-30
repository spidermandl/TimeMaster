package com.time.master.fragment.date;

import java.util.HashMap;

import com.time.master.R;
import com.time.master.dialog.HumanDialogFragment;
import com.time.master.dialog.LocationDialogFragment;
import com.time.master.dialog.RepeatDialogFragment;
import com.time.master.dialog.TimeDialogFragment;
import com.time.master.dialog.WheelDialogFragment;
import com.time.master.interfacer.WheelResultInterface;
import com.time.master.view.BasicEditText;
import com.time.master.view.BasicTextView;

import android.graphics.Color;
import android.os.Bundle;
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
 * @author Desmond
 *
 */
public class DateDetailCreateFragment extends Fragment implements OnTouchListener,android.view.View.OnClickListener{
	WheelDialogFragment dateFragment, locationFragment, humanFragment;
	DialogFragment repeatFragment;
	BasicEditText dateSelector,locationSelector,humanSelector;
	BasicTextView dateRepeat;
	BasicTextView tvdate, //日期 /倒计 按钮
	              tvduration;//占用/期间 按钮
	           
	
	HashMap<Integer, Boolean> viewStatus=new HashMap<Integer, Boolean>();
	
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
		
		dateRepeat=(BasicTextView)layout.findViewById(R.id.plan_repeat);
		dateRepeat.setOnClickListener(this);

		tvdate = (BasicTextView) layout.findViewById(R.id.plan_model);
		tvdate.setOnClickListener(this);
		viewStatus.put(tvdate.getId(), false);
		//tvdate.setBackgroundColor(R.color.dateforcolor);
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
		//tvduration.setBackgroundColor(R.color.dateforcolor);
		String durationString = (String) getText(R.string.date_plan_time_period_1);
		SpannableStringBuilder durationstyle = new SpannableStringBuilder(
				durationString);
		durationstyle.setSpan(new ForegroundColorSpan(Color.BLACK), 0,
				3, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
		durationstyle.setSpan(new ForegroundColorSpan(Color.WHITE), 3,
				5, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
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
				if (dateFragment == null){
					dateFragment = new TimeDialogFragment();
					/**设定获取滚轮内容接口*/
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
				if (locationFragment == null){
					locationFragment = new LocationDialogFragment();
					/**设定获取滚轮内容接口*/
					locationFragment.setWheelInterface(new WheelResultInterface() {
						
						@Override
						public void getResult(String result) {
							locationSelector.setText(result);
						}
					});
				}
				showDialog(locationFragment);
				break;
			case R.id.plan_human:
				if (humanFragment == null){
					humanFragment = new HumanDialogFragment();
					/**设定获取滚轮内容接口*/
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

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.plan_repeat:
			repeatFragment=new RepeatDialogFragment();
			repeatFragment.setShowsDialog(true);
			showDialog(repeatFragment);
			break;
		case R.id.plan_model:
			if (viewStatus.get(R.id.plan_model)) {
				viewStatus.put(R.id.plan_model,false);
				String dateString = (String) getText(R.string.date_layout_plan_model_1);
				SpannableStringBuilder datestyle = new SpannableStringBuilder(
						dateString);
				datestyle.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 3,
						Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
				datestyle.setSpan(new ForegroundColorSpan(Color.WHITE), 3, 5,
						Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
				tvdate.setText(datestyle);
				tvdate.setBackgroundColor(Color.parseColor("#FFACD6FF"));
				//tvdate.setBackgroundColor(R.color.calendar_background);
				
				

			} else {
				viewStatus.put(R.id.plan_model,true);
				String dateString = (String) getText(R.string.date_layout_plan_model_2);
				SpannableStringBuilder datestyle = new SpannableStringBuilder(
						dateString);
				datestyle.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 3,
						Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
				datestyle.setSpan(new ForegroundColorSpan(Color.WHITE), 3, 5,
						Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
				tvdate.setText(datestyle);
				tvdate.setBackgroundColor(Color.YELLOW);
				//tvdate.setBackgroundColor(R.color.dateforcolor);
			}

			break;
		case R.id.plan_time_period:
			if (viewStatus.get(R.id.plan_time_period)) {
				viewStatus.put(R.id.plan_time_period,false);
				String durationString = (String) getText(R.string.date_plan_time_period_1);
				SpannableStringBuilder durationstyle = new SpannableStringBuilder(
						durationString);
				durationstyle.setSpan(new ForegroundColorSpan(Color.BLACK), 0,
						3, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
				durationstyle.setSpan(new ForegroundColorSpan(Color.WHITE), 3,
						5, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
				tvduration.setText(durationstyle);
				tvduration.setBackgroundColor(Color.parseColor("#FFACD6FF"));
				//tvduration.setBackgroundColor(R.color.datebackcolor);
			} else {
				viewStatus.put(R.id.plan_time_period,true);
				String durationString = (String) getText(R.string.date_plan_time_period_2);
				SpannableStringBuilder durationstyle = new SpannableStringBuilder(
						durationString);
				durationstyle.setSpan(new ForegroundColorSpan(Color.BLACK), 0,
						3, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
				durationstyle.setSpan(new ForegroundColorSpan(Color.WHITE), 3,
						5, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
				tvduration.setText(durationstyle);
				tvduration.setBackgroundColor(Color.YELLOW);
				//tvduration.setBackgroundColor(R.color.dateforcolor);
			}
			break;
		default:
			break;
		}
	}
}
