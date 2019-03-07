package com.online.controller.web.constructionperiod;


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
import com.online.entity.online.constructionsite.ConstructionPeriod;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.ConstructionPeriodService;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.util.Constants;
import com.online.util.SpringUtils;



@Controller("constructionPeriodController")
@RequestMapping("/web/constructionperiod")
public class ConstructionPeriodController extends BaseController{
	
	@Autowired
	private ConstructionPeriodService constructionPeriodService;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_constructionperiod);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/constructionperiod/list";
	}
	
	/**
	 * 增加施工周期信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_constructionperiod);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		
		return "/admin/constructionperiod/add";
	}

	
	
	/**
	 * 修改施工周期信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("constructionperiod", constructionPeriodService.find(id));
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_constructionperiod);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/constructionperiod/update";
	}
	
	/**
	 * 查看施工周期信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("constructionperiod", constructionPeriodService.find(id));
		return "/admin/constructionperiod/view";
	}
	
	/**
	 * 查询施工周期信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<ConstructionPeriod> query(Pageable pageable,ConstructionPeriod constructionPeriod) {
		pageable.addFilter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		return constructionPeriodService.findPage(pageable);
		
	}
	

	/**
	 * 保存施工周期信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(ConstructionPeriod constructionPeriod) {
		constructionPeriod.setProject(SpringUtils.getCurrentProject());
		constructionPeriodService.save(constructionPeriod);
		return Message.success();
	}
	
	

	
	/**
	 * 更新施工周期信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(ConstructionPeriod constructionPeriod) {
		constructionPeriodService.update(constructionPeriod);
		return Message.success();
	}
	
	
	

	/**
	 * 删除施工周期信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			constructionPeriodService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
