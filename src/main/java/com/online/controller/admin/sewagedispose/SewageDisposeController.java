package com.online.controller.admin.sewagedispose;


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
import com.online.entity.online.Project;
import com.online.entity.online.SewageDispose;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.service.SewageDisposeService;
import com.online.util.Constants;
import com.online.util.Principal;
import com.online.util.SpringUtils;



@Controller("adminSewagedisposeController")
@RequestMapping("/admin/sewagedispose")
public class SewageDisposeController extends BaseController{
	
	@Autowired
	private SewageDisposeService  sewageDisposeService;
	
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_sewage);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/sewagedispose/list";
	}
	
	/**
	 * 增加污水处理页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		Project  project = SpringUtils.getCurrentProject();
		model.put("project", project);
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_steel);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/sewagedispose/add";
	}

	/**
	 * 修改污水处理页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("sewageDispose", sewageDisposeService.find(id));
		Project  project = SpringUtils.getCurrentProject();
		model.put("project", project);
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_steel);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/sewagedispose/update";
	}
	
	
	/**
	 * 查看污水处理页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("sewageDispose", sewageDisposeService.find(id));
		return "/admin/sewagedispose/view";
	}
	
	/**
	 * 查询污水处理
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<SewageDispose> query(Pageable pageable,SewageDispose sewageDispose) {
		pageable.addFilter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		return sewageDisposeService.findPage(pageable);
		
	}
	

	/**
	 * 保存污水处理
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(SewageDispose sewageDispose) {
		
		sewageDisposeService.save(sewageDispose);
		return Message.success();
	}
	
	/**
	 * 更新污水处理
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Message update(SewageDispose sewageDispose) {
		sewageDisposeService.update(sewageDispose);
		return Message.success();
	}
	

	/**
	 * 删除污水处理
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			sewageDisposeService.delete(ids[i]);
		}
		return Message.success();
	}
	
		
}
