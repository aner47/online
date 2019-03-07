package com.online.controller.admin.kiln;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.online.Message;
import com.online.Page;
import com.online.Pageable;
import com.online.Filter.Operator;
import com.online.controller.base.BaseController;
import com.online.entity.online.Kiln;
import com.online.entity.online.Project;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.pscctask.TaskPsccService;
import com.online.service.ExhaustionHoleService;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.service.GovernanceMeasuresService;
import com.online.service.KilnService;
import com.online.service.ProductService;
import com.online.service.RawMaterialsService;
import com.online.util.Constants;
import com.online.util.Principal;
import com.online.util.SpringUtils;



@Controller
@RequestMapping("/admin/kiln")
public class KilnController extends BaseController{
	
	@Autowired
	private KilnService  kilnService ;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_kiln);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		
		return "/admin/kiln/list";
	}
	
	@RequestMapping("/generallist")
	public String generallist(ModelMap model){
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_general_kiln);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		
		return "/admin/kiln/general-list";
	}
	
	
	/**
	 * 增加窑炉页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		Project  project = SpringUtils.getCurrentProject();
		model.put("project", project);
		
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_kiln);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/kiln/add";
	}
	/**
	 * 增加窑炉页面
	 */
	@RequestMapping(value = "generaladdPage")
	public String generaladdPage(ModelMap model) {
		Project  project = SpringUtils.getCurrentProject();
		model.put("project", project);
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_general_kiln);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/kiln/general-add";
	}

	/**
	 * 修改窑炉页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("kiln", kilnService.find(id));
		Project  project = SpringUtils.getCurrentProject();
		model.put("project", project);
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_kiln);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/kiln/update";
	}
	/**
	 * 修改窑炉页面
	 */
	@RequestMapping(value = "generalupdatePage")
	public String generalupdatePage(ModelMap model,Integer id) {
		model.put("kiln", kilnService.find(id));
		Project  project = SpringUtils.getCurrentProject();
		model.put("project", project);
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_general_kiln);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/kiln/general-update";
	}
	
	/**
	 * 查看窑炉页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("kiln", kilnService.find(id));
		return "/admin/kiln/view";
	}
	
	/**
	 * 查询窑炉
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<Kiln> query(Pageable pageable,Kiln kiln) {
		pageable.addFilter("enterpriseEmissionsManagement.enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		return kilnService.findPage(pageable);
		
	}
	

	/**
	 * 保存窑炉
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(Kiln kiln, Integer governanceMeasures1
			,Integer governanceMeasures2
			, Integer governanceMeasures3
			, Integer exhaustionHoleId
			, Integer exhaustionHoleTailId) {
		
		kilnService.saveKiln(kiln,governanceMeasures1,governanceMeasures2,governanceMeasures3,exhaustionHoleId,exhaustionHoleTailId);
		return Message.success();
	}
	/**
	 * 保存窑炉
	 */
	@RequestMapping(value = "/generalsave")
	@ResponseBody
	public Message generalsave(Kiln kiln,String governanceMeasures1,String governanceMeasures2,String governanceMeasures3,
			String governanceMeasures4,Date putDate1,Date putDate2,Date putDate3,Date putDate4) {
		
		kilnService.generalsave(kiln,governanceMeasures1,governanceMeasures2,governanceMeasures3,governanceMeasures4,
				putDate1,putDate2,putDate3,putDate4);
		return Message.success();
	}

	/**
	 * 更新窑炉
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Message update(Kiln kiln,Integer governanceMeasures1
			,Integer governanceMeasures2
			, Integer governanceMeasures3
			, Integer exhaustionHoleId
			, Integer exhaustionHoleTailId) {
		exhaustionHoleId = exhaustionHoleId == null?-1:exhaustionHoleId;
		exhaustionHoleTailId = exhaustionHoleTailId == null?-1:exhaustionHoleTailId;
		governanceMeasures1 = governanceMeasures1 == null?-1:governanceMeasures1;
		governanceMeasures2 = governanceMeasures2 == null?-1:governanceMeasures2;
		governanceMeasures3 = governanceMeasures3 == null?-1:governanceMeasures3;
		
		kilnService.updateKiln(kiln, governanceMeasures1
				, governanceMeasures2
				,  governanceMeasures3
				,  exhaustionHoleId
				,  exhaustionHoleTailId);
		return Message.success();
	}
	/**
	 * 更新窑炉
	 */
	@RequestMapping(value = "/generalupdate")
	@ResponseBody
	public Message generalupdate(Kiln kiln,String governanceMeasures1,String governanceMeasures2,String governanceMeasures3,
			String governanceMeasures4,Date putDate1,Date putDate2,Date putDate3,Date putDate4) {
		kilnService.updateGeneralKiln(kiln,governanceMeasures1, governanceMeasures2, governanceMeasures3,
				 governanceMeasures4, putDate1, putDate2, putDate3, putDate4);
		return Message.success();
	}

	/**
	 * 删除窑炉
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			kilnService.deleteKiln(ids[i]);
		}
		return Message.success();
	}
	/**
	 * 删除窑炉
	 */
	@RequestMapping(value = "/generaldelete", method = RequestMethod.POST)
	public @ResponseBody Message generaldelete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			kilnService.deleteKiln(ids[i]);
		}
		return Message.success();
	}
		
}
