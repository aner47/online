package com.online.controller.admin.steel;


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
import com.online.entity.online.Project;
import com.online.entity.online.Steel;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.service.ProductService;
import com.online.service.RawMaterialsService;
import com.online.service.SteelService;
import com.online.util.Constants;
import com.online.util.Principal;
import com.online.util.SpringUtils;



@Controller("adminSteelController")
@RequestMapping("/admin/steel")
public class SteelController extends BaseController{
	
	@Autowired
	private SteelService  steelService;
	@Autowired
	protected ProductService productService;
	@Autowired
	protected RawMaterialsService materialService;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_steel);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		
		return "/admin/steel/list";
	}
	
	/**
	 * 增加钢铁页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		Project  project = SpringUtils.getCurrentProject();
		model.put("project", project);
		return "/admin/steel/add";
	}

	/**
	 * 修改钢铁页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("steel", steelService.find(id));
		Project  project = SpringUtils.getCurrentProject();
		model.put("project", project);
		return "/admin/steel/update";
	}
	
	
	/**
	 * 查看钢铁页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("steel", steelService.find(id));
		return "/admin/steel/view";
	}
	
	/**
	 * 查询钢铁
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<Steel> query(Pageable pageable,Steel steel) {
		pageable.addFilter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		return steelService.findPage(pageable);
		
	}
	

	/**
	 * 保存钢铁
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(Steel steel) {
		
		steelService.saveSteel(steel);
		return Message.success();
	}
	
	/**
	 * 更新钢铁
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Message update(Steel steel) {
		steelService.updateSteel(steel);
		return Message.success();
	}
	

	/**
	 * 删除钢铁
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 steelService.delete(ids[i]);
		}
		return Message.success();
	}
	
		
}
