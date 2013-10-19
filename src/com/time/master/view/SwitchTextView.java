package com.time.master.view;

import com.time.master.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;

/***
 * µã»÷×ÖÌå×ª»»TextView
 * @author Desmond
 *
 */
public class SwitchTextView extends BasicTextView {

	String firstHalf,secondHalf;
	
	public SwitchTextView(Context context) {
		super(context);
	}
	public SwitchTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SwitchTextView);
		firstHalf = a.getString(R.styleable.SwitchTextView_first_half);
		secondHalf = a.getString(R.styleable.SwitchTextView_second_half);
		
	}
	public SwitchTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SwitchTextView);
		firstHalf = a.getString(R.styleable.SwitchTextView_first_half);
		secondHalf = a.getString(R.styleable.SwitchTextView_second_half);
	}

	@Override
	protected void init() {
		super.init();
		if(firstHalf==null)
			firstHalf="";
		if(secondHalf==null)
			secondHalf="";
	}
	@Override
	protected void actionDown() {
		if(isSelected){
			SpannableStringBuilder datestyle = new SpannableStringBuilder(firstHalf+"\n"+secondHalf);
			datestyle.setSpan(new ForegroundColorSpan(Color.BLACK), 0, firstHalf.length()+1,
					Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
			datestyle.setSpan(new ForegroundColorSpan(Color.WHITE), firstHalf.length()+1, firstHalf.length()+1+secondHalf.length(),
					Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
			this.setText(datestyle);
			this.setBackgroundColor(naturalColor);
		}
		else{

			SpannableStringBuilder datestyle = new SpannableStringBuilder(secondHalf+"\n"+firstHalf);
			datestyle.setSpan(new ForegroundColorSpan(Color.BLACK), 0, secondHalf.length()+1,
					Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
			datestyle.setSpan(new ForegroundColorSpan(Color.WHITE), secondHalf.length()+1, firstHalf.length()+1+secondHalf.length(),
					Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
			this.setText(datestyle);
			this.setBackgroundColor(Color.YELLOW);
		}
		
		isSelected=isSelected?false:true;
	}
	@Override
	protected void actionUp() {
		
	}
}
