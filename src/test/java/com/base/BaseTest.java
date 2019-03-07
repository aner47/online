package com.base;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)    
@ContextConfiguration(locations = {"classpath*:/*.xml"})   
public abstract class BaseTest {
	
	public final Logger logger = Logger.getLogger(getClass());
	
	protected String baseFilePath = System.getProperty("user.dir")+"/src/main/resources/data/配置数据表格/";
	
	
	
	
	protected static ApplicationContext applicationContext;
	
	protected static EntityManager em;  
   
	
	 @BeforeClass 
	 public static void beforeClass(){
		 if(applicationContext == null){
			 applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
			 em = applicationContext.getBean("entityManagerFactory",EntityManagerFactory.class).createEntityManager();
		 }
		
		 System.out.println("数据初始化开始！");  
	 }
	 
	 @Before
	 public void before(){
		 em.getTransaction().begin();
	 }
	 
	 
	 @After
	 public void after(){
		 em.getTransaction().commit();
	 }
	
	/**
	 * 读取Excel单元格内容转成String类型
	 * @param row  行对象
	 * @param colNum 列号
	 * @return
	 */
	protected String getStringValue(Row row ,int colNum){
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
	protected double getDoubleValue(Row row ,int colNum){
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
	protected int getIntegerValue(Row row ,int colNum){
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
	
	public boolean isNumeric(String str){ 
		 String regex = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";  
		 if(str.matches(regex)){
			 return true;
		 }
		 return false;
	}
}
