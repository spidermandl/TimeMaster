package com.time.master.fragment;
import com.time.master.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class Page2Fragment extends Fragment implements OnClickListener{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View layout = inflater.inflate(R.layout.page2,container,false);
		return layout;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
