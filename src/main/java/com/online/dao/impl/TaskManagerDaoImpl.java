package com.online.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.online.dao.TaskManagerDao;
import com.online.entity.online.TaskManager;
import com.online.util.Constants;

/**
 * 
 * @author 左志平
 * 
 * dao-任务管理实现
 *
 */
@Repository("taskManagerDaoImpl")
public class TaskManagerDaoImpl extends BaseDaoImpl<TaskManager, Long> implements TaskManagerDao {
	
	
	public void completeByEnterprise(Integer enterpriseId){
		String jpql = "update TaskManager task set task.status='complete' where task.enterprise.id=:enterpriseId";
		entityManager.createQuery(jpql).setParameter("enterpriseId", enterpriseId).executeUpdate();
	}
	//已处理
	public void processedByEnterprise(Integer enterpriseId){
		String jpql = "update TaskManager task set task.status='processed' where task.enterprise.id=:enterpriseId";
		entityManager.createQuery(jpql).setParameter("enterpriseId", enterpriseId).executeUpdate();
	}

	@Override
	public void resetTask(Integer enterpriseId) {
		String jpql = "update Enterprise en set en.taskType='UNTREATED' where id = :enterpriseId";
		entityManager.createQuery(jpql).setParameter("enterpriseId", enterpriseId).executeUpdate();
		System.out.println("jpql::::::::::"+jpql);
	}
	
	
	@Override
	public void distribution(Long id, String user,String enterpriseType,Integer projectTypeId) throws Exception {
		// TODO Auto-generated method stub
		//郑有权
		String jpql = "update TaskManager task set task.executor=:executor,task.enterpriseType=:enterpriseType,task.projectTypeId=:projectTypeId,task.status = 'assigned' where task.id=:Id";
		Query euery = entityManager.createQuery(jpql);
		euery.setParameter("executor", user);
		euery.setParameter("enterpriseType", Constants.getEnterpriseType(enterpriseType));
		euery.setParameter("projectTypeId", projectTypeId);
		euery.setParameter("Id", id).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> progressQuery(Integer projectId) {
		//郑有权
		List<Map<String, Object>> resultList = new ArrayList<>();
		String sql = "SELECT executor,COUNT(STATUS) statusCount,STATUS FROM ol_task_manager WHERE project_id = ?1  GROUP BY executor,STATUS";
		Query createNativeQuery = entityManager.createNativeQuery(sql.toString());
		
		createNativeQuery.setParameter(1, projectId);
		createNativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

		resultList = createNativeQuery.getResultList();
		
		return resultList;
	}

	
	

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findStatistics(Integer projectId,Integer type,Date start,Date end) {
		//郑有权
		List<Map<String, Object>> resultList = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		if(type ==1 ){
			sql.append("SELECT county_name,COUNT(STATUS) statusCount,STATUS FROM ol_task_manager WHERE project_id = ?1");
			if(start != null && end != null){
				sql.append(" AND create_date BETWEEN ?2 AND ?3");
			}
			sql.append(" GROUP BY county_name,STATUS ");
		//分区县已创建填报任务数量
		}else if(type == 2){
			sql.append(" SELECT county_name ,  COUNT(*) count  FROM  ol_task_manager  WHERE project_id = ?1 AND enterprise IS NOT NULL");
			if(start != null && end != null){
				sql.append(" AND create_date BETWEEN ?2 AND ?3");
			}
			sql.append(" GROUP BY county_name ");
		//分区县已认领未创建填报任务的企业数量（即统计选择的企业状态为仅销售/门店/办公、企业实体不存在等4种状态的企业数量）
		}else if(type == 3){
			sql.append("SELECT county_name , enterprise_status , COUNT(*) count   FROM ol_task_manager WHERE project_id = ?1 AND enterprise IS  NULL AND STATUS = 'complete'");
			if(start != null && end != null){
				sql.append(" AND create_date BETWEEN ?2 AND ?3");
			}
			sql.append(" GROUP BY county_name,enterprise_status ");
		}
		
		Query createNativeQuery = entityManager.createNativeQuery(sql.toString());
		
		createNativeQuery.setParameter(1, projectId);
		if(start != null && end != null){
			createNativeQuery.setParameter(2, start);
			createNativeQuery.setParameter(3, end);
		}
		createNativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

		resultList = createNativeQuery.getResultList();
		
		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findCompleteTaskManager(Integer projectId) {
		//郑有权
		
//		String jpql = "Select en From TaskManager en where en.projectId = :projectId and STATUS = 'complete'";
//		return entityManager.createQuery(jpql).setParameter("projectId", projectId).getResultList();
		List<Map<String, Object>> resultList = new ArrayList<>();
		String sql = "Select en.id id,en.enterprise_name name From ol_task_manager en where en.project_id = ?1 and STATUS = 'complete'";
		Query createNativeQuery = entityManager.createNativeQuery(sql.toString());
		createNativeQuery.setParameter(1, projectId);
		createNativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

		resultList = createNativeQuery.getResultList();
		
		return resultList;
		
	}



}