package com.online.controller.admin.monthlyinformation;


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
import com.online.entity.online.MonthlyInformation;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.service.MonthlyInformationService;
import com.online.util.Constants;
import com.online.util.Principal;
import com.online.util.SpringUtils;



@Controller("monthlyInformationController")
@RequestMapping("/admin/monthlyinformation")
public class MonthlyInformationController extends BaseController{
	
	@Autowired
	private MonthlyInformationService  monthlyInformationService ;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@RequestMapping("/list")
	public String list(ModelMap model,String monthlyType,Integer sourceId){
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_monthlyinformation);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		model.put("monthlyType", monthlyType);
		model.put("sourceId", sourceId);
		
		return "/admin/monthlyinformation/list";
	}
	
	/**
	 * 增加分月数据页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/monthlyinformation/add";
	}

	/**
	 * 修改分月数据页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("monthlyInformation", monthlyInformationService.find(id));
		return "/admin/monthlyinformation/update";
	}
	
	/**
	 * 查看分月数据页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("monthlyInformation", monthlyInformationService.find(id));
		return "/admin/monthlyinformation/view";
	}
	
	/**
	 * 查询分月数据
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<MonthlyInformation> query(Pageable pageable,MonthlyInformation monthlyInformation,String monthlyType,Integer sourceId) {
//		pageable.addEntity(monthlyInformation);
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		pageable.addFilter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		
		//如果分月类型是发电机组
				if("powerplant".equals(monthlyType)){
					pageable.addFilter("powerPlantId", Operator.eq, sourceId);
				//锅炉
				}else if("boiler".equals(monthlyType)){
					pageable.addFilter("boilerId", Operator.eq, sourceId);
				//煤气炉
				}else if("gasstove".equals(monthlyType)){
					pageable.addFilter("gasstoveId", Operator.eq, sourceId);
					
				//炉窑
				}else if("kiln".equals(monthlyType)){
					pageable.addFilter("kilnId", Operator.eq, sourceId);
				//生产线
				}else if("section".equals(monthlyType)){
					pageable.addFilter("sectionId", Operator.eq, sourceId);
				//原辅料	
				}else if("rawmaterials".equals(monthlyType)){
					pageable.addFilter("rawMaterialsId", Operator.eq, sourceId);
				}
		return monthlyInformationService.findPage(pageable);
		
	}
	

	/**
	 * 保存分月数据
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(MonthlyInformation  monthlyInformation) {
		 monthlyInformationService.save(monthlyInformation);
		return Message.success();
	}

	/**
	 * 更新分月数据
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(MonthlyInformation monthlyInformation) {
		monthlyInformationService.update(monthlyInformation);
		return Message.success();
	}

	/**
	 * 删除分月数据
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 monthlyInformationService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
