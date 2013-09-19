package com.time.master.view;

import com.time.master.TimeMasterApplication;
import com.time.master.interfacer.LayoutStyleableInterface;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class BasicViewGroup extends ViewGroup{
	
	int screen_width,
	unit_width,//view 单位长度
	gap,//view的间隔长度
	current_margin_top=0,//当前放置y坐标
	current_margin_left=0;//当前放置x坐标
	
	public BasicViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		screen_width=TimeMasterApplication.getInstance().getScreen_W();
		unit_width=screen_width/6;
		gap=screen_width/36;
		current_margin_top-=0.75*unit_width;
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
				current_margin_left=gap;
			}
			LayoutParams layoutParams=view.getLayoutParams();
			layoutParams.width=styleable.getMultiWidth()*unit_width+(styleable.getMultiWidth()-1)*gap;
			layoutParams.height=(int)(unit_width*0.75);
			view.layout(current_margin_left, 
					current_margin_top, 
					current_margin_left+layoutParams.width, 
					current_margin_top+layoutParams.height);
			current_margin_left+=layoutParams.width+gap;
		}
	}

}
