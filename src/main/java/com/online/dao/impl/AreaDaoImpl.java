package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.AreaDao;
import com.online.entity.Area;

/**
 * 
 * @author 左志平
 * 
 * dao-区域配置实现
 *
 */
@Repository("areaDaoImpl")
public class AreaDaoImpl extends BaseDaoImpl<Area, Integer> implements AreaDao {

	@Override
	public Area findAreaByAreaCode(String areaCode) {
		String sql = "SELECT area FROM Area area WHERE code=:code";
		try {
			return entityManager.createQuery(sql, Area.class).setParameter("code", areaCode).getSingleResult();
		} catch (Exception e) {
			return null; 
		}
	}

}