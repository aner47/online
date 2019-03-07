package com.online.controller.admin.fieldconfig;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.Message;
import com.online.Page;
import com.online.Pageable;
import com.online.controller.base.BaseController;
import com.online.entity.online.ModuleInfo;
import com.online.entity.online.fieldset.FieldConfig;
import com.online.entity.online.fieldset.FieldModule;
import com.online.service.FieldConfigService;
import com.online.service.FieldModuleService;



@Controller
@RequestMapping("/admin/fieldconfig")
public class FieldConfigController extends BaseController{
	
	@Autowired
	private FieldConfigService  fieldConfigService;
	@Autowired
	private FieldModuleService  fieldModuleService;
	
	
	@RequestMapping("/list")
	public String list(ModelMap map,String fieldModelId){
		map.put("moduleId", fieldModelId);
		return "/admin/fieldconfig/list";
	}
	
	/**
	 * 增加字段配置页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap map ,String moduleId) {
		 map.put("moduleId", moduleId);
		return "/admin/fieldconfig/add";
	}

	/**
	 * 修改字段配置页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id,String moduleId) {
		model.put("fieldConfig", fieldConfigService.find(id));
		model.put("moduleId", moduleId);
		return "/admin/fieldconfig/update";
	}
	
	/**
	 * 查看字段配置页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("moduleInfo", fieldConfigService.find(id));
		return "/admin/fieldconfig/view";
	}
	
	/**
	 * 查询字段配置
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<FieldConfig> query(Pageable pageable,FieldConfig fieldConfig,int moduleId) {
		 pageable.addFilter("fieldModule.id", Operator.eq, moduleId);
		return fieldConfigService.findPage(pageable);
		
	}
	
	/**
	 * 查询字段配置
	 */
	@RequestMapping(value = "/queryAll")
	@ResponseBody
	public List<FieldConfig> queryAll(Pageable pageable,FieldConfig fieldConfig,int moduleId) {
		return fieldConfigService.findByFilter(new Filter("fieldModule.id", Operator.eq, moduleId));
		
	}
	

	/**
	 * 保存字段配置
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(FieldConfig  fieldConfig,String moduleId) {
		FieldModule fieldModule = fieldModuleService.find(Integer.parseInt(moduleId));
		fieldConfig.setFieldModule(fieldModule);
		fieldConfigService.save(fieldConfig);
		return Message.success();
	}

	/**
	 * 更新字段配置
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(FieldConfig fieldConfig,String moduleId) {
		FieldModule fieldModule = fieldModuleService.find(Integer.parseInt(moduleId));
		fieldConfig.setFieldModule(fieldModule);
		fieldConfigService.update(fieldConfig);
		return Message.success();
	}

	/**
	 * 删除字段配置
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer moduleId,Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			fieldConfigService.deleteFieldConfig(moduleId,ids[i]);
			 
		}
		return Message.success();
	}
	/**
     * 根据填表类型查询所有模块数据
     */
    @RequestMapping(value = "/findModuleByType", method = RequestMethod.POST)
    public @ResponseBody Message findModuleByType(String moduleClassification) {
        List<Filter> filters = new ArrayList<>();
        filters.add(new Filter("moduleClassification", Operator.eq, moduleClassification));
        List<FieldConfig> findList = fieldConfigService.findList(null, filters, null);
        return Message.success(findList);
    }
		
}
