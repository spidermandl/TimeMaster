package com.time.master.fragment.date;

import com.time.master.R;
import com.time.master.activity.FrameActivity;
import com.time.master.fragment.DateFragment;
import com.time.master.view.BasicTextView;
import com.time.master.view.BasicViewGroup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

/**
 * 日---”行“界面
 * @author Desmond
 *
 */
public class DateHabbitFragment extends Fragment implements OnClickListener{
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View layout = inflater.inflate(R.layout.date_habbit_page, container, false);
		
		BasicViewGroup viewGroup=(BasicViewGroup)layout.findViewById(R.id.date_habbit_page);
		BasicTextView textView = (BasicTextView)layout.findViewById(R.id.walk_page_add);
		
		viewGroup.setOnClickListener(this);
		textView.setOnClickListener(this);
		return layout;
	}
	@Override
	public void onClick(View v) {
		FrameActivity activity=(FrameActivity)getActivity();
		Class T;
		switch (v.getId()) {
		case R.id.walk_page_add:
			//activity.showNext(new DateFragment(), R.id.date_fragment);
			return;

		default:
			T=DateDetailCreateFragment.class;
			activity.showNext(R.id.date_fragment, T, R.layout.date_detail_create_page);
			break;
		}
	}

}
