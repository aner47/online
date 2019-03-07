package com.online.controller.admin.systemrole;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.online.entity.SystemMenu;
import com.online.entity.SystemRole;
import com.online.service.SystemMenuService;
import com.online.service.SystemRoleService;



@Controller
@RequestMapping("/admin/systemrole")
public class SystemRoleController extends BaseController{
	
	@Autowired
	private SystemRoleService  systemRoleService ;
	@Autowired
	private SystemMenuService systemMenuService;
	@RequestMapping("/list")
	public String list(){
		return "/admin/systemrole/list";
	}
	
	/**
	 * 增加角色页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/systemrole/add";
	}

	/**
	 * 修改角色页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("systemRole", systemRoleService.find(id));
		return "/admin/systemrole/update";
	}
	
	/**
	 * 查看角色页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("systemRole", systemRoleService.find(id));
		return "/admin/systemrole/view";
	}
	
	/**
	 * 查询角色
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<SystemRole> query(Pageable pageable,SystemRole systemRole) {
	    String name = systemRole.getName();
        if (StringUtils.isNotEmpty(name)) {
            pageable.addFilter("name", Operator.like, "%"+name.trim()+"%");
        }
		return systemRoleService.findPage(pageable);
		
	}
	/**
	 * 查询所有菜单
	 */
	@RequestMapping(value = "/queryRole")
	 @ResponseBody
	public  List<SystemRole> queryRole() {
		return  systemRoleService.findAll();
		
	}
	/**
	 * 查询菜单
	 * 
	 */
	@RequestMapping(value = "/queryRoles")
     @ResponseBody
	public  List<String> queryRole(int id)  {
		List<String> roles =systemRoleService.findRoles(id);
           
		 return roles;
	}
	/**
	 * 保存角色
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(SystemRole  systemRole,String menuIds) {
		 systemRoleService.saveEntity(systemRole,menuIds);
		return Message.success();
	}
	

	/**
	 * 更新角色
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(SystemRole systemRole,String selectPerm) {
		String [] menuid = selectPerm.split(",");
		Set<SystemMenu> systemMenus =new HashSet<>();
		for (String string : menuid) {
			Integer id = Integer.valueOf(string);
			SystemMenu menu = systemMenuService.find(id);
			systemMenus.add(menu);
			systemRole.setSystemMenus(systemMenus);
		}
		
		systemRoleService.update(systemRole);
		return Message.success();
	}

	/**
	 * 删除角色
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
			for (Integer id : ids) {
				//1.删除用户角色关系表
				systemRoleService.deleteUserRole(id);
				//2.角色菜单
				systemRoleService.deleteRoleMenu(id);
				//3.删除角色
				systemRoleService.delete(id);
			}
			 
		return Message.success();
	}
		
}
