package com.online.service;

import com.online.entity.online.ProjectTemplates;
/**
 * 
 * @author zuozhiping
 * 
 * 项目模板服务接口
 *
 */
public interface ProjectTemplatesService extends BaseService<ProjectTemplates, Integer> {
	
	public ProjectTemplates findName(String name) ;
	
	
	public ProjectTemplates saveApply(ProjectTemplates projectTemplates,Integer nameId) ;
	
	

}