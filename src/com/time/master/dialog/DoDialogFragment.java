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

public class DoDialogFragment extends DialogFragment{
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
		getDialog().setCanceledOnTouchOutside(true);
		Window window=getDialog().getWindow();
		window.setGravity(Gravity.BOTTOM);
		WindowManager.LayoutParams para=(WindowManager.LayoutParams)window.getAttributes();
		
		para.height=LayoutParams.WRAP_CONTENT;
		para.width=LayoutParams.MATCH_PARENT;
		window.setAttributes(para);
		window.clearFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND | WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		
		View layout=inflater.inflate(R.layout.do_dialog, container, false);
		return layout;
	}

}
