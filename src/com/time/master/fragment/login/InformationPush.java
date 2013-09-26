package com.time.master.fragment.login;

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

public class InformationPush extends Fragment implements OnClickListener {
	TextView tvInformapush;
	BasicTextView tvUsername,tvRememberpw,tvLogin,tvForgetpw,tvKeyboard;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View layout = inflater.inflate(R.layout.page_information_push, container, false);

		tvInformapush = (TextView) layout.findViewById(R.id.informapush_tvEnd);
		tvUsername=(BasicTextView)layout.findViewById(R.id.informainto_tvUsername);
		tvRememberpw=(BasicTextView)layout.findViewById(R.id.informainto_tvRememberpw);
		tvLogin=(BasicTextView)layout.findViewById(R.id.informainto_tvLogin);
		tvForgetpw=(BasicTextView)layout.findViewById(R.id.informainto_tvFogetpw);
		tvKeyboard=(BasicTextView)layout.findViewById(R.id.informainto_tvKeyboard);
	
		Button button = (Button) layout.findViewById(R.id.informainto_btnRegist);
		tvUsername.setOnClickListener(this);
		tvRememberpw.setOnClickListener(this);
		tvLogin.setOnClickListener(this);
		tvForgetpw.setOnClickListener(this);
		tvKeyboard.setOnClickListener(this);
		button.setOnClickListener(this);
		tvInformapush.setOnClickListener(this);
		return layout;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.informapush_tvEnd:
			tvInformapush.setVisibility(View.GONE);
			break;
		case R.id.informainto_btnRegist:
			MainActivity activity = (MainActivity) getActivity();
			activity.showNext(new SystemProtocol(), R.id.mainlayout);
			break;
		case R.id.informainto_tvUsername:
			if(tvUsername.isclick()){
				tvUsername.setIsclick(false);
				Drawable drawable=getResources().getDrawable(R.drawable.checkbox);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
				tvUsername.setCompoundDrawables(drawable, null, null, null);
			}
			else {
				tvUsername.setIsclick(true);
				Drawable drawable=getResources().getDrawable(R.drawable.checkboxonclick);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
				tvUsername.setCompoundDrawables(drawable, null, null, null);
			}
			break;
		case R.id.informainto_tvRememberpw:
			if(tvRememberpw.isclick()){
				tvRememberpw.setIsclick(false);
				Drawable drawable=getResources().getDrawable(R.drawable.checkbox);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
				tvRememberpw.setCompoundDrawables(drawable, null, null, null);
			}
			else {
				tvRememberpw.setIsclick(true);
				Drawable drawable=getResources().getDrawable(R.drawable.checkboxonclick);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
				tvRememberpw.setCompoundDrawables(drawable, null, null, null);
			}
			break;
		case R.id.informainto_tvLogin:
			if(tvLogin.isclick()){
				tvLogin.setIsclick(false);
				Drawable drawable=getResources().getDrawable(R.drawable.checkbox);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
				tvLogin.setCompoundDrawables(drawable, null, null, null);
			}
			else {
				tvLogin.setIsclick(true);
				Drawable drawable=getResources().getDrawable(R.drawable.checkboxonclick);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
				tvLogin.setCompoundDrawables(drawable, null, null, null);
			}
			break;
		case R.id.informainto_tvFogetpw:
			if(tvForgetpw.isclick()){
				tvForgetpw.setIsclick(false);
				Drawable drawable=getResources().getDrawable(R.drawable.checkbox);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
				tvForgetpw.setCompoundDrawables(drawable, null, null, null);
			}
			else {
				tvForgetpw.setIsclick(true);
				Drawable drawable=getResources().getDrawable(R.drawable.checkboxonclick);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
				tvForgetpw.setCompoundDrawables(drawable, null, null, null);
			}
			break;
		case R.id.informainto_tvKeyboard:
			if(tvKeyboard.isclick()){
				tvKeyboard.setIsclick(false);
				Drawable drawable=getResources().getDrawable(R.drawable.checkbox);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
				tvKeyboard.setCompoundDrawables(drawable, null, null, null);
			}
			else {
				tvKeyboard.setIsclick(true);
				Drawable drawable=getResources().getDrawable(R.drawable.checkboxonclick);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
				tvKeyboard.setCompoundDrawables(drawable, null, null, null);
			}
			break;
		}
		
			
	}
}
