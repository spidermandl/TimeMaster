package com.time.master.fragment;
import com.time.master.R;
import com.time.master.activity.MainActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class Page4Fragment extends Fragment implements OnClickListener{
	CheckBox checkBox;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View layout = inflater.inflate(R.layout.page4,container,false);
		checkBox=(CheckBox)layout.findViewById(R.id.page4_agreecb);
		checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					MainActivity activity=(MainActivity)getActivity();
					activity.showNext(new Page5Fragment(),R.id.mainlayout);
					checkBox.setChecked(false);
				}
			}
		});
		return layout;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
