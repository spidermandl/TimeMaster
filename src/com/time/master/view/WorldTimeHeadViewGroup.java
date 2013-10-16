package com.time.master.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;

import com.time.master.interfacer.LayoutStyleableInterface;

public class WorldTimeHeadViewGroup extends BasicViewGroup{
	public WorldTimeHeadViewGroup(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public WorldTimeHeadViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public WorldTimeHeadViewGroup(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
		
	
	}
}
