package com.time.master.view;

import com.time.master.TimeMasterApplication;
import com.time.master.interfacer.LayoutStyleableInterface;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;

public class WorldTimeTranViewGroup extends BasicViewGroup {

	public WorldTimeTranViewGroup(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public WorldTimeTranViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public WorldTimeTranViewGroup(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		
		int num=this.getChildCount();
		int line=1;
		for(int i=0;i<num;i++){
			View view=this.getChildAt(i);
			LayoutStyleableInterface styleable=(LayoutStyleableInterface)view;
			if(styleable.isNewLine()){
				line++;
			}
		}
		int height=line==0?0:(int)(line*unit_width*0.75+gap*(line+1));
		setMeasuredDimension(TimeMasterApplication.getInstance().getScreen_W(),height);
	}
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		
		int num=this.getChildCount();
		for(int i=0;i<num;i++){
			View view=this.getChildAt(i);
			LayoutStyleableInterface styleable=(LayoutStyleableInterface)view;
			if(styleable.isNewLine()){
				current_margin_top+=0.75*unit_width+gap;
				current_margin_left=4*unit_width+5*gap;
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
			current_margin_left+=layoutParams.width+gap;
	}
	
	}
}
