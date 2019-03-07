package com.online.service;

import com.online.entity.online.CategoryMenuConfig;
/**
 * 行业菜单配置
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2018年2月8日 下午5:34:33 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return
 */
public interface CategoryMenuConfigService extends BaseService<CategoryMenuConfig, Integer> {
	
	String findMenuByIndustry(String industryCategoryId);

}