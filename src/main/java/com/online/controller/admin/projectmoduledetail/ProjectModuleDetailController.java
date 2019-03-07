package com.online.controller.admin.projectmoduledetail;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.Message;
import com.online.Page;
import com.online.Pageable;
import com.online.controller.base.BaseController;
import com.online.entity.online.ModuleDetail;
import com.online.entity.online.ProjectModuleDetail;
import com.online.entity.online.ProjectModuleInfo;
import com.online.service.ModuleDetailService;
import com.online.service.ProjectModuleDetailService;
import com.online.service.ProjectModuleInfoService;



@Controller
@RequestMapping("/admin/projectmoduledetail")
public class ProjectModuleDetailController extends BaseController{
	
	@Autowired
	private ProjectModuleDetailService  projectModuleDetailService ;
	@Autowired
	private ProjectModuleInfoService projectModuleInfoService;
	@Autowired
	private ModuleDetailService moduleDetailService;
	@RequestMapping("/list")
	public String list(ModelMap map ,Integer projectModuleInfoId){
		System.out.println("projectMoudleInfoId：：：：：：：：：："+projectModuleInfoId);
	    map.put("projectModuleInfoId", projectModuleInfoId);
		return "/admin/projectmoduledetail/list";
	}
	
	/**
	 * 增加项目模块明细页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap map ,Integer projectModuleInfoId) {
		map.put("projectModuleInfo", projectModuleInfoService.find(projectModuleInfoId));
		return "/admin/projectmoduledetail/add";
	}

	/**
	 * 修改项目模块明细页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("projectModuleDetail", projectModuleDetailService.find(id));
		return "/admin/projectmoduledetail/update";
	}
	
	/**
	 * 查看项目模块明细页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("projectModuleDetail", projectModuleDetailService.find(id));
		return "/admin/projectmoduledetail/view";
	}
	
	/**
	 * 查询项目模块明细
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<ProjectModuleDetail> query(Pageable pageable,Integer projectModuleInfoId,String key,String head) {
	    pageable.addFilter("projectModuleInfo.id", Operator.eq, projectModuleInfoId);
	    if(StringUtils.isNotBlank(key)){
	    	pageable.addFilter(new Filter("key", Operator.like, "%"+key+"%"));
	    }
	    if(StringUtils.isNotBlank(head)){
	    	pageable.addFilter(new Filter("head", Operator.like, "%"+head+"%"));
	    }
		return projectModuleDetailService.findPage(pageable);
		
	}
	

	/**
	 * 保存项目模块明细
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(Integer moduleDetailId,Integer projectModuleInfoId) {
		ProjectModuleInfo projectModuleInfo = projectModuleInfoService.find(projectModuleInfoId);
		ModuleDetail moduleDetail = moduleDetailService.find(moduleDetailId);
		ProjectModuleDetail projectModuleDetail = new ProjectModuleDetail();
		projectModuleDetail.setProjectModuleInfo(projectModuleInfo);
		projectModuleDetail.setProjectId(projectModuleInfo.getProjectId());
		if(moduleDetail != null){
			projectModuleDetail.setHead(moduleDetail.getHead());
			projectModuleDetail.setKey(moduleDetail.getKey());
			projectModuleDetail.setModuleOrder(moduleDetail.getModuleOrder());
		}
		
		projectModuleDetailService.save(projectModuleDetail);
		return Message.success();
	}

	/**
	 * 更新项目模块明细
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(ProjectModuleDetail projectModuleDetail) {
		projectModuleDetailService.update(projectModuleDetail,"projectModuleInfo");
		return Message.success();
	}

	/**
	 * 删除项目模块明细
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 projectModuleDetailService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
