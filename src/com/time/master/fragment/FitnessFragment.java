package com.time.master.fragment;

import com.time.master.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

public class FitnessFragment extends Fragment implements OnClickListener{
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View layout = inflater.inflate(R.layout.fitness_page, container, false);
		return layout;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
