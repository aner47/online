package com.online.service.impl;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.entity.online.CategoryMenuConfig;
import com.online.service.CategoryMenuConfigService;
import com.online.util.SpringUtils;
/**
 * 行业菜单配置
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2018年2月8日 下午5:32:46 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return
 */
@Service("categoryMenuConfigServiceImpl")
public class CategoryMenuConfigServiceImpl extends BaseServiceImpl<CategoryMenuConfig, Integer> implements CategoryMenuConfigService  {

	@Override
	public String findMenuByIndustry(String industryCategoryId) {
		String menus = null;
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("enterpriseType", Operator.eq, SpringUtils.getCurrentEnterprise().getEnterpriseType().toString()));
		filters.add(new Filter("categoryId", Operator.eq, industryCategoryId));
		
		List<CategoryMenuConfig> categoryMenuConfigs = findList(null, filters, null);
		//findByFilter(new Filter("categoryId", Operator.eq, industryCategoryId));
		if(categoryMenuConfigs != null && categoryMenuConfigs.size() >0){
			menus = categoryMenuConfigs.get(0).getMenus();
		}
		//郑有权
		return menus;
	}
	
	
}
