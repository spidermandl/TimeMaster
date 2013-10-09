package com.time.master.fragment.login;

import com.time.master.R;
import com.time.master.activity.MainActivity;
import com.time.master.view.BasicTextView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

/**
 * 注册信息界面
 * 
 * @author Desmond
 * 
 */
public class RegisterInformation extends Fragment implements OnClickListener{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 设置按钮监听事件
		View layout = inflater.inflate(R.layout.page_register_information,container,false);
		BasicTextView button=(BasicTextView)layout.findViewById(R.id.register_btnEnd);
		button.setOnClickListener(this);
		return layout;
	}

	@Override
	public void onClick(View v) {
		// 按钮点击事件
		switch (v.getId()) {
		case R.id.register_btnEnd:
			MainActivity activity=(MainActivity)getActivity();
			activity.showNext(new PasswordSet(),R.id.mainlayout);
			break;

		default:
			break;
		}
	}
}
