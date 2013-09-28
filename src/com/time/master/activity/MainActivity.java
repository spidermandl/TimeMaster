package com.time.master.activity;

import com.time.master.R;
import com.time.master.fragment.login.SystemPrompt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MainActivity extends FrameActivity implements OnClickListener {

	ViewFlipper viewFlipper = null;
	TextView page1_tvinform;
	Button page2_btnregister, page4_btnconfirm, page5_btnend, page6_btnend,
			page7_btnend, page8_btnend;
	CheckBox page3_cbagree;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainfragment);
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		SystemPrompt pageFragment = new SystemPrompt();
		fragmentTransaction.add(R.id.mainlayout, pageFragment);
		fragmentTransaction.commit();
	}

	// public void showPopupWindow(Context context, View view) {
	// // TODO Auto-generated method stub
	// LayoutInflater
	// inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	// View vPopupWindow=inflater.inflate(R.layout.page2,null);
	// DisplayMetrics display=new DisplayMetrics();
	// getWindowManager().getDefaultDisplay().getMetrics(display);
	// final PopupWindow pWindow=new
	// PopupWindow(vPopupWindow,display.widthPixels,display.heightPixels/2);
	// Button button=(Button)vPopupWindow.findViewById(R.id.page2_btnregister);
	// button.setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// // TODO Auto-generated method stub
	// viewFlipper.showNext();
	// pWindow.dismiss();
	// }
	// });
	// pWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
	// }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
}
