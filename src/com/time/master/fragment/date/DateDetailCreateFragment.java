package com.time.master.fragment.date;

import com.time.master.R;
import com.time.master.dialog.HumanDialogFragment;
import com.time.master.dialog.LocationDialogFragment;
import com.time.master.dialog.RepeatDialogFragment;
import com.time.master.dialog.TimeDialogFragment;
import com.time.master.dialog.WheelDialogFragment;
import com.time.master.interfacer.WheelResultInterface;
import com.time.master.view.BasicEditText;
import com.time.master.view.BasicTextView;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
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

		default:
			break;
		}
	}
}
