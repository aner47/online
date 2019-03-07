package com.online.controller.web.constructionsitebase;


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
import com.online.entity.online.constructionsite.ConstructionSiteBase;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.ConstructionSiteBaseService;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.util.Constants;
import com.online.util.SpringUtils;



@Controller("constructionSiteBaseController")
@RequestMapping("/web/constructionsitebase")
public class ConstructionSiteBaseController extends BaseController{
	
	@Autowired
	private ConstructionSiteBaseService constructionSiteBaseService;
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		ConstructionSiteBase constructionSiteBaseFind = constructionSiteBaseService.findListByProjectAndEnterprise();
		if (constructionSiteBaseFind == null) {
			ConstructionSiteBase constructionSiteBase = new ConstructionSiteBase();
			constructionSiteBase.setProject(SpringUtils.getCurrentProject());
			constructionSiteBase.setEnterprise(SpringUtils.getCurrentEnterprise());
			constructionSiteBaseService.save(constructionSiteBase);
			model.put("constructionSiteBase", constructionSiteBase);
		} else {
			model.put("constructionSiteBase", constructionSiteBaseFind);
		}
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_constructionsite_base);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		model.put("enterprise", SpringUtils.getCurrentEnterprise());
		return "/admin/constructionsitebase/list";
	}
	
	/**
	 * 增加施工工地基本信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_constructionsite_base);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/constructionsitebase/add";
	}

	
	
	/**
	 * 修改施工工地基本信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("singleboilerfuel", constructionSiteBaseService.find(id));
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_constructionsite_base);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/constructionsitebase/update";
	}
	
	/**
	 * 查看施工工地基本信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("singleboilerfuel", constructionSiteBaseService.find(id));
		return "/admin/constructionsitebase/view";
	}
	
	/**
	 * 查询施工工地基本信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<ConstructionSiteBase> query(Pageable pageable,ConstructionSiteBase constructionSiteBase) {
		pageable.addFilter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		return constructionSiteBaseService.findPage(pageable);
		
	}
	

	/**
	 * 保存施工工地基本信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(ConstructionSiteBase constructionSiteBase) {
		constructionSiteBase.setProject(SpringUtils.getCurrentProject());
		constructionSiteBase.setEnterprise(SpringUtils.getCurrentEnterprise());
		if(constructionSiteBase.getId() !=null){
			constructionSiteBaseService.update(constructionSiteBase);
		}
		
		return Message.success();
	}
	
	

	
	/**
	 * 更新施工工地基本信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(ConstructionSiteBase constructionSiteBase) {
		constructionSiteBaseService.update(constructionSiteBase);
		return Message.success();
	}
	
	
	

	/**
	 * 删除施工工地基本信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			constructionSiteBaseService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
