package com.online.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.online.dao.ProjectDao;
import com.online.entity.online.Project;

/**
 * 
 * @author 左志平
 * 
 * dao-项目实现
 *
 */
@Repository("projectDaoImpl")
public class ProjectDaoImpl extends BaseDaoImpl<Project, Integer> implements ProjectDao {

	@Override
	public Project findProjectByInvitationCode(String invitationCode) {
		Date date = new Date();
		String jpql = "select project from Project project where invitationCode = :invitationCode and startDate <=:startDate and endDate >=:endDate";
		List<Project> list = entityManager.createQuery(jpql, Project.class).setParameter("invitationCode", invitationCode).setParameter("startDate", date).setParameter("endDate", date).getResultList();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Project findProjectByInvitationCode(String invitationCode, boolean isPastDate) {
		
		Date date = new Date();
		
		List<Project> list = null;
		if(isPastDate){
			String jpql = "select project from Project project where invitationCode = :invitationCode and startDate <=:startDate and endDate >=:endDate";
			 list = entityManager.createQuery(jpql, Project.class).setParameter("invitationCode", invitationCode).setParameter("startDate", date).setParameter("endDate", date).getResultList();
		}else{
			String jpql = "select project from Project project where invitationCode = :invitationCode ";
			 list = entityManager.createQuery(jpql, Project.class).setParameter("invitationCode", invitationCode).getResultList();
		
		}
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}



}