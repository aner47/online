package com.online.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.dao.SystemConfigDao;
import com.online.entity.SystemConfig;
import com.online.service.SystemConfigService;
/**
 * 
 * @author 左志平
 * 
 * 系统配置服务实现
 *
 */
@Service("systemConfigServiceImpl")
public class SystemConfigServiceImpl extends BaseServiceImpl<SystemConfig, Integer> implements SystemConfigService {
	@Autowired
	private SystemConfigDao systemConfigDao;
	public SystemConfig findBySysKey(String syskey){
		return systemConfigDao.findBySysKey(syskey);
	}
}
