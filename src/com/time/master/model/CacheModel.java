package com.time.master.model;

import java.util.HashMap;

import com.time.master.tool.ChineseCalendar;
/**
 * 应用缓存模型
 * @author desmond.duan
 *
 */
public class CacheModel {

	public ChineseCalendar  startTime,//时间模块开始时间
	                        endTime,//时间模块结束时间
	                        currentTime;//当前选中时间,变化频繁，缺少参考性
	
	public long tickingTime=0;//计时时间，long型

   
     public HashMap<String, String> tmpResultsCache=new HashMap<String, String>();//dialog 返回结果数据缓存
    

   
}