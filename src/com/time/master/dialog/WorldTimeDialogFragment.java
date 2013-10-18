package com.time.master.dialog;
/**
 * 世界时间选择界面
 * @author ZhouYongJian
 *
 */
import com.time.master.R;
import com.time.master.view.SelectedTextView;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public  class WorldTimeDialogFragment extends BasicDialogFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NO_FRAME,android.R.style.Theme_Light);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setDialogStyle();
		
        View layout=inflater.inflate(R.layout.world_time_layout, container, false);
        View context=layout.findViewById(R.id.worldtime);
		return layout;
	}

}
