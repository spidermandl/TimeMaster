package com.time.master.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
/**
 * 具有选中特效的TextView
 * @author Desmond
 *
 */
public class SelectedTextView extends BasicTextView {
	
	private GroupSingleTouchViewGroup.SingleSelectedInterface singleSelectedInterface;
	
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
		if(isSelected){
			this.setBackgroundColor(naturalColor);
		}
		else{
			this.setBackgroundColor(0xFFFFFFFF);
			if(singleSelectedInterface!=null){
				singleSelectedInterface.setSingleView(this);
				Log.e("SelectedTextView", "actionDown");
			}
		}
		
		isSelected=isSelected?false:true;
	}
	@Override
	protected void actionUp() {
	}
	
	public void setSingleSelectedInterface(GroupSingleTouchViewGroup.SingleSelectedInterface singleSelectedInterface) {
		this.singleSelectedInterface = singleSelectedInterface;
	}
}
