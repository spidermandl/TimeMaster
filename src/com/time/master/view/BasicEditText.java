package com.time.master.view;

import com.time.master.R;
import com.time.master.interfacer.LayoutStyleableInterface;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.EditText;

public class BasicEditText extends EditText implements LayoutStyleableInterface{

	int multi_width;
	boolean isNewLine;
	boolean isTop;
	boolean isBottom;
	boolean isVisible;
	
	public BasicEditText(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public BasicEditText(Context context, AttributeSet attrs) {
		this(context, attrs,0);
		// TODO Auto-generated constructor stub
	}
	
	public BasicEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewGroupType, defStyle, 0);
		multi_width = a.getInt(R.styleable.ViewGroupType_width_multi, 1);
		isNewLine=a.getBoolean(R.styleable.ViewGroupType_new_line, false);
		isTop=a.getBoolean(R.styleable.ViewGroupType_top, false);
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
	public boolean isTop() {
		// TODO Auto-generated method stub
		return isTop;
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
