package com.online.controller.admin.systemdepartment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.Message;
import com.online.Page;
import com.online.Pageable;
import com.online.controller.base.BaseController;
import com.online.entity.SystemDepartment;
import com.online.service.SystemDepartmentService;
import com.online.util.Principal;
import com.online.util.SpringUtils;

/** 
 * @author 作者名 
 * @email  邮箱名
 * @time   2017年4月26日 下午2:15:47 
 */
@Controller("systemDepartmentController")
@RequestMapping("/admin/systemdepartment")
public class SystemDepartmentController extends BaseController {
	
	@Autowired
	private SystemDepartmentService systemDepartmentService;
	
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		return "/admin/systemdepartment/list";
	}
	
	
	
	/**
	 *	添加部门
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/systemdepartment/add";
	}
	
	/**
	 * 查看角色页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("systemDepartment", systemDepartmentService.find(id));
		return "/admin/systemdepartment/view";
	}

	
	
	/**
	 * 修改部门
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("systemDepartment", systemDepartmentService.find(id));
		return "/admin/systemdepartment/update";
	}
	
	
	/**
	 * 查询部门
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<SystemDepartment> query(Pageable pageable,SystemDepartment systemDepartment) {
		return systemDepartmentService.findPage(pageable);
		
	}
	
	/**
	 * 查询所有部门
	 */
	@RequestMapping(value = "/queryDepartment")
	 @ResponseBody
	public  List<SystemDepartment> queryRole() {
		return  systemDepartmentService.findAll();
		
	}
	

	/**
	 * 保存部门
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(SystemDepartment systemDepartment,String selectRole) {
		
		systemDepartmentService.saveDepartmentEntity(systemDepartment, selectRole);
		return Message.success();
	}
	
	

	
	/**
	 * 更新部门
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(SystemDepartment systemDepartment,String selectRole) {
		systemDepartmentService.updateDepartmentEntity(systemDepartment,selectRole);
		return Message.success();
	}
	
	
	

	/**
	 * 删除部门
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			systemDepartmentService.delete(ids[i]);
		}
		return Message.success();
	}
	
	
	
	
	
}
