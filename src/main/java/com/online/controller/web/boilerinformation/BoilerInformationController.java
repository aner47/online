package com.online.controller.web.boilerinformation;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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
import com.online.entity.online.BoilerInformation;
import com.online.entity.online.Project;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.BoilerInformationService;
import com.online.service.ExhaustionHoleService;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.service.GovernanceMeasuresService;
import com.online.service.MonthlyInformationService;
import com.online.util.Constants;
import com.online.util.SpringUtils;



@Controller("webBoilerInformationController")
@RequestMapping("/web/boilerinformation")
public class BoilerInformationController extends BaseController{
	
	@Autowired
	private BoilerInformationService boilerInformationService;
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@Autowired
	private GovernanceMeasuresService governanceMeasuresService;
	@Autowired
	private ExhaustionHoleService exhaustionHoleService;
	
	@Autowired 
	protected MonthlyInformationService monthlyInformationService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		Project  project = SpringUtils.getCurrentProject();
		model.put("project", project);
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_boilerinformation);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/boilerinformation/list";
	}
	
	@RequestMapping("/simplelist")
	public String simplelist(ModelMap model){
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_simple_boilerinformation);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/boilerinformation/simple-list";
	}
	
	@RequestMapping("/generallist")
	public String generallist(ModelMap model){
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_general_boilerinformation);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/boilerinformation/general-list";
	}
	
	
	
	
	/**
	 * 增加锅炉信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		Project  project = SpringUtils.getCurrentProject();
		model.put("project", project);
		
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_boilerinformation);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		
		return "/admin/boilerinformation/add";
	}
	
	/**
	 * 增加锅炉信息页面
	 */
	@RequestMapping(value = "addsimplePage")
	public String addsimplePage(ModelMap model) {
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_simple_boilerinformation);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		
		return "/admin/boilerinformation/simple-add";
	}
	/**
	 * 增加锅炉信息页面
	 */
	@RequestMapping(value = "addgeneralPage")
	public String addgeneralPage(ModelMap model) {
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_general_boilerinformation);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/boilerinformation/general-add";
	}
	
	

	/**
	 * 修改锅炉信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		BoilerInformation boilerInformation= boilerInformationService.find(id);
		model.put("boilerInformation", boilerInformation);
		Project  project = SpringUtils.getCurrentProject();
		model.put("project", project);
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_boilerinformation);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/boilerinformation/update";
	}
	
	/**
	 * 修改锅炉信息页面
	 */
	@RequestMapping(value = "updatesimplePage")
	public String updatesimplePage(ModelMap model,Integer id) {
		model.put("boilerInformation", boilerInformationService.find(id));
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_simple_boilerinformation);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/boilerinformation/simple-update";
	}
	/**
	 * 修改锅炉信息页面
	 */
	@RequestMapping(value = "updategeneralPage")
	public String updategeneralPage(ModelMap model,Integer id) {
		model.put("boilerInformation", boilerInformationService.find(id));
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_general_boilerinformation);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/boilerinformation/general-update";
	}
	
	
	
	/**
	 * 查看锅炉信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("boilerInformation", boilerInformationService.find(id));
		return "/admin/boilerinformation/view";
	}
	
	/**
	 * 查询锅炉信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<BoilerInformation> query(Pageable pageable,BoilerInformation boilerInformation) {
		pageable.addFilter("emissionsManagement.enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		return boilerInformationService.findPage(pageable);
		
	}
	

	/**
	 * 保存详表锅炉信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(HttpServletRequest request,BoilerInformation boilerInformation, Integer governanceMeasures1
			, Integer governanceMeasures2, Integer governanceMeasures3, Integer governanceMeasures4
			, Integer exhaustionHoleId,String allData) {
		
		boilerInformationService.saveDetailBoilerInformation(boilerInformation,governanceMeasures1
				, governanceMeasures2, governanceMeasures3, governanceMeasures4, exhaustionHoleId, allData);
		
		return Message.success();
	}
	
	/**
	 * 更新详表锅炉信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(BoilerInformation boilerInformation,Integer governanceMeasures1
			, Integer governanceMeasures2, Integer governanceMeasures3, Integer governanceMeasures4
			, Integer exhaustionHoleId) {
		boilerInformationService.updateDetailBoilerInformation(
				boilerInformation,governanceMeasures1
				,governanceMeasures2,governanceMeasures3,governanceMeasures4,exhaustionHoleId);
		return Message.success();
	}
	
	
	
	
	

	/**
	 * 保存简表锅炉信息
	 */
	@RequestMapping(value = "/simplesave")
	@ResponseBody
	public Message simplesave(BoilerInformation boilerInformation
			,String governanceMeasures1, String governanceMeasures2
			, String governanceMeasures3) {
		boilerInformationService.simplesave(boilerInformation,governanceMeasures1
				,governanceMeasures2,governanceMeasures3);
		return Message.success();
	}
	
	/**
	 * 更新简表锅炉信息
	 */
	@RequestMapping(value = "/simpleupdate", method = RequestMethod.POST)
	@ResponseBody
	public Message simpleupdate(BoilerInformation boilerInformation,String governanceMeasures1
			,String governanceMeasures2,String governanceMeasures3) {
		boilerInformationService.simpleupdate( boilerInformation, governanceMeasures1
				, governanceMeasures2, governanceMeasures3);
		return Message.success();
	}
	
	
	
	/**
	 * 保存普查表锅炉信息
	 */
	@RequestMapping(value = "/generalsave")
	@ResponseBody
	public Message generalsave(BoilerInformation boilerInformation
			, String governanceMeasures1
			, String governanceMeasures2
			,String governanceMeasures3
			,Date putDate1,Date putDate2,Date putDate3) {
		
		boilerInformationService.generalsave( boilerInformation
				,  governanceMeasures1
				,  governanceMeasures2
				, governanceMeasures3
				, putDate1, putDate2, putDate3);
		return Message.success();
	}
	
	
	
	

	
	
	
	
	
	/**
	 * 更新普查表锅炉信息
	 */
	@RequestMapping(value = "/generalupdate", method = RequestMethod.POST)
	@ResponseBody
	public Message generalupdate(BoilerInformation boilerInformation,String governanceMeasures1,String governanceMeasures2,
			String governanceMeasures3,Date putDate1,Date putDate2,Date putDate3) {
		
		return Message.success();
	}

	/**
	 * 删除锅炉信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 boilerInformationService.delete(ids[i]);
			 monthlyInformationService.deleteByType(Constants.MONTH_TYPE_BOILER,ids[i]);
		}
		return Message.success();
	}
	/**
	 * 删除锅炉信息
	 */
	@RequestMapping(value = "/simpledelete", method = RequestMethod.POST)
	public @ResponseBody Message simpledelete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			
			BoilerInformation boilerInformation = boilerInformationService.find(ids[i]);
			
			boilerInformationService.delete(ids[i]);
			
			if(boilerInformation != null && boilerInformation.getEmissionsManagement() != null){
				governanceMeasuresService.delete(boilerInformation.getEmissionsManagement().getGovernanceMeasures1().getId());
				governanceMeasuresService.delete(boilerInformation.getEmissionsManagement().getGovernanceMeasures2().getId());
				governanceMeasuresService.delete(boilerInformation.getEmissionsManagement().getGovernanceMeasures3().getId());
				exhaustionHoleService.delete(boilerInformation.getEmissionsManagement().getExhaustionHole().getId());
			}
			
			monthlyInformationService.deleteByType(Constants.MONTH_TYPE_BOILER,ids[i]);
		}
		return Message.success();
	}
	/**
	 * 删除锅炉信息
	 */
	@RequestMapping(value = "/generaldelete", method = RequestMethod.POST)
	public @ResponseBody Message generaldelete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			
			BoilerInformation boilerInformation = boilerInformationService.find(ids[i]);
			
			boilerInformationService.delete(ids[i]);
			
			if(boilerInformation != null && boilerInformation.getEmissionsManagement() != null){
				governanceMeasuresService.delete(boilerInformation.getEmissionsManagement().getGovernanceMeasures1().getId());
				governanceMeasuresService.delete(boilerInformation.getEmissionsManagement().getGovernanceMeasures2().getId());
				governanceMeasuresService.delete(boilerInformation.getEmissionsManagement().getGovernanceMeasures3().getId());
				exhaustionHoleService.delete(boilerInformation.getEmissionsManagement().getExhaustionHole().getId());
			}
			
			monthlyInformationService.deleteByType(Constants.MONTH_TYPE_BOILER,ids[i]);
			
		}
		return Message.success();
	}
		
}
