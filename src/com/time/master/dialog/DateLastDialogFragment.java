package com.time.master.dialog;

import com.time.master.R;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;

public class DateLastDialogFragment extends WheelDialogFragment {

	public static final String TAG="DateLastDialogFragment";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NO_FRAME,android.R.style.Theme_Light);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		getDialog().setCanceledOnTouchOutside(true);//点击dialog以外区域，关闭dialog
        Window window = getDialog().getWindow();
        window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置  
        window.setWindowAnimations(R.style.wheelAnimation);  //添加动画 
        WindowManager.LayoutParams para=(WindowManager.LayoutParams)window.getAttributes();
        para.height=LayoutParams.WRAP_CONTENT;
        para.width=LayoutParams.MATCH_PARENT;
        window.setAttributes(para);
        window.clearFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND | WindowManager.LayoutParams.FLAG_DIM_BEHIND);

		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	protected String getSelectedString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void pushConfirm() {
		// TODO Auto-generated method stub
		
	}

}
