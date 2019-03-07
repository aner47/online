package com.online.controller.admin.breakdownservicebase;


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
import com.online.entity.online.breakdownservice.BreakdownServiceBase;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.BreakdownServiceBaseService;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.util.Constants;
import com.online.util.Principal;
import com.online.util.SpringUtils;



@Controller("adminbreakdownServiceBaseController")
@RequestMapping("/admin/breakdownservicebase")
public class BreakdownServiceBaseController extends BaseController{
	
	@Autowired
	private BreakdownServiceBaseService breakdownServiceBaseService;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		BreakdownServiceBase breakdownServiceBaseFind = breakdownServiceBaseService.findListByProjectAndEnterprise();
		if (breakdownServiceBaseFind == null) {
			BreakdownServiceBase breakdownServiceBase = new BreakdownServiceBase();
			breakdownServiceBase.setProject(SpringUtils.getCurrentProject());
			breakdownServiceBase.setEnterprise(SpringUtils.getCurrentEnterprise());
			breakdownServiceBaseService.save(breakdownServiceBase);
			model.put("breakdownServiceBase", breakdownServiceBase);
		} else {
			model.put("breakdownServiceBase", breakdownServiceBaseFind);
		}
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_breakdownservicebase);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		model.put("enterprise", SpringUtils.getCurrentEnterprise());
		return "/admin/breakdownservicebase/list";
	}
	
	/**
	 * 增加汽修调查表页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/breakdownservicebase/add";
	}

	
	
	/**
	 * 修改汽修调查表页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("breakdownServiceBase", breakdownServiceBaseService.find(id));
		return "/admin/breakdownservicebase/update";
	}
	
	/**
	 * 查看汽修调查表页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("breakdownServiceBase", breakdownServiceBaseService.find(id));
		return "/admin/breakdownservicebase/view";
	}
	
	/**
	 * 查询汽修调查表信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<BreakdownServiceBase> query(Pageable pageable,BreakdownServiceBase breakdownServiceBase) {
		pageable.addFilter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		return breakdownServiceBaseService.findPage(pageable);
		
	}
	

	/**
	 * 保存汽修调查表基本信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(BreakdownServiceBase breakdownServiceBase) {
		breakdownServiceBase.setProject(SpringUtils.getCurrentProject());
		breakdownServiceBase.setEnterprise(SpringUtils.getCurrentEnterprise());
		if(breakdownServiceBase.getId() !=null){
			breakdownServiceBaseService.update(breakdownServiceBase);
		}
		
		return Message.success();
	}
	
	

	
	/**
	 * 更新汽修调查表基本信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(BreakdownServiceBase breakdownServiceBase) {
		breakdownServiceBaseService.update(breakdownServiceBase);
		return Message.success();
	}
	
	
	

	/**
	 * 删除汽修调查表基本信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			breakdownServiceBaseService.delete(ids[i]);
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
