package com.online.controller.admin.openyard;


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
import com.online.entity.online.OpenYard;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.service.OpenYardService;
import com.online.util.Constants;
import com.online.util.Principal;
import com.online.util.SpringUtils;



@Controller("openYardController")
@RequestMapping("/admin/openyard")
public class OpenYardController extends BaseController{
	
	@Autowired
	private OpenYardService  openYardService ;
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_openyard);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		
		return "/admin/openyard/list";
	}
	
	@RequestMapping("/simplelist")
	public String simplelist(ModelMap model){
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_simple_openyard);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/openyard/simple-list";
	}
	
	@RequestMapping("/generallist")
	public String generallist(ModelMap model){
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_general_openyard);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/openyard/general-list";
	}
	
	/**
	 * 增加露天堆场页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_openyard);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/openyard/add";
	}
	
	/**
	 * 增加露天堆场页面
	 */
	@RequestMapping(value = "addsimplePage")
	public String addsimplePage(ModelMap model) {
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_simple_openyard);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/openyard/simple-add";
	}
	/**
	 * 增加露天堆场页面
	 */
	@RequestMapping(value = "addgeneralPage")
	public String addgeneralPage(ModelMap model) {
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_general_openyard);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/openyard/general-add";
	}
	
	/**
	 * 修改露天堆场页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		OpenYard openYard = openYardService.find(id);
		model.put("openYard", openYard);
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_openyard);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/openyard/update";
	}
	
	/**
	 * 修改露天堆场页面
	 */
	@RequestMapping(value = "updatesimplePage")
	public String updatesimplePage(ModelMap model,Integer id) {
		OpenYard openYard = openYardService.find(id);
		model.put("openYard", openYard);
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_simple_openyard);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		
		return "/admin/openyard/simple-update";
	}
	/**
	 * 修改露天堆场页面
	 */
	@RequestMapping(value = "updategeneralPage")
	public String updategeneralPage(ModelMap model,Integer id) {
		OpenYard openYard = openYardService.find(id);
		model.put("openYard", openYard);
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_general_openyard);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/openyard/general-update";
	}
	
	/**
	 * 查看露天堆场页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("openYard", openYardService.find(id));
		return "/admin/openyard/view";
	}
	
	/**
	 * 查询露天堆场
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<OpenYard> query(Pageable pageable,OpenYard openYard) {
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		pageable.addFilter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		return openYardService.findPage(pageable);
		
	}
	

	/**
	 * 保存露天堆场
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(OpenYard openYard) {
		openYard.setProject(SpringUtils.getCurrentProject());
		openYard.setEnterprise(SpringUtils.getCurrentEnterprise());
		openYardService.save(openYard);
		return Message.success();
	}

	/**
	 * 更新露天堆场
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(OpenYard openYard) {
		openYardService.update(openYard,"enterprise","project");
		return Message.success();
	}

	/**
	 * 删除露天堆场
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 openYardService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
