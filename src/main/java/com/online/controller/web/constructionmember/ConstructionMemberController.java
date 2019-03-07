package com.online.controller.web.constructionmember;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.Message;
import com.online.Page;
import com.online.Pageable;
import com.online.controller.base.BaseController;
import com.online.entity.online.constructionsite.ConstructionMember;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.ConstructionMemberService;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.util.Constants;
import com.online.util.SpringUtils;



@Controller("constructionMemberController")
@RequestMapping("/web/constructionmember")
public class ConstructionMemberController extends BaseController{
	
	@Autowired
	private ConstructionMemberService constructionMemberService;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_constructionmember);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/constructionmember/list";
	}
	
	/**
	 * 增加机械数量页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_constructionmember);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/constructionmember/add";
	}

	
	
	/**
	 * 修改机械数量页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("constructionMember", constructionMemberService.find(id));
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_constructionmember);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/constructionmember/update";
	}
	
	/**
	 * 查看机械数量页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("constructionMember", constructionMemberService.find(id));
		return "/admin/constructionmember/view";
	}
	
	/**
	 * 查询机械数量信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<ConstructionMember> query(Pageable pageable,ConstructionMember constructionMember) {
		pageable.addFilter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		return constructionMemberService.findPage(pageable);
		
		
	}
	
	/**
	 * 查询机械数量---施工阶段
	 */
	@RequestMapping(value = "/queryStage")
	@ResponseBody
	public List<String> query() {
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId()));
		filters.add(new Filter("project.id", Operator.eq, SpringUtils.getProjectId()));
		
		List<ConstructionMember> constructionMembers = constructionMemberService.findList(null, filters, null);
		
		List<String> constructionPhases = new ArrayList<>();
		if(constructionMembers != null && constructionMembers.size()>0){
			for(int i=0;i<constructionMembers.size();i++ ){
				constructionPhases.add(constructionMembers.get(i).getConstructionPhases());
			}
		}
		
		return constructionPhases;
		
	}
	
	

	/**
	 * 保存机械数量信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(ConstructionMember constructionMember) {
		constructionMember.setProject(SpringUtils.getCurrentProject());
		constructionMemberService.save(constructionMember);
		return Message.success();
	}
	
	

	
	/**
	 * 更新机械数量信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(ConstructionMember constructionMember) {
		constructionMemberService.update(constructionMember);
		return Message.success();
	}
	
	
	

	/**
	 * 删除机械数量信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			constructionMemberService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
