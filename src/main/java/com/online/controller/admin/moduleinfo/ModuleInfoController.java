package com.online.controller.admin.moduleinfo;


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
import com.online.entity.online.ModuleInfo;
import com.online.service.ModuleDetailService;
import com.online.service.ModuleInfoService;



@Controller
@RequestMapping("/admin/moduleinfo")
public class ModuleInfoController extends BaseController{
	
	@Autowired
	private ModuleInfoService  moduleInfoService ;
	@Autowired
	private ModuleDetailService moduleDetailService;
	@RequestMapping("/list")
	public String list(){
		return "/admin/moduleinfo/list";
	}
	
	/**
	 * 增加模块信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/moduleinfo/add";
	}

	/**
	 * 修改模块信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("moduleInfo", moduleInfoService.find(id));
		return "/admin/moduleinfo/update";
	}
	
	/**
	 * 查看模块信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("moduleInfo", moduleInfoService.find(id));
		return "/admin/moduleinfo/view";
	}
	
	/**
	 * 查询模块信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<ModuleInfo> query(Pageable pageable,ModuleInfo moduleInfo) {
	    String name = moduleInfo.getName();
        if (StringUtils.isNotEmpty(name)) {
            pageable.addFilter("name", Operator.like, "%"+name.trim()+"%");
        }
        String moduleClassification = moduleInfo.getModuleClassification();
        if (StringUtils.isNotEmpty(moduleClassification)) {
            pageable.addFilter("moduleClassification", Operator.eq, moduleClassification);
        }
		return moduleInfoService.findPage(pageable);
		
	}
	

	/**
	 * 保存模块信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(ModuleInfo  moduleInfo) {
		 moduleInfoService.save(moduleInfo);
		return Message.success();
	}

	/**
	 * 更新模块信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(ModuleInfo moduleInfo) {
		moduleInfoService.update(moduleInfo);
		return Message.success();
	}

	/**
	 * 删除模块信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
		    moduleDetailService.deleteDetails(ids[i]);
			 moduleInfoService.delete(ids[i]);
			 
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
        List<ModuleInfo> findList = moduleInfoService.findList(null, filters, null);
        return Message.success(findList);
    }
		
}
