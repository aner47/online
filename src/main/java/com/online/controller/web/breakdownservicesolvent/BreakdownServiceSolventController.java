package com.online.controller.web.breakdownservicesolvent;


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
import com.online.entity.online.breakdownservice.BreakdownServiceSolvent;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.BreakdownServiceSolventService;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.util.Constants;
import com.online.util.SpringUtils;



@Controller("breakdownServiceSolventController")
@RequestMapping("/web/breakdownservicesolvent")
public class BreakdownServiceSolventController extends BaseController{
	
	@Autowired
	private BreakdownServiceSolventService breakdownServiceSolventService;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_breakdownservicesolvent);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/breakdownservicesolvent/list";
	}
	
	/**
	 * 增加有机溶剂使用情况页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_breakdownservicesolvent);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/breakdownservicesolvent/add";
	}

	
	
	/**
	 * 修改有机溶剂使用情况页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("breakdownServiceSolvent", breakdownServiceSolventService.find(id));
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_breakdownservicesolvent);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/breakdownservicesolvent/update";
	}
	
	/**
	 * 查看有机溶剂使用情况页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("breakdownServiceSolvent", breakdownServiceSolventService.find(id));
		return "/admin/breakdownservicesolvent/view";
	}
	
	/**
	 * 查询有机溶剂使用情况
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<BreakdownServiceSolvent> query(Pageable pageable,BreakdownServiceSolvent breakdownServiceSolvent) {
		pageable.addFilter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		return breakdownServiceSolventService.findPage(pageable);
		
	}
	

	/**
	 * 保存有机溶剂使用情况
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(BreakdownServiceSolvent breakdownServiceSolvent) {
		breakdownServiceSolvent.setProject(SpringUtils.getCurrentProject());
		breakdownServiceSolventService.save(breakdownServiceSolvent);
		return Message.success();
	}
	
	

	
	/**
	 * 更新有机溶剂使用情况
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(BreakdownServiceSolvent breakdownServiceSolvent) {
		breakdownServiceSolventService.update(breakdownServiceSolvent);
		return Message.success();
	}
	
	
	

	/**
	 * 删除有机溶剂使用情况
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			breakdownServiceSolventService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
