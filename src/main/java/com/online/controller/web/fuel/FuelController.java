package com.online.controller.web.fuel;


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
import com.online.entity.online.Fuel;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.service.FuelService;
import com.online.util.Constants;
import com.online.util.SpringUtils;



@Controller("webFuelController")
@RequestMapping("/web/fuel")
public class FuelController extends BaseController{
	
	@Autowired
	private FuelService  fuelService ;
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	
	@RequestMapping("/list")
	public String list(ModelMap model,String fuelType,Integer sourceId){
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_fuel);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		model.put("fuelType", fuelType);
		model.put("sourceId", sourceId);
		
		return "/admin/fuel/list";
	}
	
	/**
	 * 增加燃料页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model,String fuelType,Integer sourceId) {
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_fuel);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		model.put("fuelType", fuelType);
		model.put("sourceId", sourceId);
		return "/admin/fuel/add";
	}

	/**
	 * 修改燃料页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id,String fuelType,Integer sourceId) {
		model.put("fuel", fuelService.find(id));
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_fuel);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		model.put("fuelType", fuelType);
		model.put("sourceId", sourceId);
		return "/admin/fuel/update";
	}
	
	/**
	 * 查看燃料页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("fuel", fuelService.find(id));
		return "/admin/fuel/view";
	}
	
	/**
	 * 查询燃料
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<Fuel> query(Pageable pageable,Fuel fuel,String fuelType,Integer sourceId) {
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		pageable.addFilter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		//如果燃料是发电机组
		if("powerplant".equals(fuelType)){
			pageable.addFilter("powerPlantId", Operator.eq, sourceId);
		//锅炉
		}else if("boiler".equals(fuelType)){
			pageable.addFilter("boilerId", Operator.eq, sourceId);
		//煤气炉
		}else if("gasstove".equals(fuelType)){
			pageable.addFilter("gasstoveId", Operator.eq, sourceId);
			
		//炉窑
		}else if("kiln".equals(fuelType)){
			pageable.addFilter("kilnId", Operator.eq, sourceId);
		}
		
		return fuelService.findPage(pageable);
		
	}
	

	/**
	 * 保存燃料
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(Fuel  fuel) {
		fuel.setProject(SpringUtils.getCurrentProject());
		fuel.setEnterprise(SpringUtils.getCurrentEnterprise());
		fuelService.save(fuel);
		return Message.success();
	}

	/**
	 * 更新燃料
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(Fuel fuel) {
		fuelService.update(fuel,"enterprise","project","powerPlantId","boilerId","kilnId","gasstoveId");
		return Message.success();
	}

	/**
	 * 删除燃料
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 fuelService.delete(ids[i]);
		}
		return Message.success();
	}
	
}
