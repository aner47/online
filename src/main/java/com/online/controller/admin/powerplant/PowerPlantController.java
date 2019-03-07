package com.online.controller.admin.powerplant;


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
import com.online.entity.online.PowerPlant;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.ExhaustionHoleService;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.service.GovernanceMeasuresService;
import com.online.service.PowerPlantService;
import com.online.util.Constants;
import com.online.util.Principal;
import com.online.util.SpringUtils;



@Controller
@RequestMapping("/admin/powerplant")
public class PowerPlantController extends BaseController{
	
	@Autowired
	private PowerPlantService  powerPlantService ;
	
	@Autowired
	protected ExhaustionHoleService exhaustionHoleService;
	@Autowired 
	protected GovernanceMeasuresService governanceMeasurService;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_powerplant);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/powerplant/list";
	}
	
	/**
	 * 增加电厂页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/powerplant/add";
	}

	/**
	 * 修改电厂页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("powerPlant", powerPlantService.find(id));
		return "/admin/powerplant/update";
	}
	
	/**
	 * 查看电厂页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("powerPlant", powerPlantService.find(id));
		return "/admin/powerplant/view";
	}
	
	/**
	 * 查询电厂
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<PowerPlant> query(Pageable pageable,PowerPlant powerPlant) {
		pageable.addFilter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		pageable.addEntity(powerPlant);
		return powerPlantService.findPage(pageable);
		
	}
	

	/**
	 * 保存电厂
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(PowerPlant  powerPlant) {
		 powerPlantService.save(powerPlant);
		return Message.success();
	}

	/**
	 * 更新电厂
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(PowerPlant powerPlant, Integer governanceMeasures11, Integer governanceMeasures21, Integer governanceMeasures31, Integer exhaustionHoleId1) {
	    if (exhaustionHoleId1!=null) {
	        powerPlant.setExhaustionHole(exhaustionHoleService.find(exhaustionHoleId1));
        }
	    if (governanceMeasures11!=null) {
	        powerPlant.setGovernanceMeasures1(governanceMeasurService.find(governanceMeasures11));
        }
	    if (governanceMeasures21!=null) {
	        powerPlant.setGovernanceMeasures2(governanceMeasurService.find(governanceMeasures21));
        }
	    if (governanceMeasures31!=null) {
	        powerPlant.setGovernanceMeasures3(governanceMeasurService.find(governanceMeasures31));
        }
		powerPlantService.update(powerPlant);
		return Message.success();
	}

	/**
	 * 删除电厂
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 powerPlantService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
