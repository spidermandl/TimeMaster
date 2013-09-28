package com.time.master.fragment;

import com.time.master.R;
import com.time.master.view.BasicTextView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class AddFragment extends Fragment implements OnClickListener{
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View layout = inflater.inflate(R.layout.mainpage_add, container, false);
		BasicTextView textView=(BasicTextView)layout.findViewById(R.id.add_page_add);
		textView.setOnClickListener(this);
		return layout;
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_page_add:
			Toast.makeText(getActivity(), "111", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
		
	}

}
