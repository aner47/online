package com.online.controller.admin.cateringfuel;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.online.Filter.Operator;
import com.online.Message;
import com.online.Page;
import com.online.Pageable;
import com.online.controller.base.BaseController;
import com.online.entity.online.catering.CateringFuel;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.CateringFuelService;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.util.Constants;
import com.online.util.Principal;
import com.online.util.SpringUtils;



@Controller("admincateringFuelController")
@RequestMapping("/admin/cateringfuel")
public class CateringFuelController extends BaseController{
	
	@Autowired
	private CateringFuelService cateringFuelService;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_cateringfuel);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		
		return "/admin/cateringfuel/list";
	}
	
	/**
	 * 增加餐饮燃料
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/cateringfuel/add";
	}

	
	
	/**
	 * 修改餐饮燃料
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("cateringFuel", cateringFuelService.find(id));
		return "/admin/cateringfuel/update";
	}
	
	/**
	 * 查看餐饮燃料
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("cateringFuel", cateringFuelService.find(id));
		return "/admin/dryclearsolvent/view";
	}
	
	/**
	 * 查询餐饮燃料
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<CateringFuel> query(Pageable pageable,CateringFuel cateringFuel) {
		pageable.addFilter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		return cateringFuelService.findPage(pageable);
		
	}
	

	/**
	 * 保存餐饮燃料
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(CateringFuel cateringFuel) {
		cateringFuel.setProject(SpringUtils.getCurrentProject());
		cateringFuelService.save(cateringFuel);
		return Message.success();
	}
	
	

	
	/**
	 * 更新餐饮燃料
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(CateringFuel cateringFuel) {
		cateringFuelService.update(cateringFuel);
		return Message.success();
	}
	
	
	

	/**
	 * 删除餐饮燃料
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			cateringFuelService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
