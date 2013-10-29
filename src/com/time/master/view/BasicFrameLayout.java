package com.time.master.view;

import com.time.master.R;
import com.time.master.interfacer.LayoutStyleableInterface;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class BasicFrameLayout extends FrameLayout implements LayoutStyleableInterface{
	
	
	int multi_width;
	boolean isNewLine;
	int group;
	
	public BasicFrameLayout(Context context) {
		super(context);
	}
	
	public BasicFrameLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewGroupType);
		multi_width = a.getInt(R.styleable.ViewGroupType_width_multi, 1);

		isNewLine=a.getBoolean(R.styleable.ViewGroupType_new_line, false);
		
		group=a.getInt(R.styleable.ViewGroupType_attr_group, -1);
		
        a.recycle();
      
	}
	
	public BasicFrameLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewGroupType, defStyle, 0);
		multi_width = a.getInt(R.styleable.ViewGroupType_width_multi, 1);
		isNewLine=a.getBoolean(R.styleable.ViewGroupType_new_line, false);
		
        a.recycle();
        
	}


	@Override
	public int getMultiWidth() {
		// TODO Auto-generated method stub
		return multi_width;
	}

	@Override
	public boolean isNewLine() {
		// TODO Auto-generated method stub
		return isNewLine;
	}

	@Override
	public int getGroup() {
		// TODO Auto-generated method stub
		return group;
	}

}
