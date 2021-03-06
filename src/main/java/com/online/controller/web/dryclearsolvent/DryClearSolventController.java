package com.online.controller.web.dryclearsolvent;


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
import com.online.entity.online.dryclear.DryClearSolvent;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.DryClearSolventService;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.util.Constants;
import com.online.util.SpringUtils;



@Controller("dryClearSolventController")
@RequestMapping("/web/dryclearsolvent")
public class DryClearSolventController extends BaseController{
	
	@Autowired
	private DryClearSolventService dryClearSolventService;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_dryclearsolvent);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/dryclearsolvent/list";
	}
	
	/**
	 * 增加年有机溶剂使用量
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_dryclearsolvent);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/dryclearsolvent/add";
	}

	
	
	/**
	 * 修改年有机溶剂使用量
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("dryClearSolvent", dryClearSolventService.find(id));
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_dryclearsolvent);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/dryclearsolvent/update";
	}
	
	/**
	 * 查看年有机溶剂使用量
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("dryClearSolvent", dryClearSolventService.find(id));
		return "/admin/dryclearsolvent/view";
	}
	
	/**
	 * 查询年有机溶剂使用量
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<DryClearSolvent> query(Pageable pageable,DryClearSolvent dryClearSolvent) {
		pageable.addFilter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		return dryClearSolventService.findPage(pageable);
		
	}
	

	/**
	 * 保存年有机溶剂使用量
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(DryClearSolvent dryClearSolvent) {
		dryClearSolvent.setProject(SpringUtils.getCurrentProject());
		dryClearSolventService.save(dryClearSolvent);
		return Message.success();
	}
	
	

	
	/**
	 * 更新年有机溶剂使用量
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(DryClearSolvent dryClearSolvent) {
		dryClearSolventService.update(dryClearSolvent);
		return Message.success();
	}
	
	
	

	/**
	 * 删除年有机溶剂使用量
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			dryClearSolventService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
