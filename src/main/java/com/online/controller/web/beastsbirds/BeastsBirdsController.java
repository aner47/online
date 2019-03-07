package com.online.controller.web.beastsbirds;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.Filter.Operator;
import com.online.Message;
import com.online.Page;
import com.online.Pageable;
import com.online.controller.base.BaseController;
import com.online.entity.online.beastsbirds.BeastsBirds;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.BeastsBirdsService;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.util.Constants;
import com.online.util.SpringUtils;



@Controller("beastsBirdsController")
@RequestMapping("/web/beastsbirds")
public class BeastsBirdsController extends BaseController{
	
	@Autowired
	private BeastsBirdsService beastsBirdsService;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_beastsbirds);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		model.put("enterprise", SpringUtils.getCurrentEnterprise());
		return "/admin/beastsbirds/list";
	}
	
	/**
	 * 增加畜禽养殖场
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_beastsbirds);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/beastsbirds/add";
	}

	
	
	/**
	 * 修改畜禽养殖场
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("beastsBirds", beastsBirdsService.find(id));
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_beastsbirds);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/beastsbirds/update";
	}
	
	/**
	 * 查看畜禽养殖场
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("beastsBirds", beastsBirdsService.find(id));
		return "/admin/beastsbirds/view";
	}
	
	/**
	 * 查询畜禽养殖场
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<BeastsBirds> query(Pageable pageable,BeastsBirds beastsBirds) {
		pageable.addFilter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		return beastsBirdsService.findPage(pageable);
		
	}
	

	/**
	 * 保存畜禽养殖场
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(BeastsBirds beastsBirds) {
		beastsBirds.setProject(SpringUtils.getCurrentProject());
		beastsBirdsService.save(beastsBirds);
		return Message.success();
	}
	
	

	
	/**
	 * 更新畜禽养殖场
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(BeastsBirds beastsBirds) {
		beastsBirdsService.update(beastsBirds);
		return Message.success();
	}
	
	
	

	/**
	 * 删除畜禽养殖场
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			beastsBirdsService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
