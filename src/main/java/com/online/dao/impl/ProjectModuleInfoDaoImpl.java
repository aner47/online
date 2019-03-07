package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.ProjectModuleInfoDao;
import com.online.entity.online.ProjectModuleInfo;

/**
 * 
 * @author 左志平
 * 
 * dao-项目模块信息实现
 *
 */
@Repository("projectModuleInfoDaoImpl")
public class ProjectModuleInfoDaoImpl extends BaseDaoImpl<ProjectModuleInfo, Integer> implements ProjectModuleInfoDao {

    @Override
    public void delteModuleInfoByTemplateId(Integer ids) {
       String sql="delete from ol_project_module_info where project_templates =?1";
       entityManager.createNativeQuery(sql).setParameter(1, ids).executeUpdate();
       
        
    }

	@Override
	public ProjectModuleInfo findName(String name) {
		
		String jpql = "select project from ProjectModuleInfo project where name = :name";
		
		try {
			return (ProjectModuleInfo)entityManager.createQuery(jpql, ProjectModuleInfo.class).setParameter("name", name).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}
		
		
	}



}