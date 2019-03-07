package com.online.controller.admin.singleboilerfuel;


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
import com.online.entity.online.SingleBoilerFuel;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.service.SingleBoilerFuelService;
import com.online.util.Constants;
import com.online.util.Principal;
import com.online.util.SpringUtils;



@Controller("adminsingleBoilerFuelController")
@RequestMapping("/admin/singleboilerfuel")
public class SingleBoilerFuelController extends BaseController{
	
	@Autowired
	private SingleBoilerFuelService singleBoilerFuelService;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_single_boilerinfuel);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/singleboilerfuel/list";
	}
	
	/**
	 * 增加单独锅炉燃料信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/singleboilerfuel/add";
	}

	
	
	/**
	 * 修改单独锅炉燃料信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("singleboilerfuel", singleBoilerFuelService.find(id));
		return "/admin/singleboilerfuel/update";
	}
	
	/**
	 * 查看单独锅炉燃料信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("singleboilerfuel", singleBoilerFuelService.find(id));
		return "/admin/singleboilerfuel/view";
	}
	
	/**
	 * 查询锅炉燃料信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<SingleBoilerFuel> query(Pageable pageable,SingleBoilerFuel singleBoilerFuel) {
		pageable.addFilter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		return singleBoilerFuelService.findPage(pageable);
		
	}
	

	/**
	 * 保存单独锅炉信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(SingleBoilerFuel singleBoilerFuel) {
		singleBoilerFuel.setProject(SpringUtils.getCurrentProject());
		singleBoilerFuelService.save(singleBoilerFuel);
		return Message.success();
	}
	
	

	
	/**
	 * 更新锅炉燃料信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(SingleBoilerFuel singleBoilerFuel) {
		singleBoilerFuelService.update(singleBoilerFuel);
		return Message.success();
	}
	
	
	

	/**
	 * 删除锅炉燃料信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			singleBoilerFuelService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
