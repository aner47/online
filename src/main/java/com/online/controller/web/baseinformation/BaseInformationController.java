package com.online.controller.web.baseinformation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.Message;
import com.online.controller.base.BaseController;
import com.online.entity.online.BaseInformation;
import com.online.entity.online.Project;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.BaseInformationService;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.service.SimpleBaseInformationService;
import com.online.util.Constants;
import com.online.util.Principal;
import com.online.util.SpringUtils;



@Controller("webBaseInformationController")
@RequestMapping("/web/baseinformation")
public class BaseInformationController extends BaseController{
	
	@Autowired
	private BaseInformationService  baseInformationService ;
	@Autowired
	private SimpleBaseInformationService  simpleBaseInformationService ;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		
		BaseInformation baseInformationFind = baseInformationService.findListByProjectAndEnterprise();
		if (baseInformationFind == null) {
			BaseInformation baseInformation = new BaseInformation();
			baseInformation.setProject(SpringUtils.getCurrentProject());
			baseInformation.setEnterprise(SpringUtils.getCurrentEnterprise());
			simpleBaseInformationService.deleteByProjectAndEnterprise();
			baseInformationService.save(baseInformation);
			model.put("baseInformation", baseInformation);
		} else {
			model.put("baseInformation", baseInformationFind);
		}
		Project  project = SpringUtils.getCurrentProject();
		model.put("project", project);
		
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_productionInformation);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		
		model.put("enterprise", SpringUtils.getCurrentEnterprise());
		return "/admin/baseinformation/list";
	}
	

	/**
	 * 保存生产
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(BaseInformation  baseInformation) {
		try {
			baseInformation.setProject(SpringUtils.getCurrentProject());
			baseInformation.setEnterprise(SpringUtils.getCurrentEnterprise());
			if(baseInformation.getId() !=null){
				baseInformationService.update(baseInformation);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return Message.error("保存信息失败", null);
		}
		
//		else{
//			baseInformationService.save(baseInformation);
//		}
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
	
	@RequestMapping(value="/upateTableStatus")
	public @ResponseBody Message upateTableStatus(String type){
		return Message.success();
	}
		
}
