package com.time.master.view;

import com.time.master.TimeMasterApplication;
import com.time.master.interfacer.LayoutStyleableInterface;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;

public class BasicviewGroup2 extends BasicViewGroup{

	public BasicviewGroup2(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public BasicviewGroup2(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public BasicviewGroup2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	protected void init(){
		screen_width=TimeMasterApplication.getInstance().getScreen_W();
		unit_width=screen_width/8;
		gap=screen_width/64;
		current_margin_top=(int)(-0.75*unit_width);
	}
protected void onLayout(boolean changed, int l, int t, int r, int b) {
		
		int num=this.getChildCount();
		for(int i=0;i<num;i++){
			View view=this.getChildAt(i);
			LayoutStyleableInterface styleable=(LayoutStyleableInterface)view;
			if(styleable.isNewLine()){
				current_margin_top+=0.75*unit_width+gap;
				current_margin_left=gap;
			}
			ViewGroup.LayoutParams layoutParams=view.getLayoutParams();
			layoutParams.width=styleable.getMultiWidth()*unit_width+(styleable.getMultiWidth()-1)*gap;
			layoutParams.height=(int)(unit_width*0.75);
			if(i==18)
				layoutParams.height=layoutParams.height*2+gap;
			view.setLayoutParams(layoutParams);
			/***设置子view 大小，子view的onMeasure方法被回调 **/
			view.measure(
					MeasureSpec.makeMeasureSpec(layoutParams.width, MeasureSpec.EXACTLY), 
					MeasureSpec.makeMeasureSpec(layoutParams.height, MeasureSpec.EXACTLY));
			view.layout(current_margin_left, 
					current_margin_top, 
					current_margin_left+layoutParams.width, 
					current_margin_top+layoutParams.height);
			current_margin_left+=layoutParams.width+gap;

		}

		init();
	}
}
