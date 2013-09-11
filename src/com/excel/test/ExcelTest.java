package com.excel.test;

import java.io.File;
import java.io.ObjectInputStream.GetField;

import jxl.*; 

public class ExcelTest{
    public static void main(String[] args) {
        int i,j,maxRow,maxColumn;
        Sheet sheet;
        Workbook book;
        Cell[] cell;
        try {
            //t.xls为要读取的excel文件名
        	//String file=ExcelTest.class.getResource("/").getPath()+"jxlrwtest.xls";
            book= Workbook.getWorkbook(new File("D:\\中国行政区划.xls")); 
            
            //获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
            sheet=book.getSheet(0); 
            //获取左上角的单元格
            
            //System.out.println("标题："+cell.getContents()); 
            
            maxRow=sheet.getRows();
            
            maxColumn=sheet.getColumns();
            System.out.println(maxRow);
            System.out.println(maxColumn);
            /*while(true)
            {
                //获取每一行的单元格 
                cell1=sheet.getCell(0,i);//（列，行）
                cell2=sheet.getCell(1,i);
                cell3=sheet.getCell(2,i);
                if("".equals(cell1.getContents())==true)    //如果读取的数据为空
                    break;
                System.out.println(cell1.getContents()+"\t"+cell2.getContents()+"\t"+cell3.getContents()); 
                i++;
            }*/
            for(i=0;i<maxRow;i++){
            	
            		cell=sheet.getRow(i);
            		for(j=0;j<cell.length;j++){
            			
            		if("".equals(cell[j].getContents())==true){
            			System.out.print("\t\t");
            			continue;
            		}
            			
            			System.out.print(cell[j].getContents()+"\t");
            		}
            		
            		System.out.print("\n");
            	
            }
            book.close(); 
        }
        catch(Exception e)  { 
        	e.printStackTrace();
        } 
    }
}
