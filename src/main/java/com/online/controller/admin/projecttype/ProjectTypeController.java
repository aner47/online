package com.online.controller.admin.projecttype;


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
import com.online.entity.ProjectType;
import com.online.service.ProjectService;
import com.online.service.ProjectTypeService;



@Controller("adminProjectTypeController")
@RequestMapping("/admin/projecttype")
public class ProjectTypeController extends BaseController{
	
	@Autowired
	private ProjectTypeService projectTypeService;
	
	@Autowired
	private ProjectService projectService;
	
	@RequestMapping("/list")
	public String list(ModelMap model,Integer projectId){
		model.put("projectId",projectId);
		return "/admin/projecttype/list";
	}
	
	/**
	 * 增加页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model,Integer projectId) {
		model.put("projectId",projectId);
		return "/admin/projecttype/add";
	}

	/**
	 * 修改页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("projecttype", projectTypeService.find(id));
		return "/admin/projecttype/update";
	}
	
	
	/**
	 * 查看页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("projecttype", projectTypeService.find(id));
		return "/admin/projecttype/view";
	}
	
	/**
	 * 查询
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<ProjectType> query(Pageable pageable,ProjectType projectType,Integer projectId,String enterpriseType) {
		pageable.addFilter("project",Operator.eq,projectId);
		if(StringUtils.isNotBlank(enterpriseType)){
			pageable.addFilter("enterpriseType",Operator.eq,enterpriseType);
		}
		
		return projectTypeService.findPage(pageable);
		
	}
	

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(ProjectType projecttype,Integer projectId) {
		projecttype.setProject(projectId);
		projectTypeService.save(projecttype);
		return Message.success();
	}
	
	/**
	 * 更新
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Message update(ProjectType projectType) {
		projectTypeService.update(projectType,"project");
		return Message.success();
	}
	

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			projectTypeService.delete(ids[i]);
		}
		return Message.success();
	}
	
		
}
