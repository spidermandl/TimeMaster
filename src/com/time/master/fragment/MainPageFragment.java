package com.time.master.fragment;


import com.time.master.R;
import com.time.master.activity.FrameActivity;
import com.time.master.activity.MainActivity;
import com.time.master.view.BasicTextView;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class MainPageFragment extends Fragment implements OnClickListener{
	@Override
	
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View layout = inflater.inflate(R.layout.main_page, container, false);
		BasicTextView add=(BasicTextView)layout.findViewById(R.id.main_page_add);
		add.setOnClickListener(this);
		
		return layout;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.main_page_add:
			FrameActivity activity=(FrameActivity)getActivity();
			activity.showNext(new Page4Fragment(), R.id.date_fragment);
			break;

		default:
			break;
		}
	}
}
