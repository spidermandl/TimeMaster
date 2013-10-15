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
	private boolean hasRecord = true;//定义属性并初始化hasRecord


	public static int ANIM_ALPHA_DURATION = 100;//设置动画时间为100毫秒

	
	public interface OnItemClick {   //自定义接口类 
		public void OnClick(DateWidgetDayCell item);  //单元格的点击事件

	}

	// 构造函数
	public DateWidgetDayCell(Context context, int iWidth, int iHeight) {

		super(context);
		setFocusable(true);  // 设置单元格有焦点 
		setLayoutParams(new LayoutParams(iWidth, iHeight));  // 单元格布局 宽 高

	}

	// 取变量值  
	public Calendar getDate() {

		Calendar calDate = Calendar.getInstance(); //得到Calendar对象
		calDate.clear(); // 清除之前calendar文件 相当于刷新
		calDate.set(Calendar.YEAR, iDateYear);  //设置年 值为iDateYear
		calDate.set(Calendar.MONTH, iDateMonth);  //设置月 值为iDateMonth
		calDate.set(Calendar.DAY_OF_MONTH, iDateDay);   //设置日 值为iDateDay
		return calDate; //返回calDate

	}

	// 设置变量值
	public void setData(int iYear, int iMonth, int iDay, Boolean bToday,
			Boolean bHoliday, int iActiveMonth, boolean hasRecord) {

		iDateYear = iYear;  
		iDateMonth = iMonth; 
		iDateDay = iDay; 

		this.sDate = Integer.toString(iDateDay); //将iDateDay int型转换为String型赋值给sDate 
		this.bIsActiveMonth = (iDateMonth == iActiveMonth); //判断是否为当前月
		this.bToday = bToday; // 判断是否为当天日期
		this.bHoliday = bHoliday; // 判断是否为节假日
		this.hasRecord = hasRecord; // 判断是否有记录（安排）

	}

	// 重载绘制方法
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);//继承父类构造方法


		rect.set(0, 0, this.getWidth(), this.getHeight());   //设置矩形宽高
		rect.inset(1, 1);   //设置矩形宽高比例为1:1

		final boolean bFocused = IsViewFocused();   //单元格由IsViewFocused（）方法决定

		drawDayView(canvas, bFocused);    //  绘制日历方格
		drawDayNumber(canvas);    //  绘制日历中的数字
	}

	public boolean IsViewFocused() {//方法
		return (this.isFocused() || bTouchedDown);//返回当前是否聚焦和点下

		
	}



	// 绘制日历方格
	private void drawDayView(Canvas canvas, boolean bFocused) { //在画布上绘制方格 并判断其是否有焦点

		if (bSelected || bFocused) {  // 单元格是否被选中 ，是否有焦点
			LinearGradient lGradBkg = null;   //

			if (bFocused) { // 当单元格有焦点时
				lGradBkg = new LinearGradient(rect.left, 0, rect.right, 0,
						0xffaa5500, 0xffffddbb, Shader.TileMode.CLAMP);   // 创建线 x轴上以矩形的左边到右边为长度
			}

			if (bSelected) {  // 当单元格被选中
				lGradBkg = new LinearGradient(rect.left, 0, rect.right, 0,
						0xff225599, 0xffbbddff, Shader.TileMode.CLAMP);  // 创建线 x轴上以矩形的左边到右边为长度
			}

			if (lGradBkg != null) { //当至少有一个条件满足时
				pt.setShader(lGradBkg);  // 设置画笔着色器 做线
				canvas.drawRect(rect, pt); // 在画布上利用画笔绘制单元格rect
			}

			pt.setShader(null);  //不做线

		} else {
			pt.setColor(getColorBkg(bHoliday, bToday));  //为节假日和当日设置背景颜色
			canvas.drawRect(rect, pt);
		}

		if (hasRecord) {  //有记录的 提醒
			CreateReminder(canvas, Constant.special_Reminder);
		}
		// else if (!hasRecord && !bToday && !bSelected) {
		// CreateReminder(canvas, Calendar_TestActivity.Calendar_DayBgColor);
		// }
	}

	// 绘制日历中的数
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

		pt.setTypeface(null);
		pt.setAntiAlias(true); //去锯齿
		pt.setShader(null);
		pt.setFakeBoldText(true);  //粗体
		pt.setTextSize(fTextSize);  
		pt.setColor(Constant.isPresentMonth_FontColor);
		pt.setUnderlineText(false);
		
		if (!bIsActiveMonth)  //如果不是当前月  设置颜色 
			pt.setColor(Constant.unPresentMonth_FontColor);

		if (bToday) //如果是今天 设置下划线
			pt.setUnderlineText(true);

		final int iPosX = (int) rect.left + ((int) rect.width() >> 1)  //数字的宽
				- ((int) pt.measureText(sDate) >> 1);

		final int iPosY = (int) (this.getHeight()           // 数字的高
				- (this.getHeight() - getTextHeight()) / 2 - pt
				.getFontMetrics().bottom);


		canvas.drawText(sDate, iPosX, iPosY, pt); // 绘制数字


		pt.setUnderlineText(false);//无下划线
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
		if (this.bSelected != bEnable) {//如果选中情况不等于返回布尔类型值
			this.bSelected = bEnable;//如果选中情况等于返回布尔类型值
			this.invalidate();//当前窗口无效，需重新绘制
		}
	}

	// 设置单元格点击
	public void setItemClick(OnItemClick itemClick) {
		this.itemClick = itemClick;

	}
	// 单元格点击事件
	public void doItemClick() {
		if (itemClick != null)//如果访问不为空
			itemClick.OnClick(this);//访问当前
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