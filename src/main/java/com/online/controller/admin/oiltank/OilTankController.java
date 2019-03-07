package com.online.controller.admin.oiltank;


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
import com.online.entity.online.OilTank;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.service.OilTankService;
import com.online.util.Constants;
import com.online.util.Principal;
import com.online.util.SpringUtils;



@Controller("oilTankController")
@RequestMapping("/admin/oiltank")
public class OilTankController extends BaseController{
	
	@Autowired
	private OilTankService  oilTankService ;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_oiltank);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/oiltank/list";
	}
	
	@RequestMapping("/simplelist")
	public String simplelist(ModelMap model){
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_simple_oiltank);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/oiltank/simple-list";
	}
	
	@RequestMapping("/generallist")
	public String generallist(ModelMap model){
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_general_oiltank);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/oiltank/general-list";
	}
	
	/**
	 * 增加石油产品储罐页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/oiltank/add";
	}

	/**
	 * 修改石油产品储罐页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("oilTank", oilTankService.find(id));
		return "/admin/oiltank/update";
	}
	
	/**
	 * 查看石油产品储罐页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("oilTank", oilTankService.find(id));
		return "/admin/oiltank/view";
	}
	
	/**
	 * 查询石油产品储罐
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<OilTank> query(Pageable pageable,OilTank oilTank) {
//		pageable.addEntity(oilTank);
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		pageable.addFilter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		return oilTankService.findPage(pageable);
		
	}
	

	/**
	 * 保存石油产品储罐
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(OilTank  oilTank) {
		 oilTankService.save(oilTank);
		return Message.success();
	}

	/**
	 * 更新石油产品储罐
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(OilTank oilTank) {
		oilTankService.update(oilTank);
		return Message.success();
	}

	/**
	 * 删除石油产品储罐
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 oilTankService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
