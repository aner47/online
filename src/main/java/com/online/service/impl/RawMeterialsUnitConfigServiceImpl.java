package com.online.service.impl;



import org.springframework.stereotype.Service;

import com.online.entity.online.RawMeterialsUnitConfig;
import com.online.service.RawMeterialsUnitConfigService;
/**
 * 
 * @author 左志平
 * 
 * 原辅料单位配置服务实现
 *
 */
@Service("rawMeterialsUnitConfigServiceImpl")
public class RawMeterialsUnitConfigServiceImpl extends BaseServiceImpl<RawMeterialsUnitConfig, Integer> implements RawMeterialsUnitConfigService {
	@Override
	public RawMeterialsUnitConfig save(RawMeterialsUnitConfig entity) {
		// TODO Auto-generated method stub
		//郑有权
		entity.setStatus("unsynchronized");
		return super.save(entity);
	}
	
	@Override
	public RawMeterialsUnitConfig update(RawMeterialsUnitConfig entity) {
		// TODO Auto-generated method stub
		//郑有权
		entity.setStatus("unsynchronized");
		return super.update(entity);
	}
}
