package com.time.master.fragment;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.time.master.R;
import com.time.master.adapter.BaseAdapterDemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class YearFragment extends Fragment {
	private ListView TitleList;
	private BaseAdapterDemo aDemo;
	private List<Map<String, Object>> mData;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout=inflater.inflate(R.layout.year_layout, container, false);
		
		mData=getData();
		TitleList = (ListView)  layout.findViewById(R.id.listView1);
		
		aDemo =new BaseAdapterDemo(this.getActivity(), mData);

		TitleList.setAdapter(aDemo);
		
        
		return layout;
	}
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		Map<String, Object>map=new HashMap<String, Object>();
		  map.put("time", "    1");
		  list.add(map);
		  map = new HashMap<String, Object>();
		  map.put("time", "    2");
		  list.add(map);
		  map = new HashMap<String, Object>();
		  map.put("time", "    3");
		  list.add(map);
		  map = new HashMap<String, Object>();
		  map.put("time", "    4");
		  list.add(map);
		  map = new HashMap<String, Object>();
		  map.put("time", "    5");
		  list.add(map);
		  map = new HashMap<String, Object>();
		  map.put("time", "    6");
		  list.add(map);
		 
		
		return list;
	}

}

