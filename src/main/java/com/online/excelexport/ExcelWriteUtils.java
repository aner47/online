package com.online.excelexport;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriteUtils {
	
	
	   public static void writeValue2Excel(String value ,Sheet sheet ,int rowNumber,int colNumber){
		   Row row = sheet.getRow(rowNumber);
		   if(row == null){
			   row = sheet.createRow(rowNumber);
		   }
		   Cell cell = row.createCell(colNumber);
		   cell.setCellValue(value);  
	   } 
	   
	   
		/**
		 * 读取Excel单元格内容转成String类型
		 * @param row  行对象
		 * @param colNum 列号
		 * @return
		 */
		public static String getStringValue(Row row ,int colNum){
			try {
				Cell cell = row.getCell(colNum);
				if(cell == null) {
					return "";
				}
				int key =cell.getCellType() ; 
				switch (key) {
					case Cell.CELL_TYPE_NUMERIC:
						return (long)cell.getNumericCellValue() +"";
					case Cell.CELL_TYPE_ERROR:
						return "-1";
					case Cell.CELL_TYPE_BOOLEAN:
						return cell.getBooleanCellValue()?"true":"false";
					case Cell.CELL_TYPE_STRING:
						return cell.getStringCellValue().trim();
					case Cell.CELL_TYPE_BLANK:
						return "";
					default:
						return "";
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("colNum:"+colNum);
				System.out.println("row::"+row.getRowNum());
				return  "" ;
			}
		}
		
		/**
		 * 读取Excel单元格内容转成double类型
		 * @param row  行对象
		 * @param colNum 列号
		 * @return
		 */
		public static double getDoubleValue(Row row ,int colNum){
			try {
				Cell cell = row.getCell(colNum);
				if(cell == null) {
					return 0;
				}
				int key =cell.getCellType() ; 
				switch (key) {
					case Cell.CELL_TYPE_NUMERIC:
						return cell.getNumericCellValue();
					case Cell.CELL_TYPE_ERROR:
						return -1;
					case Cell.CELL_TYPE_BOOLEAN:
						return (cell.getBooleanCellValue()?1:0);
					case Cell.CELL_TYPE_STRING:
						Boolean isnumber = isNumeric(cell.getStringCellValue().trim());
						if(!isnumber){
							return -1;
						}
						if(Double.parseDouble(cell.getStringCellValue().trim())<0){
							return -1;
						}
						return Double.parseDouble(cell.getStringCellValue().trim());
					case Cell.CELL_TYPE_BLANK:
						return 0;
					default:
						return 0;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("colNum:"+colNum);
				System.out.println("row::"+row.getRowNum());
				return -1;
			}
		}
		/**
		 * 读取Excel单元格内容转成int类型
		 * @param row  行对象
		 * @param colNum 列号
		 * @return
		 */
		public static int getIntegerValue(Row row ,int colNum){
			try {
				Cell cell = row.getCell(colNum);
				if(cell == null) {
					return -1;
				}
				int key =cell.getCellType() ; 
				switch (key) {
					case Cell.CELL_TYPE_NUMERIC:
						return (int)cell.getNumericCellValue();
					case Cell.CELL_TYPE_ERROR:
						return -1;
					case Cell.CELL_TYPE_BOOLEAN:
						return cell.getBooleanCellValue()?1:0;
					case Cell.CELL_TYPE_STRING:
						Boolean isnumber = isNumeric(cell.getStringCellValue());
						if(!isnumber){
							return -1;
						}
						if(Double.parseDouble(cell.getStringCellValue().trim())<0){
							return -1;
						}
						return Integer.parseInt(cell.getStringCellValue().trim());
					case Cell.CELL_TYPE_BLANK:
						return -1;
					default:
						return -1;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("colNum:"+colNum);
				System.out.println("row::"+row.getRowNum());
				return  -1 ;
			}
		}
		
		
		public static boolean isNumeric(String str){ 
			 String regex = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";  
			 if(str.matches(regex)){
				 return true;
			 }
			 return false;
		}
		
		@SuppressWarnings("resource")
		public static Sheet getSheet(String filePath,int sheetNum){
			System.out.println(new File(filePath).exists());
			XSSFWorkbook wb;
			try {
				wb = new XSSFWorkbook(new FileInputStream(filePath));
				return  wb.getSheetAt(sheetNum);
			} catch (Exception e) {
				return null;
			}  
		}
		
	public static boolean isBlankRow(Row row) {
		if (row == null)
			return true;
		boolean result = true;
		for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
			Cell cell = row.getCell(i, HSSFRow.RETURN_BLANK_AS_NULL);
			String value = "";
			if (cell != null) {
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					break;
				case Cell.CELL_TYPE_NUMERIC:
					value = String.valueOf((int) cell.getNumericCellValue());
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					value = String.valueOf(cell.getBooleanCellValue());
					break;
				case Cell.CELL_TYPE_FORMULA:
					value = String.valueOf(cell.getCellFormula());
					break;
				// caseCell.CELL_TYPE_BLANK:
				// break;
				default:
					break;
				}

				if (!value.trim().equals("")) {
					result = false;
					break;
				}
			}
		}

		return result;
	}
}
