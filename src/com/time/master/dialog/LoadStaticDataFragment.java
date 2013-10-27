package com.time.master.dialog;

import com.time.master.TimeMasterApplication;
import com.time.master.database.TimeMasterHelper;
import com.time.master.tool.ExcelParseTool;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 初始化数据库进度对话框
 * @author Desmond
 *
 */
public class LoadStaticDataFragment extends DialogFragment {

	private static final int LOADING_DATA=0;
	private static final int LOADING_FINISHED=1;
	
	int dot=2;
	boolean finished=false;
	TextView content;
	/**更新UI*/
	Handler handler=new Handler(){	
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case LOADING_DATA:
				content.setText(dot==2?"加载数据。。":"加载数据。。。");
				dot=dot==2?3:2;
				break;
			case LOADING_FINISHED:
				finished=true;
				TimeMasterApplication.getInstance().setDataInitialized(true);
				LoadStaticDataFragment.this.dismiss();
				break;
			default:
				break;
			}
		};
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setStyle(DialogFragment.STYLE_NO_FRAME,android.R.style.Theme_Light);
		content=new TextView(this.getActivity());
		this.setCancelable(false);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
//				ExcelParseTool.initNationalLocationByExcel(LoadStaticDataFragment.this.getActivity(), 
//						TimeMasterApplication.getInstance().getDatabaseHelper().getDb());
				handler.sendEmptyMessage(LOADING_FINISHED);
				
			}
		}).start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (!finished) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					handler.sendEmptyMessage(LOADING_DATA);
				}
				
			}
		}).start();
		
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	   return new AlertDialog.Builder(getActivity())
	    .setView(content)
        .create();
	}
}
