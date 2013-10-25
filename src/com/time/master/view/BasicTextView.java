package com.time.master.view;

import com.time.master.R;
import com.time.master.interfacer.LayoutStyleableInterface;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;
/***
 * 基本TextView，BasicViewGroup的子view
 * @author Desmond
 *
 */
public class BasicTextView extends TextView implements LayoutStyleableInterface{


	/**单位宽度的倍数*/
	protected int multi_width;
	/**单位高度的倍数*/
	protected int multi_height;
	/**起始行单位空间*/
	protected boolean isNewLine;
	/**默认背景色*/
	protected int naturalColor;
	/**所属控件组*/
	protected int group;
	/**判断是否被选中*/
	protected boolean isSelected=false;

	protected int status=0;//状态值
	
	public BasicTextView(Context context) {
		super(context);
	}
	
	public BasicTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewGroupType);
		multi_width = a.getInt(R.styleable.ViewGroupType_width_multi, 1);

		isNewLine=a.getBoolean(R.styleable.ViewGroupType_new_line, false);
		naturalColor=a.getInt(R.styleable.ViewGroupType_default_bg, -1);
		group=a.getInt(R.styleable.ViewGroupType_attr_group, -1);
        a.recycle();
        init();
	}
	
	public BasicTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewGroupType, defStyle, 0);
		multi_width = a.getInt(R.styleable.ViewGroupType_width_multi, 1);
		isNewLine=a.getBoolean(R.styleable.ViewGroupType_new_line, false);
		naturalColor=a.getInt(R.styleable.ViewGroupType_default_bg, -1);
        a.recycle();
        init();
	}

	/**还原成未选中状态颜色*/
	public void setNaturalBackground(){
		isSelected=false;
		if(naturalColor!=1){
			this.setBackgroundColor(naturalColor);
		}
	}

	/**按钮是否被选中*/
	public boolean isSelected(){
		return isSelected;
	}
	
	protected void init(){
		if(naturalColor!=-1)
			setBackgroundColor(naturalColor);
	}

	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			/**点击背景颜色变化*/
			actionDown();
			break;
		case MotionEvent.ACTION_UP:
			actionUp();
			break;
		default:
			break;
		}
		return super.onTouchEvent(event);
	}
	
	protected void actionDown(){
		if(naturalColor!=-1)
			this.setBackgroundColor(0xFFFFFFFF);
	}
	protected void actionUp(){
		if(naturalColor!=-1)
			this.setBackgroundColor(naturalColor);
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
		return group;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	

}
