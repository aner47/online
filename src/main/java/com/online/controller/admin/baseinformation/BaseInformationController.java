package com.online.controller.admin.baseinformation;


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
import com.online.controller.base.BaseController;
import com.online.entity.online.BaseInformation;
import com.online.entity.online.Project;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.BaseInformationService;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.service.ProjectEnterpriseUserService;
import com.online.util.Constants;
import com.online.util.Principal;
import com.online.util.SpringUtils;



@Controller
@RequestMapping("/admin/baseinformation")
public class BaseInformationController extends BaseController{
	
	@Autowired
	private BaseInformationService  baseInformationService ;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@Autowired
	private ProjectEnterpriseUserService projectEnterpriseUserService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		model.put("baseInformation", baseInformationService.findListByProjectAndEnterprise());
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		Project  project = SpringUtils.getCurrentProject();
		model.put("project", project);
		
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_productionInformation);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		
		model.put("enterprise", SpringUtils.getCurrentEnterprise());
		model.put("sysEnterUser", projectEnterpriseUserService.findProjectTypeByProjectIdAndEnterpriseId(project.getId(), SpringUtils.getCurrentEnterprise().getId()));
		return "/admin/baseinformation/list";
	}
	
	/**
	 * 增加生产页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/baseinformation/add";
	}

	/**
	 * 修改生产页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("baseInformation", baseInformationService.find(id));
		return "/admin/baseinformation/update";
	}
	
	/**
	 * 查看生产页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("baseInformation", baseInformationService.find(id));
		return "/admin/baseinformation/view";
	}
	
	/**
	 * 查询生产
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<BaseInformation> query(Pageable pageable,BaseInformation baseInformation) {
		pageable.addEntity(baseInformation);
		return baseInformationService.findPage(pageable);
		
	}
	

	/**
	 * 保存生产
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(BaseInformation  baseInformation) {
		baseInformation.setProject(SpringUtils.getCurrentProject());
		baseInformation.setEnterprise(SpringUtils.getCurrentEnterprise());
		if(baseInformation.getId() !=null){
			baseInformationService.update(baseInformation);
		}
		return Message.success();
	}

	/**
	 * 更新生产
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(BaseInformation baseInformation) {
		baseInformationService.update(baseInformation);
		return Message.success();
	}

	/**
	 * 删除生产
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 baseInformationService.delete(ids[i]);
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
