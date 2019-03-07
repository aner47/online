package com.online;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *导出Excel 
 * @author wangpan
 * @2017年3月9日
 *
 */
public class ExportExcel{
	
	public static void exportExcel(String title, String[] oneheaders,String[] headers,String[] headerUnits,Map<Integer,List<String>> map, OutputStream out){
		  // 声明一个工作薄
		XSSFWorkbook workbook = new XSSFWorkbook();
		  // 生成一个表格
		XSSFSheet sheet = workbook.createSheet(title);
		  // 生成一个样式
		XSSFCellStyle style = workbook.createCellStyle();
		  // 设置这些样式
//		  style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
//		  style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//		  style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//		  style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//		  style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//		  style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//		  style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		  // 生成一个字体
		  XSSFFont font = workbook.createFont();
//		  font.setColor(HSSFColor.VIOLET.index);
		  font.setFontHeightInPoints((short) 12);
//		  font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		  // 把字体应用到当前的样式
		  style.setFont(font);
		  //第一行
		  XSSFRow row = sheet.createRow(0);
		  for (short i = 0; i < oneheaders.length; i++) {
			  XSSFCell cell = row.createCell(i);
		      cell.setCellStyle(style);
		      cell.setCellValue(oneheaders[i]);
		  }
		  //第一行
		  XSSFRow row1 = sheet.createRow(1);
		  for (short i = 0; i < headers.length; i++) {
			  XSSFCell cell = row1.createCell(i);
			  cell.setCellStyle(style);
			  cell.setCellValue(headers[i]);
		  }
		  
		  XSSFRow row2 = sheet.createRow(2);
		  for (short i = 0; i < headerUnits.length; i++) {
			  XSSFCell cell = row2.createCell(i);
		      cell.setCellStyle(style);
		      cell.setCellValue(headerUnits[i]);
		  }
		  
		  
		  //遍历集合数据，产生数据行
	      int index = 2;
	      style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
	      // 生成另一个字体
	      XSSFFont font2 = workbook.createFont();
	      font2.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
	      for(Integer key:map.keySet()){
	    	  index++;
	    	  List<String> list = map.get(key);
	    	  row = sheet.createRow(index);
	    	  for(int i=0;i<list.size();i++){
	    		  XSSFCell cell = row.createCell(i);
	    		  cell.setCellStyle(style);
	    		  cell.setCellValue(list.get(i));
	    	  }
	      }
	      try {
			workbook.write(out);
			out.flush();
			out.close(); 
	      } catch (IOException e) {
			e.printStackTrace();
	      }
	}
	
	public static void main(String[] args) {
		
		ClassLoader classloader =
				   org.apache.poi.poifs.filesystem.POIFSFileSystem.class.getClassLoader();
				URL res = classloader.getResource(
				             "org/apache/poi/poifs/filesystem/POIFSFileSystem.class");
				String path = res.getPath();
				System.out.println("POI Core came from " + path);

				classloader = org.apache.poi.POIXMLDocument.class.getClassLoader();
				res = classloader.getResource("org/apache/poi/POIXMLDocument.class");
				path = res.getPath();
				System.out.println("POI OOXML came from " + path);

//				classloader = org.apache.poi.hslf.HSLFSlideShow.class.getClassLoader();
//				res = classloader.getResource("org/apache/poi/hslf/HSLFSlideShow.class");
//				path = res.getPath();
//				System.out.println("POI Scratchpad came from " + path);
			   
//		String time = "2005-01-01 00:00:00.0";
//		System.out.println(time.substring(0, 4));
//		System.out.println(time.substring(5, 7));
//		try {
//			FileOutputStream out = new FileOutputStream("D:/enterprise_"+System.currentTimeMillis()+".xls");
//			String[] headers ={"公司名称","联系人","电话","门牌号"};
//			String[] headerUnits ={"吨","吨","吨","吨"};
//			Map<Integer,List<String>> map = new HashMap<>();
//	        for(int i = 0;i<10;i++){
//	        	List<String> list = new ArrayList<String>();
//	        	list.add("test"+i);
//	        	list.add("联系人"+i);
//	        	list.add("联系电话"+i);
//	        	map.put(i+1, list);
//	        }
//			exportExcel("企业表",headers,headerUnits,map,out);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
	}

	
	

}
