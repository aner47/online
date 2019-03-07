package com.online.controller.admin.constructionyard;


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
import com.online.entity.online.constructionsite.ConstructionYard;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.ConstructionYardService;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.util.Constants;
import com.online.util.Principal;
import com.online.util.SpringUtils;



@Controller("adminconstructionYardController")
@RequestMapping("/admin/constructionyard")
public class ConstructionYardController extends BaseController{
	
	@Autowired
	private ConstructionYardService constructionYardService;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_constructionyard);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/constructionyard/list";
	}
	
	/**
	 * 增加露天堆场信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/constructionyard/add";
	}

	
	
	/**
	 * 修改露天堆场信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("constructionYard", constructionYardService.find(id));
		return "/admin/constructionyard/update";
	}
	
	/**
	 * 查看露天堆场信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("constructionYard", constructionYardService.find(id));
		return "/admin/constructionyard/view";
	}
	
	/**
	 * 查询露天堆场信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<ConstructionYard> query(Pageable pageable,ConstructionYard constructionYard) {
		pageable.addFilter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		return constructionYardService.findPage(pageable);
		
	}
	

	/**
	 * 保存露天堆场信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(ConstructionYard constructionYard) {
		constructionYard.setProject(SpringUtils.getCurrentProject());
		constructionYardService.save(constructionYard);
		return Message.success();
	}
	
	

	
	/**
	 * 更新露天堆场信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(ConstructionYard constructionYard) {
		constructionYardService.update(constructionYard);
		return Message.success();
	}
	
	
	

	/**
	 * 删除露天堆场信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			constructionYardService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
