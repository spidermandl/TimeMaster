package com.time.master.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class TimeMasterGirdView extends GridView { 

    public TimeMasterGirdView(Context context, AttributeSet attrs) { 
        super(context, attrs); 
    } 

    public TimeMasterGirdView(Context context) { 
        super(context); 
      
    } 

    public TimeMasterGirdView(Context context, AttributeSet attrs, int defStyle) { 
        super(context, attrs, defStyle); 
       
    } 
    

    @Override 
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { 

        int expandSpec = MeasureSpec.makeMeasureSpec( 
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST); 
        super.onMeasure(widthMeasureSpec, expandSpec); 
    } 
} 