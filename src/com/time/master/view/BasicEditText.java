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
	
	public BasicEditText(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public BasicEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewGroupType);
		multi_width = a.getInt(R.styleable.ViewGroupType_width_multi, 1);
		isNewLine=a.getBoolean(R.styleable.ViewGroupType_new_line, false);
        a.recycle();
	}
	
	public BasicEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewGroupType, defStyle, 0);
		multi_width = a.getInt(R.styleable.ViewGroupType_width_multi, 1);
		isNewLine=a.getBoolean(R.styleable.ViewGroupType_new_line, false);
        a.recycle();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
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
	public int getGroup() {
		// TODO Auto-generated method stub
		return -1;
	}
}
