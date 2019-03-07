package com.online.service;

import com.online.entity.SystemMenuConfig;
/**
 * 
 * @author zuozhiping
 * 
 * 菜单服务接口
 *
 */
public interface SystemMenuConfigService extends BaseService<SystemMenuConfig, Integer> {

	SystemMenuConfig saveSystemMenuConfig(SystemMenuConfig  systemMenuConfig);
	SystemMenuConfig updateSystemMenuConfig(SystemMenuConfig  systemMenuConfig);
	
	/**
	 * 根据前台菜单id查询后台对应菜单id
	 * @author 郑有权
	 * @date 创建时间：2018年5月22日 下午3:19:17 
	 * @param menus  前台菜单id集合  
	 * @return
	 */
	String findEndMenus(String menus);

}