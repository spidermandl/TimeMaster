package com.time.master.dialog;

/**
 * 世界时间选择界面
 * @author ZhouYongJian
 *
 */
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.time.master.R;
import com.time.master.view.BasicTextView;
import com.time.master.view.BasicViewGroup;
import com.time.master.view.SelectedTextView;

public class WorldTimeDialogFragment extends BasicDialogFragment implements
		OnClickListener {
	private TimeDialogFragment temp = new TimeDialogFragment();
	private BasicTextView showStatus;
	private SelectedTextView translate, idlerWheel, conversion;
	private SelectedTextView city1, city2, city3, city4, city5, city6, city7,
			city8, city9, city10, city11, city12, city13, city14, city15,
			city16, solarcalendar, lunarcalendar, countdown, goon, forward;
	private boolean languageStatus = true;
	private int calendarStatus = temp.dayModelStatus;
	private static final boolean IDLERWHEELACTIVE = true;
	private static final boolean CONVERSIONACTIVE = false;
	private boolean activeStatus = CONVERSIONACTIVE;
	private String calendarStatusTop;
	private String calendarStatusBottom;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Light);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		setDialogStyle();

		View layout = inflater.inflate(R.layout.world_time_layout, container,
				false);
		View context = layout.findViewById(R.id.worldtime);

		city1 = (SelectedTextView) layout.findViewById(R.id.Beikin);
		city2 = (SelectedTextView) layout.findViewById(R.id.Seoul);
		city3 = (SelectedTextView) layout.findViewById(R.id.Tokyo);
		city4 = (SelectedTextView) layout.findViewById(R.id.Melbourne);
		city5 = (SelectedTextView) layout.findViewById(R.id.Hawaii);
		city6 = (SelectedTextView) layout.findViewById(R.id.Sanfrancisco);
		city7 = (SelectedTextView) layout.findViewById(R.id.NewYork);
		city8 = (SelectedTextView) layout.findViewById(R.id.Vancouver);
		city9 = (SelectedTextView) layout.findViewById(R.id.London);
		city10 = (SelectedTextView) layout.findViewById(R.id.Paris);
		city11 = (SelectedTextView) layout.findViewById(R.id.Roman);
		city12 = (SelectedTextView) layout.findViewById(R.id.Moscow);
		city13 = (SelectedTextView) layout.findViewById(R.id.Dubai);
		city14 = (SelectedTextView) layout.findViewById(R.id.Cairo);
		city15 = (SelectedTextView) layout.findViewById(R.id.India);
		city16 = (SelectedTextView) layout.findViewById(R.id.Singapore);
		conversion = (SelectedTextView) layout.findViewById(R.id.conversion);
		conversion.setBackgroundColor(0xFFFFFFFF);
		idlerWheel = (SelectedTextView) layout.findViewById(R.id.idlerwheel);
		showStatus = (BasicTextView) layout.findViewById(R.id.showstatus);
		if (calendarStatus == 0) {
			calendarStatusTop = "农历";
			calendarStatusBottom = "阳历";
			showStatus.setText(calendarStatusTop + "\n" + calendarStatusBottom);
		} else {
			calendarStatusTop = "阳历";
			calendarStatusBottom = "农历";
			showStatus.setText(calendarStatusTop + "\n" + calendarStatusBottom);
		}
		solarcalendar = (SelectedTextView) layout
				.findViewById(R.id.solarcalendar);
		lunarcalendar = (SelectedTextView) layout
				.findViewById(R.id.lunarcalendar);
		countdown = (SelectedTextView) layout.findViewById(R.id.countdown);
		goon = (SelectedTextView) layout.findViewById(R.id.goon);
		forward = (SelectedTextView) layout.findViewById(R.id.forward);

		translate = (SelectedTextView) layout.findViewById(R.id.translate);
		translate.setOnClickListener(this);
		solarcalendar.setOnClickListener(this);
		lunarcalendar.setOnClickListener(this);
		countdown.setOnClickListener(this);
		goon.setOnClickListener(this);
		forward.setOnClickListener(this);
		idlerWheel.setOnClickListener(this);
		conversion.setOnClickListener(this);

		return layout;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.translate:
			if (languageStatus) {
				city1.setText("Beijing");
				city2.setText("Seoul");
				city3.setText("Tokyo");
				city4.setText("Melb\nourne");
				city5.setText("Hawaii");
				city6.setText("Sanfra\nncisco");
				city7.setText("New\nYork");
				city8.setText("Vanc\nouver");
				city9.setText("London");
				city10.setText("Paris");
				city11.setText("Roman");
				city12.setText("Moscow");
				city13.setText("Dubai");
				city14.setText("Cairo");
				city15.setText("India");
				city16.setText("Sing\napore");

				languageStatus = !languageStatus;
			} else {
				city1.setText("北京");
				city2.setText("首尔");
				city3.setText("东京");
				city4.setText("墨尔\n本");
				city5.setText("夏威\n夷");
				city6.setText("旧金\n山");
				city7.setText("纽约");
				city8.setText("温哥\n华");
				city9.setText("伦敦");
				city10.setText("巴黎");
				city11.setText("罗马");
				city12.setText("莫斯\n科");
				city13.setText("迪拜");
				city14.setText("开罗");
				city15.setText("印度");
				city16.setText("新加\n坡");
				languageStatus = !languageStatus;
			}

			break;
		case R.id.solarcalendar:
			if (activeStatus) {
				calendarStatusBottom = (String) solarcalendar.getText();
				showStatus.setText(calendarStatusTop + "\n"
						+ calendarStatusBottom);
			} else {
				calendarStatusTop = (String) solarcalendar.getText();
				showStatus.setText(calendarStatusTop + "\n"
						+ calendarStatusBottom);
			}
			break;
		case R.id.lunarcalendar:
			if (activeStatus) {
				calendarStatusBottom = (String) lunarcalendar.getText();
				showStatus.setText(calendarStatusTop + "\n"
						+ calendarStatusBottom);
			} else {
				calendarStatusTop = (String) lunarcalendar.getText();
				showStatus.setText(calendarStatusTop + "\n"
						+ calendarStatusBottom);
			}
			break;
		case R.id.countdown:
			if (activeStatus) {
				calendarStatusBottom = (String) countdown.getText();
				showStatus.setText(calendarStatusTop + "\n"
						+ calendarStatusBottom);
			} else {
				calendarStatusTop = (String) countdown.getText();
				showStatus.setText(calendarStatusTop + "\n"
						+ calendarStatusBottom);
			}
			break;
		case R.id.goon:
			if (activeStatus) {
				calendarStatusBottom = (String) goon.getText();
				showStatus.setText(calendarStatusTop + "\n"
						+ calendarStatusBottom);
			} else {
				calendarStatusTop = (String) goon.getText();
				showStatus.setText(calendarStatusTop + "\n"
						+ calendarStatusBottom);
			}
			break;
		case R.id.forward:
			if (activeStatus) {
				calendarStatusTop = (String) forward.getText();
				showStatus.setText(calendarStatusTop + "\n"
						+ calendarStatusBottom);
			} else {
				calendarStatusBottom = (String) forward.getText();
				showStatus.setText(calendarStatusTop + "\n"
						+ calendarStatusBottom);
			}
			break;
		case R.id.conversion:
			activeStatus = CONVERSIONACTIVE;
			break;
		case R.id.idlerwheel:
			activeStatus = IDLERWHEELACTIVE;
			conversion.setBackgroundColor(0xFFFFFF00);
			break;
		default:
			break;
		}
	}

}
