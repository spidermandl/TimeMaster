package com.time.master.adapter;

import android.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	private Context mContext;
    private Integer[] mImageId={};
    public ImageAdapter (Context c){
    	this.mContext=c;
    }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mImageId.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView imageView=new ImageView(mContext);
		imageView.setImageResource(mImageId[position]);
		imageView.setLayoutParams(new Gallery.LayoutParams(5, 5));
		imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		return imageView;
	}

}
