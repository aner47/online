package com.online.controller.web.a2urbanmanagementbureau.cityroadcleanmachine;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.Message;
import com.online.Page;
import com.online.Pageable;
import com.online.controller.base.BaseController;
import com.online.entity.online.a2urbanmanagementbureau.CityRoadCleanMachine;
import com.online.service.a2urbanmanagementbureau.CityRoadCleanMachineService;



@Controller("webCityRoadCleanMachineController")
@RequestMapping("/web/cityroadcleanmachine")
public class CityRoadCleanMachineController extends BaseController{
	
	@Autowired
	private CityRoadCleanMachineService cityRoadCleanMachineService;
	
	
	
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		return "/admin/a2urbanmanagementbureau/cityroadcleanmachine/list";
	}
	
	
	/**
	 * 增加界面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		return "/admin/a2urbanmanagementbureau/cityroadcleanmachine/add";
	}

	
	
	/**
	 * 修改界面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("cityRoadCleanMachine", cityRoadCleanMachineService.find(id));
		return "/admin/a2urbanmanagementbureau/cityroadcleanmachine/update";
	}
	
	/**
	 * 查看界面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("cityRoadCleanMachine", cityRoadCleanMachineService.find(id));
		return "/admin/a2urbanmanagementbureau/cityroadcleanmachine/view";
	}
	
	/**
	 * 查询
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<CityRoadCleanMachine> query(Pageable pageable,CityRoadCleanMachine cityRoadCleanMachine) {
		return cityRoadCleanMachineService.findPage(pageable);
		
	}
	

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(CityRoadCleanMachine cityRoadCleanMachine) {
		try {
			cityRoadCleanMachineService.saveCityRoadCleanMachine(cityRoadCleanMachine);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Message.success();
	}
	
	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(CityRoadCleanMachine cityRoadCleanMachine) {
		try {
			cityRoadCleanMachineService.updateCityRoadCleanMachine(cityRoadCleanMachine);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Message.success();
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			cityRoadCleanMachineService.delete(ids[i]);
		}
		return Message.success();
	}
	
		
}
