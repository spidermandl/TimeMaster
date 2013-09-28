package com.time.master.fragment.login;

import com.time.master.R;
import com.time.master.activity.MainActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

/**
 * 完善个人信息界面
 * 
 * @author Desmond
 * 
 */
public class PersonalInformation extends Fragment implements OnClickListener{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//设置按钮监听事件
		View layout = inflater.inflate(R.layout.page_personal_information,container,false);
		View next=layout.findViewById(R.id.personal_btnEnd);
		next.setOnClickListener(this);
		return layout;
	}

	@Override
	public void onClick(View v) {
		//按钮点击事件
		switch (v.getId()) {
		case R.id.personal_btnEnd:
			MainActivity activity=(MainActivity)getActivity();
			activity.showNext(new FamilyInformation(),R.id.mainlayout);
			break;
		}
	}
}
