package com.time.master.wheel.adapters;

import android.content.Context;

public class MinuteNumbericWheelAdapter extends NumericWheelAdapter{
	 // Values
    private int minValue;
    private int maxValue;
    
    // format
    private String format;
	
	public MinuteNumbericWheelAdapter(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public MinuteNumbericWheelAdapter(Context context, int minValue,
			int maxValue) {
		super(context, minValue, maxValue);
		// TODO Auto-generated constructor stub
	}
	
	public MinuteNumbericWheelAdapter(Context context, int minValue,
			int maxValue, String format) {
		super(context);
		 this.minValue = minValue;
	     this.maxValue = maxValue;
	     this.format = format;
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public CharSequence getItemText(int index) {
		// TODO Auto-generated method stub
		if (index >= 0 && index < getItemsCount()) {
            int value = minValue + index*5;
            return format != null ? String.format(format, value) : Integer.toString(value);
        }
        return null;
	}
	 public int getItemsCount() {
	        return (maxValue - minValue + 1)/5;
	    }    
}
