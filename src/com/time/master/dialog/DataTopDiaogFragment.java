package com.time.master.dialog;



import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;

import com.time.master.R;
import com.time.master.wheel.adapters.NumericWheelAdapter;
import com.time.master.wheel.widget.OnWheelClickedListener;
import com.time.master.wheel.widget.OnWheelScrollListener;
import com.time.master.wheel.widget.UIWheelView;
import com.time.master.wheel.widget.WheelView;

public class DataTopDiaogFragment extends WheelDialogFragment implements
OnTouchListener, android.view.View.OnClickListener {
	
	public static final String TAG="DataDialogFragment";
	private Day aboutDay;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME,android.R.style.Theme_Light);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.getDialog().setCanceledOnTouchOutside(true);
		Window window=this.getDialog().getWindow();
		window.setGravity(Gravity.BOTTOM);
		window.setWindowAnimations(R.style.wheelAnimation);
		WindowManager.LayoutParams para=(WindowManager.LayoutParams)window.getAttributes();
		para.width=LayoutParams.MATCH_PARENT;
		para.height=LayoutParams.WRAP_CONTENT;
		window.setAttributes(para);
		window.clearFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND|WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		
		aboutDay=new Day();
	    for(int i=0;i<=100;i++){
	    	aboutDay.setDay(i);
	    	aboutDay.setAdd_times(i);
	    	aboutDay.setDay_times(i);
	    	aboutDay.setSpace(i);
	    	aboutDay.setWhatever(i);
	    }
		
	    View layout = inflater.inflate(R.layout.data_top_layout, container, false);
	    
		day=(UIWheelView)layout.findViewById(R.id.day);
	    dayAdapter=new NumericWheelAdapter(getActivity(), aboutDay.day-100, aboutDay.day+0);
	    dayAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
	    dayAdapter.setItemTextResource(R.id.numeric_text);
	    day.setViewAdapter(dayAdapter);
	    day.setBackground(R.drawable.wheel_bg_full);
	    day.setCurrentItem(50);
	    day.addScrollingListener(new OnWheelScrollListener() {
			
			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				aboutDay.day=wheel.getCurrentItem();
			}
		});
	    	day.addClickingListener(clickListener);
	    	
	    	add_times=(UIWheelView)layout.findViewById(R.id.add_times);
		    addtimesAdapter=new NumericWheelAdapter(getActivity(), aboutDay.add_times-100, aboutDay.add_times+0);
		    addtimesAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
		    addtimesAdapter.setItemTextResource(R.id.numeric_text);
		    add_times.setViewAdapter(addtimesAdapter);
		    add_times.setBackground(R.drawable.wheel_bg_full);
		    add_times.setCurrentItem(50);
		    add_times.addScrollingListener(new OnWheelScrollListener() {
				
				@Override
				public void onScrollingStarted(WheelView wheel) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onScrollingFinished(WheelView wheel) {
					// TODO Auto-generated method stub
					aboutDay.add_times=wheel.getCurrentItem();
				}
			});
		    add_times.addClickingListener(clickListener);
		    	
		    day_times=(UIWheelView)layout.findViewById(R.id.day_times);
		    daytimesAdapter=new NumericWheelAdapter(getActivity(), aboutDay.day_times-100, aboutDay.day_times+0);
		    daytimesAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
		    daytimesAdapter.setItemTextResource(R.id.numeric_text);
		    day_times.setViewAdapter(daytimesAdapter);
		    day_times.setBackground(R.drawable.wheel_bg_full);
		    day_times.setCurrentItem(50);
		    day_times.addScrollingListener(new OnWheelScrollListener() {
				
				@Override
				public void onScrollingStarted(WheelView wheel) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onScrollingFinished(WheelView wheel) {
					// TODO Auto-generated method stub
					aboutDay.day_times=wheel.getCurrentItem();
				}
			});
		    day_times.addClickingListener(clickListener);
	    	
		    space=(UIWheelView)layout.findViewById(R.id.space);
		    spaceAdapter=new NumericWheelAdapter(getActivity(), aboutDay.space-100, aboutDay.space+0);
		    spaceAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
		    spaceAdapter.setItemTextResource(R.id.numeric_text);
		    space.setViewAdapter(dayAdapter);
		    space.setBackground(R.drawable.wheel_bg_full);
		    space.setCurrentItem(50);
		    space.addScrollingListener(new OnWheelScrollListener() {
				
				@Override
				public void onScrollingStarted(WheelView wheel) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onScrollingFinished(WheelView wheel) {
					// TODO Auto-generated method stub
					aboutDay.space=wheel.getCurrentItem();
				}
			});
		    space.addClickingListener(clickListener);
		    
		    whatever=(UIWheelView)layout.findViewById(R.id.whatever);
		    whateverAdapter=new NumericWheelAdapter(getActivity(), aboutDay.whatever-100, aboutDay.whatever+0);
		    whateverAdapter.setItemResource(R.layout.wheel_nemeric_text_item);
		    whateverAdapter.setItemTextResource(R.id.numeric_text);
		    whatever.setViewAdapter(whateverAdapter);
		    whatever.setBackground(R.drawable.wheel_bg_full);
		    whatever.setCurrentItem(50);
		    whatever.addScrollingListener(new OnWheelScrollListener() {
				
				@Override
				public void onScrollingStarted(WheelView wheel) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onScrollingFinished(WheelView wheel) {
					// TODO Auto-generated method stub
					aboutDay.whatever=wheel.getCurrentItem();
				}
			});
		    whatever.addClickingListener(clickListener);
	superInit();
	return layout;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected String getSelectedString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	class Day{
		int day,add_times,day_times,space,whatever;

		public int getDay() {
			return day;
		}

		public void setDay(int day) {
			this.day = day;
		}

		public int getAdd_times() {
			return add_times;
		}

		public void setAdd_times(int add_times) {
			this.add_times = add_times;
		}

		public int getDay_times() {
			return day_times;
		}

		public void setDay_times(int day_times) {
			this.day_times = day_times;
		}

		public int getSpace() {
			return space;
		}

		public void setSpace(int space) {
			this.space = space;
		}

		public int getWhatever() {
			return whatever;
		}

		public void setWhatever(int whatever) {
			this.whatever = whatever;
		}
		
	}
	
	UIWheelView day,add_times,day_times,space,whatever;
	NumericWheelAdapter dayAdapter,addtimesAdapter,daytimesAdapter,spaceAdapter,whateverAdapter;
	OnWheelClickedListener clickListener=new OnWheelClickedListener() {
		
		@Override
		public void onItemClicked(WheelView wheel, int itemIndex) {
			// TODO Auto-generated method stub
			wheel.setCurrentItem(itemIndex,true);
		}
	};
	
	@Override
	protected void pushConfirm() {
		// TODO Auto-generated method stub
		
	}
	
}