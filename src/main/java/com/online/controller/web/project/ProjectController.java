package com.online.controller.web.project;


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
import com.online.entity.online.Project;
import com.online.service.ProjectService;



@Controller("webProjectController")
@RequestMapping("/web/project")
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
		pageable.addEntity(project);
		return projectService.findPage(pageable);
		
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
		projectService.update(project);
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
