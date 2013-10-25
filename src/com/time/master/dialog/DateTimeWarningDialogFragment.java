package com.time.master.dialog;

import com.time.master.R;
import com.time.master.view.BasicViewGroup;

import android.R.layout;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * 定时提醒界面
 * 单击类按钮弹出此dialog
 * @author juan
 * 
 */
public class DateTimeWarningDialogFragment extends BasicDialogFragment {
	public static final String tag = "DateTimeWarningDialogFragment";
	BasicViewGroup basicViewGroup;
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
			View layout=inflater.inflate(R.layout.date_time_warning, container, false);
			basicViewGroup=(BasicViewGroup)layout.findViewById(R.id.day_warning_viewgroup);
			basicViewGroup.setSpecialChildren(6, 3);
			
			return layout;
		}
}
