package com.time.master.fragment;

import com.time.master.R;
import com.time.master.activity.FrameActivity;
import com.time.master.view.BasicTextView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

public class WalkFragment extends Fragment implements OnClickListener{
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View layout = inflater.inflate(R.layout.mainpage_walk, container, false);
		BasicTextView textView = (BasicTextView)layout.findViewById(R.id.walk_page_add);
		textView.setOnClickListener(this);
		return layout;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.walk_page_add:
			FrameActivity activity=(FrameActivity)getActivity();
			activity.showNext(new DateFragment(), R.id.date_fragment);
			break;

		default:
			break;
		}
	}

}
