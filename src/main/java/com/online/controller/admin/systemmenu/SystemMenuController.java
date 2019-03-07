package com.online.controller.admin.systemmenu;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.online.service.SystemMenuService;

/**
 * 菜单入口
 */

@Controller
@RequestMapping("/admin/systemmenu")
public class SystemMenuController extends BaseController{
	
	@Autowired
	private SystemMenuService  systemMenuService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/systemmenu/list";
	}
	
	/**
	 * 增加菜单页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/systemmenu/add";
	}

	/**
	 * 修改菜单页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("systemMenu", systemMenuService.find(id));
		return "/admin/systemmenu/update";
	}
	
	/**
	 * 查看菜单页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("systemMenu", systemMenuService.find(id));
		return "/admin/systemmenu/view";
	}
	
	/**
	 * 查询菜单
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<SystemMenu> query(Pageable pageable,SystemMenu systemMenu) {
	    String name = systemMenu.getName();
        if (StringUtils.isNotEmpty(name)) {
            pageable.addFilter("name", Operator.like,"%" +name.trim()+"%");
        }
		return systemMenuService.findPage(pageable);
		
	}
	/**
	 * 查询菜单
	 * 
	 */
	@RequestMapping(value = "/queryMenus")
     @ResponseBody
	public  List<String> queryMenus(int id)  {
		List<String> menus =systemMenuService.queryMenus(id);
           
		 return menus;
	}
	
	/**
	 * 查询菜单详情
	 */
	@RequestMapping(value = "/info")
	@ResponseBody
	public List<SystemMenu> info(SystemMenu menu) {
		return  systemMenuService.findList(menu.getId());
		
	}
	
	/**
	 * 查询所有菜单
	 */
	@RequestMapping(value = "/querymenu")
	 @ResponseBody
	public  List<SystemMenu> querymenu(ModelMap model ) {
		return  systemMenuService.findAll();
		
	}
	/**
	 * 查询所有菜单
	 */
	@RequestMapping(value = "/querymenuByPid")
	@ResponseBody
	public  List<SystemMenu> querymenuByPid(Integer pid ) {
		return  systemMenuService.querymenuByPid(pid);
		
	}
	/**
	 * 动态菜单
	 */
	@RequestMapping(value = "/queryActivemenu")
	 @ResponseBody
	public  List<SystemMenu> queryActivemenu(ModelMap model ,HttpServletRequest request) {
		List<SystemMenu> queryActivemenu = systemMenuService.queryActivemenu();
		return  queryActivemenu;
		
	}
	

	/**
	 * 保存菜单
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(SystemMenu  systemMenu) {
		 systemMenuService.save(systemMenu);
		return Message.success();
	}

	/**
	 * 角色授权菜单 ******************************************
	 */
	@RequestMapping(value="/perms")
	public Message perms(ModelMap model){
		//查询列表数据
		List<SystemMenu> menuList = systemMenuService.queryList(new HashMap<String, Object>());
		model.put("menuList", menuList);
		return Message.success();
	}
	
	
	
	/**
	 * 更新菜单
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(SystemMenu systemMenu) {
		systemMenuService.update(systemMenu);
		return Message.success();
	}

	/**
	 * 删除菜单
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 systemMenuService.delete(ids[i]);
		}
		return Message.success();
	}
	
		
}
