package com.time.master.view;

import com.time.master.interfacer.LayoutStyleableInterface;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class GapCleanViewGroup extends BasicViewGroup {

	public GapCleanViewGroup(Context context) {
		super(context);
		init();
	}
	
	public GapCleanViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public GapCleanViewGroup(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int num=this.getChildCount();
		int line=0;
		int width=0;
		int max_width=0;//计算最大宽度的行
		for(int i=0;i<num;i++){
			View view=this.getChildAt(i);
			LayoutStyleableInterface styleable=(LayoutStyleableInterface)view;
			if(styleable.isNewLine()){
				if(width>max_width)
					max_width=width;
				width=0;
				line++;
			}
			width+=styleable.getMultiWidth()*unit_width+(styleable.getMultiWidth()-1)*gap;
		}
		max_width=max_width<width?width:max_width;
		int height=line==0?0:(int)(line*unit_width*0.75+gap*(line+1));
		
		setMeasuredDimension(max_width,height);
		//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		init();
		int num=this.getChildCount();
		for(int i=0;i<num;i++){
			View view=this.getChildAt(i);
			LayoutStyleableInterface styleable=(LayoutStyleableInterface)view;
			if(styleable.isNewLine()){
				current_margin_top+=0.75*unit_width+gap;
				current_margin_left=0;
			}
			ViewGroup.LayoutParams layoutParams=view.getLayoutParams();
			layoutParams.width=styleable.getMultiWidth()*unit_width+(styleable.getMultiWidth()-1)*gap;
			layoutParams.height=(int)(unit_width*0.75);
			
			view.setLayoutParams(layoutParams);
			/***设置子view 大小，子view的onMeasure方法被回调 **/
			view.measure(
					MeasureSpec.makeMeasureSpec(layoutParams.width, MeasureSpec.EXACTLY), 
					MeasureSpec.makeMeasureSpec(layoutParams.height, MeasureSpec.EXACTLY));
			view.layout(current_margin_left, 
					current_margin_top, 
					current_margin_left+layoutParams.width, 
					current_margin_top+layoutParams.height);
			current_margin_left+=layoutParams.width;

		}

		
	}

}
