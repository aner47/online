package com.online.controller.web.loadinginformation;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.Filter.Operator;
import com.online.Filter;
import com.online.Message;
import com.online.Order;
import com.online.Page;
import com.online.Pageable;
import com.online.controller.base.BaseController;
import com.online.entity.online.LoadingInformation;
import com.online.entity.online.OilTank;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.service.LoadingInformationService;
import com.online.util.Constants;
import com.online.util.SpringUtils;



@Controller("webLoadingInformationController")
@RequestMapping("/web/loadinginformation")
public class LoadingInformationController extends BaseController{
	
	@Autowired
	private LoadingInformationService  loadingInformationService ;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_loadinginformation);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/loadinginformation/list";
	}
	
	/**
	 * 增加装载页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_loadinginformation);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/loadinginformation/add";
	}

	/**
	 * 修改装载页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("loadingInformation", loadingInformationService.find(id));
		
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_loadinginformation);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		
		return "/admin/loadinginformation/update";
	}
	
	/**
	 * 查看装载页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("loadingInformation", loadingInformationService.find(id));
		return "/admin/loadinginformation/view";
	}
	
	/**
	 * 查询装载
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<LoadingInformation> query(Pageable pageable,LoadingInformation loadingInformation) {
	    pageable.addFilter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
	    pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		pageable.addEntity(loadingInformation);
		return loadingInformationService.findPage(pageable);
		
	}
	

	/**
	 * 保存装载
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(LoadingInformation  loadingInformation) {
		loadingInformation.setEnterprise(SpringUtils.getCurrentEnterprise());
		loadingInformation.setProject(SpringUtils.getCurrentProject());
		int no = 1;
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project.id", Operator.eq, SpringUtils.getProjectId()));
		filters.add(new Filter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId()));
		List<Order> listOrder = new ArrayList<>();
		listOrder.add(Order.desc("createDate"));
		List<LoadingInformation> lists = loadingInformationService.findList(null, filters, null);
		if(lists != null && lists.size()>0){
			if(lists.get(0).getNo() != null){
				no = Integer.parseInt(lists.get(0).getNo())+1;
			}
			
		}
		loadingInformation.setNo(no+"");
		 loadingInformationService.save(loadingInformation);
		return Message.success();
	}

	/**
	 * 更新装载
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(LoadingInformation loadingInformation) {
		loadingInformationService.update(loadingInformation,"enterprise","project");
		return Message.success();
	}

	/**
	 * 删除装载
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 loadingInformationService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
