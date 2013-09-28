package com.time.master.fragment.login;

import com.time.master.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

/**
 * 浏览注册页面（点击“信息推送”进入的页面）
 * 
 * @author Desmond
 * 
 */
public class InformationPush extends Fragment implements OnClickListener{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View layout = inflater.inflate(R.layout.page_information_push,container,false);
		return layout;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
