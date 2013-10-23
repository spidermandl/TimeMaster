package com.time.master.view;

import java.util.ArrayList;
import java.util.HashMap;

import com.time.master.interfacer.LayoutStyleableInterface;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class GroupSingleSelectedViewGroup extends BasicviewGroup2 {


    /**子控件按group号分组*/
	HashMap<Integer, ArrayList<LayoutStyleableInterface>> groupMap=new HashMap<Integer, ArrayList<LayoutStyleableInterface>>();
	
	public GroupSingleSelectedViewGroup(Context context) {
		super(context);
	}
	
	public GroupSingleSelectedViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public GroupSingleSelectedViewGroup(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		/**遍历子view，按group号分组*/
		int childcount=this.getChildCount();
		for(int i=0;i<childcount;i++){
			View view=this.getChildAt(i);
			Log.e("GroupSingleSelectedTextView", "onmeasure");
			if(view instanceof LayoutStyleableInterface){
				int group=((LayoutStyleableInterface)view).getGroup();
				/**未分组状态下，group为-1*/
				if(group!=-1){
					if(view instanceof BasicTextView)
						((BasicTextView)view).setSingleSelectedInterface(singleInterface);
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
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
    /**单选操作接口，由子view触发*/
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

	interface SingleSelectedInterface{
		void setSingleView(LayoutStyleableInterface Ilayout);
	}
	
}



