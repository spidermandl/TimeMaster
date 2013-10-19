package com.time.master.activity;

import java.util.HashMap;

import com.time.master.R;
import com.time.master.TimeMasterApplication;
import com.time.master.database.TimeMasterHelper;
import com.time.master.dialog.LoadStaticDataFragment;
import com.time.master.view.BasicViewGroup;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabWidget;

public class FrameActivity extends FragmentActivity {

	HashMap<Integer, Fragment> fragmentCache=new HashMap<Integer, Fragment>();

	 TabHost tabHost;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_main);
        tabHost=(TabHost)this.findViewById(R.id.main_tab);
        tabHost.setup();
        
        tabHost.addTab(tabHost.newTabSpec("generation").setIndicator(this.getResources().getString(R.string.generation)).setContent(R.id.generation_fragment));
        tabHost.addTab(tabHost.newTabSpec("year").setIndicator(this.getResources().getString(R.string.year)).setContent(R.id.year_fragment));
        tabHost.addTab(tabHost.newTabSpec("month").setIndicator(this.getResources().getString(R.string.month)).setContent(R.id.month_fragment));
        tabHost.addTab(tabHost.newTabSpec("week").setIndicator(this.getResources().getString(R.string.week)).setContent(R.id.week_fragment));
        tabHost.addTab(tabHost.newTabSpec("date").setIndicator(this.getResources().getString(R.string.date)).setContent(R.id.date_fragment));
        tabHost.addTab(tabHost.newTabSpec("list").setIndicator(this.getResources().getString(R.string.list)).setContent(R.id.issue_list_fragment));
        tabHost.addTab(tabHost.newTabSpec("new").setIndicator(this.getResources().getString(R.string.new_item)).setContent(R.id.new_issue_fragment));
        tabHost.setCurrentTabByTag("generation");
        
        TabWidget tabWidget = tabHost.getTabWidget();  
        // 标签的个数  
        int count = tabWidget.getChildCount();  
        // 获取手机屏幕的宽高  
        DisplayMetrics displayMetrics = new DisplayMetrics();  
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);  
        int screenWidth = displayMetrics.widthPixels;  
		if (count >= 4) {
			for (int i = 0; i < count; i++) {
				// 设置每个标签的宽度，为屏幕的1/4
				tabWidget.getChildTabViewAt(i).setMinimumWidth(
						(screenWidth) / 4);
			}
		} 
		
		/**本地数据库建立，初始表数据*/
		if (!TimeMasterApplication.getInstance().isDataInitialized()) {
			DialogFragment df=new LoadStaticDataFragment();
			df.show(this.getSupportFragmentManager(), "dialog");
		}
		System.out.println("new");
		System.out.println(TimeMasterHelper.getDataBasePath());
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
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
    	// TODO Auto-generated method stub
    	super.onConfigurationChanged(newConfig);
    	TimeMasterApplication.getInstance().setScreenMode(newConfig.orientation);
    	tabHost.invalidate();
    	
    }
}

