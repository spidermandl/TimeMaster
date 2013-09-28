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

/**
 * 系统提示页面
 * 
 * @author Desmond
 * 
 */
public class SystemPrompt extends Fragment implements OnClickListener {
	TextView tvInformapush;
	BasicTextView tvUsername,tvRememberpw,tvLogin,tvForgetpw,tvKeyboard;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 设置按钮和textview点击监听事件
		View layout = inflater.inflate(R.layout.page_system_prompt, container, false);

		tvInformapush = (TextView) layout.findViewById(R.id.systemprompt_tvEnd);
		tvUsername=(BasicTextView)layout.findViewById(R.id.informapush_tvUsername);
		tvRememberpw=(BasicTextView)layout.findViewById(R.id.informapush_tvRememberpw);
		tvLogin=(BasicTextView)layout.findViewById(R.id.informapush_tvLogin);
		tvForgetpw=(BasicTextView)layout.findViewById(R.id.informapush_tvFogetpw);
		tvKeyboard=(BasicTextView)layout.findViewById(R.id.informapush_tvKeyboard);
		
		Button button = (Button) layout.findViewById(R.id.informapush_btnRegist);
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
		// 点击事件，点击按钮，跳转到下一个页面
		switch (v.getId()) {
		case R.id.systemprompt_tvEnd:
			tvInformapush.setVisibility(View.GONE);
			break;
		case R.id.informapush_btnRegist:
			MainActivity activity = (MainActivity) getActivity();
			activity.showNext(new SystemProtocol(), R.id.mainlayout);
			break;
		// 点击事件，点击切换图片
		case R.id.informapush_tvUsername:
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
			// 点击事件，点击切换图片
		case R.id.informapush_tvRememberpw:
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
			// 点击事件，点击切换图片
		case R.id.informapush_tvLogin:
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
			// 点击事件，点击切换图片
		case R.id.informapush_tvFogetpw:
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
			// 点击事件，点击切换图片
		case R.id.informapush_tvKeyboard:
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
