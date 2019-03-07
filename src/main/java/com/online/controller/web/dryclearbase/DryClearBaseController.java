package com.online.controller.web.dryclearbase;


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
import com.online.entity.online.dryclear.DryClearBase;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.DryClearBaseService;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.util.Constants;
import com.online.util.SpringUtils;



@Controller("dryClearBaseController")
@RequestMapping("/web/dryclearbase")
public class DryClearBaseController extends BaseController{
	
	@Autowired
	private DryClearBaseService dryClearBaseService;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		DryClearBase dryClearBaseFind = dryClearBaseService.findListByProjectAndEnterprise();
		if (dryClearBaseFind == null) {
			DryClearBase dryClearBase = new DryClearBase();
			dryClearBase.setProject(SpringUtils.getCurrentProject());
			dryClearBase.setEnterprise(SpringUtils.getCurrentEnterprise());
			dryClearBaseService.save(dryClearBase);
			model.put("dryClearBase", dryClearBase);
		} else {
			model.put("dryClearBase", dryClearBaseFind);
		}
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_dryclearbase);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		model.put("enterprise", SpringUtils.getCurrentEnterprise());
		return "/admin/dryclearbase/list";
	}
	
	/**
	 * 增加干洗调查表基本信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_dryclearbase);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/dryclearbase/add";
	}

	
	
	/**
	 * 修改干洗调查表基本信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("dryClearBase", dryClearBaseService.find(id));
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_dryclearbase);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/dryclearbase/update";
	}
	
	/**
	 * 查看干洗调查表基本信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("dryClearBase", dryClearBaseService.find(id));
		return "/admin/dryclearbase/view";
	}
	
	/**
	 * 查询干洗调查表基本信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<DryClearBase> query(Pageable pageable,DryClearBase dryClearBase) {
		pageable.addFilter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		return dryClearBaseService.findPage(pageable);
		
	}
	

	/**
	 * 保存干洗调查表基本信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(DryClearBase dryClearBase) {
		dryClearBase.setProject(SpringUtils.getCurrentProject());
		dryClearBase.setEnterprise(SpringUtils.getCurrentEnterprise());
		if(dryClearBase.getId() !=null){
			dryClearBaseService.update(dryClearBase);
		}
		
		return Message.success();
	}
	
	

	
	/**
	 * 更新干洗调查表基本信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(DryClearBase dryClearBase) {
		dryClearBaseService.update(dryClearBase);
		return Message.success();
	}
	
	
	

	/**
	 * 删除干洗调查表基本信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			dryClearBaseService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
