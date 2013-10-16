package com.time.master.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

public class SwtchTextView extends SwitchTextView {
	public SwtchTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public SwtchTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	public SwtchTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void actionDown() {
		//super.actionDown();
		if(isSelected){
		
		this.setBackgroundColor(naturalColor);
		}else {
			this.setBackgroundColor(Color.RED);
		}
	}

	@Override
	protected void actionUp() {
		// TODO Auto-generated method stub
		super.actionUp();
	}

}
