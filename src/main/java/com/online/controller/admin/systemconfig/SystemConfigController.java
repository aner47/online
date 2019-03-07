package com.online.controller.admin.systemconfig;


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
import com.online.entity.SystemConfig;
import com.online.service.SystemConfigService;



@Controller
@RequestMapping("/admin/systemconfig")
public class SystemConfigController extends BaseController{
	
	@Autowired
	private SystemConfigService  systemConfigService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/systemconfig/list";
	}
	
	/**
	 * 增加系统配置页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/systemconfig/add";
	}

	/**
	 * 修改系统配置页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("systemConfig", systemConfigService.find(id));
		return "/admin/systemconfig/update";
	}
	
	/**
	 * 查看系统配置页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("systemConfig", systemConfigService.find(id));
		return "/admin/systemconfig/view";
	}
	
	/**
	 * 查询系统配置
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<SystemConfig> query(Pageable pageable,SystemConfig systemConfig) {
	    String description = systemConfig.getDescription();
        if (StringUtils.isNotEmpty(description)) {
            pageable.addFilter("description", Operator.like,"%"+description.trim()+"%");
        }
		return systemConfigService.findPage(pageable);
		
	}
	

	/**
	 * 保存系统配置
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(SystemConfig  systemConfig) {
		systemConfigService.save(systemConfig);
		return Message.success();
	}

	/**
	 * 更新系统配置
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(SystemConfig systemConfig) {
		systemConfigService.update(systemConfig);
		return Message.success();
	}

	/**
	 * 删除系统配置
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 systemConfigService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
