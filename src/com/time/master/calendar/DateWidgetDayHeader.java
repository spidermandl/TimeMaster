/**
 * @梁丽丽
 */
package com.time.master.calendar;
/*
 * 谢冬
*/
import com.time.master.tool.Constant;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;

/**
 * 日历控件头部绘制类
 * @Descriptio.n: 日历控件头部绘制类
 */
public class DateWidgetDayHeader extends View {
	// 字体大小
	private final static int fTextSize = 22;
	private Paint pt = new Paint();//定义一个属性画笔，实例化画笔对象pt
	private RectF rect = new RectF();//定义一个矩形，并实例化矩形对象rect
	private int iWeekDay = -1;//初始化当天礼拜几

	public DateWidgetDayHeader(Context context, int iWidth, int iHeight) {//设置日历头部控件内容，宽和高的方法
		super(context);//继承父类内容
		setLayoutParams(new LayoutParams(iWidth, iHeight));//像布局传入屏宽和屏高并实例化屏的宽和高
	}

	@Override
	protected void onDraw(Canvas canvas) {//复写方法，参数为画布类型的对象canvas
		super.onDraw(canvas);//继承父类

		// 设置矩形大小
		rect.set(0, 0, this.getWidth(), this.getHeight());
		rect.inset(1, 1);//描边，为正数时：描内边，为负数时:描外边。

		// 绘制日历头部
		drawDayHeader(canvas);
	}

	private void drawDayHeader(Canvas canvas) {
		// 画矩形，并设置矩形画笔的颜色
		pt.setColor(Constant.Calendar_WeekBgColor);
		canvas.drawRect(rect, pt);//在画布上用画笔绘制矩形区域

		// 写入日历头部，设置画笔参数
		pt.setTypeface(null);//字体样式默认
		pt.setTextSize(fTextSize);//画笔大小
		pt.setAntiAlias(true);//给画笔设置是否抗锯齿，参数为true，抗锯齿
		pt.setFakeBoldText(true);//设置字体
		pt.setColor(Constant.Calendar_WeekFontColor);//设置颜色
		
		// draw day name
		final String sDayName = DayStyle.getWeekDayName(iWeekDay);//画出当天星期几

		final int iPosX = (int) rect.left + ((int) rect.width() >> 1)//设置字体居中。精确的定位到方框中

				- ((int) pt.measureText(sDayName) >> 1);
		final int iPosY = (int) (this.getHeight()
				- (this.getHeight() - getTextHeight()) / 2 - pt
				.getFontMetrics().bottom);
		canvas.drawText(sDayName, iPosX, iPosY, pt);
	}

	// 得到字体高度
	private int getTextHeight() {
		return (int) (-pt.ascent() + pt.descent());//查
	}

	// 得到一星期的第几天的文本标记
	public void setData(int iWeekDay) {
		this.iWeekDay = iWeekDay;
	}
}