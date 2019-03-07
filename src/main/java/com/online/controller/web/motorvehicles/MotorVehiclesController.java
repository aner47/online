package com.online.controller.web.motorvehicles;


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
import com.online.entity.online.motorvehicles.MotorVehicles;
import com.online.service.MotorVehiclesService;
import com.online.util.SpringUtils;



@Controller("motorVehiclesController")
@RequestMapping("/web/motorvehicles")
public class MotorVehiclesController extends BaseController{
	
	@Autowired
	private MotorVehiclesService  motorVehiclesService ;
	
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/admin/motorvehicles/list";
	}
	
	/**
	 * 增加机动车调查表
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/motorvehicles/add";
	}

	/**
	 * 修改机动车调查表
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("motorVehicles", motorVehiclesService.find(id));
		return "/admin/motorvehicles/update";
	}
	
	/**
	 * 查看机动车调查表
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("motorVehicles", motorVehiclesService.find(id));
		return "/admin/motorvehicles/view";
	}
	
	/**
	 * 查询机动车调查表
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<MotorVehicles> query(Pageable pageable,MotorVehicles motorVehicles) {
	    pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		pageable.addEntity(motorVehicles);
		return motorVehiclesService.findPage(pageable);
		
	}
	

	/**
	 * 保存机动车调查表
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(MotorVehicles  motorVehicles) {
		motorVehicles.setProject(SpringUtils.getCurrentProject());
		motorVehiclesService.save(motorVehicles);
		return Message.success();
	}

	/**
	 * 更新机动车调查表
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(MotorVehicles motorVehicles) {
		motorVehiclesService.update(motorVehicles);
		return Message.success();
	}

	/**
	 * 删除机动车调查表
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			motorVehiclesService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
