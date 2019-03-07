package com.online.controller.admin.equipmentleaked;


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
import com.online.entity.online.EquipmentLeaked;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.EquipmentLeakedService;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.util.Constants;
import com.online.util.Principal;
import com.online.util.SpringUtils;



@Controller
@RequestMapping("/admin/equipmentleaked")
public class EquipmentLeakedController extends BaseController{
	
	@Autowired
	private EquipmentLeakedService  equipmentLeakedService ;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
        Principal attribute = SpringUtils.getPrincipal();
        if (attribute.getEnterpriseId()!=null) {
         modelMap.put("enterpriseId", attribute.getEnterpriseId());
        }
        modelMap.put("principal", attribute);
        FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_equipmentleaked);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				modelMap.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/equipmentleaked/list";
	}
	
	/**
	 * 增加设备泄露页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_equipmentleaked);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/equipmentleaked/add";
	}
	
	/**
	 * 修改设备泄露页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("equipmentLeaked", equipmentLeakedService.find(id));
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_equipmentleaked);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/equipmentleaked/update";
	}
	
	/**
	 * 查看设备泄露页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("equipmentLeaked", equipmentLeakedService.find(id));
		return "/admin/equipmentleaked/view";
	}
	
	/**
	 * 查询设备泄露
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<EquipmentLeaked> query(Pageable pageable,EquipmentLeaked equipmentLeaked) {
	    pageable.addFilter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
	    pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		pageable.addEntity(equipmentLeaked);
		return equipmentLeakedService.findPage(pageable);
		
	}
	

	/**
	 * 保存设备泄露
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(EquipmentLeaked  equipmentLeaked) {
		equipmentLeaked.setProject(SpringUtils.getCurrentProject());
		 equipmentLeakedService.save(equipmentLeaked);
		return Message.success();
	}

	/**
	 * 更新设备泄露
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(EquipmentLeaked equipmentLeaked) {
		equipmentLeakedService.update(equipmentLeaked,"enterprise");
		return Message.success();
	}

	/**
	 * 删除设备泄露
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 equipmentLeakedService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
