package com.time.master.dialog;

/**
 * "自排：做X 停X" 对话框
 * @author WangHaiyang
 */


import java.util.LinkedList;
import java.util.List;

import com.time.master.R;
import com.time.master.R.string;
import com.time.master.view.BasicTextView;

import android.R.integer;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.EditText;
import android.widget.TextView;

public class RepeatCustomizedDialogFragment extends BasicDialogFragment implements OnClickListener{
	public static final String tag="DoDialogFragment";
	BasicTextView doNext,doConfirm,num0,num1,num2,num3,num4,num5,num6,num7,num8,num9;
	EditText do1,do2,do3,ge1,ge2,ge3;
	LinkedList<EditText> list=new LinkedList<EditText>();
	LinkedList<TextView> linkedList=new LinkedList<TextView>();
	String string=null;
	int index=0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setStyle(DialogFragment.STYLE_NO_FRAME,android.R.style.Theme_Light);
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setDialogStyle();
		
		View layout=inflater.inflate(R.layout.date_repeat_customized_dialog, container, false);
		do1=(EditText)layout.findViewById(R.id.do_do1);
		do2=(EditText)layout.findViewById(R.id.do_do2);
		do3=(EditText)layout.findViewById(R.id.do_do3);
		ge1=(EditText)layout.findViewById(R.id.do_ge1);
		ge2=(EditText)layout.findViewById(R.id.do_ge2);
		ge3=(EditText)layout.findViewById(R.id.do_ge3);
		doConfirm=(BasicTextView)layout.findViewById(R.id.date_yes);
		doNext=(BasicTextView)layout.findViewById(R.id.do_next);
		num0=(BasicTextView)layout.findViewById(R.id.do_num0);
		num1=(BasicTextView)layout.findViewById(R.id.do_num1);
		num2=(BasicTextView)layout.findViewById(R.id.do_num2);
		num3=(BasicTextView)layout.findViewById(R.id.do_num3);
		num4=(BasicTextView)layout.findViewById(R.id.do_num4);
		num5=(BasicTextView)layout.findViewById(R.id.do_num5);
		num6=(BasicTextView)layout.findViewById(R.id.do_num6);
		num7=(BasicTextView)layout.findViewById(R.id.do_num7);
		num8=(BasicTextView)layout.findViewById(R.id.do_num8);
		num9=(BasicTextView)layout.findViewById(R.id.do_num9);
		
		do1.setOnClickListener(this);	
		do2.setOnClickListener(this);
		do3.setOnClickListener(this);
		ge1.setOnClickListener(this);
		ge2.setOnClickListener(this);
		ge3.setOnClickListener(this);
		doConfirm.setOnClickListener(this);
		doNext.setOnClickListener(this);
		num0.setOnClickListener(this);
		num1.setOnClickListener(this);
		num2.setOnClickListener(this);
		num3.setOnClickListener(this);
		num4.setOnClickListener(this);
		num5.setOnClickListener(this);
		num6.setOnClickListener(this);
		num7.setOnClickListener(this);
		num8.setOnClickListener(this);
		num9.setOnClickListener(this);
		
		list.add(do1);
		list.add(ge1);
		list.add(do2);
		list.add(ge2);
		list.add(do3);
		list.add(ge3);
		
		linkedList.add(num0);
		linkedList.add(num1);
		linkedList.add(num2);
		linkedList.add(num3);
		linkedList.add(num4);
		linkedList.add(num5);
		linkedList.add(num6);
		linkedList.add(num7);
		linkedList.add(num8);
		linkedList.add(num9);
		
		return layout;
	}
//	private void onChange(int id){
////		switch (id) {
////		case R.id.do_num0:
////			list.get(id).getText();
////			break;
////		case R.id.do_num1:
////			list.get(id).getText();
////			break;
////
////		default:
////			break;
////		}
//		for(int i=0;i<9;i++){
//		if(list.get(i).getId()==R.id.do_num0){
//			list.get(i).getText();
////			list.get(index).setText(string);
//		}
//		if(list.get(i).getId()==R.id.do_num1){
//			list.get(i).getText();
////			list.get(index).setText(string);
//		}
//		if(list.get(i).getId()==R.id.do_num2){
//			list.get(i).getText();
////			list.get(index).setText(string);
//		}
//		if(list.get(i).getId()==R.id.do_num3){
//			list.get(i).getText();
////			list.get(index).setText(string);
//		}
//		
//		if(list.get(i).getId()==R.id.do_num4){
//			list.get(i).getText();
////			list.get(index).setText(string);
//		}
//		if(list.get(i).getId()==R.id.do_num5){
//			list.get(i).getText();
////			list.get(index).setText(string);
//		}
//		if(list.get(i).getId()==R.id.do_num6){
//			list.get(i).getText();
////			list.get(index).setText(string);
//		}
//		if(list.get(i).getId()==R.id.do_num7){
//			list.get(i).getText();
////			list.get(index).setText(string);
//		}
//		if(list.get(i).getId()==R.id.do_num8){
//			list.get(i).getText();
////			list.get(index).setText(string);
//		}
//		if(list.get(i).getId()==R.id.do_num9){
//			list.get(i).getText();
////			list.get(index).setText(string);
//		}
//		}	
//	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.do_num0:
			string+=(String)num0.getText();
//			onChange(R.id.do_num0);
		list.get(index).setText(string);
			break;
		case R.id.do_num1:
			string+=(String)num1.getText();
//			onChange(R.id.do_num1);
			list.get(index).setText(string);
//			string+=(String) num0.getText();
				break;
		case R.id.do_num2:
//			onChange(R.id.do_num2);
			string+=(String) num2.getText();
			list.get(index).setText(string);
				break;
		case R.id.do_num3:
//			onChange(R.id.do_num3);
			list.get(index).setText(string);
			string+=(String) num3.getText();
				break;
		case R.id.do_num4:
//			onChange(R.id.do_num4);
			string+=(String) num4.getText();
			list.get(index).setText(string);
				break;
		case R.id.do_num5:
//			onChange(R.id.do_num5);
			string+=(String) num5.getText();
			list.get(index).setText(string);
				break;
		case R.id.do_num6:
//			onChange(R.id.do_num6);
			string+=(String) num6.getText();
			list.get(index).setText(string);
				break;
		case R.id.do_num7:
//			onChange(R.id.do_num7);
			string+=(String) num7.getText();
			list.get(index).setText(string);
				break;
		case R.id.do_num8:
//			onChange(R.id.do_num8);
			string+=(String) num8.getText();
			list.get(index).setText(string);
				break;
		case R.id.do_num9:
//			onChange(R.id.do_num9);
			string+=(String) num9.getText();
			list.get(index).setText(string);
				break;
				
		case R.id.do_do1:
		    do1.setInputType(InputType.TYPE_NULL);
				break;
		case R.id.do_do2:
			do2.setInputType(InputType.TYPE_NULL);
				break;
		case R.id.do_do3:
			do3.setInputType(InputType.TYPE_NULL);
				break;
		case R.id.do_ge1:
			ge1.setInputType(InputType.TYPE_NULL);
				break;
		case R.id.do_ge2:
			ge2.setInputType(InputType.TYPE_NULL);
				break;
		case R.id.do_ge3:
			ge3.setInputType(InputType.TYPE_NULL);
				break;
				
		case R.id.do_next:
			index+=1;
				break;
		case R.id.date_yes:
			
				break;
		case R.id.do_del:
			
				break;
				
				
			
		default:
			break;
		}
	}
	 
}
