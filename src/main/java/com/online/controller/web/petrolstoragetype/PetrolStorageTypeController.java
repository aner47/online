package com.online.controller.web.petrolstoragetype;


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
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.entity.online.petrol.PetrolStorageType;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.service.PetrolStorageTypeService;
import com.online.util.Constants;
import com.online.util.SpringUtils;



@Controller("petrolStorageTypeController")
@RequestMapping("/web/petrolstoragetype")
public class PetrolStorageTypeController extends BaseController{
	
	@Autowired
	private PetrolStorageTypeService petrolStorageTypeService;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_petrolstoragetype);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/petrolstoragetype/list";
	}
	
	/**
	 * 增加储罐类型页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_petrolstoragetype);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/petrolstoragetype/add";
	}

	
	
	/**
	 * 修改储罐类型页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("petrolStorageType", petrolStorageTypeService.find(id));
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_petrolstoragetype);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/petrolstoragetype/update";
	}
	
	/**
	 * 查看储罐类型页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("petrolStorageType", petrolStorageTypeService.find(id));
		return "/admin/petrolstoragetype/view";
	}
	
	/**
	 * 查询储罐类型信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<PetrolStorageType> query(Pageable pageable,PetrolStorageType petrolStorageType) {
		pageable.addFilter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		return petrolStorageTypeService.findPage(pageable);
		
	}
	

	/**
	 * 保存储罐类型信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(PetrolStorageType petrolStorageType) {
		petrolStorageType.setProject(SpringUtils.getCurrentProject());
		petrolStorageTypeService.save(petrolStorageType);
		return Message.success();
	}
	
	

	
	/**
	 * 更新储罐类型信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(PetrolStorageType petrolStorageType) {
		petrolStorageTypeService.update(petrolStorageType);
		return Message.success();
	}
	
	
	

	/**
	 * 删除储罐类型信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			petrolStorageTypeService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
