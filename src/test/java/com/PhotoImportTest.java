package com;

import java.io.File;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

import com.base.BaseTest;
import com.online.entity.online.Enterprise;
import com.online.entity.online.PhotoFile;
import com.online.entity.online.Project;
import com.online.service.EnterpriseService;
import com.online.service.ProjectService;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;

/** 
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2019年1月29日 下午5:19:17 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return  
	*/
public class PhotoImportTest extends BaseTest {
	
	
	String path = "D:/项目/online/企业名单/online/onlinegongye";
	String orgPath = "D:/项目/online/企业名单/online1/";
	String prefix = "/online/upload/feigongye/";
	
	@Test
	public void test(){
		EnterpriseService bean = applicationContext.getBean(EnterpriseService.class);
		ProjectService projectService = applicationContext.getBean(ProjectService.class);
		Project project = projectService.find(42);
		Map<String, Enterprise> collect = bean.findAll().stream().collect(Collectors.toMap(o1->o1.getId()+"", o1->o1,(o1,o2)->o1));
		File file = new File(path);
		File[] listFiles = file.listFiles();
		StringBuffer errorStr = new StringBuffer();
		for (File file2 : listFiles) {
			String name = file2.getName();
			 String name1 = name.split("\\.")[0].split("-")[0];
			 String name2 = name.split("\\.")[1];
			 name = name1;
			System.out.println(name);
			//湖南省中南炉业有限公司(2).png 如果是第2张图片
			if(name.contains("(")){
				name = name.substring(0, name.indexOf("("));
			}
			Enterprise enterprise = collect.get(name);
			if(enterprise!=null){
				String uuid = UUID.randomUUID().toString();
				String newName = uuid+"."+name2;
				FileUtil.copy(file2, new File(orgPath+newName),false);
				FileUtil.del(file2);
				PhotoFile photoFile = new PhotoFile();
				photoFile.setFilename("b_"+(file2.getName().split("-").length==2?enterprise.getName()+"-"+file2.getName().split("-")[1]:enterprise.getName()));
				photoFile.setUrl(prefix+newName);
				photoFile.setEnterprise(enterprise);
				photoFile.setProject(project);
				em.persist(photoFile);
			}else{
				errorStr.append(name);
			}
		}
		FileUtil.writeString(errorStr.toString(), new File("D:/error.txt"), "utf8");
		
	}

}
