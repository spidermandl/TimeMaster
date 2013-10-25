package com.time.master.adapter;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle.Control;

import com.time.master.R;

import android.R.anim;
import android.R.integer;
import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class YearFrontAdapter extends BaseAdapter {
	private LayoutInflater layoutInflater; // LayoutInflater是用来找layout下xml布局文件
	private List<Map<String, Object>> mData;
	private Context context;

	public YearFrontAdapter(Context context, List<Map<String, Object>> mData) {
		this.context = context;
		// 取得布局文件
		this.layoutInflater = layoutInflater.from(context);
		this.mData = mData;

	}

	@Override
	public int getCount() {

		return mData.size();
	}

	@Override
	public Object getItem(int position) {

		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	/**
	 * 当 ListView每显示一个条目的时候就会调用适配器中的getView方法 该方法有三个参数
	 *  第一个参数：是 position 或得位置
	 *  第二个参数：是 就是对第一屏数据进行了缓存,显示第二屏。 就会重用缓存的View显示背后所绑定的数据
	 *  第三个参数：parent就是父窗体了
	 */

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;
		if (convertView == null) {

			holder = new ViewHolder();
			convertView = layoutInflater.inflate(R.layout.year_item, null);
			holder.tvTime = (TextView) convertView.findViewById(R.id.time);
			holder.tvTitle = (TextView) convertView.findViewById(R.id.title);
			convertView.setTag(holder);

		} else {

			holder = (ViewHolder) convertView.getTag();
		}
		if (position % 2 == 0) {
			convertView.setBackgroundResource(R.drawable.yearwhite);

		} else {
			convertView.setBackgroundResource(R.drawable.yeargray);

		}

		holder.tvTime.setText((String) mData.get(position).get("time"));
		return convertView;
	}

	class ViewHolder {

		private TextView tvTime;
		private TextView tvTitle;
	}

}
