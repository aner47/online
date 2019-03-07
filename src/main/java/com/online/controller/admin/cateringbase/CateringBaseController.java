package com.online.controller.admin.cateringbase;


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
import com.online.entity.online.catering.CateringBase;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.CateringBaseService;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.util.Constants;
import com.online.util.Principal;
import com.online.util.SpringUtils;



@Controller("admincateringBaseController")
@RequestMapping("/admin/cateringbase")
public class CateringBaseController extends BaseController{
	
	@Autowired
	private CateringBaseService cateringBaseService;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		CateringBase cateringBaseFind = cateringBaseService.findListByProjectAndEnterprise();
		if (cateringBaseFind == null) {
			CateringBase cateringBase = new CateringBase();
			cateringBase.setProject(SpringUtils.getCurrentProject());
			cateringBase.setEnterprise(SpringUtils.getCurrentEnterprise());
			cateringBaseService.save(cateringBase);
			model.put("cateringBase", cateringBase);
		} else {
			model.put("cateringBase", cateringBaseFind);
		}
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_cateringbase);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		model.put("enterprise", SpringUtils.getCurrentEnterprise());
		return "/admin/cateringbase/list";
	}
	
	/**
	 * 增加餐饮基本信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/cateringbase/add";
	}

	
	
	/**
	 * 修改餐饮调查表基本信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("cateringBase", cateringBaseService.find(id));
		return "/admin/cateringbase/update";
	}
	
	/**
	 * 查看餐饮调查表基本信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("cateringBase", cateringBaseService.find(id));
		return "/admin/cateringbase/view";
	}
	
	/**
	 * 查询餐饮调查表基本信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<CateringBase> query(Pageable pageable,CateringBase cateringBase) {
		pageable.addFilter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		return cateringBaseService.findPage(pageable);
		
	}
	

	/**
	 * 保存餐饮调查表基本信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(CateringBase cateringBase) {
		cateringBase.setProject(SpringUtils.getCurrentProject());
		cateringBase.setEnterprise(SpringUtils.getCurrentEnterprise());
		if(cateringBase.getId() !=null){
			cateringBaseService.update(cateringBase);
		}
		
		return Message.success();
	}
	
	

	
	/**
	 * 更新餐饮调查表基本信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(CateringBase cateringBase) {
		cateringBaseService.update(cateringBase);
		return Message.success();
	}
	
	
	

	/**
	 * 删除餐饮调查表基本信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			cateringBaseService.delete(ids[i]);
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
