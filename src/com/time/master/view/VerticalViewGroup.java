package com.time.master.view;

import java.util.ArrayList;
import java.util.HashMap;

import com.time.master.TimeMasterApplication;
import com.time.master.interfacer.LayoutStyleableInterface;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class VerticalViewGroup extends BasicViewGroup{

	HashMap<Integer, ArrayList<LayoutStyleableInterface>> groupMap=new HashMap<Integer, ArrayList<LayoutStyleableInterface>>();
	public VerticalViewGroup(Context context) {
		super(context);
		this.context=context;
		init();
	}
	
	public VerticalViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context=context;
		init();
	}
	
	public VerticalViewGroup(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context=context;
		init();
	}
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		init();
		singleTouch();
		int num=this.getChildCount();
		int max_line=0;
		int line=0;
		int width=0;
		//int max_width=0;//计算最大宽度的行
		for(int i=0;i<num;i++){
			View view=this.getChildAt(i);
			LayoutStyleableInterface styleable=(LayoutStyleableInterface)view;
			if(styleable.isNewLine()){
				if(line>max_line)
					max_line=line;
				line=0;
				width+=styleable.getMultiWidth()*unit_width+styleable.getMultiWidth()*gap;
			}
				line++;
		}
		int height=max_line==0?0:(int)(line*unit_width*0.75+gap*(line+1));
		setMeasuredDimension(width,height);
	}
	private void singleTouch() {
		int childcount=this.getChildCount();
		for(int i=0;i<childcount;i++){
			View view=this.getChildAt(i);
			if(view instanceof LayoutStyleableInterface){
				int group=((LayoutStyleableInterface)view).getGroup();
				/**未分组状态下，group为-1*/
				if(group!=-1){
					if(view instanceof SelectedTextView)
						((SelectedTextView)view).setSingleSelectedInterface(singleInterface);
						//((SelectedTextView)view).setSingleSelectedInterface()
					ArrayList<LayoutStyleableInterface> list;
					if(groupMap.containsKey(group))
						list=groupMap.get(group);
					else{
						list=new ArrayList<LayoutStyleableInterface>();
						groupMap.put(group, list);
					}
					list.add((LayoutStyleableInterface)view);
					
				}
			}
		}
		
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		init();
		int num=this.getChildCount();
		int last_view_width=0;
		for(int i=0;i<num;i++){
			View view=this.getChildAt(i);
			LayoutStyleableInterface styleable=(LayoutStyleableInterface)view;
			ViewGroup.LayoutParams layoutParams=view.getLayoutParams();
			
			
			layoutParams.width=styleable.getMultiWidth()*unit_width+(styleable.getMultiWidth()-1)*gap;
			
			if(styleable.getGroup()==10){
				layoutParams.height=(int)(unit_width*0.75)*10;
			
			}
			else
				layoutParams.height=(int)(unit_width*0.75);
			if(styleable.isNewLine()){
				current_margin_top=gap;
				if(i==0){
					current_margin_left=gap;
				}
				else
				current_margin_left+=last_view_width+gap;
				last_view_width=0;
			}
			if(layoutParams.width>last_view_width)
				last_view_width=layoutParams.width;
			
			view.setLayoutParams(layoutParams);
			/***设置子view 大小，子view的onMeasure方法被回调 **/
			view.measure(
					MeasureSpec.makeMeasureSpec(layoutParams.width, MeasureSpec.EXACTLY), 
					MeasureSpec.makeMeasureSpec(layoutParams.height, MeasureSpec.EXACTLY));
			view.layout(current_margin_left, 
					current_margin_top, 
					current_margin_left+layoutParams.width, 
					current_margin_top+layoutParams.height);
			current_margin_top+=layoutParams.height+gap;
		

		}
	}
	@Override
	protected void init() {
		int screen_mode=context.getResources().getConfiguration().orientation;
		screen_width=TimeMasterApplication.getInstance().getScreen_W();
		if(screen_mode==2)
			screen_width=screen_width*7/8-screen_width/64;
		screen_height=TimeMasterApplication.getInstance().getScreen_H();
		unit_width=screen_width/6;
		gap=screen_width/36;
	}
	SingleSelectedInterface singleInterface=new SingleSelectedInterface() {
		
		@Override
		public void setSingleView(LayoutStyleableInterface Ilayout) {
			ArrayList<LayoutStyleableInterface> list=groupMap.get(Ilayout.getGroup());
			for(LayoutStyleableInterface i:list){
				Log.e("GroupSingleTouchTextView", "setSingleView");
				if(Ilayout!=i&&((SelectedTextView)i).isSelected()){
					((SelectedTextView)i).setNaturalBackground();
				}
			}
			
		}
	};
}
