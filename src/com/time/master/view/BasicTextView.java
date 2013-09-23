package com.time.master.view;

import com.time.master.R;
import com.time.master.interfacer.LayoutStyleableInterface;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

public class BasicTextView extends TextView implements LayoutStyleableInterface{

	int multi_width;
	boolean isNewLine;
	boolean isFull;
	boolean isBottom;
	boolean isVisible;
	boolean isclick;
	
	public BasicTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public BasicTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewGroupType);
		multi_width = a.getInt(R.styleable.ViewGroupType_width_multi, 1);
		isNewLine=a.getBoolean(R.styleable.ViewGroupType_new_line, false);
        a.recycle();
	}
	
	public BasicTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewGroupType, defStyle, 0);
		multi_width = a.getInt(R.styleable.ViewGroupType_width_multi, 1);
		isNewLine=a.getBoolean(R.styleable.ViewGroupType_new_line, false);
		isFull=a.getBoolean(R.styleable.ViewGroupType_full, false);
		isBottom=a.getBoolean(R.styleable.ViewGroupType_bottom, false);
		isVisible=a.getBoolean(R.styleable.ViewGroupType_visible, true);
		isclick=false;
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
	public boolean isFull(){
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

	public boolean isclick() {
		return isclick;
	}

	public void setIsclick(boolean isclick) {
		this.isclick = isclick;
	}
}
