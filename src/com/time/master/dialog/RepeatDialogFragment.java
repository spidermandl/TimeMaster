package com.time.master.dialog;

import java.util.HashMap;
import com.time.master.R;
import com.time.master.view.BasicTextView;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

/**
 * "重复"对话框界面
 * @author bianyanling
 *
 */
public class RepeatDialogFragment extends DialogFragment implements OnClickListener, android.view.View.OnClickListener  {

	public static final String tag="RepeatDialogFragment";
	BasicTextView confirm,dtmselect,dtmcurrent,dtworking,dteveryday,dtmonday,dtlmselect,dtlmcurrent,dtfestival,
	dtqueque,dttuesday,dtyselect,dtycurrent,dtcomday,dtotherweek,dtwednesday,dtlyselect,
	dtlycurrent,dtdayoff,dtsaturday,dtthursday,dtplyouself,dtsunday,dtfriday;
	HashMap<Integer, Boolean> viewStatus=new HashMap<Integer, Boolean>();
	
	DoDialogFragment doDialog;////
	BasicTextView yourselfBasicTextView;////
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setStyle(DialogFragment.STYLE_NO_FRAME,android.R.style.Theme_Light);
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		getDialog().setCanceledOnTouchOutside(true);
		Window window=getDialog().getWindow();
		window.setGravity(Gravity.BOTTOM);
		WindowManager.LayoutParams para=(WindowManager.LayoutParams)window.getAttributes();
		para.height=LayoutParams.WRAP_CONTENT;
		para.width=LayoutParams.MATCH_PARENT;
		window.setAttributes(para);
		window.clearFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND | WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		
		View layout=inflater.inflate(R.layout.date_repeat, container, false);
		
		        confirm = (BasicTextView)layout.findViewById(R.id.date_confirm);
		        dtmselect=(BasicTextView)layout.findViewById(R.id.date_month_select);
				dtmcurrent=(BasicTextView)layout.findViewById(R.id.date_month_current);
				dtworking=(BasicTextView)layout.findViewById(R.id.date_working);
				dteveryday=(BasicTextView)layout.findViewById(R.id.date_everyday);
				dtmonday=(BasicTextView)layout.findViewById(R.id.date_monday);
				dtlmcurrent=(BasicTextView)layout.findViewById(R.id.date_lunar_month_current);
				dtlmselect=(BasicTextView)layout.findViewById(R.id.date_lunar_month_select);
				dtfestival=(BasicTextView)layout.findViewById(R.id.date_festival);
				dtqueque=(BasicTextView)layout.findViewById(R.id.date_quaque);
				dttuesday=(BasicTextView)layout.findViewById(R.id.date_tuesday);
				dtyselect=(BasicTextView)layout.findViewById(R.id.date_year_select);
				dtycurrent=(BasicTextView)layout.findViewById(R.id.date_year_current);
				dtcomday=(BasicTextView)layout.findViewById(R.id.date_commemorate_day);
				dtotherweek=(BasicTextView)layout.findViewById(R.id.date_other_week);
				dtwednesday=(BasicTextView)layout.findViewById(R.id.date_wednesday);
				dtlyselect=(BasicTextView)layout.findViewById(R.id.date_lunar_year_select);
				dtlycurrent=(BasicTextView)layout.findViewById(R.id.date_lunar_year_current);
				dtdayoff=(BasicTextView)layout.findViewById(R.id.date_day_off);
				dtsaturday=(BasicTextView)layout.findViewById(R.id.date_saturday);
				dtthursday=(BasicTextView)layout.findViewById(R.id.date_thursday);
				dtplyouself=(BasicTextView)layout.findViewById(R.id.date_plan_yourself);
				dtsunday=(BasicTextView)layout.findViewById(R.id.date_sunday);
				dtfriday=(BasicTextView)layout.findViewById(R.id.date_friday);
		
		viewStatus.put(dtmselect.getId(), false);
		viewStatus.put(dtmcurrent.getId(), false);
		viewStatus.put(dtworking.getId(), false);
		viewStatus.put(dteveryday.getId(), false);
		viewStatus.put(dtmonday.getId(), false);
		viewStatus.put(dtlmcurrent.getId(), false);
		viewStatus.put(dtlmselect.getId(), false);
		viewStatus.put(dtfestival.getId(), false);
		viewStatus.put(dtqueque.getId(), false);
		viewStatus.put(dttuesday.getId(), false);
		viewStatus.put(dtyselect.getId(), false);
		viewStatus.put(dtycurrent.getId(), false);
		viewStatus.put(dtcomday.getId(), false);
		viewStatus.put(dtotherweek.getId(), false);
		viewStatus.put(dtwednesday.getId(), false);
		viewStatus.put(dtlyselect.getId(), false);
		viewStatus.put(dtlycurrent.getId(), false);
		viewStatus.put(dtdayoff.getId(), false);
		viewStatus.put(dtsaturday.getId(), false);
		viewStatus.put(dtthursday.getId(), false);
		viewStatus.put(dtplyouself.getId(), false);
		viewStatus.put(dtsunday.getId(), false);
		viewStatus.put(dtfriday.getId(), false);
		
		
				
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
		
		yourselfBasicTextView=(BasicTextView)layout.findViewById(R.id.date_plan_yourself);
		yourselfBasicTextView.setOnClickListener(this);////
		
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
		dialogFragment.show(ft, "dialog");////
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.date_month_select:
			if (viewStatus.get(R.id.date_month_select)) {
				viewStatus.put(R.id.date_month_select,false);				
				dtmselect.setBackgroundColor(Color.parseColor("#FFACD6FF"));
			} else {
				viewStatus.put(R.id.date_month_select,true);						
				dtmselect.setBackgroundColor(Color.WHITE);
				}
			break;
case R.id.date_month_current:
	if (viewStatus.get(R.id.date_month_current)) {
		viewStatus.put(R.id.date_month_current,false);				
		dtmcurrent.setBackgroundColor(Color.parseColor("#FFACD6FF"));
	} else {
		viewStatus.put(R.id.date_month_current,true);						
		dtmcurrent.setBackgroundColor(Color.WHITE);
		}
			break;
case R.id.date_working:
	if (viewStatus.get(R.id.date_working)) {
		viewStatus.put(R.id.date_working,false);				
		dtworking.setBackgroundColor(Color.parseColor("#FFACD6FF"));
	} else {
		viewStatus.put(R.id.date_working,true);						
		dtworking.setBackgroundColor(Color.WHITE);
		}
	break;
case R.id.date_everyday:
	if (viewStatus.get(R.id.date_everyday)) {
		viewStatus.put(R.id.date_everyday,false);				
		dteveryday.setBackgroundColor(Color.parseColor("#FFACD6FF"));
	} else {
		viewStatus.put(R.id.date_everyday,true);						
		dteveryday.setBackgroundColor(Color.WHITE);
		}
	break;
case R.id.date_monday:
	if (viewStatus.get(R.id.date_monday)) {
		viewStatus.put(R.id.date_monday,false);				
		dtmonday.setBackgroundColor(Color.parseColor("#FFACD6FF"));
	} else {
		viewStatus.put(R.id.date_monday,true);						
		dtmonday.setBackgroundColor(Color.WHITE);
		}
	break;
case R.id.date_lunar_month_select:
	if (viewStatus.get(R.id.date_lunar_month_select)) {
		viewStatus.put(R.id.date_lunar_month_select,false);				
		dtlmselect.setBackgroundColor(Color.parseColor("#FFACD6FF"));
	} else {
		viewStatus.put(R.id.date_lunar_month_select,true);						
		dtlmselect.setBackgroundColor(Color.WHITE);
		}
	break;
case R.id.date_lunar_month_current:
	if (viewStatus.get(R.id.date_lunar_month_current)) {
		viewStatus.put(R.id.date_lunar_month_current,false);				
		dtlmcurrent.setBackgroundColor(Color.parseColor("#FFACD6FF"));
	} else {
		viewStatus.put(R.id.date_lunar_month_current,true);						
		dtlmcurrent.setBackgroundColor(Color.WHITE);
		}
	break;
case R.id.date_festival:
	if (viewStatus.get(R.id.date_festival)) {
		viewStatus.put(R.id.date_festival,false);				
		dtfestival.setBackgroundColor(Color.parseColor("#FFACD6FF"));
	} else {
		viewStatus.put(R.id.date_festival,true);						
		dtfestival.setBackgroundColor(Color.WHITE);
		}
	break;
case R.id.date_quaque:
	if (viewStatus.get(R.id.date_quaque)) {
		viewStatus.put(R.id.date_quaque,false);				
		dtqueque.setBackgroundColor(Color.parseColor("#FFACD6FF"));
	} else {
		viewStatus.put(R.id.date_quaque,true);						
		dtqueque.setBackgroundColor(Color.WHITE);
		}
	break;
case R.id.date_tuesday:
	if (viewStatus.get(R.id.date_tuesday)) {
		viewStatus.put(R.id.date_tuesday,false);				
		dttuesday.setBackgroundColor(Color.parseColor("#FFACD6FF"));
	} else {
		viewStatus.put(R.id.date_tuesday,true);						
		dttuesday.setBackgroundColor(Color.WHITE);
		}
	break;
case R.id.date_year_select:
	if (viewStatus.get(R.id.date_year_select)) {
		viewStatus.put(R.id.date_year_select,false);				
		dtyselect.setBackgroundColor(Color.parseColor("#FFACD6FF"));
	} else {
		viewStatus.put(R.id.date_year_select,true);						
		dtyselect.setBackgroundColor(Color.WHITE);
		}
	break;
case R.id.date_year_current:
	if (viewStatus.get(R.id.date_year_current)) {
		viewStatus.put(R.id.date_year_current,false);				
		dtycurrent.setBackgroundColor(Color.parseColor("#FFACD6FF"));
	} else {
		viewStatus.put(R.id.date_year_current,true);						
		dtycurrent.setBackgroundColor(Color.WHITE);
		}
	break;
case R.id.date_commemorate_day:
	if (viewStatus.get(R.id.date_commemorate_day)) {
		viewStatus.put(R.id.date_commemorate_day,false);				
		dtcomday.setBackgroundColor(Color.parseColor("#FFACD6FF"));
	} else {
		viewStatus.put(R.id.date_commemorate_day,true);						
		dtcomday.setBackgroundColor(Color.WHITE);
		}
	break;
case R.id.date_other_week:
	if (viewStatus.get(R.id.date_other_week)) {
		viewStatus.put(R.id.date_other_week,false);				
		dtotherweek.setBackgroundColor(Color.parseColor("#FFACD6FF"));
	} else {
		viewStatus.put(R.id.date_other_week,true);						
		dtotherweek.setBackgroundColor(Color.WHITE);
		}
	break;
case R.id.date_wednesday:
	if (viewStatus.get(R.id.date_wednesday)) {
		viewStatus.put(R.id.date_wednesday,false);				
		dtwednesday.setBackgroundColor(Color.parseColor("#FFACD6FF"));
	} else {
		viewStatus.put(R.id.date_wednesday,true);						
		dtwednesday.setBackgroundColor(Color.WHITE);
		}
	break;
case R.id.date_lunar_year_select:
	if (viewStatus.get(R.id.date_lunar_year_select)) {
		viewStatus.put(R.id.date_lunar_year_select,false);				
		dtlyselect.setBackgroundColor(Color.parseColor("#FFACD6FF"));
	} else {
		viewStatus.put(R.id.date_lunar_year_select,true);						
		dtlyselect.setBackgroundColor(Color.WHITE);
		}
	break;
case R.id.date_lunar_year_current:
	if (viewStatus.get(R.id.date_lunar_year_current)) {
		viewStatus.put(R.id.date_lunar_year_current,false);				
		dtlycurrent.setBackgroundColor(Color.parseColor("#FFACD6FF"));
	} else {
		viewStatus.put(R.id.date_lunar_year_current,true);						
		dtlycurrent.setBackgroundColor(Color.WHITE);
		}
	break;
case R.id.date_day_off:
	if (viewStatus.get(R.id.date_day_off)) {
		viewStatus.put(R.id.date_day_off,false);				
		dtdayoff.setBackgroundColor(Color.parseColor("#FFACD6FF"));
	} else {
		viewStatus.put(R.id.date_day_off,true);						
		dtdayoff.setBackgroundColor(Color.WHITE);
		}
	break;
case R.id.date_saturday:
	if (viewStatus.get(R.id.date_saturday)) {
		viewStatus.put(R.id.date_saturday,false);				
		dtsaturday.setBackgroundColor(Color.parseColor("#FFACD6FF"));
	} else {
		viewStatus.put(R.id.date_saturday,true);						
		dtsaturday.setBackgroundColor(Color.WHITE);
		}
	break;
case R.id.date_thursday:
	if (viewStatus.get(R.id.date_thursday)) {
		viewStatus.put(R.id.date_thursday,false);				
		dtthursday.setBackgroundColor(Color.parseColor("#FFACD6FF"));
	} else {
		viewStatus.put(R.id.date_thursday,true);						
		dtthursday.setBackgroundColor(Color.WHITE);
		}
	break;
case R.id.date_plan_yourself:
	doDialog=new DoDialogFragment();
	doDialog.setShowsDialog(true);
	showDialog(doDialog);
	if (viewStatus.get(R.id.date_plan_yourself)) {
		viewStatus.put(R.id.date_plan_yourself,false);				
		dtplyouself.setBackgroundColor(Color.parseColor("#FFACD6FF"));
	} else {
		viewStatus.put(R.id.date_plan_yourself,true);						
		dtplyouself.setBackgroundColor(Color.WHITE);
		}
	break;
case R.id.date_sunday:
	if (viewStatus.get(R.id.date_sunday)) {
		viewStatus.put(R.id.date_sunday,false);				
		dtsunday.setBackgroundColor(Color.parseColor("#FFACD6FF"));
	} else {
		viewStatus.put(R.id.date_sunday,true);						
		dtsunday.setBackgroundColor(Color.WHITE);
		}
	break;
case R.id.date_friday:
	if (viewStatus.get(R.id.date_friday)) {
		viewStatus.put(R.id.date_friday,false);				
		dtfriday.setBackgroundColor(Color.parseColor("#FFACD6FF"));
	} else {
		viewStatus.put(R.id.date_friday,true);						
		dtfriday.setBackgroundColor(Color.WHITE);
		}

		}
		
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		
	}

}

