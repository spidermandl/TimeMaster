package com.time.master.calendar;

import java.util.Calendar;

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
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout.LayoutParams;

import com.time.master.tool.Constant;
/*注释者
 * 谢冬
*/
/**
 * 日历控件单元格绘制类
 * @Description: 日历控件单元格绘制类
 */
public class DateWidgetDayCell extends View {
	// 字体大小
	private static final int fTextSize = 28;
	
	// 基本元素
	private OnItemClick itemClick = null;
	private Paint pt = new Paint();//定义一个属性，私有化实例化一个画笔对象pt
	private RectF rect = new RectF();//定义一个矩形，并实例化矩形对象rect
	private String sDate = "";//定义一个字符串类型的属性日期

	// 当前日期
	private int iDateYear = 0;//定义属性当前日期（年）初始化
	private int iDateMonth = 0;//定义属性当前日期（月）初始化
	private int iDateDay = 0;//定义属性当前日期（天）初始化

	// 布尔变量
	private boolean bSelected = false;//定义属性并初始化bSelected
	private boolean bIsActiveMonth = false;//定义属性并初始化bIsActiveMonth
	private boolean bToday = false;//定义属性并初始化bToday
	private boolean bTouchedDown = false;//定义属性并初始化bTouchedDown
	private boolean bHoliday = false;//定义属性并初始化bHoliday
	private boolean hasRecord = false;//定义属性并初始化hasRecord

	public static int ANIM_ALPHA_DURATION = 100;//渐变及时间属性初始化

	
	public interface OnItemClick {//日历类中的接口
		public void OnClick(DateWidgetDayCell item);//类中的抽象方法
	}

	// 构造函数
	public DateWidgetDayCell(Context context, int iWidth, int iHeight) {
		super(context);//继承父类内容
		setFocusable(true);//控制键盘是否可以获得这个按钮的焦点,可以获得
		setLayoutParams(new LayoutParams(iWidth, iHeight));//实例化使自定义布局中的宽和高
	}

	// 取变量值
	public Calendar getDate() {
		Calendar calDate = Calendar.getInstance();//实例化对象calDate并在getInstance()方法中设置变量并返回值
		calDate.clear();//清空日期
		calDate.set(Calendar.YEAR, iDateYear);//传入加年
		calDate.set(Calendar.MONTH, iDateMonth);//传入月
		calDate.set(Calendar.DAY_OF_MONTH, iDateDay);//传入日
		return calDate;//返回日期
	}

	// 设置变量值
	public void setData(int iYear, int iMonth, int iDay, Boolean bToday,
			Boolean bHoliday, int iActiveMonth, boolean hasRecord) {
		//设置日期，年月日，是否为当天，是否为假期，当前有效月，是否已记录，
		iDateYear = iYear;//当前年
		iDateMonth = iMonth;//当前日期
		iDateDay = iDay;//当前日

		this.sDate = Integer.toString(iDateDay);//把当前日整型包装类类型的iDateDay转换成字符串型并实例化
		this.bIsActiveMonth = (iDateMonth == iActiveMonth);//实例化有效月，有效月为当前月
		this.bToday = bToday;//实例化对象bToday
		this.bHoliday = bHoliday;//实力化bHoliday
		this.hasRecord = hasRecord;//实例化hasRecord
	}

	// 重载绘制方法
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);//继承父类构造方法

		rect.set(0, 0, this.getWidth(), this.getHeight());//设置矩形的宽和高
		rect.inset(1, 1);//设置矩形的宽和高

		final boolean bFocused = IsViewFocused();//控件是否聚焦

		drawDayView(canvas, bFocused);//画布上的日控件是否聚焦
		drawDayNumber(canvas);//在画布画日的数字
	}

	public boolean IsViewFocused() {//方法
		return (this.isFocused() || bTouchedDown);//返回当前是否聚焦和点下
	}

	// 绘制日历方格
	private void drawDayView(Canvas canvas, boolean bFocused) {

		if (bSelected || bFocused) {
			LinearGradient lGradBkg = null;

			if (bFocused) {
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
		pt.setTypeface(null);//设置字体样式
		pt.setAntiAlias(true);//设置矩形效果
		pt.setShader(null);//设置渲染为默认
		pt.setFakeBoldText(true);//设置字体为粗体
		pt.setTextSize(fTextSize);//设置字体大小
		pt.setColor(Constant.isPresentMonth_FontColor);//设置画笔的颜色
		pt.setUnderlineText(false);//设置有无下划线
		
		if (!bIsActiveMonth)//判断如果不是当前月
			pt.setColor(Constant.unPresentMonth_FontColor);//设置显示字体颜色

		if (bToday)//如果是当天
			pt.setUnderlineText(true);//显示字体显示下滑线

		final int iPosX = (int) rect.left + ((int) rect.width() >> 1)
				- ((int) pt.measureText(sDate) >> 1);

		final int iPosY = (int) (this.getHeight()
				- (this.getHeight() - getTextHeight()) / 2 - pt
				.getFontMetrics().bottom);

		canvas.drawText(sDate, iPosX, iPosY, pt);//设置字体位置及宽高

		pt.setUnderlineText(false);//无下划线
	}

	// 得到字体高度
	private int getTextHeight() {
		return (int) (-pt.ascent() + pt.descent());
	}

	// 根据条件返回不同颜色值
	public static int getColorBkg(boolean bHoliday, boolean bToday) {
		if (bToday)
			return Constant.isToday_BgColor;
		// if (bHoliday) //如需周末有特殊背景色，可去掉注释
		// return Calendar_TestActivity.isHoliday_BgColor;
		return Constant.Calendar_DayBgColor;//返回颜色
	}

	// 设置是否被选中
	@Override
	public void setSelected(boolean bEnable) {
		if (this.bSelected != bEnable) {//如果选中情况不等于返回布尔类型值
			this.bSelected = bEnable;//如果选中情况等于返回布尔类型值
			this.invalidate();//当前窗口无效，需重新绘制
		}
	}

	public void setItemClick(OnItemClick itemClick) {//访问器方法
		this.itemClick = itemClick;//实例化对象itemClick
	}

	public void doItemClick() {
		if (itemClick != null)//如果访问不为空
			itemClick.OnClick(this);//访问当前
	}

	// 点击事件
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean bHandled = false;
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			bHandled = true;
			bTouchedDown = true;
			invalidate();
			startAlphaAnimIn(DateWidgetDayCell.this);
		}
		if (event.getAction() == MotionEvent.ACTION_CANCEL) {
			bHandled = true;
			bTouchedDown = false;
			invalidate();
		}
		if (event.getAction() == MotionEvent.ACTION_UP) {
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
				|| (keyCode == KeyEvent.KEYCODE_ENTER)) {
			doItemClick();
		}
		return bResult;
	}

	// 不透明度渐变
	public static void startAlphaAnimIn(View view) {
		AlphaAnimation anim = new AlphaAnimation(0.5F, 1);
		anim.setDuration(ANIM_ALPHA_DURATION);
		anim.startNow();
		view.startAnimation(anim);
	}
	// 记录日期
	public void CreateReminder(Canvas canvas, int Color) {
		pt.setStyle(Paint.Style.FILL_AND_STROKE);//填充
		pt.setColor(Color);
		Path path = new Path();
		path.moveTo(rect.right - rect.width() / 4, rect.top);
		path.lineTo(rect.right, rect.top);
		path.lineTo(rect.right, rect.top + rect.width() / 4);
		path.lineTo(rect.right - rect.width() / 4, rect.top);
		path.close();//终点和起点连接
		canvas.drawPath(path, pt);
	}


}