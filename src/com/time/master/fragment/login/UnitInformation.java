package com.time.master.fragment.login;

import com.time.master.R;
import com.time.master.activity.FrameActivity;
import com.time.master.activity.MainActivity;
import com.time.master.view.BasicTextView;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

/**
 * 完善单位信息界面
 * 
 * @author Desmond
 * 
 */
public class UnitInformation extends Fragment implements OnClickListener{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 设置按钮监听
		View layout = inflater.inflate(R.layout.page_unit_information,container,false);
		BasicTextView button=(BasicTextView)layout.findViewById(R.id.unit_btnEnd);
		button.setOnClickListener(this);
		return layout;
	}

	@Override
	public void onClick(View v) {
		// 按钮点击事件
		switch (v.getId()) {
		case R.id.unit_btnEnd:
			MainActivity activity=(MainActivity)getActivity();
			Intent intent=new Intent();
			intent.setClass(activity, FrameActivity.class);
			startActivity(intent);
			break;
		}
	}
}
