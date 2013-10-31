package com.time.master.view;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.time.master.interfacer.LayoutStyleableInterface;

/**
 * 加入组单选功能的的BasicViewGroup2
 * @author desmond.duan
 *
 */
public class GroupSingleSelectedView extends BasicviewGroup2{

    /**子控件按group号分组*/
	HashMap<Integer, ArrayList<LayoutStyleableInterface>> groupMap=new HashMap<Integer, ArrayList<LayoutStyleableInterface>>();
	
	public GroupSingleSelectedView(Context context) {
		super(context);
	}
	
	public GroupSingleSelectedView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public GroupSingleSelectedView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		/**遍历子view，按group号分组*/
		int childcount=this.getChildCount();
		for(int i=0;i<childcount;i++){
			View view=this.getChildAt(i);
			Log.e("GroupSingleTouchTextView", "onmeasure");
			if(view instanceof LayoutStyleableInterface){
				int group=((LayoutStyleableInterface)view).getGroup();
				/**未分组状态下，group为-1*/
				if(group!=-1){
					if(view instanceof SelectedTextView)
						((SelectedTextView)view).setSingleSelectedInterface(singleSelectedInterface);
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
	SingleSelectedInterface singleSelectedInterface=new SingleSelectedInterface() {
		
		@Override
		public void setSingleView(LayoutStyleableInterface Ilayout) {
			int n=0;
			ArrayList<LayoutStyleableInterface> list=groupMap.get(Ilayout.getGroup());
			for(LayoutStyleableInterface i:list){
				n++;
//				Log.e("GroupSingleTouchTextView", "setSingleView");
				
				if(Ilayout!=i&&((SelectedTextView)i).isSelected()){
					((SelectedTextView)i).setNaturalBackground();
				
				}
			}
			
		}
	};
	/**单选按钮组接口*/
	interface SingleSelectedInterface{
		void setSingleView(LayoutStyleableInterface Ilayout);
	}
	
}
