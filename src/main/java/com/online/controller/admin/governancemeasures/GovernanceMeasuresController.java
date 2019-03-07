package com.online.controller.admin.governancemeasures;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.online.Message;
import com.online.Page;
import com.online.Pageable;
import com.online.Filter.Operator;
import com.online.controller.base.BaseController;
import com.online.entity.online.GovernanceMeasures;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.service.GovernanceMeasuresService;
import com.online.util.Constants;
import com.online.util.Principal;
import com.online.util.SpringUtils;



@Controller("governanceMeasuresController")
@RequestMapping("/admin/governancemeasures")
public class GovernanceMeasuresController extends BaseController{
	
	@Autowired
	private GovernanceMeasuresService  governanceMeasuresService ;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_governancemeasures);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/governancemeasures/list";
	}
	
	/**
	 * 增加污染治理措施页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/governancemeasures/add";
	}

	/**
	 * 修改污染治理措施页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("governanceMeasures", governanceMeasuresService.find(id));
		return "/admin/governancemeasures/update";
	}
	
	/**
	 * 查看污染治理措施页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("governanceMeasures", governanceMeasuresService.find(id));
		return "/admin/governancemeasures/view";
	}
	
	/**
	 * 查询污染治理措施
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<GovernanceMeasures> query(Pageable pageable,GovernanceMeasures governanceMeasures) {
//		pageable.addEntity(governanceMeasures);
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		pageable.addFilter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		return governanceMeasuresService.findPage(pageable);
		
	}
	

	/**
	 * 保存污染治理措施
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(GovernanceMeasures  governanceMeasures) {
		 governanceMeasuresService.save(governanceMeasures);
		return Message.success();
	}

	/**
	 * 更新污染治理措施
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(GovernanceMeasures governanceMeasures) {
		governanceMeasuresService.update(governanceMeasures,"enterprise","project");
		return Message.success();
	}

	/**
	 * 删除污染治理措施
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 governanceMeasuresService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
