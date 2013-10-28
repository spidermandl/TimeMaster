package com.time.master.view;

import com.time.master.TimeMasterApplication;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class TimeMasterGirdView extends GridView { 

    public TimeMasterGirdView(Context context, AttributeSet attrs) { 
        super(context, attrs); 
    } 

    public TimeMasterGirdView(Context context) { 
        super(context); 
        this.setHorizontalSpacing(TimeMasterApplication.getInstance().getScreen_W()/36);
        this.setVerticalSpacing(TimeMasterApplication.getInstance().getScreen_W()/36);
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