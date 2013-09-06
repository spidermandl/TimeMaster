package com.time.master.activity;

import com.time.master.R;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class MainActivity extends Activity implements OnClickListener{
	
	ViewFlipper viewFlipper = null;
	TextView page1_tvinform;
	Button page2_btnregister,page4_btnconfirm,page5_btnend,page6_btnend,page7_btnend,page8_btnend;
	CheckBox page3_cbagree;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myviewflipper);
		viewFlipper=(ViewFlipper)findViewById(R.id.MainActivityViewFlipper);
		
		page1_tvinform=(TextView)viewFlipper.getCurrentView().findViewById(R.id.page1_tvinform);
//		page2_btnregister=(Button)viewFlipper.getRootView().findViewById(R.id.page2_btnregister);
		page3_cbagree=(CheckBox)viewFlipper.getRootView().findViewById(R.id.page3_cbagree);
		page4_btnconfirm=(Button)viewFlipper.getRootView().findViewById(R.id.page4_btnconfirm);
		page5_btnend=(Button)viewFlipper.getRootView().findViewById(R.id.page5_btnend);
		page6_btnend=(Button)viewFlipper.getRootView().findViewById(R.id.page6_btnend);
		page7_btnend=(Button)viewFlipper.getRootView().findViewById(R.id.page7_btnend);
		page8_btnend=(Button)viewFlipper.getRootView().findViewById(R.id.page8_btnend);
		
		page1_tvinform.setOnClickListener(this);
//		page2_btnregister.setOnClickListener(this);
		page3_cbagree.setOnClickListener(this);
		page4_btnconfirm.setOnClickListener(this);
		page5_btnend.setOnClickListener(this);
		page6_btnend.setOnClickListener(this);
		page7_btnend.setOnClickListener(this);
		page8_btnend.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.page1_tvinform :
//			viewFlipper.showNext();
			showPopupWindow(MainActivity.this,page1_tvinform);
			break;
//		case R.id.page2_btnregister :
//			viewFlipper.showNext();
//			break;
		case R.id.page3_cbagree :
			viewFlipper.showNext();
			break;
		case R.id.page4_btnconfirm :
			viewFlipper.showNext();
			break;
		case R.id.page5_btnend:
			viewFlipper.showNext();
			break;
		case R.id.page6_btnend:
			viewFlipper.showNext();
			break;
		case R.id.page7_btnend:
			viewFlipper.showNext();
			break;
		case R.id.page8_btnend:
			Intent intent=new Intent();
			intent.setClass(MainActivity.this,FrameActivity.class);
			startActivity(intent);
			break;
		}	
	}
	protected void showPopupWindow(Context context, View view) {
		// TODO Auto-generated method stub
		LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View vPopupWindow=inflater.inflate(R.layout.page2,null);
		DisplayMetrics display=new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(display);
		final PopupWindow pWindow=new PopupWindow(vPopupWindow,display.widthPixels,display.heightPixels/2);
		Button button=(Button)vPopupWindow.findViewById(R.id.page2_btnregister);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				viewFlipper.showNext();
				pWindow.dismiss();
			}
		});
		pWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
	}
}

