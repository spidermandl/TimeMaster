package com.time.master.dialog;

import java.util.HashMap;
import com.time.master.R;
import com.time.master.interfacer.WheelResultInterface;
import com.time.master.view.BasicTextView;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

/**
 * "重复"对话框界面
<<<<<<< HEAD
 * @author wanghuiming
 *
=======
 * 
 * @author wanghuiming
 * 
>>>>>>> 8fa076907061732cb7a0875ee8bef7dc314099e2
 */
public class RepeatDialogFragment extends BasicDialogFragment implements
DialogInterface.OnClickListener,View.OnTouchListener, View.OnClickListener {
	WheelDialogFragment topFragment;
	public static final String tag = "RepeatDialogFragment";
	BasicTextView date_top_left;//每日一次 按钮 
	DialogFragment datatopFragment;
	BasicTextView confirm, dtmselect, dtmcurrent, dtworking, dteveryday,
			dtmonday, dtlmselect, dtlmcurrent, dtfestival, dtqueque, dttuesday,
			dtyselect, dtycurrent, dtcomday, dtotherweek, dtwednesday,
			dtlyselect, dtlycurrent, dtdayoff, dtsaturday, dtthursday,
			dtplyouself, dtsunday, dtfriday;
	HashMap<Integer, Boolean> viewStatus = new HashMap<Integer, Boolean>();
	
	BasicTextView yourselfBasicTextView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Light);
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		setDialogStyle();

		View layout = inflater.inflate(R.layout.date_repeat, container, false);
        
		date_top_left=(BasicTextView)layout.findViewById(R.id.date_top_left);
		date_top_left.setOnClickListener(this);
		
		confirm = (BasicTextView) layout.findViewById(R.id.date_confirm);
		dtmselect = (BasicTextView) layout.findViewById(R.id.date_month_select);
		dtmcurrent = (BasicTextView) layout
				.findViewById(R.id.date_month_current);
		dtworking = (BasicTextView) layout.findViewById(R.id.date_working);
		dteveryday = (BasicTextView) layout.findViewById(R.id.date_everyday);
		dtmonday = (BasicTextView) layout.findViewById(R.id.date_monday);
		dtlmcurrent = (BasicTextView) layout
				.findViewById(R.id.date_lunar_month_current);
		dtlmselect = (BasicTextView) layout
				.findViewById(R.id.date_lunar_month_select);
		dtfestival = (BasicTextView) layout.findViewById(R.id.date_festival);
		dtqueque = (BasicTextView) layout.findViewById(R.id.date_quaque);
		dttuesday = (BasicTextView) layout.findViewById(R.id.date_tuesday);
		dtyselect = (BasicTextView) layout.findViewById(R.id.date_year_select);
		dtycurrent = (BasicTextView) layout
				.findViewById(R.id.date_year_current);
		dtcomday = (BasicTextView) layout
				.findViewById(R.id.date_commemorate_day);
		dtotherweek = (BasicTextView) layout.findViewById(R.id.date_other_week);
		dtwednesday = (BasicTextView) layout.findViewById(R.id.date_wednesday);
		dtlyselect = (BasicTextView) layout
				.findViewById(R.id.date_lunar_year_select);
		dtlycurrent = (BasicTextView) layout
				.findViewById(R.id.date_lunar_year_current);
		dtdayoff = (BasicTextView) layout.findViewById(R.id.date_day_off);
		dtsaturday = (BasicTextView) layout.findViewById(R.id.date_saturday);
		dtthursday = (BasicTextView) layout.findViewById(R.id.date_thursday);
		dtplyouself = (BasicTextView) layout
				.findViewById(R.id.date_plan_yourself);
		dtsunday = (BasicTextView) layout.findViewById(R.id.date_sunday);
		dtfriday = (BasicTextView) layout.findViewById(R.id.date_friday);

		dtmselect.setOnClickListener(this);
		dtmcurrent.setOnClickListener(this);
		dtworking.setOnClickListener(this);
		dteveryday.setOnClickListener(this);
		dtmonday.setOnClickListener(this);
		dtlmcurrent.setOnClickListener(this);
		dtlmselect.setOnClickListener(this);
		dtfestival.setOnClickListener(this);
		dtqueque.setOnClickListener(this);
		dttuesday.setOnClickListener(this);
		dtyselect.setOnClickListener(this);
		dtycurrent.setOnClickListener(this);
		dtcomday.setOnClickListener(this);
		dtotherweek.setOnClickListener(this);
		dtwednesday.setOnClickListener(this);
		dtlyselect.setOnClickListener(this);
		dtlycurrent.setOnClickListener(this);
		dtdayoff.setOnClickListener(this);
		dtsaturday.setOnClickListener(this);
		dtthursday.setOnClickListener(this);
		dtplyouself.setOnClickListener(this);
		dtsunday.setOnClickListener(this);
		dtfriday.setOnClickListener(this);

		yourselfBasicTextView = (BasicTextView) layout
				.findViewById(R.id.date_plan_yourself);
		yourselfBasicTextView.setOnClickListener(this);// //

		return layout;
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v instanceof BasicTextView){
			Boolean isSelected=viewStatus.get(v.getId());
			if(isSelected==null||isSelected==false){
				viewStatus.put(v.getId(), true);
			}else{
				viewStatus.put(v.getId(), false);
			}
		}
		switch (v.getId()) {
		case R.id.date_plan_yourself:
			showDialog(new RepeatCustomizedDialogFragment());
			break;
		case R.id.date_top_left:
			datatopFragment = new DataTopDiaogFragment();
			datatopFragment.setShowsDialog(true);
			showDialog(datatopFragment);
			break;
		
		default:
			break;
		}

	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if(event.getAction()==MotionEvent.ACTION_UP){
			switch(v.getId()){
			case R.id.date_top_left:
				topFragment=new DataTopDiaogFragment();
				topFragment.setWheelInterface(new WheelResultInterface() {
					
					@Override
					public void getResult(String result) {
						// TODO Auto-generated method stub
						
					}
				}); 
					
		}
	}
		return false;
	}


}
