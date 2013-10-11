package com.time.master.dialog;

import com.time.master.R;
import com.time.master.view.BasicTextView;

import android.R.string;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

/**
 * "重复"对话框界面
 * @author bianyanling
 *
 */
public class RepeatDialogFragment extends DialogFragment implements OnClickListener {
	DialogFragment repeatFragment;
	public static final String tag="RepeatDialogFragment";
	private BasicTextView yxr;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setStyle(DialogFragment.STYLE_NO_FRAME,android.R.style.Theme_Light);
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View layout=inflater.inflate(R.layout.date_repeat, container, false);
		getDialog().setCanceledOnTouchOutside(true);
		Window window=getDialog().getWindow();
		window.setGravity(Gravity.BOTTOM);
		WindowManager.LayoutParams para=(WindowManager.LayoutParams)window.getAttributes();
		para.height=LayoutParams.WRAP_CONTENT;
		para.width=LayoutParams.MATCH_PARENT;
		window.setAttributes(para);
		BasicTextView yxr = (BasicTextView) layout.findViewById(R.id.date_month_select);
		yxr.setOnClickListener(this);
		window.clearFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND | WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		
		
		return layout;
	}
	void showDialog(DialogFragment dialogFragment) {

		// DialogFragment.show() will take care of adding the fragment
		// in a transaction. We also want to remove any currently showing
		// dialog, so make our own transaction and take care of that here.
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		Fragment prev = getFragmentManager().findFragmentByTag("dialog");
		if (prev != null) {
			ft.remove(prev);
		}
		ft.addToBackStack(null);

		// Create and show the dialog.
		dialogFragment.show(ft, "dialog");
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.date_month_select:
			repeatFragment=new RepeatDialogFragment1();
			repeatFragment.setShowsDialog(true);
			showDialog(repeatFragment);
			break;
	}
	}
}
