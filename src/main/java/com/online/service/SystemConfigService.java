package com.online.service;

import com.online.entity.SystemConfig;
/**
 * 
 * @author zuozhiping
 * 
 * 系统配置服务接口
 *
 */
public interface SystemConfigService extends BaseService<SystemConfig, Integer> {

	public SystemConfig findBySysKey(String syskey);
}