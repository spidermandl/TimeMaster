package com.time.master.fragment.date;

import com.time.master.R;
import com.time.master.view.BasicTextView;
import com.time.master.view.BasicViewGroup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

/**
 * 日---增加选项界面
 * @author Desmond
 *
 */
public class DateAddFragment extends Fragment implements OnClickListener{
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View layout = inflater.inflate(R.layout.date_add_page, container, false);
		
		BasicViewGroup viewGroup=(BasicViewGroup)layout.findViewById(R.id.date_add_page);
		BasicTextView textView=(BasicTextView)layout.findViewById(R.id.add_page_add);
		
		viewGroup.setOnClickListener(this);
		textView.setOnClickListener(this);
		return layout;
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_page_add:
			break;

		default:
			break;
		}
		
	}

}
