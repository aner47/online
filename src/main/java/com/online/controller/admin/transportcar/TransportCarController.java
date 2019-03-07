package com.online.controller.admin.transportcar;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.online.Filter.Operator;
import com.online.Message;
import com.online.Page;
import com.online.Pageable;
import com.online.controller.base.BaseController;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.entity.online.general.TransportCar;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.service.TransportCarService;
import com.online.util.Constants;
import com.online.util.Principal;
import com.online.util.SpringUtils;



@Controller("admintransportCarController")
@RequestMapping("/admin/transportcar")
public class TransportCarController extends BaseController{
	
	@Autowired
	private TransportCarService transportCarService;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_general_transportcar);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		
		return "/admin/transportcar/list";
	}
	
	@RequestMapping("/list_detail")
	public String listdetail(ModelMap model){
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_transportcar);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/transportcar/list_detail";
	}
	
	/**
	 * 增加普查表运输车页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_general_transportcar);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/transportcar/add";
	}
	/**
	 * 增加普查表运输车页面
	 */
	@RequestMapping(value = "addPagedetail")
	public String addPagedetail(ModelMap model) {
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_general_transportcar);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/transportcar/add_detail";
	}

	
	
	/**
	 * 修改普查表运输车页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("transportCar", transportCarService.find(id));
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_general_transportcar);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/transportcar/update";
	}
	/**
	 * 修改普查表运输车页面
	 */
	@RequestMapping(value = "updatePagedetail")
	public String updatePagedetail(ModelMap model,Integer id) {
		model.put("transportCar", transportCarService.find(id));
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_general_transportcar);
		if(fieldModule != null ){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		return "/admin/transportcar/update_detail";
	}
	
	/**
	 * 查看普查表运输车页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("transportCar", transportCarService.find(id));
		return "/admin/transportcar/view";
	}
	
	/**
	 * 查询普查表运输车信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<TransportCar> query(Pageable pageable,TransportCar transportCar) {
		pageable.addFilter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		return transportCarService.findPage(pageable);
		
	}
	

	/**
	 * 保存普查表运输车信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(TransportCar transportCar) {
		transportCar.setProject(SpringUtils.getCurrentProject());
		transportCarService.save(transportCar);
		return Message.success();
	}
	
	

	
	/**
	 * 更新普查表运输车信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(TransportCar transportCar) {
		transportCarService.update(transportCar);
		return Message.success();
	}
	
	
	

	/**
	 * 删除普查表运输车信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			transportCarService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
