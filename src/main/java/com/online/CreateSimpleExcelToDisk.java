package com.online;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.online.entity.online.Enterprise;
import com.online.entity.online.embeddable.Address;

/** 
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2017年7月11日 下午8:41:49 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return  
	*/
public class CreateSimpleExcelToDisk {

	/** 
     * @功能：手工构建一个简单格式的Excel 
     */  
    private static List<Enterprise> getEnterprise() throws Exception  
    {  
        List<Enterprise> list = new ArrayList<>();  
        
        for(int i = 0;i<10;i++){
        	Enterprise enterprise = new Enterprise(); 
        	enterprise.setName("test"+i);
        	enterprise.setContacts("联系人"+i);
        	enterprise.setContactNumber("联系电话"+i);
        	Address address = new Address();
        	address.setHouseNumber("门牌号"+i);
        	enterprise.setAddress(address);
        	list.add(enterprise);
        	
        }
         
       
  
        return list;  
    }  
  
    public static void main(String[] args) throws Exception  
    {  
        // 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("企业表一");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
  
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("公司名称");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 1);  
        cell.setCellValue("联系人");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 2);  
        cell.setCellValue("电话");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 3);  
        cell.setCellValue("门牌号");  
        cell.setCellStyle(style);  
  
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
        List list = CreateSimpleExcelToDisk.getEnterprise();  
  
        for (int i = 0; i < list.size(); i++)  
        {  
            row = sheet.createRow((int) i + 1);  
            Enterprise stu = (Enterprise) list.get(i);  
            // 第四步，创建单元格，并设置值  
            row.createCell((short) 0).setCellValue(stu.getName());  
            row.createCell((short) 1).setCellValue(stu.getContacts());  
            row.createCell((short) 2).setCellValue(stu.getContactNumber());  
            cell = row.createCell((short) 3);  
            cell.setCellValue(stu.getAddress().getHouseNumber());  
        }  
        // 第六步，将文件存到指定位置  
        try  
        {  
            FileOutputStream fout = new FileOutputStream("D:/enterprise.xls");  
            wb.write(fout);  
            fout.close();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
    }  
}
