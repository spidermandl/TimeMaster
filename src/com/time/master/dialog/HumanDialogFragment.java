package com.time.master.dialog;

import java.util.ArrayList;

import com.time.master.R;
import com.time.master.tool.PinYinUtility;
import com.time.master.wheel.adapters.ArrayWheelAdapter;
import com.time.master.wheel.widget.UIWheelView;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.TextView;
/**
 * 任务选择器
 * @author Desmond
 *
 */
public class HumanDialogFragment extends WheelDialogFragment {

	public static final String TAG="HumanDialogFragment";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setStyle(DialogFragment.STYLE_NO_FRAME,android.R.style.Theme_Light);
		
		super.onCreate(savedInstanceState);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		setDialogStyle();
		
        View layout=inflater.inflate(R.layout.human_wheel_layout, container, false);
		human = (UIWheelView) layout.findViewById(R.id.human_select);
		editText=(EditText)layout.findViewById(R.id.edit_human);
		confirm=(TextView)layout.findViewById(R.id.human_confirm);
		
		TextView 
		a=(TextView)layout.findViewById(R.id.A),
		b=(TextView)layout.findViewById(R.id.B),
		c=(TextView)layout.findViewById(R.id.C),
		d=(TextView)layout.findViewById(R.id.D),
		e=(TextView)layout.findViewById(R.id.E),
		f=(TextView)layout.findViewById(R.id.F),
		g=(TextView)layout.findViewById(R.id.G),
		h=(TextView)layout.findViewById(R.id.H),
		i=(TextView)layout.findViewById(R.id.I),
		j=(TextView)layout.findViewById(R.id.J),
		k=(TextView)layout.findViewById(R.id.K),
		l=(TextView)layout.findViewById(R.id.L),
		m=(TextView)layout.findViewById(R.id.M),
		n=(TextView)layout.findViewById(R.id.N),
		o=(TextView)layout.findViewById(R.id.O),
		p=(TextView)layout.findViewById(R.id.P),
		q=(TextView)layout.findViewById(R.id.Q),
		r=(TextView)layout.findViewById(R.id.R),
		s=(TextView)layout.findViewById(R.id.S),
		t=(TextView)layout.findViewById(R.id.T),
		u=(TextView)layout.findViewById(R.id.U),
		v=(TextView)layout.findViewById(R.id.V),
		w=(TextView)layout.findViewById(R.id.W),
		x=(TextView)layout.findViewById(R.id.X),
		y=(TextView)layout.findViewById(R.id.Y),
		z=(TextView)layout.findViewById(R.id.Z),
		cancel=(TextView)layout.findViewById(R.id.name_cancel);
		a.setOnClickListener(clickListener);
		b.setOnClickListener(clickListener);
		c.setOnClickListener(clickListener);
		d.setOnClickListener(clickListener);
		e.setOnClickListener(clickListener);
		f.setOnClickListener(clickListener);
		g.setOnClickListener(clickListener);
		h.setOnClickListener(clickListener);
		i.setOnClickListener(clickListener);
		j.setOnClickListener(clickListener);
		k.setOnClickListener(clickListener);
		l.setOnClickListener(clickListener);
		m.setOnClickListener(clickListener);
		n.setOnClickListener(clickListener);
		o.setOnClickListener(clickListener);
		p.setOnClickListener(clickListener);
		q.setOnClickListener(clickListener);
		r.setOnClickListener(clickListener);
		s.setOnClickListener(clickListener);
		t.setOnClickListener(clickListener);
		u.setOnClickListener(clickListener);
		v.setOnClickListener(clickListener);
		w.setOnClickListener(clickListener);
		x.setOnClickListener(clickListener);
		y.setOnClickListener(clickListener);
		z.setOnClickListener(clickListener);
		cancel.setOnClickListener(clickListener);
		
		
		humanAdapter =new ArrayWheelAdapter<String>(this.getActivity(), static_human);
		humanAdapter.setItemResource(R.layout.wheel_human_text_item);
		humanAdapter.setItemTextResource(R.id.human_text);
		human.setViewAdapter(humanAdapter);
		human.setRightLineWidth(0);
		human.setPaint(null);
		human.setBackground(0);
		human.setVisibleItems(9);
		human.setCurrentItem(static_human.length/2);
        
		superInit();
		return layout;
	}
	
	UIWheelView human;
	ArrayWheelAdapter<String> humanAdapter;
	
    static final String[] static_human={"张雅栏","薛启义","王刚","张雅栏","薛启义","王刚","张雅栏","薛启义","王刚"};
    
    /**
     * filter names by name abbrivation
     * @param src
     * @param heads
     * @return
     */
    private String[] getHeadIndexName(String[] src,String heads){
    	if(heads==null||heads.length()==0)
    		return src;
    	ArrayList<String> list=new ArrayList<String>();
    	for(String s:src){
    		String wholeHeads=PinYinUtility.getPinYinHeadChar(s);
    		if(wholeHeads.length()<heads.length())
    			break;
    		if(wholeHeads.substring(0, heads.length()).equalsIgnoreCase(heads))
    			list.add(s);
    	}
    	return list.toArray(new String[list.size()]);
    }
    
    private void updateHumanWheel(String tail){
		String text=editText.getText().toString()+tail;
		humanAdapter.setItems(getHeadIndexName(static_human, text));
		human.invalidateWheel(true);
		editText.setText(text);
    }
    
    View.OnClickListener clickListener=new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.A:
				updateHumanWheel("A");
				break;
			case R.id.B:
				updateHumanWheel("B");
				break;
			case R.id.C:
				updateHumanWheel("C");
				break;
			case R.id.D:
				updateHumanWheel("D");
				break;
			case R.id.E:
				updateHumanWheel("E");
				break;
			case R.id.F:
				updateHumanWheel("F");
				break;
			case R.id.G:
				updateHumanWheel("G");
				break;
			case R.id.H:
				updateHumanWheel("H");
				break;
			case R.id.I:
				updateHumanWheel("I");
				break;
			case R.id.J:
				updateHumanWheel("A");
				break;
			case R.id.K:
				updateHumanWheel("K");
				break;
			case R.id.L:
				updateHumanWheel("L");
				break;
			case R.id.M:
				updateHumanWheel("M");
				break;
			case R.id.N:
				updateHumanWheel("N");
				break;
			case R.id.O:
				updateHumanWheel("O");
				break;
			case R.id.P:
				updateHumanWheel("P");
				break;
			case R.id.Q:
				updateHumanWheel("Q");
				break;
			case R.id.R:
				updateHumanWheel("R");
				break;
			case R.id.S:
				updateHumanWheel("S");
				break;
			case R.id.T:
				updateHumanWheel("T");
				break;
			case R.id.U:
				updateHumanWheel("U");
				break;
			case R.id.V:
				updateHumanWheel("V");
				break;
			case R.id.W:
				updateHumanWheel("W");
				break;
			case R.id.X:
				updateHumanWheel("X");
				break;
			case R.id.Y:
				updateHumanWheel("Y");
				break;
			case R.id.Z:
				updateHumanWheel("Z");
				break;
			case R.id.name_cancel:
				humanAdapter.setItems(getHeadIndexName(static_human, null));
				human.invalidateWheel(true);
				editText.setText("");
			}
			
		}
	};

	@Override
	protected String getSelectedString() {
		return "";
	}

	@Override
	protected void pushConfirm() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected int getSelectedInt() {
		// TODO Auto-generated method stub
		return 0;
	}
}
