package com.time.master.adapter;

import com.time.master.R;
import com.time.master.TimeMasterApplication;

import android.content.Context;  
import android.view.View;  
import android.view.ViewGroup;  
import android.widget.BaseAdapter;  
import android.widget.GridView;  
import android.widget.ImageView;  
    
 public class DateGridViewImageAdapter extends BaseAdapter  
 {  
     // 定义Context  
     private Context     mContext;  
     // 定义整型数组 即图片源  
     private Integer[]   mImageIds   =   
     {   
             R.drawable.imag1,   
             R.drawable.imag2,   
             R.drawable.imag3,   
             R.drawable.imag4,   
             R.drawable.imag5,   
             R.drawable.imag6,
             R.drawable.imag7,
             R.drawable.imag8,
             R.drawable.imag9,
             R.drawable.imag10,
             R.drawable.imag11,
             R.drawable.imag12,
             R.drawable.imag13,
             R.drawable.imag14,
             R.drawable.imag15,
             R.drawable.imag16,
             R.drawable.imag17,
             
     };  
    
     public DateGridViewImageAdapter(Context c)  
     {  
        mContext = c;  
    }  
    
    // 获取图片的个数  
     public int getCount()  
    {  
        return mImageIds.length;  
     }  
   
     // 获取图片在库中的位置  
     public Object getItem(int position)  
     {  
         return position;  
    }  
   
    
     // 获取图片ID  
     public long getItemId(int position)  
     {  
         return position;  
    }  
    
     public View getView(int position, View convertView, ViewGroup parent)  
     {  
         ImageView imageView;  
         if (convertView == null)  
         {  
             // 给ImageView设置资源  
             imageView = new ImageView(mContext);  
            // 设置布局 图片120×120显示  
            imageView.setLayoutParams(new GridView.LayoutParams(
            		TimeMasterApplication.getInstance().getScreen_W()/8,
            		TimeMasterApplication.getInstance().getScreen_W()/8));  
             // 设置显示比例类型  
             imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);  
         }  
        else 
         {  
            imageView = (ImageView) convertView;  
        }  
   
         imageView.setImageResource(mImageIds[position]);  
         return imageView;  
     }  
    
 } 
