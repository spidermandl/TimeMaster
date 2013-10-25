package com.time.master.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;


public class SingleClickableTextView extends SelectedTextView {
	private ViewParent parentView;
	public SingleClickableTextView(Context context) {
		super(context);
		
	}
	public SingleClickableTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public SingleClickableTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);
		//parentView=this.getParent();
	
	}
	
	
	


}
