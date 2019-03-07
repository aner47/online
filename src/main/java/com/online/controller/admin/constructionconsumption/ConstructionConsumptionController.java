package com.online.controller.admin.constructionconsumption;


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
import com.online.entity.online.constructionsite.ConstructionConsumption;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.ConstructionConsumptionService;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.util.Constants;
import com.online.util.Principal;
import com.online.util.SpringUtils;



@Controller("adminconstructionConsumptionController")
@RequestMapping("/admin/constructionconsumption")
public class ConstructionConsumptionController extends BaseController{
	
	@Autowired
	private ConstructionConsumptionService constructionConsumptionService;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_constructionconsumption);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/constructionconsumption/list";
	}
	
	/**
	 * 增加机械消耗页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/constructionconsumption/add";
	}

	
	
	/**
	 * 修改机械消耗页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("constructionConsumption", constructionConsumptionService.find(id));
		return "/admin/constructionconsumption/update";
	}
	
	/**
	 * 查看机械消耗页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("constructionConsumption", constructionConsumptionService.find(id));
		return "/admin/constructionconsumption/view";
	}
	
	/**
	 * 查询机械消耗信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<ConstructionConsumption> query(Pageable pageable,ConstructionConsumption constructionConsumption) {
		pageable.addFilter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		return constructionConsumptionService.findPage(pageable);
		
	}
	

	/**
	 * 保存机械消耗信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(ConstructionConsumption constructionConsumption) {
		constructionConsumption.setProject(SpringUtils.getCurrentProject());
		constructionConsumptionService.save(constructionConsumption);
		return Message.success();
	}
	
	

	
	/**
	 * 更新机械消耗信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(ConstructionConsumption constructionConsumption) {
		constructionConsumptionService.update(constructionConsumption);
		return Message.success();
	}
	
	
	

	/**
	 * 删除机械消耗信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			constructionConsumptionService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
