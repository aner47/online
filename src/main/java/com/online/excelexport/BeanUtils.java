package com.online.excelexport;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.PropertyUtils;

import com.online.annotation.ColumnDescription;
import com.online.entity.online.a1constructioncommittee.HouseBuildSite;
import com.online.util.DateUtils;


public class BeanUtils {
	
	
	/**
	 * 获取对象和父类属性字段
	 * @author 郑有权
	 * @date 创建时间：2019年1月9日 上午11:48:35 
	 * @param o
	 * @return
	 */
	public static Map<String,String> getFiledsDescMap(Class<?> cls){
		List<Field> fileds = getFileds(cls);
		return fileds.stream().filter(o1->o1.getAnnotation(ColumnDescription.class) != null).collect(Collectors.toMap(o1->o1.getName(), o1->o1.getAnnotation(ColumnDescription.class).caption(),(o1,o2)->o1));
	}
	
	public static Map<String,String> getObj(Object object){
		Map<String, String> filedsDescMap = getFiledsDescMap(object.getClass());
		Map<String,String> objNameValue = new HashMap<>();
		for (Entry<String,String> entry : filedsDescMap.entrySet()) {
			String value = "";
			String label = entry.getValue();
			if(label.contains("开工时间") || label.contains("竣工时间")){
				Date date = (Date) getValueByName(object, entry.getKey());
				value = DateUtils.formatDate(date, DateUtils.DATE_FORMAT_DATEONLY);
			}else{
				
				 value = getPropertyByName(object, entry.getKey());
			}
			
			objNameValue.put(label, value);
		}
		
		return objNameValue;
	}
	
	/**
	 * 获取对象和父类属性字段
	 * @author 郑有权
	 * @date 创建时间：2019年1月9日 上午11:48:35 
	 * @param o
	 * @return
	 */
	public static List<Field> getFileds(Class<?> cls){
		List<Field> list = new ArrayList<>();
		Field[] fields = cls.getDeclaredFields();
		
		list.addAll(Arrays.asList(fields));
		Class<?> superClazz = cls.getSuperclass();
		if (superClazz != null) {
		Field[] superFields = superClazz.getDeclaredFields();
		list.addAll(Arrays.asList(superFields));
		}
//		list.stream().map(o1->o1.getName()).forEach(o1->System.out.print(o1+","));
		list = list.stream().filter(o1->!o1.getName().equals("serialVersionUID")).filter(o1->!o1.getName().equals("id")).collect(Collectors.toList());
//		System.out.println();
//		list.stream().map(o1->o1.getName()).forEach(o1->System.out.print(o1+","));
//		System.out.println();
		return list;
	}
	
	public static void main(String[] args) {
		getFiledsDescMap(HouseBuildSite.class);
	}
	
	/**
	 * 获取属性名数组
	 */
	public static String[] getFiledName(Object o) {
		List<Field> list = getFileds(o.getClass());
        String[] fieldNames = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getName());
			fieldNames[i] = list.get(i).getName();
		}
		return fieldNames;
	}
	
	/**
	 * 获取对象的所有属性值，返回一个对象数组
	 */
	public static Object[] getFiledValues(Object o) {
		String[] fieldNames = getFiledName(o);
		Object[] value = new Object[fieldNames.length];
		for (int i = 0; i < fieldNames.length; i++) {
			value[i] = getPropertyByName(o, fieldNames[i]);
		}
		return value;
	}
	
	/**
	 * 获取column中的值
	 */
	public static String[] getFiledColumn(Class<?> cls) {
		List<Field> list = getFileds(cls);
		String[] fieldNames = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			ColumnDescription column=list.get(i).getAnnotation(ColumnDescription.class);
		    if(column!=null){
		    	System.out.println("column="+column.caption());
		    	fieldNames[i] = column.caption();
		    	
		    }
		}
		return fieldNames;
	}

	public static String getPropertyByName(Object obj, String name) {
		obj = getValueByName(obj, name);
		if (obj == null) {
			return "";
		}
		return obj.toString();
	}

	public static Object getValueByName(Object obj, String name) {
		String[] names = name.split("\\.");
		for (String fieldName : names) {
			try {
				if (obj == null) {
					return "";
				}
				obj = PropertyUtils.getProperty(obj, fieldName);

			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			}
		}
		return obj;
	}
	
	
	
	
	

	/**
	 * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
	 */
	public static List<Map<String, Object>> getFiledsInfo(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		// String[] fieldNames=new String[fields.length];
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> infoMap = null;
		for (int i = 0; i < fields.length; i++) {
			infoMap = new HashMap<>();
			infoMap.put("type", fields[i].getType().toString());
			infoMap.put("name", fields[i].getName());
			infoMap.put("value", getPropertyByName(o, fields[i].getName()));
			list.add(infoMap);
		}
		return list;
	}

	

}
