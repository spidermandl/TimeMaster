/**
 * @梁丽丽
 */
package com.time.master.calendar;

import java.util.Calendar;

import com.time.master.tool.Constant;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout.LayoutParams;

/**
 * 日历控件单元格绘制类
 * 
 * @Description: 日历控件单元格绘制类
 */
public class DateWidgetDayCell extends View {
	// 字体大小
	private static final int fTextSize = 28;

	// 基本元素
	private OnItemClick itemClick = null;
	// The Paint class holds the style and color information about how to draw
	// geometries, text and bitmaps.

	private Paint pt = new Paint();// Create a new paint with default settings
	// The rectangle is represented by the coordinates of its 4 edges (left,
	// top, right bottom). These fields can be accessed directly.
	private RectF rect = new RectF();
	private String sDate = "";

	// 当前日期
	private int iDateYear = 0;// 年
	private int iDateMonth = 0;// 月
	private int iDateDay = 0;// 日

	// 布尔变量
	private boolean bSelected = false;
	private boolean bIsActiveMonth = false;
	private boolean bToday = false;
	private boolean bTouchedDown = false;
	private boolean bHoliday = false;
	private boolean hasRecord = false;

	public static int ANIM_ALPHA_DURATION = 100;

	// 给单元格设置点击事件。
	public interface OnItemClick {
		public void OnClick(DateWidgetDayCell item);
	}

	// 构造函数
	public DateWidgetDayCell(Context context, int iWidth, int iHeight) {
		super(context);
		/**
		 * Set whether this view can receive the focus. Setting this to false
		 * will also ensure that this view is not focusable in touch mode.
		 */
		setFocusable(true);
		setLayoutParams(new LayoutParams(iWidth, iHeight));// 指定单元格的大小
	}

	// 取变量值
	public Calendar getDate() {
		/**
		 * Constructs a new instance of the Calendar subclass appropriate for
		 * the default Locale.
		 */
		Calendar calDate = Calendar.getInstance();
		calDate.clear();// Clears all of the fields of this Calendar. All fields
						// are initialized to zero
		calDate.set(Calendar.YEAR, iDateYear);
		calDate.set(Calendar.MONTH, iDateMonth);
		calDate.set(Calendar.DAY_OF_MONTH, iDateDay);
		return calDate;
	}

	/**
	 * 设置变量值
	 * 
	 * @param iYear
	 *            年份
	 * @param iMonth
	 *            月份
	 * @param iDay
	 *            日期
	 * @param bToday
	 *            当天日期
	 * @param bHoliday
	 *            假期
	 * @param iActiveMonth
	 *            当前月份=8
	 * @param hasRecord
	 *            记录
	 */
	public void setData(int iYear, int iMonth, int iDay, Boolean bToday,
			Boolean bHoliday, int iActiveMonth, boolean hasRecord) {
		iDateYear = iYear;// 设置为当前的年份为2013
		iDateMonth = iMonth;// 设置为当前的月份为当前设置7月份。
		iDateDay = iDay;// 设置为当前的日为当前设置26。

		this.sDate = Integer.toString(iDateDay);// 获得当前日期
		this.bIsActiveMonth = (iDateMonth == iActiveMonth);//
		this.bToday = bToday;// 当前日期默认不是当天
		this.bHoliday = bHoliday;// 当前日期默认不是假日
		this.hasRecord = hasRecord;// 当前日期默认没有记录情况
	}

	// 重载绘制方法
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		/**
		 * Set the rectangle's coordinates(坐标) to the specified values. left The
		 * X coordinate of the left side of the rectangle top The Y coordinate
		 * of the top of the rectangle right The X coordinate of the right side
		 * of the rectangle bottom The Y coordinate of the bottom of the
		 * rectangle
		 */
		rect.set(0, 0, this.getWidth(), this.getHeight());
		rect.inset(1, 1);

		final boolean bFocused = IsViewFocused();

		drawDayView(canvas, bFocused);
		drawDayNumber(canvas);
	}

	// view获得焦点方法，调用isFocused()方法并按下。
	public boolean IsViewFocused() {
		// Returns true if this view has focus
		return (this.isFocused() || bTouchedDown);
	}

	// 绘制日历方格
	private void drawDayView(Canvas canvas, boolean bFocused) {
		// 没有被选中 也没有获得焦点
		if (bSelected || bFocused) {
			LinearGradient lGradBkg = null;

			if (bFocused) {
				/**
				 * Create a shader(着色程序) that draws a linear gradient（梯度） along
				 * a line.
				 *  x0 The x-coordinate for the start of the gradient line 
				 *  y0 The y-coordinate for the start of the gradient line
				 *  x1 The x- for the end of the gradient line 
				 *  y1 The y-coordinate for the end of the gradient line
				 *  color0 The color at the start of the gradient line. 
				 *  color1 The color at the end of the gradient line. 
				 *  tile The Shader tiling mode
				 * 
				 */
				lGradBkg = new LinearGradient(rect.left, 0, rect.right, 0,
						0xffaa5500, 0xffffddbb, Shader.TileMode.CLAMP);
			}

			if (bSelected) {
				lGradBkg = new LinearGradient(rect.left, 0, rect.right, 0,
						0xff225599, 0xffbbddff, Shader.TileMode.CLAMP);
			}

			if (lGradBkg != null) {
				pt.setShader(lGradBkg);
				canvas.drawRect(rect, pt);
			}

			pt.setShader(null);

		} else {
			pt.setColor(getColorBkg(bHoliday, bToday));
			canvas.drawRect(rect, pt);
		}

		if (hasRecord) {
			CreateReminder(canvas, Constant.special_Reminder);
		}
		// else if (!hasRecord && !bToday && !bSelected) {
		// CreateReminder(canvas, Calendar_TestActivity.Calendar_DayBgColor);
		// }
	}

	// 绘制日历中的数字
	public void drawDayNumber(Canvas canvas) {
		// draw day number
		pt.setTypeface(null);//Set or clear the typeface(字型) object. 
		pt.setAntiAlias(true);//  true to set the antialias bit in the flags, false to clear it  
		pt.setShader(null);//May be null. the new shader(着色器) to be installed in the paint
		pt.setFakeBoldText(true);//设置粗体
		pt.setTextSize(fTextSize);//设置字体
		pt.setColor(Constant.isPresentMonth_FontColor);//The new color (including alpha) to set in the paint. 
		pt.setUnderlineText(false);//设置下划线

		if (!bIsActiveMonth) //如果不是本月  设置颜色 
			pt.setColor(Constant.unPresentMonth_FontColor);

		if (bToday)//如果是今天 设置下划线
			pt.setUnderlineText(true);

		final int iPosX = (int) rect.left + ((int) rect.width() >> 1)//设置数字的宽
				- ((int) pt.measureText(sDate) >> 1);

		final int iPosY = (int) (this.getHeight()
				- (this.getHeight() - getTextHeight()) / 2 - pt//设置数字的高
				.getFontMetrics().bottom);

		canvas.drawText(sDate, iPosX, iPosY, pt);//设置数字

		pt.setUnderlineText(false);
	}

	// 得到字体高度
	private int getTextHeight() {
		return (int) (-pt.ascent() + pt.descent()); //数字距离底边的长度减去其距离顶边距离 得到数字高度
	}

	// 根据条件返回不同颜色值
	public static int getColorBkg(boolean bHoliday, boolean bToday) {
		if (bToday)
			return Constant.isToday_BgColor;  //  是当日 设置为此颜色
		// if (bHoliday) //如需周末有特殊背景色，可去掉注释
		// return Calendar_TestActivity.isHoliday_BgColor;
		return Constant.Calendar_DayBgColor;   // 是节假日设置此颜色
	}

	// 设置是否被选中
	@Override
	public void setSelected(boolean bEnable) {
		if (this.bSelected != bEnable) {
			this.bSelected = bEnable;
			this.invalidate();
		}
	}
	// 设置单元格点击
	public void setItemClick(OnItemClick itemClick) {
		this.itemClick = itemClick;
	}
	// 单元格点击事件
	public void doItemClick() {
		if (itemClick != null)
			itemClick.OnClick(this);
	}

	// 点击事件
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean bHandled = false;
		if (event.getAction() == MotionEvent.ACTION_DOWN) { //按下时 此单元格开始动画
			bHandled = true;
			bTouchedDown = true;
			invalidate();
			startAlphaAnimIn(DateWidgetDayCell.this); 
		}
		if (event.getAction() == MotionEvent.ACTION_CANCEL) { //没有动作
			bHandled = true;
			bTouchedDown = false;
			invalidate();
		}
		if (event.getAction() == MotionEvent.ACTION_UP) { //放开时 单元格点击事件
			bHandled = true;
			bTouchedDown = false;
			invalidate();
			doItemClick();
		}
		return bHandled;
	}

	// 点击事件
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		boolean bResult = super.onKeyDown(keyCode, event);
		if ((keyCode == KeyEvent.KEYCODE_DPAD_CENTER)
				|| (keyCode == KeyEvent.KEYCODE_ENTER)) {   //满足条件执行方法
			doItemClick();
		}
		return bResult;
	}

	// 不透明度渐变  被选中区域动画
	public static void startAlphaAnimIn(View view) {  
		AlphaAnimation anim = new AlphaAnimation(0.5F, 1);
		anim.setDuration(ANIM_ALPHA_DURATION);
		anim.startNow();
		view.startAnimation(anim);
	}
	//提醒
	public void CreateReminder(Canvas canvas, int Color) {
		pt.setStyle(Paint.Style.FILL_AND_STROKE); //绑定类型
		pt.setColor(Color); 
		Path path = new Path();
		path.moveTo(rect.right - rect.width() / 4, rect.top); //起始坐标
		path.lineTo(rect.right, rect.top);   
		path.lineTo(rect.right, rect.top + rect.width() / 4);
		path.lineTo(rect.right - rect.width() / 4, rect.top);
		path.close();
		canvas.drawPath(path, pt); //利用画笔画路径
	}
}