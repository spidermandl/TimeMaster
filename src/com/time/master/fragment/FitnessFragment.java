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

public class FitnessFragment extends Fragment implements OnClickListener{
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		View layout = inflater.inflate(R.layout.mainpage_fitness, container, false);//资源文件
=======
		View layout = inflater.inflate(R.layout.date_mainpage_fitness, container, false);
>>>>>>> 5d4d9b629b1dd36350370353ab016f668dcd9c16
		BasicTextView add=(BasicTextView) layout.findViewById(R.id.fitness_page_add);
		add.setOnClickListener(this);
		return layout;
		
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.fitness_page_add:
			FrameActivity activity=(FrameActivity)getActivity();
			activity.showNext(new DateFragment(), R.id.date_fragment);
			break;

		default:
			break;
		}
	}

}
