package com.time.master.dialog;

/**
 * 世界时间选择界面
 * @author ZhouYongJian
 *
 */

import com.time.master.view.SelectedTextView;

import android.app.DialogFragment;
import android.app.SearchManager.OnCancelListener;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView.FindListener;



public  class WorldTimeDialogFragment extends BasicDialogFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Light);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		getDialog().setCanceledOnTouchOutside(true);// 点击dialog以外区域，关闭dialog
		Window window = getDialog().getWindow();
		window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
		window.setWindowAnimations(R.style.wheelAnimation); // 添加动画
		WindowManager.LayoutParams para = (WindowManager.LayoutParams) window
				.getAttributes();
		para.height = LayoutParams.WRAP_CONTENT;
		para.width = LayoutParams.MATCH_PARENT;
		window.setAttributes(para);
		window.clearFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND
				| WindowManager.LayoutParams.FLAG_DIM_BEHIND);

		View layout = inflater.inflate(R.layout.world_time_layout, container,
				false);
		idlerWheel=(SelectedTextView)layout.findViewById(R.id.idlerwheel);
		conversion=(SelectedTextView)layout.findViewById(R.id.conversion);
		idlerWheel.setOnClickListener(this);
		conversion.setOnClickListener(this);
		beijing=(SelectedTextView)layout.findViewById(R.id.beijing);
		beijing.setOnClickListener(this);
=======
		setDialogStyle();
		
        View layout=inflater.inflate(R.layout.world_time_layout, container, false);
        View context=layout.findViewById(R.id.worldtime);
>>>>>>> master
		return layout;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.idlerwheel:
			idlerWheel.setBackgroundColor(0xFFFFFFFF);
			conversion.setBackgroundColor(0xFFFFFF00);
			break;
		case R.id.conversion:
			idlerWheel.setBackgroundColor(0xFFFFFF00);
			conversion.setBackgroundColor(0xFFFFFFFF);
			break;
		case R.id.chineseandenglish:
			if(beijing.getText()=="北京"){
				beijing.setText("Bei\nKin");
				
			}else{
				beijing.setText("北京");
			}
			
			break;
		default:
			break;
		}
	}

}
