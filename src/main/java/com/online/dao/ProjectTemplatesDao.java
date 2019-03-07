package com.online.dao;

import com.online.entity.online.ProjectTemplates;

/**
 * 
 * @author zuozhiping
 * 
 * dao-项目模板
 *
 */
public interface ProjectTemplatesDao extends BaseDao<ProjectTemplates, Integer> {

	public ProjectTemplates findName(String name) ;

}