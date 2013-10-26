package com.time.master.database;

import java.io.File;

import com.time.master.TimeMasterApplication;
import com.time.master.database.TimeMasterHelper.Columns.ScheduleRecordsColumn;
import com.time.master.tool.ChineseCalendar;
import com.time.master.tool.ExcelParseTool;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class TimeMasterHelper extends SQLiteOpenHelper{
	
	private static final String DB_NAME = "schedule.db";//DB name
	private static final int DB_VERSION = 1;	//DB version
	
	
	private SQLiteDatabase db;
	private Context context;
	
	
    /**
     * 构造函数
     * @param context
     */
	public TimeMasterHelper(Context context){
		super(context, getDataBasePath(), null, DB_VERSION);
		this.context=context;
		setDb(getWritableDatabase());
	}
	/**
	 * 获取本地数据库所在路径
	 * @return 数据库路径
	 */
	public static String getDataBasePath(){
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
			File f=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator + "schedule");
			if(!f.exists()){
				if(f.mkdirs())
					return f.getAbsolutePath()+File.separator+DB_NAME;
			}
			else
				return f.getAbsolutePath()+File.separator+DB_NAME;
		}
		return DB_NAME;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		TimeMasterApplication.getInstance().setDataInitialized(false);
		db.execSQL(CREATE_TABLE_NATIONAL_DISTRICT);
		db.execSQL(CREATE_TABLE_SCHEDULE_RECORDS);
		
		//ExcelParseTool.initNationalLocationByExcel(this.context,db);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public SQLiteDatabase getDb() {
		return db;
	}
	public void setDb(SQLiteDatabase db) {
		this.db = db;
	}

	/**在时间记录表(_SCHEDULE_RECORDS)中插入开始时间(_START_TIME)*/
	public void insertScheduleRecord(ChineseCalendar starttime){
		ContentValues values=new ContentValues();
		values.put(ScheduleRecordsColumn._START_TIME, starttime.getTimeInMillis());
		//values.put(ScheduleRecordsColumn._DURATION_TIME, durationtime.getTimeInMillis());
		//values.put(ScheduleRecordsColumn._END_TIME, endtime.getTimeInMillis());
		System.out.println(starttime.getTimeInMillis());
		//System.out.println(durationtime.getTimeInMillis());
		//System.out.println(endtime.getTimeInMillis());
		db.insert(Tables.T_SCHEDULE_RECORDS, null, values);
	}
	public void updateEndTime(ChineseCalendar endtime){
		ContentValues values=new ContentValues();
		values.put(ScheduleRecordsColumn._END_TIME, endtime.getTimeInMillis());
		String whereClause = null;
		String[] whereArgs = null;
		System.out.println(endtime.getTimeInMillis());
		db.update(Tables.T_SCHEDULE_RECORDS, values, whereClause, whereArgs);
	}
	public void updateDurationTime(ChineseCalendar durationtime){
		ContentValues values=new ContentValues();
		values.put(ScheduleRecordsColumn._DURATION_TIME ,durationtime.getTimeInMillis());
		String whereClause = null;
		String[] whereArgs = null;
		System.out.println(durationtime.getTimeInMillis());
		db.update(Tables.T_SCHEDULE_RECORDS, values, whereClause, whereArgs);
	}
	/*********************************静态数据库操作代码块**********************************************************************/
	/**数据库表名*/
	public interface Tables {
		/**@table 中国省市区县表*/
		public static final String T_NATIONAL_DISTRICT = "t_national_district";
		/**@table 时间管理记录表*/
		public static final String T_SCHEDULE_RECORDS = "t_schedule_records";
				
	}
	/**数据库视图*/
	public interface Views {
		
	}
    /**数据表列名*/
	public interface Columns{
		/**省、直辖市列名*/
		public interface NationDistrictColumn{
			public static final String _ID = "_id";//主键
			public static final String _NAME = "_name";//地名
			public static final String _LEVEL = "_level";//地域级别
			public static final String _UPID = "_upid";//所属地域主键
		}
		
		/**时间管理记录 列名*/
		public interface ScheduleRecordsColumn{
			public static final String _ID = "_id";
			public static final String _START_TIME = "_start_time";
			public static final String _DURATION_TIME = "_duration_time";
			public static final String _END_TIME = "_end_time";
		}

	}
	/*******************************创建表sql命令***************************************************/
	public static final String CREATE_TABLE_NATIONAL_DISTRICT = 
			"CREATE TABLE IF NOT EXISTS " +  Tables.T_NATIONAL_DISTRICT + "("
			+ Columns.NationDistrictColumn._ID + " integer primary key,"	
			+ Columns.NationDistrictColumn._NAME + " text,"
			+ Columns.NationDistrictColumn._LEVEL + " integer,"
			+ Columns.NationDistrictColumn._UPID + " integer"
			+ ")";
	
	public static final String CREATE_TABLE_SCHEDULE_RECORDS = 
			"CREATE TABLE IF NOT EXISTS " +  Tables.T_SCHEDULE_RECORDS + "("
			+ Columns.ScheduleRecordsColumn._ID + " integer primary key,"	
			+ Columns.ScheduleRecordsColumn._START_TIME + " real,"
			+ Columns.ScheduleRecordsColumn._DURATION_TIME + " real,"
			+ Columns.ScheduleRecordsColumn._END_TIME + " real"
			+ ")";

	/***************************************删除表sql命令*****************************************************************/
	public static final String DROP_TABLE_NATIONAL_DISTRICT = "DROP TABLE IF EXISTS " + Tables.T_NATIONAL_DISTRICT+" ";
	public static final String DROP_TABLE_SCHEDULE_RECORDS = "DROP TABLE IF EXISTS " + Tables.T_SCHEDULE_RECORDS+" ";

}
