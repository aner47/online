package com.online.controller.web.simplebaseinformation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.Message;
import com.online.controller.base.BaseController;
import com.online.entity.online.SimpleBaseInformation;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.BaseInformationService;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.service.SimpleBaseInformationService;
import com.online.util.Constants;
import com.online.util.SpringUtils;

@Controller("webSimpleBaseInformationController")
@RequestMapping("/web/simplebaseinformation")
public class SimpleBaseInformationController extends BaseController {

	@Autowired
	private SimpleBaseInformationService simpleBaseInformationService;

	@Autowired
	private FieldModuleService fieldModuleService;
	
	@Autowired
	private FieldShowConfigService fieldShowConfigService;

	@RequestMapping("/list")
	public String list(ModelMap model) {
		model.put("baseInformation", simpleBaseInformationService.findListByProjectAndEnterprise());
		return "/admin/simplebaseinformation/list";
	}

	@RequestMapping("/simple_list")
	public String simple_list(ModelMap model) {
		SimpleBaseInformation simpleBaseInformationFind = simpleBaseInformationService.findListByProjectAndEnterprise();
		if (simpleBaseInformationFind == null) {
			simpleBaseInformationService.deleteByProjectAndEnterprise();
			SimpleBaseInformation simpleBaseInformation = new SimpleBaseInformation();
			simpleBaseInformation.setProject(SpringUtils.getCurrentProject());
			simpleBaseInformation.setEnterprise(SpringUtils.getCurrentEnterprise());
			
			simpleBaseInformationService.save(simpleBaseInformation);
			model.put("baseInformation", simpleBaseInformation);
		} else {
			model.put("baseInformation", simpleBaseInformationFind);
		}
		
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_simplebaseinformation);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		
		model.put("enterprise", SpringUtils.getCurrentEnterprise());
		return "/admin/simplebaseinformation/simple_list";
	}

	/**
	 * 保存生产 保存功能添加至添加企业时创建，后续只有修改功能----郑有权
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(SimpleBaseInformation baseInformation) {
		baseInformation.setProject(SpringUtils.getCurrentProject());
		baseInformation.setEnterprise(SpringUtils.getCurrentEnterprise());
		if (baseInformation.getId() != null) {
			simpleBaseInformationService.update(baseInformation);
		}
		// else{
		// simpleBaseInformationService.save(baseInformation);
		// }
		return Message.success();
	}

	/**
	 * 更新生产
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(SimpleBaseInformation baseInformation) {
		simpleBaseInformationService.update(baseInformation);
		return Message.success();
	}

	/**
	 * 删除生产
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			simpleBaseInformationService.delete(ids[i]);
		}
		return Message.success();
	}

	@RequestMapping(value = "/upateTableStatus")
	public @ResponseBody Message upateTableStatus(String type) {
		return Message.success();
	}

}
