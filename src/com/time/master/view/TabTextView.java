package com.time.master.view;

import com.time.master.TimeMasterApplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;

public class TabTextView extends BasicTextView {

	protected int screen_width,
	screen_height,
	unit_width,//view 单位长度
	gap,//view的间隔长度
	screen_mode; //1代表竖屏 ， 2代表横屏
	
	Paint mPaint,marginPaint;
	
	public TabTextView(Context context) {
		super(context);
		init();
	}
	public TabTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	public TabTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	/***
	 * 初始化所有参数
	 */
	protected void init(){
		screen_mode=TimeMasterApplication.getInstance().getScreenMode();
		screen_width=TimeMasterApplication.getInstance().getScreen_W();
		screen_height=TimeMasterApplication.getInstance().getScreen_H();
		unit_width=screen_width/6;
		gap=screen_width/36;
		mPaint=new Paint();
		mPaint.setColor(0xFFCCCCCC);
		mPaint.setStrokeWidth(5f);
		marginPaint=new Paint();
		marginPaint.setColor(0xFFFFFFFF);
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		this.setMeasuredDimension(gap+unit_width, (int)(unit_width*0.75)+5);
		//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawRect(0, 0, gap/2, unit_width*0.75f, marginPaint);
		canvas.drawRect(gap/2+unit_width, 0, gap+unit_width, unit_width*0.75f, marginPaint);
		canvas.drawRect(gap/2, 0, gap/2+unit_width, unit_width*0.75f, mPaint);
		canvas.drawLine(0, unit_width*0.75f, unit_width+gap, unit_width*0.75f, mPaint);
		super.onDraw(canvas);
	}
	
	public TabTextView setCenterText(String text){
		this.setText(text);
		this.setTextColor(0xFF000000);
		this.setGravity(Gravity.CENTER);
		return this;
	}

}
