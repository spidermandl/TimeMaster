package com.time.master.fragment.date;

import com.time.master.R;
import com.time.master.activity.FrameActivity;
import com.time.master.dialog.DateWarningDialogFragment;
import com.time.master.dialog.RepeatDialogFragment;
import com.time.master.view.BasicTextView;
import com.time.master.view.BasicViewGroup;
import com.time.master.view.GapCleanViewGroup;

import android.graphics.Color;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.widget.GridView;

import com.time.master.R;
import com.time.master.activity.FrameActivity;
import com.time.master.adapter.DateGridViewImageAdapter;
import com.time.master.dialog.*;
import com.time.master.interfacer.WheelResultInterface;

public class DateWarningFragment extends Fragment implements OnClickListener {

	BasicTextView warningEverytime, warningAdd, warningCompose, warningConfirm,
			warningRing, warningMusic, warningPronunciation, warningVibrate,warningIcon,warningMotion;
	BasicTextView centerOne,	centerTwo, centerThree, centerFour, centerFive, centerSix,
	centerSeven, centerEight;
	BasicTextView leftOne,leftTwo,leftThree,leftFour,leftFive,leftSix,leftSeven;
	DialogFragment warningFragment;
	BasicViewGroup viewGroup;
	GapCleanViewGroup gapCleanViewGroup;
	GridView gridview1;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View layout = inflater.inflate(R.layout.date_warning, container, false);
		
		viewGroup=(BasicViewGroup)layout.findViewById(R.id.groupOne);
		gapCleanViewGroup=(GapCleanViewGroup)layout.findViewById(R.id.groupThree);
		warningEverytime=(BasicTextView)layout.findViewById(R.id.warning_everytime);
		warningAdd=(BasicTextView)layout.findViewById(R.id.warning_add);
		warningCompose=(BasicTextView)layout.findViewById(R.id.warning_compose);
		warningConfirm=(BasicTextView)layout.findViewById(R.id.warning_confirm);
		warningRing=(BasicTextView)layout.findViewById(R.id.warning_ring);
		warningMusic=(BasicTextView)layout.findViewById(R.id.warning_music);
		warningPronunciation=(BasicTextView)layout.findViewById(R.id.warning_pronunciation);
		warningVibrate=(BasicTextView)layout.findViewById(R.id.warning_vibrate);
		warningIcon=(BasicTextView)layout.findViewById(R.id.warning_icon);
		warningMotion=(BasicTextView)layout.findViewById(R.id.warning_motion);
		
		leftOne=(BasicTextView)layout.findViewById(R.id.warning_collect);
		leftTwo=(BasicTextView)layout.findViewById(R.id.warning_download);
		leftThree=(BasicTextView)layout.findViewById(R.id.warning_audition);
		leftFour=(BasicTextView)layout.findViewById(R.id.warning_transcribe);
		leftFive=(BasicTextView)layout.findViewById(R.id.warning_man_sound);
		leftSix=(BasicTextView)layout.findViewById(R.id.warning_famale_sound);
		leftSeven=(BasicTextView)layout.findViewById(R.id.warning_child_sound);
		
		centerOne=(BasicTextView)layout.findViewById(R.id.warning_center_one);
		centerTwo=(BasicTextView)layout.findViewById(R.id.warning_center_two);
		centerThree=(BasicTextView)layout.findViewById(R.id.warning_center_three);
		centerFour=(BasicTextView)layout.findViewById(R.id.warning_center_four);
		centerFive=(BasicTextView)layout.findViewById(R.id.warning_center_five);
		centerSix=(BasicTextView)layout.findViewById(R.id.warning_center_six);
		centerSeven=(BasicTextView)layout.findViewById(R.id.warning_center_seven);
		centerEight=(BasicTextView)layout.findViewById(R.id.warning_center_eight);
		
		warningEverytime.setOnClickListener(this);
		warningAdd.setOnClickListener(this);
		warningCompose.setOnClickListener(this);
		warningConfirm.setOnClickListener(this);
		warningRing.setOnClickListener(this);
		warningMusic.setOnClickListener(this);
		warningPronunciation.setOnClickListener(this);
		warningVibrate.setOnClickListener(this);
		warningIcon.setOnClickListener(this);
		warningMotion.setOnClickListener(this);
		
		viewGroup.cutDownBottomGap(1);
		gridview1=(GridView)layout.findViewById(R.id.gridview);
		gridview1.setAdapter(new DateGridViewImageAdapter(getActivity()));
		return layout;
	}
//	 void showDialog(DialogFragment dialogFragment) {
//			// TODO Auto-generated method stub
//			
//				// DialogFragment.show() will take care of adding the fragment
//				// in a transaction. We also want to remove any currently showing
//				// dialog, so make our own transaction and take care of that here.
//				FragmentTransaction ft = getFragmentManager().beginTransaction();
//				Fragment prev = getFragmentManager().findFragmentByTag("dialog");
//				if (prev != null) {
//					ft.remove(prev);
//				}
//				ft.addToBackStack(null);
//
//				// Create and show the dialog.
//				dialogFragment.show(ft, "dialog");
//			
//		}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
//		Class T;
//		FrameActivity activity=(FrameActivity)getActivity();
		switch (v.getId()) {
		case R.id.warning_ring:
			centerOne.setText(R.string.warning_ring_one);
			centerTwo.setText(R.string.warning_ring_two);
			centerThree.setText(R.string.warning_ring_three);
			centerFour.setText(R.string.warning_ring_four);
			centerFive.setText(R.string.warning_ring_five);
			centerSix.setText(R.string.warning_ring_six);
			centerSeven.setText(R.string.warning_ring_seven);
			centerEight.setText(R.string.warning_ring_eight);
			centerOne.setBackgroundColor(Color.parseColor("#FFAA00"));
			centerTwo.setBackgroundColor(Color.parseColor("#FFAA00"));
			centerThree.setBackgroundColor(Color.parseColor("#FFAA00"));
			centerFour.setBackgroundColor(Color.parseColor("#FFAA00"));
			centerFive.setBackgroundColor(Color.parseColor("#FFAA00"));
			centerSix.setBackgroundColor(Color.parseColor("#FFAA00"));
			centerSeven.setBackgroundColor(Color.parseColor("#FFAA00"));
			centerEight.setBackgroundColor(Color.parseColor("#FFAA00"));
			centerOne.setTextColor(Color.parseColor("#000000"));
			centerTwo.setTextColor(Color.parseColor("#000000"));
			centerThree.setTextColor(Color.parseColor("#000000"));
			centerFour.setTextColor(Color.parseColor("#000000"));
			centerFive.setTextColor(Color.parseColor("#000000"));
			centerSix.setTextColor(Color.parseColor("#000000"));
			centerSeven.setTextColor(Color.parseColor("#000000"));
			centerEight.setTextColor(Color.parseColor("#000000"));
			leftOne.setText(R.string.warning_collect);
			leftTwo.setText(R.string.warning_download);
			leftThree.setText(R.string.warning_audition);
			leftFour.setText(R.string.warning_transcribe);
			leftFive.setText(R.string.warning_man_sound);
			leftSix.setText(R.string.warning_female_sound);
			leftSeven.setText(R.string.warning_child_sound);
			gridview1.setVisibility(View.INVISIBLE);
			gapCleanViewGroup.setVisibility(View.VISIBLE);
			break;
			
		case R.id.warning_music:			
			centerOne.setText(R.string.warning_music_one);
			centerTwo.setText(R.string.warning_music_two);
			centerThree.setText(R.string.warning_music_three);
			centerFour.setText(R.string.warning_music_four);
			centerFive.setText(R.string.warning_music_five);
			centerSix.setText(R.string.warning_music_six);
			centerSeven.setText(R.string.warning_music_seven);
			centerEight.setText(R.string.warning_music_eight);
			centerOne.setBackgroundColor(Color.parseColor("#8FFF6B"));
			centerTwo.setBackgroundColor(Color.parseColor("#8FFF6B"));
			centerThree.setBackgroundColor(Color.parseColor("#8FFF6B"));
			centerFour.setBackgroundColor(Color.parseColor("#8FFF6B"));
			centerFive.setBackgroundColor(Color.parseColor("#8FFF6B"));
			centerSix.setBackgroundColor(Color.parseColor("#8FFF6B"));
			centerSeven.setBackgroundColor(Color.parseColor("#8FFF6B"));
			centerEight.setBackgroundColor(Color.parseColor("#8FFF6B"));
			centerOne.setTextColor(Color.parseColor("#000000"));
			centerTwo.setTextColor(Color.parseColor("#000000"));
			centerThree.setTextColor(Color.parseColor("#000000"));
			centerFour.setTextColor(Color.parseColor("#000000"));
			centerFive.setTextColor(Color.parseColor("#000000"));
			centerSix.setTextColor(Color.parseColor("#000000"));
			centerSeven.setTextColor(Color.parseColor("#000000"));
			centerEight.setTextColor(Color.parseColor("#000000"));
			gridview1.setVisibility(View.INVISIBLE);
			leftOne.setText(R.string.warning_collect);
			leftTwo.setText(R.string.warning_download);
			leftThree.setText(R.string.warning_audition);
			leftFour.setText(R.string.warning_transcribe);
			leftFive.setText(R.string.warning_man_sound);
			leftSix.setText(R.string.warning_female_sound);
			leftSeven.setText(R.string.warning_child_sound);
			gapCleanViewGroup.setVisibility(View.VISIBLE);
			break;

		case R.id.warning_pronunciation:
			centerOne.setText(R.string.warning_pronunciation_time);
			centerTwo.setText(R.string.warning_pronunciation_plan);
			centerThree.setText(R.string.warning_pronunciation_record);
			centerFour.setText(R.string.warning_pronunciation_getup);
			centerFive.setText(R.string.warning_pronunciation_dine);
			centerSix.setText(R.string.warning_pronunciation_work);
			centerSeven.setText(R.string.warning_pronunciation_pills);
			centerEight.setText(R.string.warning_pronunciation_rest);
			centerOne.setBackgroundColor(Color.parseColor("#00B97D"));
			centerTwo.setBackgroundColor(Color.parseColor("#00B97D"));
			centerThree.setBackgroundColor(Color.parseColor("#00B97D"));
			centerFour.setBackgroundColor(Color.parseColor("#00B97D"));
			centerFive.setBackgroundColor(Color.parseColor("#00B97D"));
			centerSix.setBackgroundColor(Color.parseColor("#00B97D"));
			centerSeven.setBackgroundColor(Color.parseColor("#00B97D"));
			centerEight.setBackgroundColor(Color.parseColor("#00B97D"));
			centerOne.setTextColor(Color.parseColor("#000000"));
			centerTwo.setTextColor(Color.parseColor("#000000"));
			centerThree.setTextColor(Color.parseColor("#000000"));
			centerFour.setTextColor(Color.parseColor("#000000"));
			centerFive.setTextColor(Color.parseColor("#000000"));
			centerSix.setTextColor(Color.parseColor("#000000"));
			centerSeven.setTextColor(Color.parseColor("#000000"));
			centerEight.setTextColor(Color.parseColor("#000000"));
			leftOne.setText(R.string.warning_collect);
			leftTwo.setText(R.string.warning_download);
			leftThree.setText(R.string.warning_audition);
			leftFour.setText(R.string.warning_transcribe);
			leftFive.setText(R.string.warning_man_sound);
			leftSix.setText(R.string.warning_female_sound);
			leftSeven.setText(R.string.warning_child_sound);
			gridview1.setVisibility(View.INVISIBLE);
			gapCleanViewGroup.setVisibility(View.VISIBLE);
			break;
		case R.id.warning_vibrate:
			centerOne.setText(R.string.warning_vibrate_one);
			centerTwo.setText(R.string.warning_vibrate_two);
			centerThree.setText(R.string.warning_vibrate_three);
			centerFour.setText(R.string.warning_vibrate_four);
			centerFive.setText(R.string.warning_vibrate_five);
			centerSix.setText(R.string.warning_vibrate_six);
			centerSeven.setText(R.string.warning_vibrate_seven);
			centerEight.setText(R.string.warning_vibrate_eight);
			centerOne.setTextColor(Color.parseColor("#FFFFFF"));
			centerTwo.setTextColor(Color.parseColor("#FFFFFF"));
			centerThree.setTextColor(Color.parseColor("#FFFFFF"));
			centerFour.setTextColor(Color.parseColor("#FFFFFF"));
			centerFive.setTextColor(Color.parseColor("#FFFFFF"));
			centerSix.setTextColor(Color.parseColor("#FFFFFF"));
			centerSeven.setTextColor(Color.parseColor("#FFFFFF"));
			centerEight.setTextColor(Color.parseColor("#FFFFFF"));
			centerOne.setBackgroundColor(Color.parseColor("#007AB9"));
			centerTwo.setBackgroundColor(Color.parseColor("#007AB9"));
			centerThree.setBackgroundColor(Color.parseColor("#007AB9"));
			centerFour.setBackgroundColor(Color.parseColor("#007AB9"));
			centerFive.setBackgroundColor(Color.parseColor("#007AB9"));
			centerSix.setBackgroundColor(Color.parseColor("#007AB9"));
			centerSeven.setBackgroundColor(Color.parseColor("#007AB9"));
			centerEight.setBackgroundColor(Color.parseColor("#007AB9"));
			leftOne.setText(R.string.warning_collect);
			leftTwo.setText(R.string.warning_download);
			leftThree.setText(R.string.warning_audition);
			leftFour.setText(R.string.warning_transcribe);
			leftFive.setText(R.string.warning_man_sound);
			leftSix.setText(R.string.warning_female_sound);
			leftSeven.setText(R.string.warning_child_sound);
			gridview1.setVisibility(View.INVISIBLE);
			gapCleanViewGroup.setVisibility(View.VISIBLE);
			break;
		case R.id.warning_icon:
		
			leftOne.setText(R.string.warning_icon_sign);
			leftTwo.setText(R.string.warning_icon_geometry);
			leftThree.setText(R.string.warning_icon_mask);
			leftFour.setText(R.string.warning_icon_cartoon);
			leftFive.setText(R.string.warning_icon_backgroundcolor);
			leftSix.setText(R.string.warning_icon_framecolor);
			leftSeven.setText(R.string.warning_icon_wordcolor);
			gridview1.setVisibility(View.VISIBLE);
			gapCleanViewGroup.setVisibility(View.INVISIBLE);
			break;
		case R.id.warning_motion:
			leftOne.setText(R.string.warning_icon_sign);
			leftTwo.setText(R.string.warning_icon_geometry);
			leftThree.setText(R.string.warning_icon_mask);
			leftFour.setText(R.string.warning_icon_cartoon);
			leftFive.setText(R.string.warning_icon_backgroundcolor);
			leftSix.setText(R.string.warning_icon_framecolor);
			leftSeven.setText(R.string.warning_icon_wordcolor);
			gridview1.setVisibility(View.VISIBLE);
			gapCleanViewGroup.setVisibility(View.INVISIBLE);
			break;
		default:
			break;
		}
	}
	
}
