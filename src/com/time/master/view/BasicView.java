package com.time.master.view;

import com.time.master.R;
import com.time.master.interfacer.LayoutStyleableInterface;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

public class BasicView extends View implements LayoutStyleableInterface{

	int multi_width;
	boolean isNewLine;
    boolean isFull;
	boolean isBottom;
	boolean isVisible;
	
	public BasicView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public BasicView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
		// TODO Auto-generated constructor stub
	}
	
	public BasicView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewGroupType, defStyle, 0);
		multi_width = a.getInt(R.styleable.ViewGroupType_width_multi, 1);
		isNewLine=a.getBoolean(R.styleable.ViewGroupType_new_line, false);
		isFull=a.getBoolean(R.styleable.ViewGroupType_full,false);
		isBottom=a.getBoolean(R.styleable.ViewGroupType_bottom, false);
		isVisible=a.getBoolean(R.styleable.ViewGroupType_visible, true);
        a.recycle();
	}

	@Override
	public int getMultiWidth() {
		return multi_width;
	}

	@Override
	public boolean isNewLine() {
		return isNewLine;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return isFull;
	}

	@Override
	public boolean isBottom() {
		// TODO Auto-generated method stub
		return isBottom;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return isVisible;
	}

}