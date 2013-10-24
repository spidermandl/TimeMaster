package com.time.master.dialog;

import java.util.HashMap;

import com.time.master.interfacer.WheelResultInterface;
import com.time.master.tool.ChineseCalendar;

import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * wheelDialog抽象出共有操作功能
 * @author Desmond
 *
 */
public abstract class WheelDialogFragment extends BasicDialogFragment {

	protected EditText editText;
	protected TextView confirm;
	protected TextView mode;
	
	
	private WheelResultInterface wheelInterface;
	
	
	
	public void setWheelInterface(WheelResultInterface wheelInterface) {
		this.wheelInterface = wheelInterface;
	}
	
	/**
	 * 在子类onCreateView中被调用
	 * 初始化控件功能
	 */
	protected void superInit(){
        
		if(editText!=null){
		    editText.setInputType(InputType.TYPE_NULL);
		    editText.setText(getSelectedString());
		}
        
		if(confirm!=null&&wheelInterface!=null){
			confirm.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					WheelDialogFragment.this.dismiss();
					
					pushConfirm();
					wheelInterface.getResult(getSelectedString());
				}
			});
		}
	}
	
	abstract protected String getSelectedString();
	/** 确认按钮事件*/
	abstract protected void pushConfirm();
	
	abstract protected int getSelectedInt();

	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
