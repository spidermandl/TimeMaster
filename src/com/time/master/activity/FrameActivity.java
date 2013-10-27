package com.time.master.activity;

import java.util.HashMap;

import com.time.master.R;
import com.time.master.TimeMasterApplication;
import com.time.master.database.TimeMasterHelper;
import com.time.master.dialog.LoadStaticDataFragment;

import com.time.master.view.TabTextView;

import android.content.res.Configuration;
import android.graphics.LinearGradient;

import android.os.Bundle;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TabWidget;

/***
 * 
 * @author desmond.duan
 *
 */
public class FrameActivity extends FragmentActivity {

	HashMap<Integer, Fragment> fragmentCache=new HashMap<Integer, Fragment>();
    
	TabHost tabHost;
	LinearLayout mainFrame;
	TabWidget tabWidget;  
    ScrollView verticalScroll;
    HorizontalScrollView horizontalScroll;
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_main);
        tabHost=(TabHost)this.findViewById(R.id.main_tab);
        tabHost.setup();
        mainFrame=(LinearLayout)this.findViewById(R.id.main_layout);
        tabWidget = tabHost.getTabWidget();  
        verticalScroll=(ScrollView)this.findViewById(R.id.vertical_scroll_tab);
        horizontalScroll=(HorizontalScrollView)this.findViewById(R.id.horizontal_scroll_tab);
        
        configureOrientation();
        
        TabTextView generationTab=new TabTextView(this).setCenterText("辈"),
        yearTab=new TabTextView(this).setCenterText("年"),
        monthTab=new TabTextView(this).setCenterText("月"),
        weekTab=new TabTextView(this).setCenterText("周"),
        dateTab=new TabTextView(this).setCenterText("日"),
        listTab=new TabTextView(this).setCenterText("列表"),
        newTab=new TabTextView(this).setCenterText(" 新建");
        generationTab.setCenterBackgroud(0xFFFF0000);
        yearTab.setCenterBackgroud(0xFF00FF00);
        monthTab.setCenterBackgroud(0xFF0000FF);
        weekTab.setCenterBackgroud(0xFFFFFF00);
        dateTab.setCenterBackgroud(0xFF00FFFF);
        listTab.setCenterBackgroud(0xFFFF00FF);
        newTab.setCenterBackgroud(0xFFCCF0CC);
        newTab.setRightMargin();
        
        tabHost.addTab(tabHost.newTabSpec("generation").setIndicator(generationTab).setContent(R.id.generation_fragment));
        tabHost.addTab(tabHost.newTabSpec("year").setIndicator(yearTab).setContent(R.id.year_fragment));
        tabHost.addTab(tabHost.newTabSpec("month").setIndicator(monthTab).setContent(R.id.month_fragment));
        tabHost.addTab(tabHost.newTabSpec("week").setIndicator(weekTab).setContent(R.id.week_fragment));
        tabHost.addTab(tabHost.newTabSpec("date").setIndicator(dateTab).setContent(R.id.date_fragment));
        tabHost.addTab(tabHost.newTabSpec("list").setIndicator(listTab).setContent(R.id.issue_list_fragment));
        tabHost.addTab(tabHost.newTabSpec("new").setIndicator(newTab).setContent(R.id.new_issue_fragment));
        tabHost.setCurrentTabByTag("date");
        
        // 标签的个数  
        int count = tabWidget.getChildCount();  
        // 获取手机屏幕的宽高  
        DisplayMetrics displayMetrics = new DisplayMetrics();  
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);  
        int screenWidth = displayMetrics.widthPixels;  
        
//		for (int i = 0; i < count; i++) {
//		   tabWidget.getChildTabViewAt(i).getLayoutParams().width=screenWidth/6+screenWidth/36+(i==count-1?screenWidth/36:0);
//	    }
		
		if (count >= 5) {
			for (int i = 0; i < count; i++) {
				// 设置每个标签的宽度，为屏幕的1/5
				tabWidget.getChildTabViewAt(i).setMinimumWidth((screenWidth) / 5);
			}
		} 
		

		/**本地数据库建立，初始表数据*/
		if (!TimeMasterApplication.getInstance().isDataInitialized()) {
			DialogFragment df=new LoadStaticDataFragment();
			df.show(this.getSupportFragmentManager(), "dialog");
		}
		
    }
    /**fragment切换*/
    public void showNext(int containerID,Class<Fragment> fragmentName , int fragmentID) {
		Fragment f=fragmentCache.get(fragmentID);
		if(f==null){
			try {
				f=fragmentName.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
				return;
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				return;
			}
			fragmentCache.put(fragmentID, f);
		}
		
		FragmentManager fragmentManager = this.getSupportFragmentManager();
		Fragment fragment=fragmentManager.findFragmentById(containerID);
		if(fragment==null){
			return;
		}
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
		
		fragmentTransaction.replace(containerID, f);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		fragmentTransaction.commit();
	}
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
    	tabHost.invalidate();
    	configureOrientation();
    	super.onConfigurationChanged(newConfig);
    	
    }
    
    private void configureOrientation(){
    	int orientation=this.getResources().getConfiguration().orientation;
		if(orientation==Configuration.ORIENTATION_PORTRAIT){
			ViewGroup.LayoutParams para=horizontalScroll.getLayoutParams();
			para.width=LayoutParams.MATCH_PARENT;
			para.height=LayoutParams.WRAP_CONTENT;
			para.width=LayoutParams.MATCH_PARENT;
			para.height=LayoutParams.WRAP_CONTENT;
			tabWidget.setOrientation(Configuration.ORIENTATION_LANDSCAPE);
		}else{
			ViewGroup.LayoutParams para=horizontalScroll.getLayoutParams();
			para.width=LayoutParams.WRAP_CONTENT;
			para.height=LayoutParams.MATCH_PARENT;
			para.width=LayoutParams.WRAP_CONTENT;
			para.height=LayoutParams.MATCH_PARENT;
			tabWidget.setOrientation(Configuration.ORIENTATION_PORTRAIT);
		}
    }
}

