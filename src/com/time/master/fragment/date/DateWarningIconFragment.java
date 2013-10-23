package com.time.master.fragment.date;

import com.time.master.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DateWarningIconFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View layout = inflater.inflate(R.layout.date_warning_two, container, false);
//		View layout = inflater.inflate(R.layout.date_warning_icon, container, false);
		return layout;
	}
}
