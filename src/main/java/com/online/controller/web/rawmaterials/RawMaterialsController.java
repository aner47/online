package com.online.controller.web.rawmaterials;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.online.Message;
import com.online.Page;
import com.online.Pageable;
import com.online.Filter;
import com.online.Filter.Operator;
import com.online.controller.Token;
import com.online.controller.base.BaseController;
import com.online.entity.online.Project;
import com.online.entity.online.RawMaterials;
import com.online.entity.online.RawMaterials.RawMaterialsType;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.service.MonthlyInformationService;
import com.online.service.RawMaterialsService;
import com.online.util.Constants;
import com.online.util.SpringUtils;



@Controller("webRawMaterialsController")
@RequestMapping("/web/rawmaterials")
public class RawMaterialsController extends BaseController{
	
	@Autowired
	private RawMaterialsService  rawMaterialsService ;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@Autowired 
	protected MonthlyInformationService monthlyInformationService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_rawmaterials);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/rawmaterials/list";
	}
	
	
	@RequestMapping("/simplelist")
	public String simplelist(ModelMap model){
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
	public String addPage(ModelMap model) {
		Project  project = SpringUtils.getCurrentProject();
		model.put("project", project);
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
	public Message save(RawMaterials  rawMaterials,Integer sectionId,String productName,
			RawMaterialsType rawMaterialsType2,String name2
			,Double consumption2 ,String unit2,String solventCategory2,String solventType2,
			Double vocRate2,Double vocVolatility2,
			RawMaterialsType rawMaterialsType3,String name3
			,Double consumption3 ,String unit3,String solventCategory3,String solventType3,
			Double vocRate3,Double vocVolatility3) {
		
		rawMaterialsService.saveEnter(rawMaterials);
		
		if(StringUtils.isNotBlank(name2)){
			RawMaterials  rawMaterials2 = new RawMaterials();
			rawMaterials2.setYear(rawMaterials.getYear());
			rawMaterials2.setSectionId(sectionId);
			rawMaterials2.setProductName(productName);
			rawMaterials2.setRawMaterialsType(rawMaterialsType2);
			rawMaterials2.setName(name2);
			rawMaterials2.setConsumption(consumption2);
			rawMaterials2.setUnit(unit2);
			rawMaterials2.setSolventCategory(solventCategory2);
			rawMaterials2.setSolventType(solventType2);
			rawMaterials2.setVocRate(vocRate2);
			rawMaterials2.setVocVolatility(vocVolatility2);
			rawMaterialsService.saveEnter(rawMaterials2);
			
		}
		if(StringUtils.isNotBlank(name3)){
			RawMaterials  rawMaterials3 = new RawMaterials();
			rawMaterials3.setYear(rawMaterials.getYear());
			rawMaterials3.setSectionId(sectionId);
			rawMaterials3.setProductName(productName);
			rawMaterials3.setRawMaterialsType(rawMaterialsType3);
			rawMaterials3.setName(name3);
			rawMaterials3.setConsumption(consumption3);
			rawMaterials3.setUnit(unit3);
			rawMaterials3.setSolventCategory(solventCategory3);
			rawMaterials3.setSolventType(solventType3);
			rawMaterials3.setVocRate(vocRate3);
			rawMaterials3.setVocVolatility(vocVolatility3);
			rawMaterialsService.saveEnter(rawMaterials3);
			
		}
		 
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
			 monthlyInformationService.deleteByType(Constants.MONTH_TYPE_RAWMATERIALS,ids[i]);
		}
		return Message.success();
	}
		
}
