package com.online.controller.web.nonroad;


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
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.entity.online.general.NonRoad;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.service.NonRoadService;
import com.online.util.Constants;
import com.online.util.SpringUtils;



@Controller("nonRoadController")
@RequestMapping("/web/nonroad")
public class NonRoadController extends BaseController{
	
	@Autowired
	private NonRoadService nonRoadService;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_general_nonroad);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/nonroad/list";
	}
	
	@RequestMapping("/list_detail")
	public String listDetail(ModelMap model){
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_nonroad);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/nonroad/list_detail";
	}
	
	/**
	 * 增加普查表非道路机械信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_general_nonroad);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/nonroad/add";
	}
	/**
	 * 增加普查表非道路机械信息页面
	 */
	@RequestMapping(value = "addPageDetail")
	public String addPageDetail(ModelMap model) {
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_nonroad);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/nonroad/add_detail";
	}

	
	
	/**
	 * 修改普查表非道路机械信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("nonRoad", nonRoadService.find(id));
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_general_nonroad);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/nonroad/update";
	}
	/**
	 * 修改普查表非道路机械信息页面
	 */
	@RequestMapping(value = "updatePageDetail")
	public String updatePageDetail(ModelMap model,Integer id) {
		model.put("nonRoad", nonRoadService.find(id));
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_nonroad);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/nonroad/update_detail";
	}
	
	/**
	 * 查看普查表非道路机械信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("nonRoad", nonRoadService.find(id));
		return "/admin/nonroad/view";
	}
	
	/**
	 * 查询普查表非道路机械信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<NonRoad> query(Pageable pageable,NonRoad nonRoad) {
		pageable.addFilter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		return nonRoadService.findPage(pageable);
		
	}
	

	/**
	 * 保存普查表非道路机械信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(NonRoad nonRoad) {
		nonRoad.setProject(SpringUtils.getCurrentProject());
		nonRoadService.save(nonRoad);
		return Message.success();
	}
	
	

	
	/**
	 * 更新普查表非道路机械信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(NonRoad nonRoad) {
		nonRoadService.update(nonRoad);
		return Message.success();
	}
	
	
	

	/**
	 * 删除普查表非道路机械信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			nonRoadService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
