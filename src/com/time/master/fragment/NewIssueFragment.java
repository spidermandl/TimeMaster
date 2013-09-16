package com.time.master.fragment;

import com.time.master.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NewIssueFragment extends Fragment implements OnClickListener{
	LinearLayout linearLayout1;
	LinearLayout linearLayout2;
	LinearLayout linearLayout3;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout=inflater.inflate(R.layout.new_issue, container, false);
		linearLayout1=(LinearLayout) layout.findViewById(R.id.newpage_linearlayout_one);
		linearLayout1.setVisibility(View.GONE);
		linearLayout2=(LinearLayout) layout.findViewById(R.id.newpage_linearlayout_two);
		linearLayout2.setVisibility(View.GONE);
		linearLayout3=(LinearLayout) layout.findViewById(R.id.newpage_linearlayout_three);
		linearLayout3.setVisibility(View.GONE);
		TextView textView1=(TextView) layout.findViewById(R.id.textView1);
		TextView textView2=(TextView) layout.findViewById(R.id.textView2);
		TextView textView3=(TextView) layout.findViewById(R.id.textView3);
		textView1.setOnClickListener(this);
		textView2.setOnClickListener(this);
		textView3.setOnClickListener(this);
		return layout;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.textView1:
			linearLayout1.setVisibility(View.VISIBLE);
			linearLayout2.setVisibility(View.GONE);
			linearLayout3.setVisibility(View.GONE);
			break;
		case R.id.textView2:
			linearLayout1.setVisibility(View.GONE);
			linearLayout2.setVisibility(View.VISIBLE);
			linearLayout3.setVisibility(View.GONE);
			break;
		case R.id.textView3:
			linearLayout1.setVisibility(View.GONE);
			linearLayout2.setVisibility(View.GONE);
			linearLayout3.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}
}
