package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.ProjectTemplatesDao;
import com.online.entity.online.ProjectTemplates;

/**
 * 
 * @author 左志平
 * 
 * dao-项目模板实现
 *
 */
@Repository("projectTemplatesDaoImpl")
public class ProjectTemplatesDaoImpl extends BaseDaoImpl<ProjectTemplates, Integer> implements ProjectTemplatesDao {

	@Override
	public ProjectTemplates findName(String name) {
			String jpql = "select project from ProjectTemplates project where name = :name";
		
		try {
			return (ProjectTemplates)entityManager.createQuery(jpql, ProjectTemplates.class).setParameter("name", name).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}
	}



}