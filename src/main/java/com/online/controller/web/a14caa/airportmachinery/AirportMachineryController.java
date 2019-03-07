package com.online.controller.web.a14caa.airportmachinery;


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
import com.online.entity.online.a14caa.AirportMachinery;
import com.online.service.a14caa.AirportMachineryService;



@Controller("webAirportMachineryController")
@RequestMapping("/web/airportmachinery")
public class AirportMachineryController extends BaseController{
	
	@Autowired
	private AirportMachineryService  airportMachineryService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/a14caa/airportmachinery/list";
	}
	
	
	
	
	/**
	 * 增加机场车辆信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/a14caa/airportmachinery/add";
	}

	/**
	 * 修改机场车辆信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("airportMachinery", airportMachineryService.find(id));
		return "/admin/a14caa/airportmachinery/update";
	}
	
	/**
	 * 查看机场车辆信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("airportMachinery", airportMachineryService.find(id));
		return "/admin/a14caa/airportmachinery/view";
	}
	
	/**
	 * 查询机场车辆信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<AirportMachinery> query(Pageable pageable,AirportMachinery airportMachinery) {
		pageable.addEntity(airportMachinery);
		return airportMachineryService.findPage(pageable);
		
	}
	

	/**
	 * 保存机场车辆信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(AirportMachinery  airportMachinery) {
	 	try {
			airportMachineryService.saveAirportMachinery(airportMachinery);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return Message.success();
	}

	/**
	 * 更新机场车辆信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(AirportMachinery airportMachinery) {
		try {
			airportMachineryService.updateAirportMachinery(airportMachinery);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Message.success();
	}

	/**
	 * 删除机场车辆信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 airportMachineryService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
