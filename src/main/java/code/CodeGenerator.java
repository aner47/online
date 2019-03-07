package code;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import com.online.annotation.ColumnDescription;
import com.online.annotation.EntityDescription;

public class CodeGenerator {
	public static final String project = "online";
	public static final String servicePackage = "com."+project+".service";
	public static final String serviceImplPackage = "com."+project+".service.impl";
	public static final String daoPackage = "com."+project+".dao";
	public static final String daoImplPackage = "com."+project+".dao.impl";
	public static final String controllerPackage = "com."+project+".controller.admin";
	public static final String servicePostfix = "Service";
	public static final String serviceImplPostfix = "ServiceImpl";
	public static final String daoPostfix = "Dao";
	public static final String daoImplPostfix = "DaoImpl";

	public static final String jspUrl = "/admin";
	public static final String javaFileDir = System.getProperty("user.dir")+"/src/main/java/";
	public static final String jspFileDir = System.getProperty("user.dir")+"/src/main/webapp/WEB-INF/jsp/admin/";
	
	
	public static VelocityEngine ve;
	 static{
		ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");  
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.init();
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
//		System.out.println(System.getProperty("user.dir"));
//		createFile(TaskListLog.class,"任务历史记录","Integer");
		 
	}
	 @SuppressWarnings("rawtypes")
	 public static void createFileByPackName(String basePack) throws ClassNotFoundException, IOException{
		  List<Class> searchClass = searchClass(basePack);
		  System.out.println(searchClass.size());
		  for (Class<?> class1 : searchClass) {
			if(class1.getAnnotation(Entity.class) !=null ){
				EntityDescription annotation = (EntityDescription) class1.getAnnotation(EntityDescription.class);
				createFile(class1,annotation.caption(), "Integer");
			};
			
		}
	 }
	 
	   @SuppressWarnings("rawtypes")
	public static List<Class> searchClass(String basePack ) throws ClassNotFoundException {
	      
	        String classpath = CodeGenerator.class.getResource("/").getPath();
	        basePack = basePack.replace(".", File.separator);
	        String searchPath = classpath + basePack;
	        System.out.println("searchPath ::"+ searchPath);
	        System.out.println("classPaths ::" + classPaths);
	        doPath(new File(searchPath));
	        List<Class> list = new ArrayList<>();
	        for (String s : classPaths) {
	            s = s.replace(classpath.replace("/","\\").replaceFirst("\\\\",""),"").replace("\\",".").replace(".class","");
	           System.out.println("s:::"+s);
	            Class<?> cls = Class.forName(s);
	            list.add(cls);
	        }
	        return list;
	    }
	   static List<String> classPaths = new ArrayList<String>();
	    /**
	     * 该方法会得到所有的类，将类的绝对路径写入到classPaths中
	     * @param file
	     */
	    private static void doPath(File file) {
	        if (file!=null) {
	            if (file.isDirectory()) {//文件夹
	                //文件夹我们就递归
	                File[] files = file.listFiles();
	                if (file!=null) {
	                    for (File f1 : files) {
	                        doPath(f1);
	                    }
	                }
	            } else {//标准文件
	                //标准文件我们就判断是否是class文件
	                if (file.getName().endsWith(".class")) {
	                    //如果是class文件我们就放入我们的集合中。
	                    classPaths.add(file.getPath());
	                }
	            }
            }
	    }
	   
	
	public static void createFile(Class<?> cls,String description,String idType) throws IOException{
		createJavaFile(cls, description, idType);
		createJspFile(cls,description);
		createMenu(cls, description);
	}
	
	public static void createMenu(Class<?> cls,String description){
		String sql = "insert into `sys_menu` ( `create_date`, `modify_date`, `version`, `icon`, `name`, `pid`, `sort`, `url`) values(now(),now(),'0',NULL,'"+description+"','19','0','/admin/"+cls.getSimpleName().toLowerCase()+"/list.jhtml')";
		JdbcTest.excuteSql(sql);
	}
	
	public static void createJavaFile(Class<?> cls,String description,String idType) throws IOException{
		service(cls,description,idType);
		serviceImpl(cls,description, idType);
		dao(cls,description, idType);
		daoImpl(cls,description, idType);
		controller(cls, description, idType);
	}
	
	
	
	public static void createJspFile(Class<?> cls,String description) throws IOException{
		Field[] declaredFields = cls.getDeclaredFields();
		List<Column> list = new ArrayList<>();
		for (Field field : declaredFields) {
			boolean isStatic = Modifier.isStatic(field.getModifiers());
			if(isStatic){
				continue;
			}
			Column column = new Column();
			column.setName(field.getName());
			ColumnDescription annotation = field.getAnnotation(ColumnDescription.class);
			if(annotation == null){
				continue;
			}else{
				column.setCaption(annotation.caption());
				column.setInputType(annotation.inputType());
				column.setHidden(annotation.hidden());
				column.setQuery(annotation.query());
				column.setUpdate(annotation.update());
				column.setCreate(annotation.create());
				column.setFilter(annotation.filter());
			}
			list.add(column);
		}
		Class<?> superclass = cls.getSuperclass();
		for (Field field : superclass.getDeclaredFields()) {
			boolean isStatic = Modifier.isStatic(field.getModifiers());
			if(isStatic){
				continue;
			}
			Column column = new Column();
			column.setName(field.getName());
			ColumnDescription annotation = field.getAnnotation(ColumnDescription.class);
			if(annotation == null){
				continue;
			}else{
				column.setInputType(annotation.inputType());
				column.setHidden(annotation.hidden());
				column.setCaption(annotation.caption());
				column.setQuery(annotation.query());
				column.setUpdate(annotation.update());
				column.setCreate(annotation.create());
				column.setFilter(annotation.filter());
			}
			list.add(column);
		}
		
//		service(cls,description);
//		serviceImpl(cls,description);
//		dao(cls,description);
//		daoImpl(cls,description);
		addJsp(cls,description,list);
		listJsp(cls,description,list);
		updateJsp(cls,description,list);
		viewJsp(cls,description,list);
	}
	/**
	 * 
	 * @param cls
	 * @param description
	 * @throws IOException 
	 */
	public static void viewJsp(Class<?> cls,String description,List<Column> list) throws IOException{
		Template t = ve.getTemplate("template/jsp/view.vm","utf-8");
		VelocityContext ctx = new VelocityContext();
		ctx.put("columns", list);
		ctx.put("entityName", firstCharLowCass(cls.getSimpleName()));
		ctx.put("entityDescription", "查看"+description);
		ctx.put("project", project);
		createJspFile(ctx, t, cls,cls.getSimpleName().toLowerCase(),"view.jsp");
	}
	
	/**
	 * 
	 * @param cls
	 * @param description
	 * @throws IOException 
	 */
	public static void addJsp(Class<?> cls,String description,List<Column> list) throws IOException{
		Template t = ve.getTemplate("template/jsp/add.vm","utf-8");
		VelocityContext ctx = new VelocityContext();
		ctx.put("columns", list);
		ctx.put("project", project);
		ctx.put("entityDescription", "增加"+description);
		createJspFile(ctx, t, cls,cls.getSimpleName().toLowerCase(),"add.jsp");
	}
	/**
	 * 
	 * @param cls
	 * @param description
	 * @throws IOException 
	 */
	public static void listJsp(Class<?> cls,String description,List<Column> list) throws IOException{
		Template t = ve.getTemplate("template/jsp/list.vm","utf-8");
		VelocityContext ctx = new VelocityContext();
		ctx.put("project", project);
		ctx.put("columns", list);
		ctx.put("entityDescription", description);
		createJspFile(ctx, t, cls,cls.getSimpleName().toLowerCase(),"list.jsp");
	}
	
	/**
	 * 
	 * @param cls
	 * @param description
	 * @throws IOException 
	 */
	public static void updateJsp(Class<?> cls,String description,List<Column> list) throws IOException{
		Template t = ve.getTemplate("template/jsp/update.vm","utf-8");
		VelocityContext ctx = new VelocityContext();
		ctx.put("project", project);
		ctx.put("columns", list);
		ctx.put("entityName", firstCharLowCass(cls.getSimpleName()));
		ctx.put("entityDescription", "修改"+description);
		createJspFile(ctx, t, cls,cls.getSimpleName().toLowerCase(),"update.jsp");
	}
	
	/**
	 * 
	 * @param cls
	 * @param description
	 * @throws IOException 
	 */
	public static void controller(Class<?> cls,String description,String idType) throws IOException{
		Template t = ve.getTemplate("template/Controller.vm","utf-8");
		VelocityContext ctx = new VelocityContext();
		ctx.put("entity", cls.getName());
		ctx.put("idType", idType);
		ctx.put("entityVariable",firstCharLowCass(cls.getSimpleName()));
		ctx.put("entityName", cls.getSimpleName());
		ctx.put("entityDescription", description);
		ctx.put("entityLowCass", cls.getSimpleName().toLowerCase());
		ctx.put("entityService", servicePackage+"."+cls.getSimpleName()+servicePostfix);
		ctx.put("entityServiceName", cls.getSimpleName()+servicePostfix);
		ctx.put("entityServiceNameVariable", firstCharLowCass(cls.getSimpleName())+servicePostfix);
		ctx.put("controllerPackage", controllerPackage+"."+cls.getSimpleName().toLowerCase());
		ctx.put("jspPath",jspUrl);
		ctx.put("project", project);
		createJavaFile(ctx, t, cls,controllerPackage+"."+cls.getSimpleName().toLowerCase(),"Controller.java");
	}
	
	/**
	 * 
	 * @param cls
	 * @param description
	 * @throws IOException 
	 */
	public static void dao(Class<?> cls,String description,String idType) throws IOException{
		Template t = ve.getTemplate("template/Dao.vm","utf-8");
		VelocityContext ctx = new VelocityContext();
		ctx.put("entity", cls.getName());
		ctx.put("idType", idType);
		ctx.put("entityName", cls.getSimpleName());
		ctx.put("entityDescription", description);
		ctx.put("daoPackage", daoPackage);
		ctx.put("project", project);
		createJavaFile(ctx, t, cls,daoPackage,"Dao.java");
	}
	/**
	 * 
	 * @param cls
	 * @param description
	 * @throws IOException 
	 */
	public static void daoImpl(Class<?> cls,String description,String idType) throws IOException{
		Template t = ve.getTemplate("template/DaoImpl.vm","utf-8");
		VelocityContext ctx = new VelocityContext();
		ctx.put("entity", cls.getName());
		ctx.put("idType", idType);
		ctx.put("entityName", cls.getSimpleName());
		ctx.put("entityDescription", description);
		ctx.put("daoImplPackage", daoImplPackage);
		ctx.put("entityDao", daoPackage+"."+cls.getSimpleName()+"Dao");
		ctx.put("entityServiceName", cls.getSimpleName()+"Dao");
		ctx.put("project", project);
		ctx.put("beanName", firstCharLowCass(cls.getSimpleName())+"DaoImpl");
		createJavaFile(ctx, t, cls,daoImplPackage,"DaoImpl.java");
	}
	
	/**
	 * 
	 * @param cls
	 * @param description
	 * @throws IOException 
	 */
	public static void service(Class<?> cls,String description,String idType) throws IOException{
		Template t = ve.getTemplate("template/Service.vm","utf-8");
		VelocityContext ctx = new VelocityContext();
		ctx.put("project", project);
		ctx.put("entity", cls.getName());
		ctx.put("idType", idType);
		ctx.put("entityName", cls.getSimpleName());
		ctx.put("entityDescription", description);
		ctx.put("servicePackage", servicePackage);
		createJavaFile(ctx, t, cls,servicePackage,"Service.java");
	}
	
	/**
	 * 
	 * @param cls
	 * @param description
	 * @throws IOException 
	 */
	public static void serviceImpl(Class<?> cls,String description,String idType) throws IOException{
		Template t = ve.getTemplate("template/ServiceImpl.vm","utf-8");
		VelocityContext ctx = new VelocityContext();
		ctx.put("project", project);
		ctx.put("entity", cls.getName());
		ctx.put("idType", idType);
		ctx.put("entityName", cls.getSimpleName());
		ctx.put("beanName", firstCharLowCass(cls.getSimpleName())+"ServiceImpl");
		ctx.put("entityDescription", description);
		ctx.put("serviceImplPackage", serviceImplPackage);
		ctx.put("entityService", servicePackage+"."+cls.getSimpleName()+"Service");
		ctx.put("entityServiceName", cls.getSimpleName()+"Service");
		createJavaFile(ctx, t, cls,serviceImplPackage,"ServiceImpl.java");

	}
	/**
	 * 
	 * @param ctx
	 * @param t
	 * @param cls
	 * @param packageStr
	 * @param filtType
	 * @throws IOException
	 */
	public static void  createJavaFile(VelocityContext ctx,Template t,Class<?> cls,String packageStr,String filtType) throws IOException{
		String filePath = javaFileDir + packageStr.replace(".", "/");
		System.out.println(filePath);
		File  fileDir = new File(filePath);
		if(!fileDir.exists()){
			fileDir.mkdirs();
		}
		File file = new File(filePath+File.separator+cls.getSimpleName()+filtType);
		FileWriter fileWriter = new FileWriter(file);
		t.merge(ctx, fileWriter);
		fileWriter.flush();
		fileWriter.close();
	}
	
	/**
	 * 
	 * @param ctx
	 * @param t
	 * @param cls
	 * @param packageStr
	 * @param filtType
	 * @throws IOException
	 */
	public static void  createJspFile(VelocityContext ctx,Template t,Class<?> cls,String packageStr,String filtType) throws IOException{
		String filePath = jspFileDir + packageStr + File.separator;
		System.out.println(filePath);
		File  fileDir = new File(filePath);
		if(!fileDir.exists()){
			fileDir.mkdirs();
		}
		File file = new File(filePath+filtType);
	
		FileWriter fileWriter = new FileWriter(file);
		t.merge(ctx, fileWriter);
		fileWriter.flush();
		fileWriter.close();
	}
	
	
	public static String firstCharLowCass(String str){
		return str.substring(0,1).toLowerCase()+str.substring(1,str.length()); 
	}
	
	
	


}