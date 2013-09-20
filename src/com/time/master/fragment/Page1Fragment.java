package com.time.master.fragment;

import com.time.master.R;
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

public class Page1Fragment extends Fragment implements OnClickListener {
	TextView textView;
	BasicTextView checkbox1,checkbox2,checkbox3,checkbox4,checkbox5;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View layout = inflater.inflate(R.layout.page1, container, false);

		textView = (TextView) layout.findViewById(R.id.page1_endtv);
		checkbox1=(BasicTextView)layout.findViewById(R.id.page2_checkBox1);
		checkbox2=(BasicTextView)layout.findViewById(R.id.page2_checkBox2);
		checkbox3=(BasicTextView)layout.findViewById(R.id.page2_checkBox3);
		checkbox4=(BasicTextView)layout.findViewById(R.id.page2_checkBox4);
		checkbox5=(BasicTextView)layout.findViewById(R.id.page2_checkBox5);
	
		Button button = (Button) layout.findViewById(R.id.page2_registbtn);
		checkbox1.setOnClickListener(this);
		checkbox2.setOnClickListener(this);
		checkbox3.setOnClickListener(this);
		checkbox4.setOnClickListener(this);
		checkbox5.setOnClickListener(this);
		button.setOnClickListener(this);
		textView.setOnClickListener(this);
		return layout;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.page1_endtv:
			textView.setVisibility(View.GONE);
			break;
		case R.id.page2_registbtn:
			MainActivity activity = (MainActivity) getActivity();
			activity.showNext(new Page4Fragment(), R.id.mainlayout);
			break;
		case R.id.page2_checkBox1:
			if(checkbox1.isclick()){
				checkbox1.setIsclick(false);
				Drawable drawable=getResources().getDrawable(R.drawable.checkbox);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
				checkbox1.setCompoundDrawables(drawable, null, null, null);
			}
			else {
				checkbox1.setIsclick(true);
				Drawable drawable=getResources().getDrawable(R.drawable.checkboxonclick);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
				checkbox1.setCompoundDrawables(drawable, null, null, null);
			}
			break;
		case R.id.page2_checkBox2:
			if(checkbox2.isclick()){
				checkbox2.setIsclick(false);
				Drawable drawable=getResources().getDrawable(R.drawable.checkbox);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
				checkbox2.setCompoundDrawables(drawable, null, null, null);
			}
			else {
				checkbox2.setIsclick(true);
				Drawable drawable=getResources().getDrawable(R.drawable.checkboxonclick);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
				checkbox2.setCompoundDrawables(drawable, null, null, null);
			}
			break;
		case R.id.page2_checkBox3:
			if(checkbox3.isclick()){
				checkbox3.setIsclick(false);
				Drawable drawable=getResources().getDrawable(R.drawable.checkbox);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
				checkbox3.setCompoundDrawables(drawable, null, null, null);
			}
			else {
				checkbox3.setIsclick(true);
				Drawable drawable=getResources().getDrawable(R.drawable.checkboxonclick);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
				checkbox3.setCompoundDrawables(drawable, null, null, null);
			}
			break;
		case R.id.page2_checkBox4:
			if(checkbox4.isclick()){
				checkbox4.setIsclick(false);
				Drawable drawable=getResources().getDrawable(R.drawable.checkbox);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
				checkbox4.setCompoundDrawables(drawable, null, null, null);
			}
			else {
				checkbox4.setIsclick(true);
				Drawable drawable=getResources().getDrawable(R.drawable.checkboxonclick);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
				checkbox4.setCompoundDrawables(drawable, null, null, null);
			}
			break;
		case R.id.page2_checkBox5:
			if(checkbox5.isclick()){
				checkbox5.setIsclick(false);
				Drawable drawable=getResources().getDrawable(R.drawable.checkbox);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
				checkbox5.setCompoundDrawables(drawable, null, null, null);
			}
			else {
				checkbox5.setIsclick(true);
				Drawable drawable=getResources().getDrawable(R.drawable.checkboxonclick);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
				checkbox5.setCompoundDrawables(drawable, null, null, null);
			}
			break;
		}
		
			
	}
}
