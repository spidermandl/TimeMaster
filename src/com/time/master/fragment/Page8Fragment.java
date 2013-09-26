package com.time.master.fragment;

import com.time.master.R;
import com.time.master.activity.MainActivity;
import com.time.master.view.BasicTextView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class Page8Fragment extends Fragment implements OnClickListener{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View layout = inflater.inflate(R.layout.page8,container,false);
		BasicTextView button=(BasicTextView)layout.findViewById(R.id.page8_btnEnd);
		button.setOnClickListener(this);
		return layout;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.page8_btnEnd:
			MainActivity activity=(MainActivity)getActivity();
			activity.showNext(new Page9Fragment(),R.id.mainlayout);
			break;
		}
	}
}
