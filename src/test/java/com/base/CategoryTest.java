package com.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.junit.Test;

import com.online.entity.online.Category;
import com.online.entity.online.Category.CategoryLevel;
import com.zkhq.easyexcel.EasyExcelUtils;
import com.zkhq.easyexcel.bean.MySheet;
import com.zkhq.easyexcel.bean.MyWorkBook;



public class CategoryTest extends BaseTest {
	
	@Test
	public void init2017(){
		
		try {
			MyWorkBook myWorkBook = MyWorkBook.createMyWorkBook(new File("D:/test/行业系统 - 新.xlsx"));
			MySheet mySheet = myWorkBook.getMySheet(1);
			List<List<Object>> excelDatas = mySheet.getDatas();
			if(excelDatas!=null && excelDatas.size()>0){
				Map<String, Category> categoryMap = em.createQuery("select c from Category c", Category.class).getResultList().stream().collect(Collectors.toMap(o1->o1.getCode(), o1->o1,(o1,o2)->o1));
				for (int i = 1; i < excelDatas.size(); i++) {
					String code = EasyExcelUtils.getStringCellValue(excelDatas, i, 2,"");
					String name = EasyExcelUtils.getStringCellValue(excelDatas, i, 3,"");
					Category category = categoryMap.get(code);
					if(category!=null){
						if(Objects.equals(category.getName(), name)){
							//category.setVersionInfo("2011版本");
						}else{
							//category.setVersionInfo("2011版本 2017修改名称");
						}
					}else{
						category = create(code, name);
						em.persist(category);
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Category create(String code ,String name){
		Category category = new Category();
		category.setCode(code);
		category.setName(name);
		//category.setVersionInfo("2017新增");
		category.setCategoryLevel(getCategoryLevel(code));
		category.setParent(getParent(code));
		return category;
	}
	
	private CategoryLevel getCategoryLevel(String code){
		if(code.length() == 1){
			return CategoryLevel.CATEGORIES;
		}else if(code.length() == 4){
			if(code.endsWith("00")){
				return CategoryLevel.MAIN;
			}else if(code.endsWith("0")){
				return CategoryLevel.MIDDLE;
			}else{
				return CategoryLevel.SMALL;
			}
		}
		return null;
	}
	private String getParent(String code){
		if(code.length() == 1){
			return null;
		}else if(code.length() == 4){
			if(code.endsWith("00")){
				int parseInt = Integer.parseInt(code.substring(0,2));
				
				if(parseInt<6){
					return "A";
				}else if(parseInt>=6 && parseInt<13){
					return "B";
				}else if(parseInt>=13 && parseInt<44){
					return "C";
				}else if(parseInt>=44 && parseInt<47){
					return "D";
				}else if(parseInt>=47 && parseInt<51){
					return "E";
				}else if(parseInt>=51 && parseInt<53){
					return "F";
				}else if(parseInt>=53 && parseInt<61){
					return "G";
				}else if(parseInt>=61 && parseInt<63){
					return "H";
				}else if(parseInt>=63 && parseInt<66){
					return "I";
				}else if(parseInt>=66 && parseInt<70){
					return "J";
				}else if(parseInt>=70 && parseInt<71){
					return "K";
				}else if(parseInt>=71 && parseInt<73){
					return "L";
				}else if(parseInt>=73 && parseInt<76){
					return "M";
				}else if(parseInt>=76 && parseInt<80){
					return "N";
				}else if(parseInt>=80 && parseInt<83){
					return "O";
				}else if(parseInt>=83 && parseInt<84){
					return "P";
				}else if(parseInt>=84 && parseInt<86){
					return "Q";
				}else if(parseInt>=86 && parseInt<91){
					return "R";
				}else if(parseInt>=91 && parseInt<97){
					return "S";
				}else if(parseInt>=97){
					return "T";
				}
				return "";
			}else if(code.endsWith("0")){
				return code.substring(0, 2)+"00";
			}else{
				return code.substring(0, 3)+"0";
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		try {
			MyWorkBook myWorkBook = MyWorkBook.createMyWorkBook(new File("D:/test/行业系统 - 新.xlsx"));
			MySheet mySheet = myWorkBook.getMySheet(1);
			List<List<Object>> excelDatas = mySheet.getDatas();
			for (int i = 1; i < excelDatas.size(); i++) {
				String code = EasyExcelUtils.getStringCellValue(excelDatas, i, 2,"");
				String name = EasyExcelUtils.getStringCellValue(excelDatas, i, 3,"aa");
				System.out.println(code+"_"+ name);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
