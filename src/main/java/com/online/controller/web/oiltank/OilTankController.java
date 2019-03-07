package com.online.controller.web.oiltank;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.Filter;
import com.online.Message;
import com.online.Order;
import com.online.Page;
import com.online.Pageable;
import com.online.Filter.Operator;
import com.online.controller.base.BaseController;
import com.online.entity.online.Kiln;
import com.online.entity.online.OilTank;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.service.OilTankService;
import com.online.util.Constants;
import com.online.util.SpringUtils;



@Controller("webOilTankController")
@RequestMapping("/web/oiltank")
public class OilTankController extends BaseController{
	
	@Autowired
	private OilTankService oilTankService;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_oiltank);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/oiltank/list";
	}
	
	@RequestMapping("/simplelist")
	public String simplelist(ModelMap model){
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_simple_oiltank);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/oiltank/simple-list";
	}
	
	@RequestMapping("/generallist")
	public String generallist(ModelMap model){
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_general_oiltank);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/oiltank/general-list";
	}
	
	/**
	 * 增加石油产品储罐页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_oiltank);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/oiltank/add";
	}
	
	/**
	 * 增加石油产品储罐页面
	 */
	@RequestMapping(value = "addsimplePage")
	public String addsimplePage(ModelMap model) {
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_simple_oiltank);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/oiltank/simple-add";
	}
	/**
	 * 增加石油产品储罐页面
	 */
	@RequestMapping(value = "addgeneralPage")
	public String addgeneralPage(ModelMap model) {
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_general_oiltank);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/oiltank/general-add";
	}
	
	/**
	 * 修改石油产品储罐页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("oilTank", oilTankService.find(id));
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_oiltank);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/oiltank/update";
	}
	

	/**
	 * 修改石油产品储罐页面
	 */
	@RequestMapping(value = "updatesimplePage")
	public String updatesimplePage(ModelMap model,Integer id) {
		model.put("oilTank", oilTankService.find(id));
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_simple_oiltank);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/oiltank/simple-update";
	}
	/**
	 * 修改石油产品储罐页面
	 */
	@RequestMapping(value = "updategeneralPage")
	public String updategeneralPage(ModelMap model,Integer id) {
		model.put("oilTank", oilTankService.find(id));
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_general_oiltank);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/oiltank/general-update";
	}
	
	
	
	

	
	
	/**
	 * 查看石油产品储罐页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("oilTank", oilTankService.find(id));
		return "/admin/oiltank/view";
	}
	
	/**
	 * 查询石油产品储罐
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<OilTank> query(Pageable pageable,OilTank oilTank) {
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		pageable.addFilter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		return oilTankService.findPage(pageable);
		
	}
	

	/**
	 * 保存石油产品储罐
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(OilTank oilTank) {
		oilTank.setProject(SpringUtils.getCurrentProject());
		oilTank.setEnterprise(SpringUtils.getCurrentEnterprise());
		int no = 1;
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project.id", Operator.eq, SpringUtils.getProjectId()));
		filters.add(new Filter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId()));
		List<Order> listOrder = new ArrayList<>();
		listOrder.add(Order.desc("createDate"));
		List<OilTank> lists = oilTankService.findList(null, filters, null);
		if(lists != null && lists.size()>0){
			if(lists.get(0).getNo() != null){
				no = lists.get(0).getNo()+1;
			}
			
		}
		oilTank.setNo(no);
		oilTankService.save(oilTank);
		return Message.success();
	}

	/**
	 * 更新石油产品储罐
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(OilTank oilTank) {
		oilTankService.update(oilTank,"project","enterprise");
		return Message.success();
	}

	/**
	 * 删除石油产品储罐
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 oilTankService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
