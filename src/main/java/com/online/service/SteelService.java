package com.online.service;

import com.online.entity.online.Steel;
/**
 * 钢铁信息
 */
public interface SteelService extends BaseService<Steel, Integer> {

	public Steel saveSteel(Steel steel);
	
	public Steel updateSteel(Steel steel);
	
	void deleteSteelByEnterpriseId(Integer id) throws Exception;

}