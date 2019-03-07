package com.online.controller.admin.fieldmodule;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
import com.online.entity.online.fieldset.FieldModule;
import com.online.service.FieldModuleService;



@Controller
@RequestMapping("/admin/fieldmodule")
public class FieldModuleController extends BaseController{
	
	@Autowired
	private FieldModuleService  fieldModuleService;
	
	
	@RequestMapping("/list")
	public String list(){
		return "/admin/fieldmodule/list";
	}
	
	/**
	 * 增加模块信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/fieldmodule/add";
	}

	/**
	 * 修改模块信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("moduleInfo", fieldModuleService.find(id));
		return "/admin/fieldmodule/update";
	}
	
	/**
	 * 查看模块信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("moduleInfo", fieldModuleService.find(id));
		return "/admin/fieldmodule/view";
	}
	
	/**
	 * 查询模块信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<FieldModule> query(Pageable pageable,FieldModule fieldModule) {
	    String name = fieldModule.getName();
        if (StringUtils.isNotEmpty(name)) {
            pageable.addFilter("name", Operator.like, "%"+name.trim()+"%");
        }
        String tableType = fieldModule.getTableType();
        if (StringUtils.isNotEmpty(tableType)) {
            pageable.addFilter("tableType", Operator.eq, tableType);
        }
		return fieldModuleService.findPage(pageable);
		
	}
	

	/**
	 * 保存模块信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(FieldModule  fieldModule) {
		
		FieldModule fieldModuleBack = fieldModuleService.findByNameTableType(fieldModule.getName(), fieldModule.getTableType());
		if(fieldModuleBack != null){
			return Message.error("菜单已存在，不能重复添加", null);
		}else{
			fieldModuleService.save(fieldModule);
			return Message.success();
		}
		
	}

	/**
	 * 更新模块信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(FieldModule fieldModule) {
		FieldModule fieldModuleBack = fieldModuleService.findByNameTableType(fieldModule.getName(), fieldModule.getTableType());
		if(fieldModuleBack != null && fieldModuleBack.getId() !=fieldModule.getId()){
			return Message.error("菜单已存在，不能重复添加", null);
		}else{
			fieldModuleService.update(fieldModule);
			return Message.success();
		}
		
	}

	/**
	 * 删除模块信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			fieldModuleService.delete(ids[i]);
			 
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
        List<FieldModule> findList = fieldModuleService.findList(null, filters, null);
        return Message.success(findList);
    }
		
}
