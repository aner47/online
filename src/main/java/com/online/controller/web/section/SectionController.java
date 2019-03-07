package com.online.controller.web.section;


import java.util.Date;

import org.apache.commons.lang.StringUtils;
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
import com.online.entity.online.Enterprise;
import com.online.entity.online.Project;
import com.online.entity.online.Section;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.pscctask.TaskPsccService;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.service.MonthlyInformationService;
import com.online.service.SectionService;
import com.online.util.Constants;
import com.online.util.SpringUtils;



@Controller("webSectionController")
@RequestMapping("/web/section")
public class SectionController extends BaseController{
	
	@Autowired
	private SectionService sectionService;
	
	
	@Autowired
	protected TaskPsccService taskPsccService;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@Autowired 
	protected MonthlyInformationService monthlyInformationService;
	
	@RequestMapping("/generallist")
	public String generallist(ModelMap model){
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_general_section);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/section/generallist";
	}
	
	@RequestMapping("/simplelist")
	public String simplelist(ModelMap model){
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_simple_section);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/section/simplelist";
	}
	@RequestMapping("/list")
	public String list(ModelMap model){
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_section);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/section/list";
	}
	
	/**
	 * 增加工段页面
	 */
	@RequestMapping(value = "generalAddPage")
	public String generalAddPage(ModelMap model) {
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_general_section);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/section/generaladd";
	}
	/**
	 * 增加工段页面
	 */
	@RequestMapping(value = "simpleAddPage")
	public String simpleAddPage(ModelMap model) {
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_simple_section);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/section/simpleadd";
	}
	/**
	 * 增加工段页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		Project  project = SpringUtils.getCurrentProject();
		model.put("project", project);
		Enterprise  enterprise = SpringUtils.getCurrentEnterprise();
		model.put("enterprise", enterprise);
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_section);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		//如果项目的产品行业不为空,跳转到分行业表
		if(StringUtils.isNotBlank(project.getSysProductIndustrys())){
			//如果企业配置产品行业为其他
			if(Constants.ENTERPRISE_PRODUCT_INDUSTRY_OTHER.equals(enterprise.getProductIndustry()) ){
				return "/admin/section/add";
			}else{
				return "/admin/section/add_industrys";
			}
			
		}else{
			return "/admin/section/add";
		}
		
		
	}

	/**
	 * 修改工段页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("section", sectionService.find(id));
		Project  project = SpringUtils.getCurrentProject();
		model.put("project", project);
		Enterprise  enterprise = SpringUtils.getCurrentEnterprise();
		model.put("enterprise", enterprise);
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_section);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		//如果项目的产品行业不为空,跳转到分行业表
		if(StringUtils.isNotBlank(project.getSysProductIndustrys())){
			//如果企业配置产品行业为其他
			if(Constants.ENTERPRISE_PRODUCT_INDUSTRY_OTHER.equals(enterprise.getProductIndustry()) ){
				return "/admin/section/update";
			}else{
				return "/admin/section/update_industrys";
			}
			
		}else{
			return "/admin/section/update";
		}
		
	}
	/**
	 * 修改工段页面
	 */
	@RequestMapping(value = "/simpleUpdatePage")
	public String simpleUpdatePage(ModelMap model,Integer id) {
		model.put("section", sectionService.find(id));
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_simple_section);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/section/simpleupdate";
	}
	/**
	 * 修改工段页面
	 */
	@RequestMapping(value = "/generalUpdatePage")
	public String generalUpdatePage(ModelMap model,Integer id) {
		model.put("section", sectionService.find(id));
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_general_section);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/section/generalupdate";
	}
	
	/**
	 * 查看工段页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("section", sectionService.find(id));
		return "/admin/section/view";
	}
	
	/**
	 * 查询工段
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<Section> query(Pageable pageable,Section section) {
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		pageable.addFilter("enterpriseEmissionsManagement.enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		return sectionService.findPage(pageable);
		
	}
	
	/**
	 *  保存普表工段
	 * @param section
	 * @param governanceMeasures1 脱硫工艺
     * @param governanceMeasures2除尘工艺
     * @param governanceMeasures3 脱销工艺
     * @param governanceMeasures4 voc措施
	 * @return
	 */
	@RequestMapping(value = "/generalsave")
	@ResponseBody
	public Message generalsave(Section section, String governanceMeasures1,String governanceMeasures2,
	        String governanceMeasures3,String governanceMeasures4
	        ,Date putDate1,Date putDate2,Date putDate3,Date putDate4) {
		
		Section sectionback = sectionService.saveGeneralSection(section,governanceMeasures1,governanceMeasures2,governanceMeasures3
				,governanceMeasures4,putDate1,putDate2,putDate3,putDate4);
		
		return Message.success();
	}
	/**
	 *  保存简表工段
	 * @param section
	 * @param governanceMeasures1 脱硫工艺
	 * @param governanceMeasures2除尘工艺
	 * @param governanceMeasures3 脱销工艺
	 * @param governanceMeasures4 voc措施
	 * @return
	 */
	@RequestMapping(value = "/simplesave")
	@ResponseBody
	public Message simplesave(Section section, String governanceMeasures1,String governanceMeasures2,
			String governanceMeasures3,String governanceMeasures4) {
		
		Section sectionback = sectionService.saveSimpleSection(section,governanceMeasures1,governanceMeasures2,governanceMeasures3,governanceMeasures4);
		return Message.success();
	}
	/**
	 * 保存工段
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(Section section, Integer exhaustionHoleId,
	        Integer governanceMeasures1, 
	Integer governanceMeasures2, Integer governanceMeasures3, Integer governanceMeasures4) {
		
		Section sectionback = sectionService.saveSection(section,exhaustionHoleId,governanceMeasures1,governanceMeasures2,governanceMeasures3,  governanceMeasures4);
		
		return Message.success();
	}

	/**
	 * 更新工段
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(Section section,Integer exhaustionHoleId,
	        Integer governanceMeasures1, 
	Integer governanceMeasures2, Integer governanceMeasures3, Integer governanceMeasures4) {
		Section sectionback = sectionService.updateSection(section, exhaustionHoleId,
		         governanceMeasures1, 
		    	 governanceMeasures2,  governanceMeasures3,  governanceMeasures4);
		return Message.success();
	}
	/**更新简表工段
	 * @param section
	 * @param governanceMeasures1
	 * @param governanceMeasures2
	 * @param governanceMeasures3
	 * @param governanceMeasures4
	 * @return
	 */
	@RequestMapping(value = "/updateSimple", method = RequestMethod.POST)
	@ResponseBody
	public Message updateSimple(Section section,String governanceMeasures1,String governanceMeasures2,
            String governanceMeasures3,String governanceMeasures4) {
		Section sectionback = sectionService.updateSimpleSection(section,governanceMeasures1,governanceMeasures2,governanceMeasures3,governanceMeasures4);
		
		return Message.success();
	}
	
	@RequestMapping(value = "/updateGeneral", method = RequestMethod.POST)
	@ResponseBody
	public Message updateGeneral(Section section,String governanceMeasures1,String governanceMeasures2,
			String governanceMeasures3,String governanceMeasures4
	        ,Date putDate1,Date putDate2,Date putDate3,Date putDate4) {
		Section sectionback = sectionService.updateGeneralSection(section,governanceMeasures1,governanceMeasures2,governanceMeasures3
				,governanceMeasures4,putDate1,putDate2,putDate3,putDate4);
		return Message.success();
	}

	/**
	 * 删除工段
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			sectionService.deleteSection(ids[i]);
			monthlyInformationService.deleteByType(Constants.MONTH_TYPE_SECTION,ids[i]);
		}
		return Message.success();
	}
	/**
	 * 删除工段
	 */
	@RequestMapping(value = "/simpledelete", method = RequestMethod.POST)
	public @ResponseBody Message simpledelete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			sectionService.deleteSection(ids[i]);
			monthlyInformationService.deleteByType(Constants.MONTH_TYPE_SECTION,ids[i]);
		}
		return Message.success();
	}
	/**
	 * 删除工段
	 */
	@RequestMapping(value = "/generaldelete", method = RequestMethod.POST)
	public @ResponseBody Message generaldelete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			sectionService.deleteSection(ids[i]);
			monthlyInformationService.deleteByType(Constants.MONTH_TYPE_SECTION,ids[i]);
		}
		return Message.success();
	}
		
}
