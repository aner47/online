package com.online.controller.admin.project;


import java.util.List;

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
import com.online.entity.online.Project;
import com.online.service.ProjectService;
import com.online.service.SystemUserService;



@Controller("projectController")
@RequestMapping("/admin/project")
public class ProjectController extends BaseController{
	
	@Autowired
	private ProjectService  projectService ;
	
	@RequestMapping("/list")
	public String list(){
		return "/admin/project/list";
	}
	
	/**
	 * 增加项目页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/project/add";
	}

	/**
	 * 修改项目页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("project", projectService.find(id));
		return "/admin/project/update";
	}
	
	/**
	 * 查看项目页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("project", projectService.find(id));
		return "/admin/project/view";
	}
	
	/**
	 * 查询项目
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<Project> query(Pageable pageable,Project project) {
		String name = project.getName();
		if (StringUtils.isNotEmpty(name)) {
            pageable.addFilter("name", Operator.like, "%"+name.trim()+"%");
        }
		return projectService.findPage(pageable);
		
	}
	
	/**
	 * 查询所有项目
	 */
	@RequestMapping(value = "/queryAll")
	@ResponseBody
	public List<Project> query(Project project) {
		return projectService.findAll();
		
	}
	

	/**
	 * 保存项目
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(Project  project) {
		 projectService.save(project);
		return Message.success();
	}

	/**
	 * 更新项目
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(Project project) {
//		String oldproject = projectService.find(project.getId()).getInvitationCode();
//		systemUserService.updateProjectInvitationCode(oldproject, null);
		Project projectback =  projectService.update(project);
//		systemUserService.updateProjectInvitationCode(null, projectback.getInvitationCode());
		
		return Message.success();
	}

	/**
	 * 删除项目
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 projectService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
