package com.time.master.view;

import android.content.Context;
import android.util.AttributeSet;

/**
 * 从数据库加载子view内容
 * @author Desmond
 *
 */
public class CursorViewGroup extends BasicViewGroup {

	public CursorViewGroup(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CursorViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public CursorViewGroup(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);

	}
	
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		super.init();
	}

}
