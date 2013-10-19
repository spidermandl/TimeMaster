package com.time.master;

import java.lang.ref.SoftReference;
import java.util.HashMap;

import com.time.master.database.TimeMasterHelper;


import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class TimeMasterApplication extends Application {

	/**
	 * 屏幕宽度、高度
	 */
	private int screen_width,screen_height;
	/**
	 * singleton instance of Application
	 */
	private static TimeMasterApplication instance;
	
	private TimeMasterHelper databaseHelper;
	/**
	 * 界面显示图片资源缓存
	 * 系统内存不足时会被清除，释放内存
	 */
	HashMap<Integer,SoftReference<Bitmap>> bitmapCache=new HashMap<Integer, SoftReference<Bitmap>>();
    
	/***数据库已经初始化*/
	private boolean dataInitialized=true;
	
	public static TimeMasterApplication getInstance(){
		return instance;
	}
	
	@Override
	public void onCreate() {
		instance=this;
		DisplayMetrics displaymetrics = new DisplayMetrics();
		WindowManager window=(WindowManager)(this.getSystemService(Context.WINDOW_SERVICE));
		window.getDefaultDisplay().getMetrics(displaymetrics);
		screen_width=displaymetrics.widthPixels;
		screen_height=displaymetrics.heightPixels;
//		initialOrientation=this.getResources().getConfiguration().orientation;
//		dbHelper=new DiaryHelper(this);
//		
//		ueHandler = new UEHandler(this); 
//        Thread.setDefaultUncaughtExceptionHandler(ueHandler); 
//		FlurryAgent.onStartSession(this, Constant.FLURRY_KEY);
		setDatabaseHelper(new TimeMasterHelper(this));
		super.onCreate();
	}
	
	public void setBitmap(Integer resid,Bitmap bitmap){
		SoftReference<Bitmap> softBitmap=new SoftReference<Bitmap>(bitmap);
		bitmapCache.put(resid, softBitmap);
	}
	public Bitmap getBitmap(Integer resid){
		SoftReference<Bitmap> softBitmap=bitmapCache.get(resid);
		if(softBitmap!=null){
			Bitmap bitmap=softBitmap.get();
			return bitmap;
		}
		return null;
	}
	public void freeBitmap(Integer resid){
		if(bitmapCache!=null){
			SoftReference<Bitmap> softBitmap=bitmapCache.get(resid);
			if(softBitmap!=null){
				softBitmap.clear();
			}
		}
	}

	public int getScreen_W(){
		return this.screen_width;
	}
	
	public int getScreen_H(){
		return this.screen_height;
	}

	public TimeMasterHelper getDatabaseHelper() {
		return databaseHelper;
	}

	public void setDatabaseHelper(TimeMasterHelper databaseHelper) {
		this.databaseHelper = databaseHelper;
	}

	public boolean isDataInitialized() {
		return dataInitialized;
	}

	public void setDataInitialized(boolean dataInitialized) {
		this.dataInitialized = dataInitialized;
	}
}
