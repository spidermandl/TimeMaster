package com.time.master.fragment;

import java.util.Calendar;

import com.time.master.R;
import com.time.master.fragment.MonthFragment.DateModel;
import com.time.master.wheel.adapters.ArrayWheelAdapter;
import com.time.master.wheel.widget.UIWheelView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class YearFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout=inflater.inflate(R.layout.year_layout, container, false);
		human = (UIWheelView) layout.findViewById(R.id.human_select);
		humanAdapter =new ArrayWheelAdapter<String>(this.getActivity(), static_human);
		humanAdapter.setItemResource(R.layout.wheel_human_text_item);
		humanAdapter.setItemTextResource(R.id.human_text);
		human.setViewAdapter(humanAdapter);
		human.setRightLineWidth(0);
		human.setPaint(null);
		human.setBackground(0);
		human.setVisibleItems(9);
		human.setCurrentItem(static_human.length/2);
        
		return layout;
	}
	
	EditText editText;
	UIWheelView human;
	ArrayWheelAdapter<String> humanAdapter;
	
    static final String[] static_human={"’≈—≈¿∏","—¶∆Ù“Â","Õı∏’","’≈—≈¿∏","—¶∆Ù“Â","Õı∏’","’≈—≈¿∏","—¶∆Ù“Â","Õı∏’"};
}
