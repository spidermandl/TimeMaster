package com.time.master.adapter;

import com.time.master.R;
import com.time.master.TimeMasterApplication;

import android.content.Context;  
import android.view.View;  
import android.view.ViewGroup;  
import android.widget.BaseAdapter;  
import android.widget.GridView;  
import android.widget.ImageView;  
    
 public class DateGridViewActionImageAdapter extends BaseAdapter  
 {  
     // 定义Context  
     private Context     mContext;  
     // 定义整型数组 即图片源  
     private Integer[]   mImageIds   =   
     {   
             R.drawable.img1,   
             R.drawable.img2,   
             R.drawable.img3,   
             R.drawable.img4,   
             R.drawable.img5,   
             R.drawable.img6,
             R.drawable.img7,
             R.drawable.img8,
             R.drawable.img9,
             R.drawable.img10,
             R.drawable.img11,
             R.drawable.img12,
             R.drawable.img13,
             R.drawable.img14,
             R.drawable.img15,
             R.drawable.img16,
             R.drawable.img17,
             R.drawable.img18,
             R.drawable.img19,
             R.drawable.img20,
             
     };  
    
     public DateGridViewActionImageAdapter(Context c)  
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
