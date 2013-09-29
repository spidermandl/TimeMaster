package com.time.master.fragment;

import com.time.master.R;
import com.time.master.activity.FrameActivity;
import com.time.master.dialog.HumanDialogFragment;
import com.time.master.dialog.LocationDialogFragment;
import com.time.master.dialog.TimeDialogFragment;
import com.time.master.dialog.WheelDialogFragment;
import com.time.master.fragment.date.DateAddFragment;
import com.time.master.fragment.date.DateSportFragment;
import com.time.master.fragment.date.DateHabbitFragment;
import com.time.master.interfacer.WheelResultInterface;
import com.time.master.view.BasicEditText;
import com.time.master.view.BasicTextView;
import com.time.master.view.BasicViewGroup;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.annotation.SuppressLint;
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
import android.webkit.WebView.FindListener;

/**
 * "»’"√Ê∞Â
 * 
 * @author duanlei
 * 
 */
public class DateFragment extends Fragment implements android.view.View.OnClickListener {

	@Override	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View layout = inflater.inflate(R.layout.date_layout, container, false);
		BasicViewGroup viewGroup=(BasicViewGroup)layout.findViewById(R.id.date_front_page);
		BasicTextView add=(BasicTextView)layout.findViewById(R.id.main_page_add);
		BasicTextView fitness=(BasicTextView)layout.findViewById(R.id.main_page_fitness);
		BasicTextView walk=(BasicTextView)layout.findViewById(R.id.main_page_walk);

		viewGroup.setOnClickListener(this);
		add.setOnClickListener(this);
		fitness.setOnClickListener(this);
		walk.setOnClickListener(this);
		return layout;

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		FrameActivity activity=(FrameActivity)getActivity();
		Class T;
		switch (v.getId()) {
		case R.id.main_page_add:
			T=DateAddFragment.class;
			activity.showNext(R.id.date_fragment,T, R.layout.date_add_page);
			break;
		case R.id.main_page_fitness:
			T=DateSportFragment.class;
			activity.showNext(R.id.date_fragment,T, R.layout.date_sports_page);
			break;
		case R.id.main_page_walk:
			T=DateHabbitFragment.class;
			activity.showNext(R.id.date_fragment,T, R.layout.date_habbit_page);
			break;
		default:

			break;
		}

	}
}
