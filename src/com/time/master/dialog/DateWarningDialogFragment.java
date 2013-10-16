package com.time.master.dialog;

import com.time.master.R;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DateWarningDialogFragment extends BasicDialogFragment {
	public static final String tag = "DateWarningDialogFragment";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Light);
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setDialogStyle();

		View layout = inflater.inflate(R.layout.date_warning_picture, container, false);

		return layout;
	}
}
