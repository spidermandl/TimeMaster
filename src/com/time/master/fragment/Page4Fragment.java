package com.time.master.fragment;

import com.time.master.R;
import com.time.master.activity.MainActivity;
import com.time.master.view.BasicTextView;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class Page4Fragment extends Fragment implements OnClickListener{
	BasicTextView tvagree,tvdisagree;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View layout = inflater.inflate(R.layout.page4,container,false);
		 tvagree=(BasicTextView)layout.findViewById(R.id.page4_agreetv);
		 tvdisagree=(BasicTextView)layout.findViewById(R.id.page4_disagreetv);
		
		 tvagree.setOnClickListener(this);
		 tvdisagree.setOnClickListener(this);
		return layout;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
	case R.id.page4_disagreetv:
		if(tvdisagree.isclick()){
			tvdisagree.setIsclick(false);
			Drawable drawable=getResources().getDrawable(R.drawable.checkbox);
			drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
			tvdisagree.setCompoundDrawables(drawable, null, null, null);
		}
		else {
			tvdisagree.setIsclick(true);
			Drawable drawable=getResources().getDrawable(R.drawable.checkboxonclick);
			drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
			tvdisagree.setCompoundDrawables(drawable, null, null, null);
		}
		break;
	case R.id.page4_agreetv:
		if(tvagree.isclick()){
			break;
		}
		else {
			tvagree.setIsclick(true);
			Drawable drawable=getResources().getDrawable(R.drawable.checkboxonclick);
			drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
			tvagree.setCompoundDrawables(drawable, null, null, null);
			MainActivity activity=(MainActivity)getActivity();
			activity.showNext(new Page5Fragment(),R.id.mainlayout);
			
		}
		break;// TODO Auto-generated method stub
		}
	}
}
