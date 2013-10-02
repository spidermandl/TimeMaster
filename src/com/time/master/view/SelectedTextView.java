package com.time.master.view;

import android.content.Context;
import android.util.AttributeSet;
/**
 * 具有选中特效的TextView
 * @author Desmond
 *
 */
public class SelectedTextView extends BasicTextView {
	
	public SelectedTextView(Context context) {
		super(context);
	}	
	public SelectedTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public SelectedTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void actionDown() {
		if(isSelected)
			this.setBackgroundColor(naturalColor);
		else
			this.setBackgroundColor(0xFFFFFFFF);
		
		isSelected=isSelected?false:true;
	}
	@Override
	protected void actionUp() {
		
	}
}
