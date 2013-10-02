package com.time.master.dialog;

/**
 * "自排：做X 停X" 对话框
 * @author WangHaiyang
 */


import com.time.master.R;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public class RepeatCustomizedDialogFragment extends BasicDialogFragment{
	public static final String tag="DoDialogFragment";
	
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
		setDialogStyle();
		
		View layout=inflater.inflate(R.layout.date_repeat_customized_dialog, container, false);
		return layout;
	}

}
