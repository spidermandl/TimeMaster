package com.time.master.tool;

import java.io.InputStream;

import com.time.master.TimeMasterApplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

public class BitmapCustomize {

	/**
	 * access bitmap from cache according resource id,
	 * create and measure a new bitmap if no bitmap found in cache.
	 * @param con
	 * @param resid
	 * @param required_width
	 * @param required_height
	 * @param zoom
	 * @return
	 */
	static public Bitmap customizePicture(Context con, int resid,int required_width,int required_height,boolean zoom) {
		Bitmap temp = TimeMasterApplication.getInstance().getBitmap(resid);
		if (temp == null) {
			InputStream is = con.getResources().openRawResource(resid);
			
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			// options.inSampleSize = 10; // width，hight设为原来的十分一
			/**
			 * The function of decodeStream directly call JNI >> nativeDecodeAsset().
			 * Save the memory consumed by Java Dalvik. 
			 */
			BitmapFactory.decodeStream(is, null, options);
			//BitmapFactory.decodeResource(con.getResources(), resid,options);
			//System.gc();
			
			options.inSampleSize=calculateInSampleSize(options, required_width, required_height);
			options.inJustDecodeBounds=false;
			temp=BitmapFactory.decodeStream(is,null,options);
			if(zoom)
				temp=zoomBitmap(temp, required_width, required_height);
			TimeMasterApplication.getInstance().setBitmap(resid, temp);
		}
		return temp;
	}
	
	public static void freeBitmap(int resID){
		TimeMasterApplication.getInstance().freeBitmap(resID);
	}
	
	public static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidth = ((float) width / w);
		float scaleHeight = ((float) height / h);
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
		return newbmp;
	}
	/**
	 * remeasure size of bitmap
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static int calculateInSampleSize(BitmapFactory.Options options,int reqWidth,int reqHeight) {
		if (reqWidth==0)
			reqWidth=options.outWidth;
		if(reqHeight==0)
			reqHeight=options.outHeight;
		
		final int height=options.outHeight;
		final int width=options.outWidth;
		int inSampleSize=1;
		
		if(height>reqHeight || width>reqWidth){
			final int heightRatio =Math.round((float)height/(float) reqHeight);
			final int widthRatio =Math.round((float)width/(float) reqWidth);
			
			inSampleSize=heightRatio<widthRatio?heightRatio:widthRatio;
			
			final float totalPixels = width * height;
			
			final float totalReqPixelsCap = reqWidth*reqHeight*2;
			
			while(totalPixels / (inSampleSize*inSampleSize)>totalReqPixelsCap){
				inSampleSize++;
			}
		}
		return inSampleSize;
	}

}
