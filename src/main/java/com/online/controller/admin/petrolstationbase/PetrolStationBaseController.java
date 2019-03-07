package com.online.controller.admin.petrolstationbase;


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
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.entity.online.petrol.PetrolStationBase;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.service.PetrolStationBaseService;
import com.online.util.Constants;
import com.online.util.Principal;
import com.online.util.SpringUtils;



@Controller("adminpetrolStationBaseController")
@RequestMapping("/admin/petrolstationbase")
public class PetrolStationBaseController extends BaseController{
	
	@Autowired
	private PetrolStationBaseService petrolStationBaseService;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		PetrolStationBase petrolStationBaseFind = petrolStationBaseService.findListByProjectAndEnterprise();
		if (petrolStationBaseFind == null) {
			PetrolStationBase petrolStationBase = new PetrolStationBase();
			petrolStationBase.setProject(SpringUtils.getCurrentProject());
			petrolStationBase.setEnterprise(SpringUtils.getCurrentEnterprise());
			petrolStationBaseService.save(petrolStationBase);
			model.put("petrolStationBase", petrolStationBase);
		} else {
			model.put("petrolStationBase", petrolStationBaseFind);
		}
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_petrolstationbase);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		
		model.put("enterprise", SpringUtils.getCurrentEnterprise());
		return "/admin/petrolstationbase/list";
	}
	
	/**
	 * 增加加油站调查表页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/petrolstationbase/add";
	}

	
	
	/**
	 * 修改加油站调查表页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("petrolStationBase", petrolStationBaseService.find(id));
		return "/admin/petrolstationbase/update";
	}
	
	/**
	 * 查看加油站调查表页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("petrolStationBase", petrolStationBaseService.find(id));
		return "/admin/petrolstationbase/view";
	}
	
	/**
	 * 查询加油站调查表信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<PetrolStationBase> query(Pageable pageable,PetrolStationBase petrolStationBase) {
		pageable.addFilter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		return petrolStationBaseService.findPage(pageable);
		
	}
	

	/**
	 * 保存加油站调查表基本信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(PetrolStationBase petrolStationBase) {
		petrolStationBase.setProject(SpringUtils.getCurrentProject());
		petrolStationBase.setEnterprise(SpringUtils.getCurrentEnterprise());
		if(petrolStationBase.getId() !=null){
			petrolStationBaseService.update(petrolStationBase);
		}
		
		return Message.success();
	}
	
	

	
	/**
	 * 更新加油站调查表基本信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(PetrolStationBase petrolStationBase) {
		petrolStationBaseService.update(petrolStationBase);
		return Message.success();
	}
	
	
	

	/**
	 * 删除加油站调查表基本信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			petrolStationBaseService.delete(ids[i]);
		}
		return Message.success();
	}
	
	/**
	 * 审核页面
	 */
	@RequestMapping(value = "checkPage")
	public String checkPage(ModelMap model,int enterprieId) {
		model.put("enterprieId", enterprieId);
		return "/admin/enterprise/check";
	}
		
}
