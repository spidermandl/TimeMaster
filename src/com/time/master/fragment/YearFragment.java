package com.time.master.fragment;

import com.time.master.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class YearFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout=inflater.inflate(R.layout.year_layout, container, false);
		
        
		return layout;
	}

}
