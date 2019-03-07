package com.online.controller.admin.petrolsales;


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
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.entity.online.petrol.PetrolSales;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.service.PetrolSalesService;
import com.online.util.Constants;
import com.online.util.Principal;
import com.online.util.SpringUtils;

/**
 * 加油站分月经销信息
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2017年6月28日 下午5:47:30 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return
 */

@Controller("adminpetrolSalesController")
@RequestMapping("/admin/petrolsales")
public class PetrolSalesController extends BaseController{
	
	@Autowired
	private PetrolSalesService petrolSalesService;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_petrolsales);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		
		return "/admin/petrolsales/list";
	}
	
	/**
	 * 增加加油站分月经销页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/petrolsales/add";
	}

	
	
	/**
	 * 修改加油站分月经销页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("petrolSales", petrolSalesService.find(id));
		return "/admin/petrolsales/update";
	}
	
	/**
	 * 查看加油站分月经销页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("petrolSales", petrolSalesService.find(id));
		return "/admin/petrolsales/view";
	}
	
	/**
	 * 查询加油站分月经销信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<PetrolSales> query(Pageable pageable,PetrolSales petrolSales) {
		pageable.addFilter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		return petrolSalesService.findPage(pageable);
		
	}
	

	/**
	 * 保存加油站分月经销信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(PetrolSales petrolSales) {
		petrolSales.setProject(SpringUtils.getCurrentProject());
		petrolSalesService.save(petrolSales);
		return Message.success();
	}
	
	

	
	/**
	 * 更新加油站分月经销信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(PetrolSales petrolSales) {
		petrolSalesService.update(petrolSales);
		return Message.success();
	}
	
	
	

	/**
	 * 删除加油站分月经销信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			petrolSalesService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
