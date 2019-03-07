package com.online.dao;

import com.online.entity.Area;

/**
 * 
 * @author zuozhiping
 * 
 * dao-区域配置
 *
 */
public interface AreaDao extends BaseDao<Area, Integer> {

	/**
	 * 根据区域代码查询区域
	 * @param areaName
	 * @return
	 */
	public Area findAreaByAreaCode(String areaCode);
}