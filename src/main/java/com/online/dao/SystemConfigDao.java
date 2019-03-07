package com.online.dao;

import com.online.entity.SystemConfig;

/**
 * 
 * @author zuozhiping
 * 
 * dao-系统配置
 *
 */
public interface SystemConfigDao extends BaseDao<SystemConfig, Integer> {

	public SystemConfig findBySysKey(String syskey);
}