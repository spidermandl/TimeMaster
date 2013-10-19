package com.time.master.activity;

import java.util.HashMap;

import com.time.master.R;
import com.time.master.TimeMasterApplication;
import com.time.master.dialog.LoadStaticDataFragment;

import android.content.res.Configuration;
import android.os.Bundle;


import android.content.Context;

import android.support.v4.app.DialogFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

/*
 * TabHost
 * @author lanhaibin
 * 
 */
public class FrameActivity extends FragmentActivity {

	HashMap<Integer, Fragment> fragmentCache=new HashMap<Integer, Fragment>();
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_main);
        TabHost tabHost=(TabHost)this.findViewById(R.id.main_tab);
        
        RelativeLayout generationTab = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.frame_minitab, null);
        TextView generationTabLabel = (TextView) generationTab.findViewById(R.id.tab_label);
        generationTabLabel.setText("辈");
        
        RelativeLayout yearTab = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.frame_minitab, null);
        TextView yearTabLabel = (TextView) yearTab.findViewById(R.id.tab_label);
        yearTabLabel.setText("年");
        
        RelativeLayout monthTab = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.frame_minitab, null);
        TextView monthTabLabel = (TextView) monthTab.findViewById(R.id.tab_label);
        monthTabLabel.setText("月");
        
        RelativeLayout weekTab = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.frame_minitab, null);
        TextView weekTabLabel = (TextView) weekTab.findViewById(R.id.tab_label);
        weekTabLabel.setText("周");
        
        RelativeLayout dateTab = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.frame_minitab, null);
        TextView dateTabLabel = (TextView) dateTab.findViewById(R.id.tab_label);
        dateTabLabel.setText("日");
        
        RelativeLayout listTab = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.frame_minitab, null);
        TextView listTabLabel = (TextView) listTab.findViewById(R.id.tab_label);
        listTabLabel.setText("列表");
        
        RelativeLayout newTab = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.frame_minitab, null);
        TextView newTabLabel = (TextView) newTab.findViewById(R.id.tab_label);
        newTabLabel.setText("新建");
        
        
        tabHost.setup();
        
        tabHost.addTab(tabHost.newTabSpec("generation").setIndicator(generationTab).setContent(R.id.generation_fragment));
        tabHost.addTab(tabHost.newTabSpec("year").setIndicator(yearTab).setContent(R.id.year_fragment));
        tabHost.addTab(tabHost.newTabSpec("month").setIndicator(monthTab).setContent(R.id.month_fragment));
        tabHost.addTab(tabHost.newTabSpec("week").setIndicator(weekTab).setContent(R.id.week_fragment));
        tabHost.addTab(tabHost.newTabSpec("date").setIndicator(dateTab).setContent(R.id.date_fragment));
        tabHost.addTab(tabHost.newTabSpec("list").setIndicator(listTab).setContent(R.id.issue_list_fragment));
        tabHost.addTab(tabHost.newTabSpec("new").setIndicator(newTab).setContent(R.id.new_issue_fragment));
        tabHost.setCurrentTabByTag("generation");
        
        TabWidget tabWidget = tabHost.getTabWidget();  
        // 标签的个数  
        int count = tabWidget.getChildCount();  
        // 获取手机屏幕的宽高  
        DisplayMetrics displayMetrics = new DisplayMetrics();  
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);  
        int screenWidth = displayMetrics.widthPixels;  
		if (count >= 5) {
			for (int i = 0; i < count; i++) {
				// 设置每个标签的宽度，为屏幕的1/5
				tabWidget.getChildTabViewAt(i).setMinimumWidth(
						(screenWidth) / 5);
			}
		} 
		
		/**本地数据库建立，初始表数据*/
		if (!TimeMasterApplication.getInstance().isDataInitialized()) {
			DialogFragment df=new LoadStaticDataFragment();
			df.show(this.getSupportFragmentManager(), "dialog");
		}
    }

//    TabHost.OnTabChangeListener tabChangeListener = new TabHost.OnTabChangeListener() {
//    	
//    	FragmentManager fm= FrameActivity.this.getSupportFragmentManager();
//    	AndroidFragment androidFragment = (GenerationFragment) fm.findFragmentById(arg0)findFragmentByTag("android");
//        AppleFragment appleFragment = (AppleFragment) fm.findFragmentByTag("apple");
//    	FragmentTransaction ft = fm.beginTransaction();
//		@Override
//		public void onTabChanged(String tabId) {
//			// TODO Auto-generated method stub
//			
//		}
//    	
//    };
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
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
    
}

