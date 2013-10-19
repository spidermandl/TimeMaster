package com.time.master.tool;

import java.io.File;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.time.master.database.TimeMasterHelper;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * excel 解析工具
 * 
 * @author Desmond
 * 
 */
public class ExcelParseTool {

	/**
	 * java application for testing out side of project
	 * 
	 * @param args
	 */
	// public static void main(String[] args) {
	// int maxRow,maxColumn;
	// Sheet sheet;
	// Workbook book;
	// Cell[] cells;
	// try {
	//
	// StringBuffer sb = new StringBuffer();
	// //t.xls为要读取的excel文件名
	// //String file=ExcelTest.class.getResource("/").getPath()+"jxlrwtest.xls";
	// book= Workbook.getWorkbook(new
	// File("assets"+File.separator+"location.xls"));
	//
	// //获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
	// sheet=book.getSheet(0);
	// //获取左上角的单元格
	//
	// //System.out.println("标题："+cell.getContents());
	//
	// maxRow=sheet.getRows();
	//
	// maxColumn=sheet.getColumns();
	// System.out.println(maxRow);
	// System.out.println(maxColumn);
	//
	// for(int i=0;i<maxRow;i++){
	//
	// cells=sheet.getRow(i);
	// /*while(true)
	// {
	// //获取每一行的单元格
	// cell1=sheet.getCell(0,i);//（列，行）
	// cell2=sheet.getCell(1,i);
	// cell3=sheet.getCell(2,i);
	// if("".equals(cell1.getContents())==true) //如果读取的数据为空
	// break;
	// System.out.println(cell1.getContents()+"\t"+cell2.getContents()+"\t"+cell3.getContents());
	// i++;
	// }*/
	// sb.append("insert "+TimeMasterHelper.Tables.T_NATIONAL_DISTRICT +" ("
	// +TimeMasterHelper.Columns.NationDistrictColumn._ID+", "
	// +TimeMasterHelper.Columns.NationDistrictColumn._NAME+", "
	// +TimeMasterHelper.Columns.NationDistrictColumn._LEVEL+", "
	// +TimeMasterHelper.Columns.NationDistrictColumn._UPID+""
	// +") values ("
	// +cells[0].getContents()+", "
	// +cells[1].getContents()+", "
	// +cells[2].getContents()+", "
	// +cells[3].getContents()+""
	// +")");
	// sb.append("\r\n");
	// System.out.print("\n");
	//
	// }
	// book.close();
	//
	// File backupFile = new File("assets"+File.separator+"location.sql");
	// if (backupFile.exists()) {
	// // 清空之前的记录
	// backupFile.delete();
	// } else {
	// backupFile.getParentFile().mkdirs();
	// }
	// backupFile.createNewFile();
	// FileOutputStream fos = new FileOutputStream(backupFile);
	// fos.write(sb.toString().getBytes());
	// fos.close();
	// }
	// catch(Exception e) {
	// e.printStackTrace();
	// }
	// }

	/***
	 * 从json文件读入数据库
	 * @param context
	 * @param db
	 */
	public static void initNationalLocationByJson(Context context,
			SQLiteDatabase db) {
		try {
			InputStream in = context.getAssets().open("location.json");
			JSONArray array = new JSONArray(Function.ReadFile(in));
			int length = array.length();
			for (int i = 0; i < length; i++) {

				JSONObject json = array.getJSONObject(i);

				StringBuffer sb = new StringBuffer();
				sb.append("insert into ")
						.append(TimeMasterHelper.Tables.T_NATIONAL_DISTRICT)
						.append(" (")
						.append(TimeMasterHelper.Columns.NationDistrictColumn._ID)
						.append(", ")
						.append(TimeMasterHelper.Columns.NationDistrictColumn._NAME)
						.append(", ")
						.append(TimeMasterHelper.Columns.NationDistrictColumn._LEVEL)
						.append(", ")
						.append(TimeMasterHelper.Columns.NationDistrictColumn._UPID)
						.append("").append(") values (").append(json.get("id"))
						.append(", ").append(json.get("name")).append(", ")
						.append(json.get("level")).append(", ")
						.append(json.get("upid")).append("").append(")");
				Log.e("db insert", sb.toString());
				db.execSQL(sb.toString());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从xls文件读入数据库
	 * @param context
	 * @param db
	 */
	public static void initNationalLocationByExcel(Context context,
			SQLiteDatabase db) {
		int maxRow, maxColumn;
		Sheet sheet;
		Workbook book;
		Cell[] cells;
		try {

			// t.xls为要读取的excel文件名
			book = Workbook.getWorkbook(context.getAssets().open("location.xls"));

			// 获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
			sheet = book.getSheet(0);
			// 获取左上角的单元格

			// System.out.println("标题："+cell.getContents());

			maxRow = sheet.getRows();

			maxColumn = sheet.getColumns();
			System.out.println(maxRow);
			System.out.println(maxColumn);

			for (int i = 0; i < maxRow; i++) {
				StringBuffer sb = new StringBuffer();
				cells = sheet.getRow(i);
				sb.append("insert into ")
						.append(TimeMasterHelper.Tables.T_NATIONAL_DISTRICT)
						.append(" (")
						.append(TimeMasterHelper.Columns.NationDistrictColumn._ID)
						.append(", ")
						.append(TimeMasterHelper.Columns.NationDistrictColumn._NAME)
						.append(", ")
						.append(TimeMasterHelper.Columns.NationDistrictColumn._LEVEL)
						.append(", ")
						.append(TimeMasterHelper.Columns.NationDistrictColumn._UPID)
						.append("").append(") values (")
						.append(cells[0].getContents()).append(", ")
						.append(cells[1].getContents()).append(", ")
						.append(cells[2].getContents()).append(", ")
						.append(cells[3].getContents()).append("").append(")");
				Log.e("db insert", sb.toString());
				db.execSQL(sb.toString());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
