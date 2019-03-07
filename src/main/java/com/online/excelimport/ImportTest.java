package com.online.excelimport;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author 作者 E-mail: 郑有权
 * @date 创建时间：2017年8月25日 上午11:28:52
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class ImportTest {

	public static void main(String[] args) {
		FileInputStream fileIn;
		try {
			fileIn = new FileInputStream("D:\\表simp1_规下企业信息表-0823.xlsx");
			// 根据指定的文件输入流导入Excel从而产生Workbook对象
			XSSFWorkbook wb0 = new XSSFWorkbook(fileIn);
			
			// 获取Excel文档中的第一个表单
			Sheet sht0 = wb0.getSheetAt(0);
			// 对Sheet中的每一行进行迭代
			for (Row r : sht0) {
				// 如果当前行的行号（从0开始）未达到2（第三行）则从新循环
				System.out.println(r.getCell(1)+"--"+r.getCell(2)+"--"+r.getCell(3)+"--"+r.getCell(6));
			}
			// 获取Excel文档中的第一个表单
			Sheet sht1 = wb0.getSheetAt(1);
			// 对Sheet中的每一行进行迭代
			for (Row r : sht1) {
				// 如果当前行的行号（从0开始）未达到2（第三行）则从新循环
				System.out.println(r.getCell(1)+"--"+r.getCell(2)+"--"+r.getCell(3)+"--"+r.getCell(6));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
