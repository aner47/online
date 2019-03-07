package com.online.service.impl;



import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.entity.SystemMenuConfig;
import com.online.service.SystemMenuConfigService;
import com.online.service.SystemMenuService;
/**
 * 
 * @author 左志平
 * 
 * 菜单服务实现
 *
 */
@Service("systemMenuConfigServiceImpl")
public class SystemMenuConfigServiceImpl extends BaseServiceImpl<SystemMenuConfig, Integer> implements SystemMenuConfigService {

	@Autowired
	public SystemMenuService systemMenuService;
	
	
	@Override
	public SystemMenuConfig saveSystemMenuConfig(SystemMenuConfig systemMenuConfig) {
		//郑有权
		
		return save(systemMenuConfig);
	}

	@Override
	public SystemMenuConfig updateSystemMenuConfig(SystemMenuConfig systemMenuConfig) {
		//郑有权
		
		return update(systemMenuConfig);
	}

	@Override
	public String findEndMenus(String menus) {
		StringBuffer menusback = new StringBuffer();
		List<Integer> filters = new ArrayList<>();
		if(StringUtils.isNotBlank(menus)){
			String[] meunbacks =  menus.split(",");
			for(String meun:meunbacks){
				filters.add(Integer.parseInt(meun));
			}
			List<SystemMenuConfig> systemMenuConfigs = findByFilter(new Filter("systemMenuFrontId", Operator.in,filters));
			for(SystemMenuConfig systemMenuConfig:systemMenuConfigs){
				menusback.append(systemMenuConfig.getSystemMenuEndId()+",");
			}
		}
		//郑有权
		return menusback.length()>0?menusback.substring(0, menusback.length()-1):"";
	}

	

}