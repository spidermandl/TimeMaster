package com.time.master.fragment;

import com.time.master.R;
import com.time.master.activity.MainActivity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Page1Fragment extends Fragment implements OnClickListener{
 TextView textView;
 @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	View layout=inflater.inflate(R.layout.page1, container, false);
	
	textView=(TextView)layout.findViewById(R.id.page1_endtv);
	Button button=(Button)layout.findViewById(R.id.page2_registbtn);
	button.setOnClickListener(this);
	textView.setOnClickListener(this);
	return layout;
}

@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	switch(v.getId()){
	case R.id.page1_endtv:
		textView.setVisibility(View.GONE);
		break;
	case R.id.page2_registbtn:
		MainActivity activity=(MainActivity)getActivity();
		activity.showNext(new Page4Fragment(), R.id.mainlayout);
	}
}
}
