package com.time.master.fragment;

import com.time.master.R;
import com.time.master.activity.FrameActivity;
import com.time.master.view.BasicTextView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class MainPageFragment extends Fragment implements OnClickListener{
	@Override	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View layout = inflater.inflate(R.layout.main_page, container, false);
		BasicTextView add=(BasicTextView)layout.findViewById(R.id.main_page_add);
		BasicTextView fitness=(BasicTextView)layout.findViewById(R.id.main_page_fitness);
		BasicTextView walk=(BasicTextView)layout.findViewById(R.id.main_page_walk);
		add.setOnClickListener(this);
		fitness.setOnClickListener(this);
		walk.setOnClickListener(this);
		
		return layout;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.main_page_add:
			FrameActivity activity=(FrameActivity)getActivity();
			activity.showNext(new AddFragment(), R.id.date_fragment);
			break;
		case R.id.main_page_fitness:
			FrameActivity activity2=(FrameActivity)getActivity();
			activity2.showNext(new FitnessFragment(), R.id.date_fragment);
			break;

		case R.id.main_page_walk:
			FrameActivity activity3=(FrameActivity)getActivity();
			activity3.showNext(new WalkFragment(), R.id.date_fragment);
			break;
		default:

			break;
		}
	}
}
