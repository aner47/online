package com.online.controller.admin.fieldshowconfig;


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
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.service.ProjectTypeService;



@Controller
@RequestMapping("/admin/fieldshowconfig")
public class FieldShowConfigController extends BaseController{
	
	@Autowired
	private FieldShowConfigService  fieldShowConfigService;
	@Autowired
	private FieldModuleService  fieldModuleService;
	@Autowired
	private ProjectTypeService  projectTypeService;
	
	
	@RequestMapping("/list")
	public String list(){
		return "/admin/fieldshowconfig/list";
	}
	
	/**
	 * 增加模块信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/fieldshowconfig/add";
	}

	/**
	 * 修改模块信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("fieldShowConfig", fieldShowConfigService.find(id));
		return "/admin/fieldshowconfig/update";
	}
	
	/**
	 * 查看模块信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("fieldShowConfig", fieldShowConfigService.find(id));
		return "/admin/fieldshowconfig/view";
	}
	
	/**
	 * 查询模块信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<FieldShowConfig> query(Pageable pageable,FieldShowConfig fieldShowConfig) {
		
	    Integer  projectId = fieldShowConfig.getProjectId();
        pageable.addFilter("projectId", Operator.eq, projectId);
        
        String tableType = fieldShowConfig.getTableType();
        if (StringUtils.isNotEmpty(tableType)) {
        	pageable.addFilter("tableType", Operator.eq,tableType);
        }
        String fieldModule = fieldShowConfig.getFieldModule();
        if (StringUtils.isNotEmpty(fieldModule)) {
        	pageable.addFilter("fieldModule", Operator.eq,Integer.parseInt(fieldModule));
        }
       
		return fieldShowConfigService.findPage(pageable);
		
	}
	/**
	 * 查询模块信息
	 */
	@RequestMapping(value = "/querybyprojectid")
	@ResponseBody
	public List<FieldShowConfig> querybyprojectid(Integer projectId,String tableType,Integer projectType) {
		
		List<Filter> filters = new ArrayList<>();
		
		filters.add(new Filter("projectId", Operator.eq, projectId));
		filters.add(new Filter("tableType", Operator.eq, tableType));
		filters.add(new Filter("projectType.id", Operator.eq, projectType));
		
		List<FieldShowConfig> fieldShowConfigs = fieldShowConfigService.findList(null, filters, null);
		
		for(FieldShowConfig fieldShowConfig:fieldShowConfigs){
			fieldShowConfig.setFieldModule(fieldModuleService.find(Integer.parseInt(fieldShowConfig.getFieldModule())).getName());
		}
		
		return fieldShowConfigs;
		
	}
	

	/**
	 * 保存模块信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(FieldShowConfig  fieldShowConfig,Integer alreadyProjectId,String alreadyMenu,Integer projectTypeId) {
		
		//如果alreadyProjectId不为空，选择套用其他项目模板
		if(alreadyProjectId != null){
			
			if(fieldShowConfig.getProjectId() == alreadyProjectId){
				return Message.error("配置已存在，不能重复添加", null);
			}else{
				if(StringUtils.isNotBlank(alreadyMenu)){
					String [] alreadyMenus = alreadyMenu.split(",");
					for(String alreadyMenuId:alreadyMenus){
						FieldShowConfig fieldShowConfigReady =  fieldShowConfigService.find(Integer.parseInt(alreadyMenuId));
						FieldShowConfig newFieldShowConfig = new FieldShowConfig();
						newFieldShowConfig.setProjectId(fieldShowConfig.getProjectId());
						newFieldShowConfig.setFieldModule(fieldShowConfigReady.getFieldModule());
						newFieldShowConfig.setHideField(fieldShowConfigReady.getHideField());
						newFieldShowConfig.setTableType(fieldShowConfig.getTableType());
						newFieldShowConfig.setProjectType(projectTypeService.find(projectTypeId));
						
						fieldShowConfigService.save(newFieldShowConfig);
					}
					return Message.success();
				}else{
					return Message.error("无配置菜单", null);
				}
				
			}
		}else{
			FieldShowConfig fieldShowConfigBack = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldShowConfig.getProjectId(),
					fieldShowConfig.getTableType(), Integer.parseInt(fieldShowConfig.getFieldModule()),projectTypeId);
			if(fieldShowConfigBack != null){
				return Message.error("配置已存在，不能重复添加", null);
			}else{
				fieldShowConfig.setProjectType(projectTypeService.find(projectTypeId));
				fieldShowConfigService.save(fieldShowConfig);
				return Message.success();
			}
		}
		
		
		
		
	}

	/**
	 * 更新模块信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(FieldShowConfig fieldShowConfig,Integer projectTypeId) {
		
		FieldShowConfig fieldShowConfigBack = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldShowConfig.getProjectId(),
				fieldShowConfig.getTableType(), Integer.parseInt(fieldShowConfig.getFieldModule()),projectTypeId);
		if(fieldShowConfigBack != null&& !fieldShowConfigBack.getId().equals(fieldShowConfig.getId())){
			return Message.error("配置已存在，不能重复添加", null);
		}else{
			fieldShowConfig.setProjectType(projectTypeService.find(projectTypeId));
			fieldShowConfigService.update(fieldShowConfig);
			return Message.success();
		}
		
		
	}

	/**
	 * 删除模块信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			fieldShowConfigService.delete(ids[i]);
			 
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
        List<FieldShowConfig> findList = fieldShowConfigService.findList(null, filters, null);
        return Message.success(findList);
    }
		
}
