package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.SystemConfigDao;
import com.online.entity.SystemConfig;

/**
 * 
 * @author 左志平
 * 
 * dao-系统配置实现
 *
 */
@Repository("systemConfigDaoImpl")
public class SystemConfigDaoImpl extends BaseDaoImpl<SystemConfig, Integer> implements SystemConfigDao {

	public SystemConfig findBySysKey(String syskey){
		String jpql = "select systemconfig from SystemConfig systemconfig where syskey=:syskey";
		return entityManager.createQuery(jpql, SystemConfig.class).setParameter("syskey", syskey).getSingleResult();
	}

}