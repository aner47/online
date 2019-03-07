package com.online.controller.admin.rawmaterials;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.online.Filter;
import com.online.Message;
import com.online.Page;
import com.online.Pageable;
import com.online.Filter.Operator;
import com.online.controller.Token;
import com.online.controller.base.BaseController;
import com.online.entity.online.RawMaterials;
import com.online.entity.online.RawMaterials.RawMaterialsType;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.service.RawMaterialsService;
import com.online.util.Constants;
import com.online.util.Principal;
import com.online.util.SpringUtils;



@Controller("rawMaterialsController")
@RequestMapping("/admin/rawmaterials")
public class RawMaterialsController extends BaseController{
	
	@Autowired
	private RawMaterialsService  rawMaterialsService ;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		return "/admin/rawmaterials/list";
	}
	
	@RequestMapping("/simplelist")
	public String simplelist(ModelMap model){
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_simple_rawmaterials);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/rawmaterials/simple-list";
	}
	
	@RequestMapping("/generallist")
	public String generallist(ModelMap model){
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_general_rawmaterials);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/rawmaterials/generallist";
	}
	
	
	/**
	 * 增加原辅料页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/rawmaterials/add";
	}
	
	
	/**
	 * 增加原辅料页面
	 */
	
	@Token(save=true)
	@RequestMapping(value = "addsimplePage")
	public String addsimplePage(ModelMap model) {
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_simple_rawmaterials);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/rawmaterials/simple-add";
	}
	/**
	 * 增加原辅料页面
	 */
	@RequestMapping(value = "addgeneralPage")
	public String addgeneralPage(ModelMap model) {
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_general_rawmaterials);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/rawmaterials/generaladd";
	}

	/**
	 * 修改原辅料页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("rawMaterials", rawMaterialsService.find(id));
		return "/admin/rawmaterials/update";
	}
	
	
	/**
	 * 修改原辅料页面
	 */
	@RequestMapping(value = "updatesimplePage")
	public String updatesimplePage(ModelMap model,Integer id) {
		model.put("rawMaterials", rawMaterialsService.find(id));
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_simple_rawmaterials);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/rawmaterials/simple-update";
	}
	/**
	 * 修改原辅料页面
	 */
	@RequestMapping(value = "updategeneralPage")
	public String updategeneralPage(ModelMap model,Integer id) {
		model.put("rawMaterials", rawMaterialsService.find(id));
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_general_rawmaterials);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/rawmaterials/generalupdate";
	}
	
	/**
	 * 查看原辅料页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("rawMaterials", rawMaterialsService.find(id));
		return "/admin/rawmaterials/view";
	}
	
	/**
	 * 查询原辅料
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<RawMaterials> query(Pageable pageable,RawMaterials rawMaterials) {
		pageable.addFilter("enterprise", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		pageable.addFilter("project", Operator.eq, SpringUtils.getProjectId());
		pageable.addFilter(Filter.isNotNull("sectionId"));
		return rawMaterialsService.findPage(pageable);
		
	}
	/**
	 * 查询原辅料
	 */
	@RequestMapping(value = "/querysimple")
	@ResponseBody
	public Page<RawMaterials> querysimple(Pageable pageable,RawMaterials rawMaterials) {
		pageable.addFilter("enterprise", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		pageable.addFilter("project", Operator.eq, SpringUtils.getProjectId());
		pageable.addFilter("rawMaterialsType", Operator.eq, RawMaterialsType.organic);
		return rawMaterialsService.findPage(pageable);
		
	}

	/**
	 * 保存原辅料
	 */
	@Token(remove = true)
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(RawMaterials  rawMaterials) {
		 rawMaterialsService.saveEnter(rawMaterials);
		return Message.success();
	}
	
	/**
	 * 保存原辅料
	 */
	@RequestMapping(value = "/savesimple")
	@ResponseBody
	public Message savesimple(RawMaterials  rawMaterials) {
		rawMaterials.setRawMaterialsType(RawMaterialsType.organic);
		rawMaterialsService.saveEnter(rawMaterials);
		return Message.success();
	}

	/**
	 * 更新原辅料
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(RawMaterials rawMaterials) {
		rawMaterialsService.updateEnter(rawMaterials);
		return Message.success();
	}
	
	
	/**
	 * 更新原辅料
	 */
	@RequestMapping(value = "/simpleupdate", method = RequestMethod.POST)
	@ResponseBody
	public Message simpleupdate(RawMaterials rawMaterials) {
		rawMaterialsService.updateSimpleEnter(rawMaterials);
		return Message.success();
	}

	/**
	 * 删除原辅料
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			
			 rawMaterialsService.deleteEnter(ids[i]);
		}
		return Message.success();
	}
		
}
