package com.time.master.dialog;

import com.time.master.R;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
/**
 * 抽象基本DialogFragment类
 * @author Desmond
 *
 */
public abstract class BasicDialogFragment extends DialogFragment {
	
	/**
	 * 按键操作缓存
	 */
	protected Bundle viewStatus;
	
	protected void showDialog(DialogFragment dialogFragment) {

		// DialogFragment.show() will take care of adding the fragment
		// in a transaction. We also want to remove any currently showing
		// dialog, so make our own transaction and take care of that here.
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		Fragment prev = getFragmentManager().findFragmentByTag("dialog");
		if (prev != null) {
			ft.remove(prev);
		}
		//ft.addToBackStack(null);

		// Create and show the dialog.
		dialogFragment.show(ft, "dialog");// //
	}
	/**
	 * 设置对话框显示类型
	 */
	protected void setDialogStyle(){
		/****************************************************
		 * 设置对话框属性，高度、宽度、动画、背景
		 ****************************************************/
		getDialog().setCanceledOnTouchOutside(true);//点击dialog以外区域，关闭dialog
		Window window = getDialog().getWindow();
        window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置  
        window.setWindowAnimations(R.style.wheelAnimation);  //添加动画
		WindowManager.LayoutParams para = (WindowManager.LayoutParams) window.getAttributes();
		para.height = LayoutParams.WRAP_CONTENT;
		para.width = LayoutParams.MATCH_PARENT;
		window.setAttributes(para);
		window.clearFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND| WindowManager.LayoutParams.FLAG_DIM_BEHIND);
	}
	
	/**
	 * 设置按钮选中状态
	 * @param view
	 */
	protected void setViewStatus(View view){
		if(view.isSelected())
			viewStatus.putBoolean(view.getId()+"", true);
		else
			viewStatus.putBoolean(view.getId()+"", false);
	}
    @Override
    public void onSaveInstanceState(Bundle arg0) {
    	if(arg0==null)
    		arg0=viewStatus;
    	else{
    		if(viewStatus!=null)
    			arg0.putAll(viewStatus);
    	}
    	super.onSaveInstanceState(arg0);
    }
    
}
