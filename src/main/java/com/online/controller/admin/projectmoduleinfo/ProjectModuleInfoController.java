package com.online.controller.admin.projectmoduleinfo;


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
import com.online.entity.online.ProjectModuleInfo;
import com.online.entity.online.ProjectTemplates;
import com.online.service.ProjectModuleInfoService;
import com.online.service.ProjectTemplatesService;



@Controller
@RequestMapping("/admin/projectmoduleinfo")
public class ProjectModuleInfoController extends BaseController{
	
	@Autowired
	private ProjectModuleInfoService  projectModuleInfoService ;
	@Autowired
	private ProjectTemplatesService projectTemplatesService;
	@RequestMapping("/list")
	public String list(ModelMap map ,String templateId,String source){
	    map.put("templateId", templateId);
	    map.put("source", source);
		return "/admin/projectmoduleinfo/list";
	}
	
	/**
	 * 增加项目模块信息页面
	 * @param moduleId 项目模板ID
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap map ,Integer templateId,String source){
		ProjectTemplates projectTemplates = projectTemplatesService.find(templateId);
        map.put("templateId", templateId);
        map.put("projectTemplates", projectTemplates);
        map.put("source", source);
		return "/admin/projectmoduleinfo/add";
	}

	/**
	 * 修改项目模块信息页面
	 * @param moduleId 项目模板ID
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id,String templateId) {
	    model.put("templateId", templateId);
		model.put("projectModuleInfo", projectModuleInfoService.find(id));
		return "/admin/projectmoduleinfo/update";
	}
	
	/**
	 * 查看项目模块信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("projectModuleInfo", projectModuleInfoService.find(id));
		return "/admin/projectmoduleinfo/view";
	}
	
	/**
	 * 查询项目模块信息
	 * @param templateId 项目模板ID
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<ProjectModuleInfo> query(Pageable pageable,Integer templateId) {
	    pageable.addFilter("projectTemplates.id", Operator.eq, templateId);
		return projectModuleInfoService.findPage(pageable);
		
	}
	

	/**
	 * 保存项目模块信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(String moduleClassification,Integer moduleInfoId,Integer templateId) {
	  projectModuleInfoService.saveModule(moduleClassification,moduleInfoId,templateId);
		return Message.success();
	}

	/**
	 * 更新项目模块信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(ProjectModuleInfo projectModuleInfo) {
		projectModuleInfoService.update(projectModuleInfo,"projectTemplates","moduleType");
		return Message.success();
	}

	/**
	 * 删除项目模块信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 projectModuleInfoService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
