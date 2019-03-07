package com.online.controller.admin.moduledetail;


import org.apache.commons.lang.StringUtils;
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
import com.online.entity.online.ModuleDetail;
import com.online.entity.online.ModuleInfo;
import com.online.service.ModuleDetailService;
import com.online.service.ModuleInfoService;



@Controller
@RequestMapping("/admin/moduledetail")
public class ModuleDetailController extends BaseController{
	
	@Autowired
	private ModuleDetailService  moduleDetailService ;
	@Autowired
	private ModuleInfoService moduleInfoService;
	@RequestMapping("/list")
	public String list(ModelMap map ,String moduleId){
	    map.put("moduleId", moduleId);
		return "/admin/moduledetail/list";
	}
	
	/**
	 * 增加模块明细页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap map ,String moduleId){
        map.put("moduleId", moduleId);
		return "/admin/moduledetail/add";
	}

	/**
	 * 修改模块明细页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id,String moduleId) {
		model.put("moduleDetail", moduleDetailService.find(id));
		model.put("moduleId", moduleId);
		return "/admin/moduledetail/update";
	}
	
	/**
	 * 查看模块明细页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("moduleDetail", moduleDetailService.find(id));
		return "/admin/moduledetail/view";
	}
	
	/**
	 * 查询模块明细
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<ModuleDetail> query(Pageable pageable,ModuleDetail moduleDetail,int moduleId) {
	    pageable.addFilter("moduleInfo.id", Operator.eq, moduleId);
	    if(StringUtils.isNotBlank(moduleDetail.getKey())){
	    	pageable.addFilter("key", Operator.like, "%"+moduleDetail.getKey()+"%");
	    }
	    if(StringUtils.isNotBlank(moduleDetail.getHead())){
	    	pageable.addFilter("head", Operator.like, "%"+moduleDetail.getHead()+"%");
	    }
		return moduleDetailService.findPage(pageable);
		
	}
	

	/**
	 * 保存模块明细
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(ModuleDetail  moduleDetail,String moduleId) {
	    ModuleInfo moduleInfo = moduleInfoService.find(Integer.parseInt(moduleId));
	    moduleDetail.setModuleInfo(moduleInfo);
		 moduleDetailService.save(moduleDetail);
		return Message.success();
	}

	/**
	 * 更新模块明细
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(ModuleDetail moduleDetail,String moduleId) {
	    ModuleInfo moduleInfo = moduleInfoService.find(Integer.parseInt(moduleId));
        moduleDetail.setModuleInfo(moduleInfo);
		moduleDetailService.update(moduleDetail);
		return Message.success();
	}

	/**
	 * 删除模块明细
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 moduleDetailService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
