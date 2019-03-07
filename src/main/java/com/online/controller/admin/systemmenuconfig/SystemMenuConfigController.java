package com.online.controller.admin.systemmenuconfig;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.Filter.Operator;
import com.online.Message;
import com.online.Page;
import com.online.Pageable;
import com.online.controller.base.BaseController;
import com.online.entity.SystemMenuConfig;
import com.online.service.SystemMenuConfigService;

/**
 * 菜单配置入口
 */

@Controller
@RequestMapping("/admin/systemmenuconfig")
public class SystemMenuConfigController extends BaseController{
	
	@Autowired
	private SystemMenuConfigService  systemMenuConfigService ;
	
	@RequestMapping("/list")
	public String list(){
		return "/admin/systemmenuconfig/list";
	}
	
	/**
	 * 增加菜单页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/systemmenuconfig/add";
	}

	/**
	 * 修改菜单页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("systemMenuConfig", systemMenuConfigService.find(id));
		return "/admin/systemmenuconfig/update";
	}
	
	/**
	 * 查看菜单页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("systemMenuConfig", systemMenuConfigService.find(id));
		return "/admin/systemmenuconfig/view";
	}
	
	/**
	 * 查询菜单
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<SystemMenuConfig> query(Pageable pageable,SystemMenuConfig systemMenuConfig) {
		if(systemMenuConfig != null ){
			String enterpriseType = systemMenuConfig.getEnterpriseType();
			 if (StringUtils.isNotBlank(enterpriseType)) {
		            pageable.addFilter("enterpriseType", Operator.eq,enterpriseType);
		        }
			 
			 Integer systemMenuFrontId = systemMenuConfig.getSystemMenuFrontId();
			 if (systemMenuFrontId != null) {
				 pageable.addFilter("systemMenuFrontId", Operator.eq,systemMenuFrontId);
			 }
			Integer systemMenuEndId = systemMenuConfig.getSystemMenuEndId();
	        if (systemMenuEndId != null) {
	        	pageable.addFilter("systemMenuEndId", Operator.eq,systemMenuEndId);
	        }
			
		}
	    
		return systemMenuConfigService.findPage(pageable);
		
	}
	
	

	/**
	 * 保存菜单
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(SystemMenuConfig  systemMenuConfig) {
		
		systemMenuConfigService.saveSystemMenuConfig(systemMenuConfig);
		return Message.success();
	}

	
	
	
	/**
	 * 更新菜单
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(SystemMenuConfig systemMenuConfig) {
		systemMenuConfigService.updateSystemMenuConfig(systemMenuConfig);
		return Message.success();
	}

	/**
	 * 删除菜单
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			systemMenuConfigService.delete(ids[i]);
		}
		return Message.success();
	}
	
		
}
