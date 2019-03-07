package com.online.controller.admin.projecttemplates;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.Filter;
import com.online.Message;
import com.online.Page;
import com.online.Pageable;
import com.online.Filter.Operator;
import com.online.controller.base.BaseController;
import com.online.entity.online.ProjectModuleInfo;
import com.online.entity.online.ProjectTemplates;
import com.online.entity.online.Enterprise.EnterpriseType;
import com.online.service.ProjectModuleDetailService;
import com.online.service.ProjectModuleInfoService;
import com.online.service.ProjectTemplatesService;



@Controller
@RequestMapping("/admin/projecttemplates")
public class ProjectTemplatesController extends BaseController{
	
	@Autowired
	private ProjectTemplatesService  projectTemplatesService ;
	@Autowired
	private ProjectModuleInfoService projectModuleInfoService;
	@Autowired
	private ProjectModuleDetailService projectModuleDetailService;
	@RequestMapping("/list")
	public String list(){
		return "/admin/projecttemplates/list";
	}
	
	@RequestMapping("/pdflist")
	public String pdflist(){
		return "/admin/projecttemplates/pdflist";
	}
	@RequestMapping("/auditlist")
	public String auditlist(){
		return "/admin/projecttemplates/auditlist";
	}
	
	/**
	 * 增加项目模板页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/projecttemplates/add";
	}

	/**
	 * 修改项目模板页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("projectTemplates", projectTemplatesService.find(id));
		return "/admin/projecttemplates/update";
	}
	
	/**
	 * 查看项目模板页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("projectTemplates", projectTemplatesService.find(id));
		return "/admin/projecttemplates/view";
	}
	
	/**
	 * 查询项目模板
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<ProjectTemplates> query(Pageable pageable,ProjectTemplates projectTemplates,String templatesType) {
		String name = projectTemplates.getName();
		String templateType = projectTemplates.getTemplateType();
		Integer projectId = projectTemplates.getProjectId();
		if (StringUtils.isNotEmpty(name)) {
            pageable.addFilter("name", Operator.like,"%"+name.trim()+"%");
        }
		if (templateType!=null) {
            pageable.addFilter("templateType", Operator.eq, templateType);
        }
		if (projectId!=null) {
            pageable.addFilter("projectId", Operator.eq, projectId);
        }
		if (templatesType!=null) {
			pageable.addFilter("templatesType", Operator.eq, templatesType);
		}
		return projectTemplatesService.findPage(pageable);
		
	}
	

	/**
	 * 保存项目模板
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(ProjectTemplates  projectTemplates,String model,Integer nameId) {
		//套用其他项目模板
		if("是".equals(model)){
			projectTemplatesService.saveApply(projectTemplates,nameId);
		}else{
			projectTemplatesService.save(projectTemplates);
		}
		 
		return Message.success();
	}

	/**
	 * 更新项目模板
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(ProjectTemplates projectTemplates) {
		projectTemplatesService.update(projectTemplates);
		int projectId = projectTemplates.getProjectId();
		int entityId = projectTemplates.getId();
		
		List<ProjectModuleInfo> lists = projectModuleInfoService.findByFilter(new Filter("projectTemplates.id", Operator.eq, entityId));
		for(ProjectModuleInfo projectModuleInfo:lists){
			projectModuleInfo.setProjectId(projectId);
			projectModuleInfoService.update(projectModuleInfo);
		}
		return Message.success();
	}

	/**
	 * 删除项目模板
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
        List<Filter> filters = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            filters.add(new Filter("projectTemplates.id", Operator.eq, ids[i]));
            List<ProjectModuleInfo> info = projectModuleInfoService.findList(null, filters, null);
            if (info != null) {
                for (ProjectModuleInfo projectModuleInfo : info) {
                    projectModuleDetailService.deleteModeleDetailByInfo(projectModuleInfo.getId());
                }
            }
            projectTemplatesService.delete(ids[i]);
        }
        return Message.success();
	}
		
}
