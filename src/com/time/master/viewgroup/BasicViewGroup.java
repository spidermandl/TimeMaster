package com.time.master.viewgroup;

import com.time.master.TimeMasterApplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class BasicViewGroup extends ViewGroup{
	int screen_width,margin,textview_width,edittext_width;
	public BasicViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		screen_width=TimeMasterApplication.getInstance().getScreen_width();
		margin=screen_width/12;
		textview_width=screen_width*4/12;
		edittext_width=screen_width*7/12;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		int num=this.getChildCount();
		int s=0;
		for(int i=0;i<num;i++){
			
			if(i%2==0&&i!=0){
				s++;
			}
			int height=(margin/3)*(s+1)+(screen_width/8)*s;
			if(i%2==0){
				View view=this.getChildAt(i);
				LayoutParams layoutParams=view.getLayoutParams();
				layoutParams.width=textview_width;
				layoutParams.height=screen_width/8;
				view.layout(margin/3,height,textview_width+margin/3,height+screen_width/8);
			}
			else {
				View view=this.getChildAt(i);
				LayoutParams layoutParams=view.getLayoutParams();
				layoutParams.width=edittext_width;
				layoutParams.height=screen_width/8;
				view.layout(margin/3*2+textview_width,height,margin/3*2+textview_width+edittext_width,height+screen_width/8);
			}
		}
	}

}
