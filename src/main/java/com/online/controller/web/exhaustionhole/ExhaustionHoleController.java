package com.online.controller.web.exhaustionhole;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.DataAccessException;
import com.online.Filter.Operator;
import com.online.Message;
import com.online.Page;
import com.online.Pageable;
import com.online.controller.base.BaseController;
import com.online.entity.online.ExhaustionHole;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.ExhaustionHoleService;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.util.Constants;
import com.online.util.SpringUtils;



@Controller("webExhaustionHoleController")
@RequestMapping("/web/exhaustionhole")
public class ExhaustionHoleController extends BaseController{
	
	@Autowired
	protected ExhaustionHoleService exhaustionHoleService;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_exhaustionhole);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/exhaustionhole/list";
	}
	
	/**
	 * 增加排放口页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_exhaustionhole);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/exhaustionhole/add";
	}

	/**
	 * 修改排放口页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("exhaustionHole", exhaustionHoleService.find(id));
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_exhaustionhole);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/exhaustionhole/update";
	}
	
	/**
	 * 查看排放口页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("exhaustionHole", exhaustionHoleService.find(id));
		return "/admin/exhaustionhole/view";
	}
	
	/**
	 * 查询排放口
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<ExhaustionHole> query(Pageable pageable,ExhaustionHole exhaustionHole) {
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		pageable.addFilter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		return exhaustionHoleService.findPage(pageable);
		
	}

	/**
	 * 保存排放口
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(ExhaustionHole exhaustionHole) {
		
		try {
			exhaustionHoleService.saveExhaustionHole(exhaustionHole);
		} catch (DataAccessException e) {
			return Message.error(e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error(e.getMessage(), null);
		}
		return Message.success();
	}
	
	/**
	 * 更新排放口
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(ExhaustionHole exhaustionHole) {
		try {
			exhaustionHoleService.updateExhaustionHole(exhaustionHole);
		} catch (DataAccessException e) {
			return Message.error(e.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error(e.getMessage(), null);
		}
		return Message.success();
	}

	/**
	 * 删除排放口
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 exhaustionHoleService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
