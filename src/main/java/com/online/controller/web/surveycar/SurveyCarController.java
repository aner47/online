package com.online.controller.web.surveycar;


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
import com.online.entity.online.motorvehicles.SurveyCar;
import com.online.service.MotorVehiclesService;
import com.online.service.SurveyCarService;



@Controller("webSurveyCarController")
@RequestMapping("/web/surveycar")
public class SurveyCarController extends BaseController{
	
	@Autowired
	private SurveyCarService  surveyCarService ;
	
	@Autowired
	private MotorVehiclesService  motorVehiclesService ;
	
	
	@RequestMapping("/list")
	public String list(ModelMap model,String motor_vehicles){
		model.put("motor_vehicles", motor_vehicles);
		return "/admin/surveycar/list";
	}
	
	/**
	 * 增加调查车辆页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model,String motor_vehicles) {
		model.put("motor_vehicles", motor_vehicles);
		return "/admin/surveycar/add";
	}

	/**
	 * 修改调查车辆页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("surveyCar", surveyCarService.find(id));
		return "/admin/surveycar/update";
	}
	
	/**
	 * 查看调查车辆页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id,String equipment_leaked) {
		model.put("equipment_leaked", equipment_leaked);
		model.put("surveyCar", surveyCarService.find(id));
		return "/admin/surveycar/view";
	}
	
	/**
	 * 查询调查车辆
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<SurveyCar> query(Pageable pageable,SurveyCar surveyCar,String motor_vehicles) {
		pageable.addFilter(new Filter("motorVehicles.id", Operator.eq, Integer.parseInt(motor_vehicles)));
		pageable.addEntity(surveyCar);
		return surveyCarService.findPage(pageable);
		
	}
	

	/**
	 * 保存调查车辆
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(SurveyCar  surveyCar,String motor_vehicles) {
		surveyCar.setMotorVehicles(motorVehiclesService.find(Integer.parseInt(motor_vehicles)));
		surveyCarService.save(surveyCar);
		return Message.success();
	}

	/**
	 * 更新调查车辆
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(SurveyCar surveyCar) {
		surveyCarService.update(surveyCar);
		return Message.success();
	}

	/**
	 * 删除调查车辆
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			surveyCarService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
